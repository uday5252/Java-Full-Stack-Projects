package com.project.demo.end_to_end.entities;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(hidden = true)
    private int id;
    @Column(unique = true)
    private String username;
    @Parameter(schema = @Schema(type = "string", format = "password"))
    @JsonProperty(access = Access.WRITE_ONLY)
    private String password;
    @Column(unique = true)
    private String email;
    @CreationTimestamp
    @Schema(hidden = true)
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Schema(hidden = true)
    private LocalDateTime updatedAt;
}
