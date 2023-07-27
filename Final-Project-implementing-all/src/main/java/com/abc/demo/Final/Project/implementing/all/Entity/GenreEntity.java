package com.abc.demo.Final.Project.implementing.all.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="genre")
public class GenreEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int gid;
	private String name;
	
	@Column(length=1000)
	private String description;
	
	
	
	
	public int getGid() {
		return gid;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	public GenreEntity(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}
	
	public GenreEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
