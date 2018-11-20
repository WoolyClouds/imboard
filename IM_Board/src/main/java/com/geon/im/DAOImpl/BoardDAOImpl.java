package com.geon.im.DAOImpl;

import javax.servlet.http.HttpServletRequest;

import com.geon.im.DAO.BoardDAO;
import com.geon.im.Domain.imboard;

import boardfunction.boardfunction;


public class BoardDAOImpl implements BoardDAO {
	
	boardfunction bf = new boardfunction();
	
	public int postCount() {
		return bf.postCount();
	}
	
	public void loadBoardlist(HttpServletRequest request, imboard imb, int postcount) {
		bf.loadBoardlist(request, imb, postcount);
	}
	
	public void regPost(imboard imb) {
		bf.regPost(imb);
	}
	
	public void loadPost(HttpServletRequest request, imboard imb) {
		bf.loadPost(request, imb);
	}
	
	public boolean PasswordCheck(imboard imb) {
		return bf.PasswordCheck(imb);
	}
	
	public void modifyPost(imboard imb) {
		bf.modifyPost(imb);
	}
	
	public void deletePost(imboard imb) {
		bf.deletePost(imb);
	}

}
