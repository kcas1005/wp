package com.example.demo.controller;

/**
* @package : com.example.demo.controller
* @name : BoardController.java
* @date : 2022-08-08 오후 6:24
* @author : Rubisco
* @version : 1.0.0
* @modifyed : 
* @description : 게시판 컨트롤러
**/

import com.example.demo.domain.Board;
import com.example.demo.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    //BoardService의 getBoardList메서드 실행 > BoardRepository(CrudRepository).findAll()를 통해서 (JPA번역)
    //DB의 데이터 불러오기(테이블전체) (SQL)
    @GetMapping("/getBoardList")
    public String getBoardList(Model model) {
        System.out.println("----------Get BoardList----------");
        List<Board> boardList = boardService.getBoardList();

        model.addAttribute("boardList", boardList);
        System.out.println("boardList : " + boardList);
        return "getBoardList";
    }

    @GetMapping("/insertBoard")
    public String insertBoardView() {
        System.out.println("----------Get insertBoard----------");
        return "insertBoard";
    }

    @PostMapping("/insertBoard")
    public String insertBoard(Board board) {
        System.out.println("----------Post insertBoard----------");
        board.setCreateDate(new Date());

        //클라이언트에서 board객체를 받아서 매개변수로 사용
        //[1]BoardService의 inserBoard메서드 실행 >
        //[2]BoardRepository(CrudRepository).save(board)를 통해서 (JPA번역)
        //DB의 저장 (SQL)
        //insertBoard라는 메서드에 board객체 인자값으로 넣기
        boardService.insertBoard(board);
        System.out.println("board : " + board);
        return "redirect:getBoardList";
    }

    @GetMapping("/getBoard")
    public String getBoard(Board board, Model model) {
        System.out.println("----------Get Board----------");
        model.addAttribute("board", boardService.getBoard(board));
        System.out.println("boardService.getBoard(board) : " + boardService.getBoard(board));
        return "getBoard";
    }

    @PostMapping ("/updateBoard")
    public String updateBoard(Board board) {
        System.out.println("----------Post updateBoard----------");
        boardService.updateBoard(board);
        System.out.println("board : " + board);
        return "redirect:getBoard?seq="+board.getSeq();
    }

    @GetMapping("/updateBoard")
    public String updateBoardView(Board board, Model model) {
        System.out.println("----------Get updateBoard----------");
        model.addAttribute("board", boardService.getBoard(board));
        System.out.println("boardService.getBoard(board) : " + boardService.getBoard(board));
        return "insertBoard";
    }

    @PostMapping("/deleteBoard")
    public String deleteBoard(Board board) {
        System.out.println("----------Post deleteBoard----------");
        boardService.deleteBoard(board);
        System.out.println("board : " + board);
        return "redirect:getBoardList";
    }
}
