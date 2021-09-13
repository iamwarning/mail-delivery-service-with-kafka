package io.jorgel.sendemail.services;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.jorgel.sendemail.models.Email;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class ConsumerService {
    private static final Logger LOGGER = LogManager.getLogger(ConsumerService.class);
    private final EmailSenderService emailSenderService;

    @Autowired
    public ConsumerService(EmailSenderService emailSenderService) {
        this.emailSenderService = emailSenderService;
    }

    @KafkaListener(topics = "${kafka.topics.data}")
    public void sendConfirmationEmails(ConsumerRecord<?, ?> commandsRecord) throws MessagingException, IOException {
        LOGGER.log(Level.INFO, () -> String.format("sendConfirmationEmails() » Topic: %s", commandsRecord.topic()));
        JsonElement object = new Gson().fromJson(commandsRecord.value().toString(), JsonObject.class);
        var subject = object.getAsJsonObject().get("subject").getAsString();
        var to = object.getAsJsonObject().get("email").getAsString();
        var context = object.getAsJsonObject().get("context").getAsJsonObject();
        Map<String, Object> props = new HashMap<>();
        props.put("name", context.get("name").getAsString());
        props.put("token", context.get("token").getAsString());
        props.put("subscriptionDate", new Date());
        Email email = Email.builder()
                .withTo(to)
                .withFrom("From Spring <support@jorgel.io>")
                .withContent("---")
                .withSubject(subject)
                .withProps(props)
                .build();
        emailSenderService.sendConfirmationEmail(email);
        LOGGER.log(Level.INFO, () -> " »» Mail sent successfully");
    }
}
