package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

public interface UserRepository extends JpaRepository<User, Long>
{
    User findByUsername(String username);

    //Modifying
    //@Query(nativeQuery = true, value = "UPDATE users u SET u.newUsername = :username, description = :description, birthdate = :birthdate WHERE u.username = :oldUsername")
    //void editProfile(String newUsername, String oldUsername, String description, Date birthdate);
}
