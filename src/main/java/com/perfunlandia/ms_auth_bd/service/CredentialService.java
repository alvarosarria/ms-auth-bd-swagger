package com.perfunlandia.ms_auth_bd.service;

import com.perfunlandia.ms_auth_bd.dto.CreateCredentialRequestDTO;
import com.perfunlandia.ms_auth_bd.dto.CredentialDTO;
import com.perfunlandia.ms_auth_bd.entity.Role;
import com.perfunlandia.ms_auth_bd.entity.UserCredentialEntity;
import com.perfunlandia.ms_auth_bd.repository.UserCredentialRepository;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Optional;



@Service
public class CredentialService {

    @Autowired
    private UserCredentialRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserCredentialEntity createCredential(CreateCredentialRequestDTO request){
        if(repository.findByEmail(request.getEmail()).isPresent()){
            throw new IllegalStateException("El email ya se encuentra en uso");
        }
        UserCredentialEntity entity = new UserCredentialEntity();
        entity.setEmail(request.getEmail());
        entity.setPasswordHash(passwordEncoder.encode(request.getPassword()));

        return repository.save(entity);
    }

    public Optional<CredentialDTO> getCredentialByEmail(String email){

        return repository.findByEmail(email)
                .map(entity -> new CredentialDTO(entity.getUserId(), entity.getEmail(),entity.getPasswordHash(),entity.getRole().name()));

    }

    public UserCredentialEntity updateUserRole(String userId, Role newRole){
        UserCredentialEntity user = repository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con el ID: " + userId));

        user.setRole(newRole);
        return repository.save(user);
    }


}
