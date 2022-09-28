package com.example.lecture_spring_2_crudproject.controller.board;

import com.example.lecture_spring_2_crudproject.entity.account.Member;
import com.example.lecture_spring_2_crudproject.entity.board.Board;
import com.example.lecture_spring_2_crudproject.entity.board.Comments;
import com.example.lecture_spring_2_crudproject.service.board.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

/**
 * @description : 게시판 컨트롤러
 **/

@Controller
@RequestMapping(path = "/board")
public class BoardController {

    private final BoardService boardService;

    @Autowired
    protected BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/insertComments")
    public String insertCommentsView(Board board, Model model) {
        model.addAttribute("TITLE", board.getTitle());
        return "/board/insertComments";
    }
    @PostMapping("/insertComments")
    public String insertComments(Comments comments, Model model) {
        boardService.insertComment(comments);
        return "redirect:/board/getCommentsList";
    }

    //board Seq전달하면 전체 comments를 불러오는 controller method
    @GetMapping("/getCommentsList")
    public String getCommentsList(Comments comments, Model model){

        model.addAttribute("commentsList", boardService.getAllComments(comments));

        return "/board/getCommentsList";
    }

    @GetMapping("/getBoardList")
    public String getBoardList(Model model, Board board) {
        System.out.println("-------------------");
        List<Board> boardList = boardService.getBoardList(board);
        model.addAttribute("boardList", boardList);
        return "/board/getBoardList";
    }

    @GetMapping("/insertBoard")
    public String insertBoard() {
        System.out.println("------insertBoard_get-------------");
        return "/board/insertBoard";
    }

    @PostMapping("/insertBoard")
    public String insertBoard(Board board) {
        System.out.println("--------insertBoard_post-----------");
        System.out.println(board.getCreateDate());
        System.out.println(board.getUpdateDate());
        board.setCreateDate(new Date());
        board.setUpdateDate(new Date());
        System.out.println(board.getCreateDate());
        System.out.println(board.getUpdateDate());
        boardService.insertBoard(board);
        return "redirect:/board/getBoardList";
    }

    @GetMapping("/getBoard")
    public String getBoard(Board board, Model model) {
        System.out.println("-------------------");
        System.out.println(board.getSeq());
        model.addAttribute("board", boardService.getBoard(board));
        return "/board/getBoard";
    }

    @PostMapping ("/updateBoard")
    public String updateBoard(Board board) {
        System.out.println("----------updateBoard---------");
        System.out.println(board.getContent());
        System.out.println(board.getSeq());
        boardService.updateBoard(board);
        return "redirect:/board/getBoard?seq="+board.getSeq();
    }

    @GetMapping("/updateBoard")
    public String updateBoardView(Board board, Model model) {
        System.out.println("-------------------");
        model.addAttribute("board", boardService.getBoard(board));
        return "/board/insertBoard";
    }

    @GetMapping("/deleteBoard")
    public String deleteBoard(Board board) {
        System.out.println("--------boarde delete-----------");
        System.out.println(board.getSeq());
        boardService.deleteBoard(board);
        return "redirect:/board/getBoardList";
    }

    @GetMapping("/selectBoard")
    public String selectBoard(Member member, Model model) {
        System.out.println("--------boarde select-----------");
        //board.getId()는 클라이언트에서 가져옴

        //@Service에 board를 인자값으로 넣고 메서드 실행
        model.addAttribute("boardList", boardService.getBoardListByMemberId(member));

        //회원이 작성한 게시글리스트(List<Board>)
        // > HTML에다가 뿌려주면 끝 (Controller에 가면 메서드가 실행되서 다른 결과물을 리턴받기 때문
        // 어느 HTML로 가느냐? = 객체지향은 재활용성이 중요한 요인 중 하나
        // HTML에 중에 재사용 할만한 것을 먼저 찾고, 그 후에 새로 만들기에 대해 고민
        // > getBoardList

        //return 페이지 Or controller mapoing
        return "/board/getBoardList";
    }
}
