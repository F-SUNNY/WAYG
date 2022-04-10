package com.project.init.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.project.init.dao.PostDao;
import com.project.init.dto.CommentsDto;
import com.project.init.dto.PostDto;
import com.project.init.dto.PostLikeDto;
import com.project.init.dto.PostViewDto;
import com.project.init.util.Constant;


@Controller
@RequestMapping("/post")

public class PostController {
	@Autowired
	private PostDao dao;
	

//	@RequestMapping("")
//	public String list(Model model) {
//		
//		ArrayList<PostDto> list = dao.list();
//		model.addAttribute("list", list);
//		model.addAttribute("user",Constant.username);
//		System.out.println(Constant.username);
//		return "post/postMain";
//	}
	
	@RequestMapping("postMain")
	public String postMain(Model model) {
		String user = Constant.username;
		ArrayList<PostDto> list = dao.list(user);
		model.addAttribute("list", list);
		model.addAttribute("user",Constant.username);
		System.out.println(Constant.username);
		return "post/postMain";
	}
	
	@RequestMapping("postLike")
	public String postLike(Model model) {
		String user = Constant.username;
		ArrayList<PostDto> list = dao.likeList(user);
		model.addAttribute("list", list);
		model.addAttribute("user",Constant.username);
		System.out.println(Constant.username);
		return "post/postMain";
	}
	
	@RequestMapping("postView")
	public String postView(Model model) {
		String user = Constant.username;
		ArrayList<PostDto> list = dao.viewList(user);
		model.addAttribute("list", list);
		model.addAttribute("user",Constant.username);
		System.out.println(Constant.username);
		return "post/postMain";
	}
	
	@RequestMapping("getPost")
	public String getPost(Model model) {
		String user = Constant.username;
		ArrayList<PostDto> list = dao.list(user);
		model.addAttribute("list", list);
		model.addAttribute("user",Constant.username);
		System.out.println(Constant.username);
		return"post/postMain";
	}
	
	@RequestMapping("addPost")
	public String addPost(Model model) {	
		model.addAttribute("user",Constant.username);
		System.out.println("=========> addPost");
		return "post/addPost";
	}
	
	@RequestMapping(value = "uploadMulti", method = { RequestMethod.POST })
	public String uploadMulti(MultipartHttpServletRequest multi, Model model) {
		String images = "";
		String titleImage="";
		String tmp="";
		int views =0;
		List<MultipartFile> fileList = multi.getFiles("img");
		String path = "C:/Users/user/git/WAYG/src/main/webapp/resources/images/";
		for (MultipartFile mf : fileList) {
			String originalFileName = mf.getOriginalFilename();
			UUID prefix = UUID.randomUUID();
			
			try {
				mf.transferTo(new File(path + prefix + originalFileName));
				tmp+=prefix + originalFileName+"/";
			
			} catch (IllegalStateException | IOException e) {
				e.getMessage(); 
				System.out.println(e.getMessage());
			}
		}
		
		images = tmp;
		String[] test = images.split("/");
		titleImage = test[0];
		PostDto dto = new PostDto(multi.getParameter("email"),multi.getParameter("content"),multi.getParameter("hashtag"),multi.getParameter("location"),titleImage,images,views);
		dao.write(dto);
		return "redirect:postMain";
	}
	
	
	
	@ResponseBody
	@RequestMapping("getlist.do")
	public ArrayList<PostDto> getlist(@RequestParam("postNo") String postNo,@RequestParam("email") String email) {
		PostDto tmp = new PostDto(postNo,email);
		ArrayList<PostDto> dto = dao.getlist(tmp);
		System.out.println("dddddddd");
		return dto;
	}
	
	@RequestMapping("delete.do")
	public String delete(HttpServletRequest request,Model model) {
		String postNo = request.getParameter("postNo");
		dao.deleteBoard(postNo);
		return "redirect:list";
	}
	
