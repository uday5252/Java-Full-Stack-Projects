package com.example.demo.End.project.ENTITY;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class LikeEntity {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	int likeid;
    
    @ManyToOne
    @JoinColumn(referencedColumnName = "videoid")
    private VideoEntity ve;
    
    @ManyToOne
    @JoinColumn(referencedColumnName = "userid")
    private UserEntity ue;

	public int getLikeid() {
		return likeid;
	}

	public void setLikeid(int likeid) {
		this.likeid = likeid;
	}

	public VideoEntity getVe() {
		return ve;
	}

	public void setVe(VideoEntity ve) {
		this.ve = ve;
	}

	public UserEntity getUe() {
		return ue;
	}

	public void setUe(UserEntity ue) {
		this.ue = ue;
	}

	public LikeEntity(VideoEntity ve, UserEntity ue) {
		super();
		this.ve = ve;
		this.ue = ue;
	}

	public LikeEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
    
     
}
