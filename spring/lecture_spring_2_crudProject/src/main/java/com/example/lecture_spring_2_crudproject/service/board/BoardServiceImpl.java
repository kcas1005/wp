package com.example.lecture_spring_2_crudproject.service.board;

import com.example.lecture_spring_2_crudproject.entity.board.Board;
import com.example.lecture_spring_2_crudproject.entity.board.Comments;
import com.example.lecture_spring_2_crudproject.repository.account.MemberRepository;
import com.example.lecture_spring_2_crudproject.repository.board.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService{


    private final BoardRepository boardRepo;

    //순환참조 중단
    @Autowired
    protected BoardServiceImpl(BoardRepository boardRepo) {
        this.boardRepo = boardRepo;
    }


    @Override
    public List<Board> getBoardList(Board board) {
        return (List<Board>) boardRepo.findAll();
    }

    @Override
    public void insertBoard(Board board) {
        boardRepo.save(board);
    }

    @Override
    public Board getBoard(Board board) {
        return boardRepo.findById(board.getSeq()).get();
    }

    @Override
    public void updateBoard(Board board) {
        Board findBoard = boardRepo.findById(board.getSeq()).get();

        findBoard.setTitle(board.getTitle());
        findBoard.setContent(board.getContent());
        boardRepo.save(findBoard);
    }

    @Override
    public void deleteBoard(Board board) {
        boardRepo.deleteById(board.getSeq());
    }

    @Override
    public void insertComment(Comments comments) {
        //boolean title 체크
        //insert comment 실행
        //트랜젝션 처리
    }
}
