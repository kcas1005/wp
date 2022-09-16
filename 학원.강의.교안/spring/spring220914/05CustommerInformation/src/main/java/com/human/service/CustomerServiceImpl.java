package com.human.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.human.dao.ICustomerDao;
import com.human.dto.CustomerDto;
import com.human.vo.CustomerInfoVo;
import com.human.vo.CustomerInfoVo2;
@Service
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public void insert(CustomerDto dto) throws Exception {
		ICustomerDao dao=sqlSession.getMapper(ICustomerDao.class);
		dao.insert(dto);		
	}
	@Override
	public CustomerDto select(int id) throws Exception {
		ICustomerDao dao=sqlSession.getMapper(ICustomerDao.class);
		return dao.select(id);
	}

	@Override
	public List<CustomerDto> selectAll() throws Exception {
		ICustomerDao dao=sqlSession.getMapper(ICustomerDao.class);
		return dao.selectAll();
	}

	@Override
	public void delete(int id) throws Exception {
		ICustomerDao dao=sqlSession.getMapper(ICustomerDao.class);
		dao.delete(id);
		
	}

	@Override
	public void update(CustomerDto dto) throws Exception {
		ICustomerDao dao=sqlSession.getMapper(ICustomerDao.class);
		dao.update(dto);
		
	}
	@Override
	public Integer selectMaxId() throws Exception {
		ICustomerDao dao=sqlSession.getMapper(ICustomerDao.class);
		return dao.selectMaxId();
	}
	@Override
	public List<CustomerInfoVo2> selectAllCustomerHobby() throws Exception {
		// TODO Auto-generated method stub
		ICustomerDao dao=sqlSession.getMapper(ICustomerDao.class);
		return dao.selectAllCustomerHobby();
	}
	

}
