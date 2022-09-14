package com.human.service;

import java.util.List;

import com.human.dto.CustomerDto;
import com.human.dto.HobbyDto;

public interface IHobbyService {
	public void insert(HobbyDto dto) throws Exception;
	public HobbyDto select(HobbyDto dto) throws Exception;
	public List<HobbyDto> selectAll() throws Exception;
	public void delete(HobbyDto dto) throws Exception;
	public void update(HobbyDto dto) throws Exception;	
	public List<Integer> insertItem() throws Exception;
	public List<String> selectId(Integer id) throws Exception;
	public void deleteId(Integer id) throws Exception;
}
