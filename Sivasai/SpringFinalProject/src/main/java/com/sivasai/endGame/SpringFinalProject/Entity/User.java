package com.sivasai.endGame.SpringFinalProject.Entity;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private int id;

    @Column(unique = true)
    private String userName;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[#$@!%&*?])[A-Za-z\\d#$@!%&*?]{8,}$", message = "Password should be minimum 8 characters length with at least one uppercase, one lowercase, one number & one special character from(#?!@$%&*-)")
    private String password;

    @Column(unique = true)
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", message = "please check email address", flags = Pattern.Flag.CASE_INSENSITIVE)
    private String email;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Timestamp created_at;

    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Timestamp updated_at;

    @Column(columnDefinition = "VARCHAR(255) default 'null'")
    @Hidden
    private String authToken;

    @Enumerated(EnumType.STRING)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Roles role;

    public User(String error) {
        this.userName = error;
    }

    public String getUserName() {
        return userName;
    }

    public int getId() {
        return id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void tokenSet(String authToken){
        this.authToken = authToken;
    }

    public Roles getRole() {
        return role;
    }

    public void roleSet(Roles role) {
        this.role = role;
    }


    public String toStringForVideo() {
        return "User{" +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", updated_at=" + updated_at +
                '}';
    }
}
