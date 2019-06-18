package com.example.dplm.spring.controller.models;

import com.example.dplm.domain.model.Token;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserVerifyResult {

    private Token token;
    private String error;
}
