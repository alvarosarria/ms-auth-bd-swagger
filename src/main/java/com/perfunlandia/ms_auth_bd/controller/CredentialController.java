package com.perfunlandia.ms_auth_bd.controller;

import com.perfunlandia.ms_auth_bd.dto.CreateCredentialRequestDTO;
import com.perfunlandia.ms_auth_bd.dto.CredentialDTO;
import com.perfunlandia.ms_auth_bd.dto.CredentialResponseDTO;
import com.perfunlandia.ms_auth_bd.dto.UpdateRoleRequestDTO;
import com.perfunlandia.ms_auth_bd.entity.Role;
import com.perfunlandia.ms_auth_bd.entity.UserCredentialEntity;
import com.perfunlandia.ms_auth_bd.service.CredentialService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/internal/db/auth/credentials")
public class CredentialController {

    @Autowired
    private CredentialService credentialService;

    @PostMapping()
    public ResponseEntity<CredentialResponseDTO> create(@RequestBody CreateCredentialRequestDTO request){
        UserCredentialEntity newEntity = credentialService.createCredential(request);

        CredentialResponseDTO responseDTO = new CredentialResponseDTO(
            newEntity.getUserId(),
            newEntity.getEmail(),
            newEntity.getRole().name(),
            null
        );
        
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{email}")
    public ResponseEntity<CredentialDTO> getByEmail(@PathVariable("email") String email) {
        return credentialService.getCredentialByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{userId}/role")
    public ResponseEntity<CredentialDTO> updateUserRole(
        @PathVariable String userId,
        @RequestBody UpdateRoleRequestDTO request){

        // Validación del request body
        if (request == null || request.getNewRole() == null || request.getNewRole().trim().isEmpty()) {
            throw new RuntimeException("El campo 'newRole' es requerido y no puede ser null o vacío");
        }

        // Convertir y validar el rol
        Role newRole;
        try {
            newRole = Role.valueOf(request.getNewRole().toUpperCase().trim());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Rol inválido: " + request.getNewRole() + 
                ". Roles válidos: " + java.util.Arrays.toString(Role.values()));
        }

        // Actualizar el usuario
        UserCredentialEntity updatedEntity = credentialService.updateUserRole(userId, newRole);

        // Crear y devolver la respuesta
        CredentialDTO response = new CredentialDTO(
            updatedEntity.getUserId(),
            updatedEntity.getEmail(),
            updatedEntity.getPasswordHash(),
            updatedEntity.getRole().name()
        );

        return ResponseEntity.ok(response);
    }
}
