package com.project.ZeeWebApplication.RapperClass;

public class RapperClass {
	private int userid;
	private String title;
	private String description;
	private String url;
    private String genrename;
    
    
	public RapperClass() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public RapperClass(int userid, String title, String description, String url, String genrename) {
		super();
		this.userid = userid;
		this.title = title;
		this.description = description;
		this.url = url;
		this.genrename = genrename;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getGenrename() {
		return genrename;
	}

	public void setGenrename(String genrename) {
		this.genrename = genrename;
	}

	
    
    
}
