package com.perfunlandia.ms_auth_bd.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCredentialRequestDTO {
    private String email;
    private String password;
}
