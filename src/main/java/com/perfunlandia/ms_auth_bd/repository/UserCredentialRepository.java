package com.perfunlandia.ms_auth_bd.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.perfunlandia.ms_auth_bd.entity.UserCredentialEntity;

import java.util.Optional;



public interface UserCredentialRepository extends JpaRepository<UserCredentialEntity, Long> {
    Optional<UserCredentialEntity> findByEmail(String email);

    Optional<UserCredentialEntity>findByUserId(String userId);

}
