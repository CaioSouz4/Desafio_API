package com.desafio.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.desafio.desafio.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
