package io.jorgel.sendemail.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(
        ignoreUnknown = true
)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder(setterPrefix = "with", toBuilder = true)
public class Email implements Serializable {
    private static final long serialVersionUID = -8303082751887676310L;
    private String to;
    private String from;
    private String subject;
    private String content;
    private Map< String, Object > props;
}
