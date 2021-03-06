package com.project.init.dto;

import java.sql.Timestamp;

public class PostDto {

	private String postNo;
	private String email;
	private String userNick;
	private String plan;
	private String titleImage;
	private String images;
	private String content;
	private String userProfileImg;
	private String hashtag;
	private Timestamp regDate;
	private String location;
	private int views;
	private int comments;
	private int likes;
	private int heart;
	private int heartCheck;
	private String authority;

	public PostDto() {
		super();
	}
	
	//addPost �삤踰꾨줈�뵫
	public PostDto(String email, String content,String hashtag, String location,String titleImage,String images,int views) {
		super();
		this.email = email;
		this.hashtag = hashtag;
		this.titleImage = titleImage;
		this.images = images;
		this.content = content;
		this.location = location;
		this.views = views;
		
	}

	public PostDto(String postNo,String content,String hashtag,String titleImage,String images) {
		super();
		this.postNo = postNo;
		this.titleImage = titleImage;
		this.images = images;
		this.content = content;
		this.hashtag = hashtag;		
	}
	
	public PostDto(String postNo, String email) {
		super();
		this.postNo = postNo;
		this.email = email;
	}
	

	public String getUserProfileImg() {
		return userProfileImg;
	}

	public void setUserProfileImg(String userProfileImg) {
		this.userProfileImg = userProfileImg;
	}

	public String getUserNick() {
		return userNick;
	}

	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}

	

	public int getHeartCheck() {
		return heartCheck;
	}

	public void setHeartCheck(int heartCheck) {
		this.heartCheck = heartCheck;
	}

	public int getHeart() {
		return heart;
	}

	public void setHeart(int heart) {
		this.heart = heart;
	}

	public int getLikes() {
		return likes;
	}


	public void setLikes(int likes) {
		this.likes = likes;
	}


	public int getComments() {
		return comments;
	}


	public void setComments(int comments) {
		this.comments = comments;
	}


	public String getPostNo() {
		return postNo;
	}

	public void setPostNo(String postNo) {
		this.postNo = postNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPlan() {
		return plan;
	}

	public void setPlan(String plan) {
		this.plan = plan;
	}


	public String getTitleImage() {
		return titleImage;
	}

	public void setTitleImage(String titleImage) {
		this.titleImage = titleImage;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getHashtag() {
		return hashtag;
	}

	public void setHashtag(String hashtag) {
		this.hashtag = hashtag;
	}

	public Timestamp getRegDate() {
		return regDate;
	}

	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
}
