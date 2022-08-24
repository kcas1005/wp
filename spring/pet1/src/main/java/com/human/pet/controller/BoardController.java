package com.human.pet.controller;

//외장 라이브러리 호출(import), gradle로 설치한 라이브러리

import com.human.pet.domain.Board;
import com.human.pet.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;
import java.util.List;

//-----------------------------------------------------------------------------------
@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    //BoardService의 getBoardList메서드 실행 > BoardRepository(CrudRepository).findAll()를 통해서 (JPA번역)
    //DB의 데이터 불러오기(테이블전체) (SQL)

    @GetMapping("/t1")
    public void gett1(){
    }

    //@GetMapping 또는@PostMapping은 @RequestMapping의 자식 클래스이다
    //RequestMapping의 기능을 모두 쓸 수 있다
    //자식클래스 어노테이션이 아닌 부모클래스 어노테이션을 쓰는 이유는 기능의 한정을 통해서
    //서버의 리소스 감소 및 보안을 위해서 이다
    @GetMapping("insertBoard")
    public String insertBoardView() {
        return "insertBoard";
    }

    //[클라이언트]html form태그의 method속성의 값인 post를 인식하여 아래의
    //@PostMapping에 연결
    @PostMapping("insertBoard")
    public String insertBoard(Board board) {
        board.setCreateDate(new Date());

        //클라이언트에서 board객체를 받아서 매개변수로 사용
        //[1]BoardService의 inserBoard메서드 실행 >
        //[2]BoardRepository(CrudRepository).save(board)를 통해서 (JPA번역)
        //DB의 저장 (SQL)
        //insertBoard라는 메서드에 board객체 인자값으로 넣기
        boardService.insertBoard(board);

        return "redirect:getBoardList";
    }
//-----------------------------------------------------------------------------------

    //@어노테이션은 메서드 혹은 클래스에 속성, 정의를 해서 스프링이나 자바에서 찾기 쉽도록 해주는 선언부
    //예) @Override 은 부모 메서드를 재정의하여 사용한다고 자바나 스프링에게 속성 명시
    //@RequestParam : [클라이언트]에서 string문자열을 [서버]에 전달하는 매개변수 선언
    //@RequestParam("title")String title에서 ("title")은 [클라이언트]의 name이라는 속성로써
    //key값을 매개변수를 전달
    @GetMapping("/getBoard")
    public String getBoard(Board board,Model model) {
        model.addAttribute("board", boardService.getBoard(board));
        return "getBoard";
    }
//-----------------------------------------------------------------------------------

//    @RequestMapping은 서버에서 디스페처서블릿을 통해 html의 action태그의 주소와 동일한
//    문자열을 찾는 매핑기능(연결)이 실행되고 하단에 메서드가 실행
//    return String인 이유는 뷰리졸버가 html파일을 찾기 위한 문자열 리턴

    //클라이언트에서 서버로 무언가 데이터를 전송하기 위한 Mapping(@RequestMapping)
    @GetMapping("/getBoardList")
    public String getBoardList(Model model) {
        List<Board> boardList = boardService.getBoardList();
        model.addAttribute("boardList", boardList);
        return "getBoardList";
    }
//-----------------------------------------------------------------------------------

    @GetMapping("deleteBoard")
    public String deleteBoard(Board board) {
        boardService.deleteBoard(board);
        return "redirect:getBoardList";
    }
//-----------------------------------------------------------------------------------

    //Post방식으로 [클라이언트]에서 [서버]로 맵핑
    @PostMapping("updateBoard")
    public String updateBoard(Board board){
        boardService.updateBoard(board);
        return "redirect:getBoard?seq="+board.getSeq();
    }
    @GetMapping("/updateBoard")
    public String updateBoardView(Board board, Model model) {
        model.addAttribute("board", boardService.getBoard(board));
        return "insertBoard";
    }
}
