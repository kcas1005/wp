package com.human.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.human.dao.IHobbyDao;
import com.human.dto.HobbyDto;
@Service
public class HobbyServiceImpl implements IHobbyService {
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void insert(HobbyDto dto) throws Exception {
		IHobbyDao dao=sqlSession.getMapper(IHobbyDao.class);
		dao.insert(dto);		
	}

	@Override
	public HobbyDto select(HobbyDto dto) throws Exception {
		IHobbyDao dao=sqlSession.getMapper(IHobbyDao.class);
		
		return dao.select(dto);
	}

	@Override
	public List<HobbyDto> selectAll() throws Exception {
		IHobbyDao dao=sqlSession.getMapper(IHobbyDao.class);
		return dao.selectAll();
	}

	@Override
	public void delete(HobbyDto dto) throws Exception {
		IHobbyDao dao=sqlSession.getMapper(IHobbyDao.class);
		dao.delete(dto);
	}

	@Override
	public void update(HobbyDto dto) throws Exception {
		IHobbyDao dao=sqlSession.getMapper(IHobbyDao.class);
		dao.update(dto);
		
	}

	@Override
	public List<Integer> insertItem() throws Exception {
		IHobbyDao dao=sqlSession.getMapper(IHobbyDao.class);
		
		return dao.insertItem();
	}

	@Override
	public List<String> selectId(Integer id) throws Exception {
	IHobbyDao dao=sqlSession.getMapper(IHobbyDao.class);
		
		return dao.selectId(id);
	}

	@Override
	public void deleteId(Integer id) throws Exception {
		// TODO Auto-generated method stub
		IHobbyDao dao=sqlSession.getMapper(IHobbyDao.class);
		dao.deleteId(id);
	}

}
