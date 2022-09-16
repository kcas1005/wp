package com.human.ex;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.human.dto.HumanDto;
import com.human.service.IHumanService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	private IHumanService service;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "redirect:selectAll";
	}
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(HumanDto humanDto) {
		System.out.println(humanDto);
		return "update";
	}
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updateDB(HumanDto humanDto,RedirectAttributes rttr) throws Exception{
		service.update(humanDto);
		rttr.addFlashAttribute("msg","success");
		return "redirect:selectAll";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(String name,RedirectAttributes rttr) throws Exception {
		service.delete(name);
		rttr.addFlashAttribute("msg","success");
		return "redirect:selectAll";
	}
	
	@RequestMapping(value = "/selectName", method = RequestMethod.GET)
	public String selectName(String name,Model model) throws Exception {
		model.addAttribute("dto", service.selectName(name) );
		return "selectName";
	}
	
	@RequestMapping(value = "/selectAll", method = RequestMethod.GET)
	public String selectAll(Model model) throws Exception {
		model.addAttribute("list", service.selectAll() );
		return "selectAll";
	}
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public String insert() {
		return "insert";
	}
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insertDB(HumanDto humanDto,RedirectAttributes rttr) throws Exception{
		service.insert(humanDto);
		rttr.addFlashAttribute("msg","success");
		return "redirect:selectAll";
	}
	
}




