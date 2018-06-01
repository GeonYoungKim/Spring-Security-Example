package com.example.security.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.security.model.dao.UserDAO;
import com.example.security.service.ShaEncoder;

@Controller
public class UserController {

	@Inject
	ShaEncoder shaEncoder;
	
	@Inject
	UserDAO userDao;
	
	@RequestMapping("/")
	public String home(Model model) {
		return "home";
	}
	@RequestMapping("/user/login.do")
	public String login(Model model) {
		return "user/login";
	}
	@RequestMapping("/user/join.do")
	public String join(Model model) {
		System.out.println("회원가입");
		return "user/join";
	}
	
	@RequestMapping("/user/insertUser.do")
	public String insertUser(@RequestParam String userid,@RequestParam String passwd,@RequestParam String name,@RequestParam String authority) {
		System.out.println("???");
		String dbpw=shaEncoder.saltEncoding(passwd, userid);
		Map<String, String> map=new HashMap<>();
		map.put("userid", userid);
		map.put("passwd", dbpw);
		map.put("name", name);
		map.put("authority",authority);
		
		int result=userDao.insertUser(map);
		return "user/login";
	}
	@RequestMapping("/admin/")
	public String admin() {
		return "admin/main";
	}
	
	
	
}
