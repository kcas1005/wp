package com.human.pet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class Index2Controller {

    private final static String MAIN = "index2";
    private final static String TEST1 = "test1";

    @GetMapping("/index2")
    public String index2(Model model){

        String id = "지성진";
        model.addAttribute("id2", id);

        return MAIN;
    }

}
