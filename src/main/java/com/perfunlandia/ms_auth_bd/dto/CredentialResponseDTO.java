package com.perfunlandia.ms_auth_bd.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CredentialResponseDTO {
    private String userId;
    private String email;
    private String role;
    private String password;
}
