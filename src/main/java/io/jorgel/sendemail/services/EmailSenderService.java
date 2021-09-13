package io.jorgel.sendemail.services;

import io.jorgel.sendemail.models.Email;

import javax.mail.MessagingException;
import java.io.IOException;

public interface EmailSenderService {
    void sendEmail(Email email);
    void sendConfirmationEmail(Email email) throws MessagingException, IOException;
}
