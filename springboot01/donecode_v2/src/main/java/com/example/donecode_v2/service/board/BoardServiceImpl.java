package com.example.donecode_v2.service.board;

import com.example.donecode_v2.entity.account.Member;
import com.example.donecode_v2.entity.board.Board;
import com.example.donecode_v2.entity.board.Comments;
import com.example.donecode_v2.entity.customDto.CustomDtoSortPages;
import com.example.donecode_v2.repository.board.BoardRepository;
import com.example.donecode_v2.repository.board.CommentsRepository;
import com.example.donecode_v2.repository.customRepository.CustomDtoExampleRepositoryPred;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService{


    private final BoardRepository boardRepo;
    private final CommentsRepository commentsRepository;
    private final CustomDtoExampleRepositoryPred customDtoExampleRepositoryPred;

    //순환참조 중단
    @Autowired
    protected BoardServiceImpl(BoardRepository boardRepo,
                               CommentsRepository commentsRepository,
                               CustomDtoExampleRepositoryPred customDtoExampleRepositoryPred
    ) {
        this.customDtoExampleRepositoryPred = customDtoExampleRepositoryPred;
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
        System.out.println("------service logic---------");
        System.out.println(comments.getBoard_title());
        System.out.println(comments.getComments_content());
        System.out.println(comments.getSeq());
//        System.out.println(comments.getBoard().getTitle());
        commentsRepository.save(comments);
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
    public List<Board> getBoardListAllBoardListByMemberId(Member member) {
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
    public List<Board> getBoardListSortColumnByBoardList(List<Board> boardList) {
        return null;
    }

    @Override
    public List<List<Object>> getBoardAndMemberUsersBoard() {
        return boardRepo.findAllByBoardAndMember();
    }

    @Override
    public List<Comments> getAllComments(Comments comments) {
//        List<Comments> checktest = commentsRepository.findCommentsByBoard_seq(comments.getBoard_title());
        List<Comments> checktest = commentsRepository.findAll();
        System.out.println(checktest.size());
        for(int i =0; i<checktest.size(); i++) {
            System.out.println("-----init for-------");
            checktest.get(i).getComments_content();
        }
        return checktest;
    }

    @Override
    public CustomDtoSortPages getPagesSortIndex(Board board) {
        CustomDtoSortPages customDtoSortPages = customDtoExampleRepositoryPred.findByPages(board.getSeq());
        System.out.println(customDtoSortPages.getPREVID());
        return customDtoSortPages;
    }
}