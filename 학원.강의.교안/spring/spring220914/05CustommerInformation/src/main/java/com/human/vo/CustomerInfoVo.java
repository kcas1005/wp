package com.human.vo;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.human.dto.CustomerDto;
import com.human.dto.HobbyDto;

public class CustomerInfoVo {
	private int id;
	private String name;
	private double height;
	@DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm")
	private Date birthday;
	
	private List<String> hobbys;

	public CustomerInfoVo() {
		
	}

	public CustomerInfoVo(CustomerDto dto,List<String> hobbys) {
		this.id=dto.getId();
		this.name=dto.getName();
		this.height=dto.getHeight();
		this.birthday=dto.getBirthday();
		this.hobbys=hobbys;
		
	}
	
	@Override
	public String toString() {
		return "CustomerVo [id=" + id + ", name=" + name + ", height=" + height + ", birthday=" + birthday + ", hobbys="
				+ hobbys + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public List<String> getHobbys() {
		return hobbys;
	}

	public void setHobbys(List<String> hobbys) {
		this.hobbys = hobbys;
	}
	
	
}
