package com.example.banking;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.data.annotation.Id;

import lombok.Data;

/**
 * Client
 */
@Data
public class Client {

    @Id
    private String id;
    @NotNull @NotBlank private String name;
    @NotNull private LocalDate birthdate;
    private String cardCode = RandomStringUtils.randomAlphanumeric(4);

}