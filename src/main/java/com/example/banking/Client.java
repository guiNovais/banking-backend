package com.example.banking;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Client
 */
@Data
@Setter
@Getter
public class Client {

    private static final String HYPHEN = "-";
    @Id
    private String id;
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    private LocalDate birthdate;
    private String activationCode = randomAlphanumeric(4);
    private String cardNumber = randomNumeric(4)
            .concat(HYPHEN.concat(randomNumeric(4)))
            .concat(HYPHEN.concat(randomNumeric(4)))
            .concat(HYPHEN.concat(randomNumeric(4)));
    private Boolean activated = false;

}