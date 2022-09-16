package com.human.service;

import java.util.List;

import com.human.dto.CustomerDto;
import com.human.vo.CustomerInfoVo;
import com.human.vo.CustomerInfoVo2;

public interface ICustomInfoService {
	public void insertCustomInfo(CustomerDto dto,CustomerInfoVo vo) throws Exception;
	public List<CustomerInfoVo> selectAll() throws Exception;
	public List<CustomerInfoVo2> selectAllCustomerHobby() throws Exception;
//	public CustomerDto select(int id) throws Exception;
//	public List<CustomerDto> selectAll() throws Exception;
//	public void delete(int id) throws Exception;
//	public void update(CustomerDto dto) throws Exception;	
//	public Integer selectMaxId() throws Exception;
}
