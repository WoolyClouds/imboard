package com.geon.im.ServiceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.geon.im.DAO.BoardDAO;
import com.geon.im.DAOImpl.BoardDAOImpl;
import com.geon.im.Domain.imboard;
import com.geon.im.Service.BoardService;

public class BoardServiceImpl implements BoardService{
	
	BoardDAO bd = new BoardDAOImpl();
	
	public int postCount() {
		return bd.postCount();
	}
	
	public void loadBoardlist(HttpServletRequest request, imboard imb, int postcount) {
		bd.loadBoardlist(request, imb, postcount);
	}
	
	public void regPost(imboard imb) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	
		imb.setRegdate(sdf.format(date));	// 게시글 등록 시간
		bd.regPost(imb);
	}
	
	public void loadPost(HttpServletRequest request, imboard imb) {
		bd.loadPost(request, imb);
	}
	
	public boolean PasswordCheck(imboard imb) {
		return bd.PasswordCheck(imb);	
	}
	
	public void modifyPost(imboard imb) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	
		imb.setRegdate(sdf.format(date));	// 게시글 수정 시간
		bd.modifyPost(imb);
	}
	
	public void deletePost(imboard imb) {
		bd.deletePost(imb);
	}

}
