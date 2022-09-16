package com.human.dto;

public class HobbyDto {
	private int id;
	private String hobby;
	
	private String modifyHobby;
	
	public String getModifyHobby() {
		return modifyHobby;
	}
	public void setModifyHobby(String modifyHobby) {
		this.modifyHobby = modifyHobby;
	}
	@Override
	public String toString() {
		return "HobbyDto [id=" + id + ", hobby=" + hobby + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	

}
