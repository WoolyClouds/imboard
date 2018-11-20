package com.geon.im.Service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.geon.im.Domain.imboard;

@Service
public interface BoardService {
	
	// 게시글 수 구하기
	public int postCount();
	
	// 게시글 목록 가져오기
	public void loadBoardlist(HttpServletRequest request, imboard imb, int postcount);
	
	// 게시글 등록하기
	public void regPost(imboard imb);
	
	// 게시글 내용 가져오기
	public void loadPost(HttpServletRequest request, imboard imb);
	
	// 비밀번호 체크
	public boolean PasswordCheck(imboard imb);
	
	// 게시글 수정
	public void modifyPost(imboard imb);
	
	// 게시글 삭제
	public void deletePost(imboard imb);

}
