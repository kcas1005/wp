package com.human.ex;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
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
		
		return "home";
	}
	@RequestMapping(value = "/helloworld", method = RequestMethod.GET)
	public String helloworld(Model model) {
		
		model.addAttribute("myString1", "Hello" );
		model.addAttribute("myString2", "world" );
		
		return "helloworld";
	}
	
	//http://blog.iotinfra.net/?p=971
		@RequestMapping(value = "board/confirmId", method = RequestMethod.GET)
		public String confirmId(HttpServletRequest httpServletRequest,Model model) {
			String id=httpServletRequest.getParameter("id");
			String pw=httpServletRequest.getParameter("pw");
			
			model.addAttribute("id",id);
			model.addAttribute("pw",pw);	
			
			return "board/confirmId";
		}
	//http://localhost:8082/formdata/board/checkId?id=abcd&pw=1234
		// @RequestParam(value="name",required=true) List<String> name,
		//cheak box
		@RequestMapping("board/checkId")
		public String checkId(@RequestParam("id") String id
				,@RequestParam("pw") int pw,Model model){
			
			model.addAttribute("identify",id);
			model.addAttribute("password",pw);
		
			return "board/checkId";
		}
	//http://localhost:8081/formdata/member/join?name=10&id=20&pw=30&email=123a
		/*@RequestMapping("member/join")
		public String joinData(@RequestParam("name") String name
				,@RequestParam("id") String id
				,@RequestParam("pw") String pw
				,@RequestParam("email") String email,Model model){
			
			Member member=new Member();
			member.setName(name);
			member.setId(id);
			member.setPw(pw);
			member.setEmail(email);
			
			model.addAttribute("memberInfo",member);
			
			
		
			return "member/join";
		}*/
		
		//클래스명과 매개변수명이 반드시 아래와 같이 정의 되어야 한다
		//Member member
		//Member member1 은 안됨!
		@RequestMapping("/member/join")
		public String joinData(Member member) {
			System.out.println(member);
			return "member/join";
		}
		//http://localhost:8082/formdata/student/30
		@RequestMapping("/student/{studentId}")
		public String getStrudent(@PathVariable String studentId,Model model) {
			model.addAttribute("studentId", studentId);
			return "student/studentView";
		}
		
		
		
		
		
		
	
}
