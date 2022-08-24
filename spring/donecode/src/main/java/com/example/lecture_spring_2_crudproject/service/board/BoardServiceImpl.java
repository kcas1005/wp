package com.example.lecture_spring_2_crudproject.service.board;

import com.example.lecture_spring_2_crudproject.entity.account.Member;
import com.example.lecture_spring_2_crudproject.entity.board.Board;
import com.example.lecture_spring_2_crudproject.entity.board.Comments;
import com.example.lecture_spring_2_crudproject.entity.customDto.CustomDtoSortPages;
import com.example.lecture_spring_2_crudproject.repository.account.MemberRepository;
import com.example.lecture_spring_2_crudproject.repository.board.BoardRepository;
import com.example.lecture_spring_2_crudproject.repository.board.CommentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService{


    private final BoardRepository boardRepo;
    private final CommentsRepository commentsRepository;

    //순환참조 중단
    @Autowired
    protected BoardServiceImpl(BoardRepository boardRepo, CommentsRepository commentsRepository) {
        this.commentsRepository = commentsRepository;
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

    @Override
    public boolean booleanMemberIdEqualsBoardWriterByMember(Member member) {
        return false;
    }

    @Override
    public List<Board> getBoardListByMemberId(Member member) {
        //Repository
        System.out.println("------getBoardListByMemberId-----");
        System.out.println(member.getId());
        return boardRepo.findAllByMemberIdEqualsBoardWriter(member.getId());
    }

    @Override
    public List<String> doNounsAnalysis(List<Board> boardlist) {
        return null;
    }

    @Override
    public List<Board> getAutoKeywordBoardList(List<String> keyword) {
        return null;
    }

    @Override
    public List<Board> getBoardListSortColumnByBoardList(List<Board> boardlist) {
        return null;
    }

    @Override
    public List<Comments> getAllComments(Comments comments){
        return commentsRepository.findCommentsByBoard_seq(comments.getBoard_seq());
    }

    public CustomDtoSortPages getPagesSortIndex(Board board){
        return customDtoExcampleRepositoryPred.findBypages(board.getSeq());
    }

}
