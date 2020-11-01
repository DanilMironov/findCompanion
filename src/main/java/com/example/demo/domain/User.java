package com.example.demo.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    public User(UUID id, String userName, String password, String birthdate, String description, ArrayList<String> eventsUuids) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.birthdate = birthdate;
        this.description = description;
        this.eventsUuids = eventsUuids;
    }

    public User() {
    }

    @Id
    @Column(name = "id", columnDefinition = "uuid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(name = "username", columnDefinition = "character varying")
    private String userName;

    @Column(name = "password", columnDefinition = "character varying")
    private String  password;

    @Column(name = "birthdate", columnDefinition = "date")
    private  String birthdate;

    @Column(name = "description", columnDefinition =  "text")
    private String description;

    @Column(name = "events_ids", columnDefinition = "uuid[]")
    private ArrayList<String> eventsUuids;

    @Transient //не имеет отображения в БД
    private String passwordConfirm;


    //методы интерфейса UserDetails

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    //геттеры-сеттеры

    public UUID getId() {
        return id;
    }

    public String setUsername() {
        return userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<String> getEventsUuids() {
        return eventsUuids;
    }

    public void setEventsUuids(ArrayList<String> eventsUuids) {
        this.eventsUuids = eventsUuids;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

}
