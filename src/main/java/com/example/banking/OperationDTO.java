package com.example.banking;

import com.mongodb.lang.Nullable;

import lombok.Data;

@Data
public class OperationDTO {

    @Nullable private Action action;
    @Nullable private String cardNumber;
    @Nullable private String activationCode;

}
