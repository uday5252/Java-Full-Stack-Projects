package com.abc.demo.Zee5.Apache.Kafka.Producer.ENTITY;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;


@Entity(name = "user_table")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;
    private String user_name;
    private String user_password;
    private String user_email;
    private LocalDateTime user_createdAt;
    private LocalDateTime user_updatedAt;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public LocalDateTime getUser_createdAt() {
        return user_createdAt;
    }

    public void setUser_createdAt(LocalDateTime user_createdAt) {
        this.user_createdAt = user_createdAt;
    }

    public LocalDateTime getUser_updatedAt() {
        return user_updatedAt;
    }

    public void setUser_updatedAt(LocalDateTime user_updatedAt) {
        this.user_updatedAt = user_updatedAt;
    }

    public UserEntity(String user_name, String user_password, String user_email) {
        this.user_name = user_name;
        this.user_password = user_password;
        this.user_email = user_email;
    }

    public UserEntity(){

    }

}
