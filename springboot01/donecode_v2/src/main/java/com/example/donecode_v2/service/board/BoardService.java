package com.example.donecode_v2.service.board;

import com.example.donecode_v2.entity.account.Member;
import com.example.donecode_v2.entity.board.Board;
import com.example.donecode_v2.entity.board.Comments;
import com.example.donecode_v2.entity.customDto.CustomDtoSortPages;

import java.util.List;

public interface BoardService {
    List<Board> getBoardList(Board board);

    void insertBoard(Board board);

    Board getBoard(Board board);

    void updateBoard(Board board);

    void deleteBoard(Board board);

    void insertComment(Comments comments);

    //board의 작성자와 회원이 같은지 확인 [2,5조...]
    boolean booleanMemberIdEqualsBoardWriterByMember(Member member);

    //작성자의 모든 게시글 출력
    List<Board> getBoardListByMemberId(Member member);

    //키워드분석 [4조]
    List<String> doNounsAnalysis(List<Board> boardList);

    //관련된 키워드 게시글 출력
    List<Board> getAutoKeywordBoardList(List<String> keyword);

    //오름차순으로 변경 (arrayList)
    List<Board> getBoardListSortColumnByBoardList(List<Board> boardlist);

    //작성자의 모든 게시글 출력
    List<Board> getBoardListAllBoardListByMemberId(Member member);

    List<List<Object>> getBoardAndMemberUsersBoard();

    List<Comments> getAllComments(Comments comments);

    CustomDtoSortPages getPagesSortIndex(Board board);
}
