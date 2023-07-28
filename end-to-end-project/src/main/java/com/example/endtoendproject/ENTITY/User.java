package com.example.endtoendproject.ENTITY;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

//User Table:
//- Columns: `id`, `username`, `password`, `email`, `created_at`, `updated_at`
@Entity
public class User {
	@Id
	private int userId;
	private String username;
	private String password;
	private String email;
	private String created_at;
	private String updated_at;
}
