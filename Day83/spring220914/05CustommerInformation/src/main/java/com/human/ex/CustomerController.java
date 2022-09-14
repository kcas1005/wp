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
import com.human.service.ICustomerService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class CustomerController {
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
	
	@Inject
	private ICustomerService customerService;
	
	/**
	 * Simply selects the home view to render by returning its name.
	
	 */
	@RequestMapping(value = "/customer/modify", method = RequestMethod.POST)
	public String modifyDB(Model model,CustomerDto dto,RedirectAttributes rttr) throws Exception{
		System.out.println("modify db");
		customerService.update(dto);
		rttr.addFlashAttribute("msg","success");
		return "redirect:/customer/selectAll";
	}
	@RequestMapping(value = "/customer/modify", method = RequestMethod.GET)
	public String modify(Model model,@RequestParam("id")int id) throws Exception{
		System.out.println("modify gui");
		model.addAttribute(customerService.select(id));
		return "/customer/update";
	}
	
	@RequestMapping(value = "/customer/delete", method = RequestMethod.GET)
	public String delete(@RequestParam("id")int id,RedirectAttributes rttr) throws Exception{
		System.out.println("delete");
		customerService.delete(id);
		rttr.addFlashAttribute("msg","success");
		return "redirect:/customer/selectAll";
	}
	
//	/ex/customer/read?bno=${
	@RequestMapping(value = "/customer/read", method = RequestMethod.GET)
	public void read(Model model,@RequestParam("id")int id) throws Exception{
		System.out.println("read");
		model.addAttribute(customerService.select(id));				
	}
	@RequestMapping(value = "/customer/selectAll", method = RequestMethod.GET)
	public void home(Model model) throws Exception{
		System.out.println("selectAll");
		List<CustomerDto> dto=customerService.selectAll();
		System.out.println(dto);
		model.addAttribute("list",dto);		
	}
	@RequestMapping(value = "/customer/insert", method = RequestMethod.GET)
	public void insert() throws Exception{
		System.out.println("insert GUI");
	}
	@RequestMapping(value = "/customer/insert", method = RequestMethod.POST)
	public String insertData(CustomerDto dto,RedirectAttributes rttr) throws Exception{
		System.out.println("insert db");
		customerService.insert(dto);
		rttr.addFlashAttribute("msg","success");
		return "redirect:/customer/selectAll";
	}
	
}



