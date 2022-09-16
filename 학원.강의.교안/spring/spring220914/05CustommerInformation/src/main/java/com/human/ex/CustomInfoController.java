package com.human.ex;

import java.text.DateFormat;
import java.util.ArrayList;
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
import com.human.service.ICustomInfoService;
import com.human.service.ICustomerService;
import com.human.service.IHobbyService;
import com.human.vo.CustomerInfoVo;
import com.human.vo.CustomerInfoVo2;

/**
 * Handles requests for the application home page.
 */
@Controller
public class CustomInfoController {
	
	private static final Logger logger = LoggerFactory.getLogger(CustomInfoController.class);
	
	@Inject
	private IHobbyService hobbyService;
	@Inject
	private ICustomerService customerService;
	
	@Inject
	private ICustomInfoService customerInfoService;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	@RequestMapping(value = "/customInfo/insert", method = RequestMethod.GET)
	public void insert(Model model) throws Exception{
		System.out.println("customInfo");
		System.out.println("insert GUI");
	}
	@RequestMapping(value = "/customInfo/insert", method = RequestMethod.POST)
	public String insertData(CustomerDto dto,CustomerInfoVo vo
			,RedirectAttributes rttr) throws Exception{
		System.out.println("insert db");
			
		customerInfoService.insertCustomInfo(dto, vo);
		
		//customerService.insert(dto);
		//hobbyService.insert(dto);
		//rttr.addFlashAttribute("msg","success");
		return "redirect:/customInfo/selectAll";
	}

	@RequestMapping(value = "/customInfo/delete", method = RequestMethod.GET)
	public String deleteCustomInfo(@RequestParam("id") int id,RedirectAttributes rttr) throws Exception{
		System.out.println("delete "+id);
		hobbyService.deleteId(id);
		customerService.delete(id);
		return "redirect:/customInfo/selectAll";
	}
	@RequestMapping(value = "/customInfo/hobbyDelete", method = RequestMethod.GET)
	public String delete(HobbyDto dto,RedirectAttributes rttr) throws Exception{
		System.out.println("delete");
		hobbyService.delete(dto);
		rttr.addFlashAttribute("msg","success");
		return "redirect:/customInfo/selectAll";
	}
//	@RequestMapping(value = "/hobby/modify", method = RequestMethod.POST)
//	public String modifyDB(Model model,HobbyDto dto,RedirectAttributes rttr) throws Exception{
//		System.out.println("modify db");
//		hobbyService.update(dto);
//		rttr.addFlashAttribute("msg","success");
//		return "redirect:/hobby/selectAll";
//	}
//	@RequestMapping(value = "/hobby/modify", method = RequestMethod.GET)
//	public String modify(Model model,HobbyDto dto) throws Exception{
//		System.out.println("modify gui");
//		model.addAttribute(hobbyService.select(dto));
//		return "/hobby/update";
//	}
//	
	
//	
//
//	@RequestMapping(value = "/hobby/read", method = RequestMethod.GET)
//	public void read(Model model,HobbyDto dto) throws Exception{
//		System.out.println("read");
//		model.addAttribute(hobbyService.select(dto));				
//	}
	@RequestMapping(value = "/customInfo/selectAll", method = RequestMethod.GET)
	public void home(Model model) throws Exception{
		System.out.println("selectAll");
		
		
		List<CustomerInfoVo> customerInfoVos=customerInfoService.selectAll();
		model.addAttribute("list",customerInfoVos);
		System.out.println(customerInfoVos);
		
//		List<HobbyDto> dto=hobbyService.selectAll();
//		System.out.println(dto);
//		model.addAttribute("list",dto);		
	}
	@RequestMapping(value = "/customInfo/selectAll2", method = RequestMethod.GET)
	public void home2(Model model) throws Exception{
		System.out.println("selectAll");
		
		
		List<CustomerInfoVo2> customerInfoVo2s	
			= customerInfoService.selectAllCustomerHobby();
		model.addAttribute("list",customerInfoVo2s);
		System.out.println(customerInfoVo2s);
		
//		List<HobbyDto> dto=hobbyService.selectAll();
//		System.out.println(dto);
//		model.addAttribute("list",dto);		
	}
	
	
	
}