	@RequestMapping("modify")
	public String modify(HttpServletRequest request,Model model) {
		String postNo = request.getParameter("postNo");
		ArrayList<PostDto> list =dao.modifyList(postNo);
		
		model.addAttribute("list", list);
		return "modify";
	}
	
	@RequestMapping(value = "modifyExcute.do", method = { RequestMethod.POST })
	public String modifyExcute(MultipartHttpServletRequest multi, Model model) {
		String images = "";
		String titleImage="";
		String tmp="";

		
		List<MultipartFile> fileList = multi.getFiles("img");
		
		
		String path = "C:/Users/user/git/WAYG/src/main/webapp/resources/images/";

		for (MultipartFile mf : fileList) {
			String originalFileName = mf.getOriginalFilename();
			UUID prefix = UUID.randomUUID();
			
			try {
				mf.transferTo(new File(path + prefix + originalFileName));
				tmp+=prefix + originalFileName+"/";
			} catch (IllegalStateException | IOException e) {
				e.getMessage(); 
			}
		}
		images = tmp;
		String[] test =  images.split("/");
		titleImage = test[0];
		
		PostDto dto = new PostDto(multi.getParameter("postNo"),multi.getParameter("content"),multi.getParameter("hashtag"),multi.getParameter("location"),titleImage,images);
		dao.modifyExcute(dto);
		return "redirect:list";
	}
	
	@ResponseBody
	@RequestMapping("addcomments.do")
	public void addcomments(@RequestParam("postNo") String postNo, @RequestParam("content") String content,@RequestParam("grpl") String grpl,@RequestParam("email") String email){
		int Igrpl = Integer.parseInt(grpl);
		CommentsDto dto = new CommentsDto(postNo,content,Igrpl,email);
		dao.addcomments(dto);
		
	}
	
	@ResponseBody
	@RequestMapping("addReplyComments.do")
	public void addReplyComments(@RequestParam("postNo") String postNo,@RequestParam("grp") String grp, @RequestParam("content") String content,@RequestParam("grpl") String grpl,@RequestParam("grps") String grps,@RequestParam("email") String email){
		int Igrp = Integer.parseInt(grp);
		int Igrpl = Integer.parseInt(grpl);
		int Igrps = Integer.parseInt(grps);
		CommentsDto dto = new CommentsDto(postNo,Igrp,content,Igrpl,Igrps,email);
		dao.addReplyComments(dto);
		
	}
	
	@ResponseBody
	@RequestMapping("deleteReplyComments.do")
	public void deleteReplyComments(@RequestParam("commentNo") String commentNo){
		dao.deleteReplyComments(commentNo);		
	}
	
	@ResponseBody
	@RequestMapping(value="getcomments.do")
	public ArrayList<CommentsDto> getcomments(@RequestParam("postNo") String postNo) {
		
		ArrayList<CommentsDto> dto = dao.getcomments(postNo);
		
		return dto;
	}
	
	@RequestMapping("searchPage")
	public String searchPage(HttpServletRequest request, Model model) {
		String keyword = request.getParameter("keyword");
		String searchVal = request.getParameter("searchVal");
		ArrayList<PostDto> list = dao.search(keyword,searchVal);
		model.addAttribute("list", list);
		
		return "postMain";
	}

	@ResponseBody
	@RequestMapping(value="addLike.do",produces = "application/text; charset=UTF-8")
	public String addLike(@RequestParam("postNo") String postNo,@RequestParam("email") String email) {
		
		PostLikeDto dto = new PostLikeDto(postNo, email);
		String result = dao.addLike(dto);
		return result;
	}
	
	@ResponseBody
	@RequestMapping("addView.do")
	public String addView(@RequestParam("postNo") String postNo,@RequestParam("email") String email) {
		PostViewDto dto = new PostViewDto(postNo, email);
		String result = dao.addView(dto);
		return result;
		
	}

	
}
