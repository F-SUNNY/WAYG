package com.project.init.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.project.init.dto.CommentsDto;
import com.project.init.dto.PostDto;
import com.project.init.dto.PostLikeDto;
import com.project.init.dto.PostViewDto;
import com.project.init.dto.SearchDto;


public class PostDao implements PostIDao{

	@Autowired
	private SqlSession sqlSession;

	@Override
	public PostDto write(PostDto dto) {
		sqlSession.insert("write", dto);
		return null;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public ArrayList<PostDto> list(String email) {
		ArrayList<PostDto> list =  (ArrayList)sqlSession.selectList("list",email);
		return list;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public ArrayList<PostDto> likeList(String email) {
		ArrayList<PostDto> list =  (ArrayList)sqlSession.selectList("likeList",email);
		return list;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public ArrayList<PostDto> viewList(String email) {
		ArrayList<PostDto> list =  (ArrayList)sqlSession.selectList("viewList",email);
		return list;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public ArrayList<PostDto> getlist(PostDto tmp) {
		ArrayList<PostDto> dto = (ArrayList)sqlSession.selectList("getlist",tmp);
		
		return dto;
	}

	@Override
	public void deleteBoard(String postNo) {
		sqlSession.delete("deleteBoard", postNo);
		sqlSession.delete("deleteComments", postNo);
		
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public ArrayList<PostDto> modifyList(String postNo) {
		ArrayList<PostDto> list =  (ArrayList)sqlSession.selectList("modifyList",postNo);
		
		return list;
	}

	@Override
	public void modifyExcute(PostDto dto) {
		sqlSession.update("modifyExcute", dto);
	}

	@Override
	public void addcomments(CommentsDto dto) {
		sqlSession.insert("addcomments", dto);
	}
	
	
	@Override
	public void addReplyComments(CommentsDto dto){
		
		sqlSession.update("beforeAddReply", dto);
		sqlSession.insert("addReplyComments", dto);
	}

	@Override
	public ArrayList<CommentsDto> getcomments(String postNo) {
		ArrayList<CommentsDto> dto =(ArrayList)sqlSession.selectList("getcomments",postNo);
		
		return dto;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public ArrayList<PostDto> search(SearchDto dto) {
		
		
		String keyword =dto.getKeyword();
		
		if(keyword.equals("Hashtag")) {
			ArrayList<PostDto> dtos =(ArrayList)sqlSession.selectList("searchHashtag", dto);			
			return dtos;
		}else if(keyword.equals("NickName")) {			
			ArrayList<String> arr =(ArrayList) sqlSession.selectList("checkNickName", dto);
			ArrayList<PostDto> dtos =(ArrayList)sqlSession.selectList("searchNickName", arr);			
			return dtos;	
			
		}else{
			ArrayList<String> arr =(ArrayList) sqlSession.selectList("checkLocation", dto);
			ArrayList<PostDto> dtos =(ArrayList)sqlSession.selectList("searchLocation", arr);			
			return dtos;	
			
		}
		
	}

	@Override
	public void deleteReplyComments(String commentNo) {
		sqlSession.update("deleteReplyComments", commentNo);
	}

	@Override
	public String addLike(PostLikeDto dto) {
		int tmp =sqlSession.selectOne("like", dto);
		String result ="";
		
		if(tmp ==0) {
			sqlSession.insert("addLike", dto);	
			result = "add";
			
		}else {
			sqlSession.delete("deleteLike", dto);
			result = "delete";
		}
		return result;
	}

	@Override
	public String addView(PostViewDto dto) {
		int tmp =sqlSession.selectOne("view", dto);	
		
		if(tmp ==0) {
			sqlSession.insert("addView", dto);	
			return "success";
		}else {
			return "success";
		}
	}
	
}
