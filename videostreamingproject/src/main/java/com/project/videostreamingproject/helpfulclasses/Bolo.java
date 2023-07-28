package com.project.videostreamingproject.helpfulclasses;

import java.util.List;

import com.project.videostreamingproject.entity.Genre;
import com.project.videostreamingproject.entity.User;

public class Bolo {
   
	private String u;
	private List<Genre> g;
	public Bolo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getU() {
		return u;
	}
	public void setU(String u) {
		this.u = u;
	}
	public List<Genre> getG() {
		return g;
	}
	public void setG(List<Genre> g) {
		this.g = g;
	}
}
