package com.project.init.controller;


import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.init.dao.PostDao;
import com.project.init.dto.PostDto;
import com.project.init.util.Constant;


@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	//김병선 작업 시작점 -----------------------------------
	@Autowired
	private PostDao dao;

	@RequestMapping("/")
	public String index(Model model) {
		logger.info("index() in >>>>");
		String user = Constant.username;
		ArrayList<PostDto> post = dao.list(user);
		model.addAttribute("post", post);
		ArrayList<PostDto> likeList = dao.likeList(user);
		model.addAttribute("likeList", likeList);
		ArrayList<PostDto> viewList = dao.viewList(user);
		model.addAttribute("viewList", viewList);
		model.addAttribute("user",Constant.username);
		
		return "index";
	}
	
	
	//김병선 작업 끝 -----------------------------------
	
	@RequestMapping("/join")
	public String join() {
		logger.info("index() in >>>>");
		return "join/join";
	}
	
}
