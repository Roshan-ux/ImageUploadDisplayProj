package com.pojo;

public class Image {
	
	private int img_id;
	private String imgsrc;
	private String name;
	
	public Image() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Image(int img_id, String imgsrc, String name) {
		super();
		this.img_id = img_id;
		this.imgsrc = imgsrc;
		this.name = name;
	}

	public int getImg_id() {
		return img_id;
	}
	
	public void setImg_id(int img_id) {
		this.img_id = img_id;
	}
	public String getImgsrc() {
		return imgsrc;
	}
	public void setImgsrc(String imgsrc) {
		this.imgsrc = imgsrc;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	

}
