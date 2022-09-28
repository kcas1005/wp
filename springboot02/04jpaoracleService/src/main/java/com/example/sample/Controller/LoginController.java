package com.example.sample.Controller;

import com.example.sample.model.entity.LoginEntity;
import com.example.sample.model.request.LoginDto;
import com.example.sample.repository.LoginRepository;
import com.example.sample.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;


@Controller
public class LoginController {
    @Autowired
    private LoginService loginService;

    @GetMapping("/insert")
    public String insertView(){
        return "insert";
    }
    @PostMapping("/insert")
    public String insert(LoginDto dto, Model model){
        //사용방법 Repository에 save메소드에 저장하고 싶은
        //entity인스턴스를 매개변수로 넘겨주면 처리결과를 entity형태로
        //리턴해준다.
        LoginEntity saved=loginService.save(dto.toEntity());
        System.out.println(saved);

        return "redirect:/selectOne/"+saved.getId();
    }
    @GetMapping("/selectOne/{id}")
    public String selectOne(@PathVariable Integer id, Model model){
        // findById 메소드 id값으로 하나의 데이터를 얻어와요
        LoginEntity dto = loginService.findById(id);
        System.out.println(dto);
        model.addAttribute("dto", dto);

        return "selectOne";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes rttr){
        LoginEntity entity = loginService.findById(id);
        if(entity!=null){
            loginService.delete(entity);
            rttr.addFlashAttribute("msg", "success");
        }
        return "redirect:/selectAll";
    }

    @GetMapping("/selectAll")
    public String SelectAll(Model model){
        ArrayList<LoginEntity> dtos=loginService.findAll();
        model.addAttribute("dtos", dtos);
        System.out.println(dtos);
        return "selectAll";
    }
    @GetMapping("/update/{id}")
    public String update(@PathVariable Integer id, Model model){
        LoginEntity dto = loginService.findById(id);
        System.out.println(dto);
            model.addAttribute("dto", dto);
        return "update";
    }
    @PostMapping("/update")
    public String update(LoginDto dto){
        //id 값이 있으면 update null이면 insert
        LoginEntity entity = dto.toEntity();
        loginService.save(entity);
//        return "redirect:/selectOne/"+entity.getId;
        return "redirect:selectAll";
    }
}
