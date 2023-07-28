package com.project.zee.Video.Streaming.Platform.WrapperClass;

public class WrapperClass {
	
	private int genreid;
	private String title ;
	private String description;
	private String url;
	private int userid;
	
	public String toString() {
		return "genreId:"+this.genreid+"\tuserId:"+this.userid;
	}
	
	
	public int getGenreid() {
		return genreid;
	}
	public void setGenreid(int genreid) {
		this.genreid = genreid;
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
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public WrapperClass(int genreid, String title, String description, String url, int userid) {
		super();
		this.genreid = genreid;
		this.title = title;
		this.description = description;
		this.url = url;
		this.userid = userid;
	}
	public WrapperClass() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
