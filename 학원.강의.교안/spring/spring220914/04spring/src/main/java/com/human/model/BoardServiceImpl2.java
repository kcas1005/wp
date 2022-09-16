package com.human.model;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.human.dto.BoardVO;
//import org.zerock.domain.Criteria;
//import org.zerock.domain.SearchCriteria;
import com.human.dao.BoardDAO;

@Service
public class BoardServiceImpl2 implements BoardService {
	@Autowired
	private SqlSession sqlSession;
		

  @Override
  public void regist(BoardVO board) throws Exception {
	  BoardDAO dao=sqlSession.getMapper(BoardDAO.class);
    dao.create(board);
  }

  @Override
  public BoardVO read(Integer bno) throws Exception {
	  BoardDAO dao=sqlSession.getMapper(BoardDAO.class);
    return dao.read(bno);
  }

  @Override
  public void modify(BoardVO board) throws Exception {
	  BoardDAO dao=sqlSession.getMapper(BoardDAO.class);
    dao.update(board);
  }

  @Override
  public void remove(Integer bno) throws Exception {
	  BoardDAO dao=sqlSession.getMapper(BoardDAO.class);
    dao.delete(bno);
  }

  @Override
  public List<BoardVO> listAll() throws Exception {
	  BoardDAO dao=sqlSession.getMapper(BoardDAO.class);
	  List<BoardVO> dtos=dao.listAll();
	  System.out.println(dtos);
    return dtos;
  }

//  @Override
//  public List<BoardVO> listCriteria(Criteria cri) throws Exception {
//
//    return dao.listCriteria(cri);
//  }
//
//  @Override
//  public int listCountCriteria(Criteria cri) throws Exception {
//
//    return dao.countPaging(cri);
//  }
//
//  @Override
//  public List<BoardVO> listSearchCriteria(SearchCriteria cri) throws Exception {
//
//    return dao.listSearch(cri);
//  }
//
//  @Override
//  public int listSearchCount(SearchCriteria cri) throws Exception {
//
//    return dao.listSearchCount(cri);
//  }

}
