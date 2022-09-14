package com.human.ex;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.human.dto.CustomerDto;
import com.human.dto.HobbyDto;
import com.human.service.ICustomerService;
import com.human.service.IHobbyService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HobbyController {
	
	private static final Logger logger = LoggerFactory.getLogger(HobbyController.class);
	
	@Inject
	private IHobbyService hobbyService;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	@RequestMapping(value = "/hobby/modify", method = RequestMethod.POST)
	public String modifyDB(Model model,HobbyDto dto,RedirectAttributes rttr) throws Exception{
		System.out.println("modify db");
		hobbyService.update(dto);
		rttr.addFlashAttribute("msg","success");
		return "redirect:/hobby/selectAll";
	}
	@RequestMapping(value = "/hobby/modify", method = RequestMethod.GET)
	public String modify(Model model,HobbyDto dto) throws Exception{
		System.out.println("modify gui");
		model.addAttribute(hobbyService.select(dto));
		return "/hobby/update";
	}
	
	@RequestMapping(value = "/hobby/delete", method = RequestMethod.GET)
	public String delete(HobbyDto dto,RedirectAttributes rttr) throws Exception{
		System.out.println("delete");
		hobbyService.delete(dto);
		rttr.addFlashAttribute("msg","success");
		return "redirect:/hobby/selectAll";
	}
	

	@RequestMapping(value = "/hobby/read", method = RequestMethod.GET)
	public void read(Model model,HobbyDto dto) throws Exception{
		System.out.println("read");
		model.addAttribute(hobbyService.select(dto));				
	}
	@RequestMapping(value = "/hobby/selectAll", method = RequestMethod.GET)
	public void home(Model model) throws Exception{
		System.out.println("selectAll");
		List<HobbyDto> dto=hobbyService.selectAll();
		System.out.println(dto);
		model.addAttribute("list",dto);		
	}
	@RequestMapping(value = "/hobby/insert", method = RequestMethod.GET)
	public void insert(Model model) throws Exception{
		
		List<Integer> dtos= hobbyService.insertItem();
		System.out.println(dtos);
		model.addAttribute("list",dtos);
		System.out.println("insert GUI");
	}
	@RequestMapping(value = "/hobby/insert", method = RequestMethod.POST)
	public String insertData(HobbyDto dto,RedirectAttributes rttr) throws Exception{
		System.out.println("insert db");
		hobbyService.insert(dto);
		rttr.addFlashAttribute("msg","success");
		return "redirect:/hobby/selectAll";
	}
	
}



