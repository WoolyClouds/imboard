/** 요구사항
FrameWork : Spring Framework 3.0 이상
WAS : Tomcat 7 이상 또는 Spring Boot
 
게시판 제작. 단, 게시글을 DB가 아닌 FILE로 관리
 
* 요구 기능 
1) 게시문 작성
2) 게시문 삭제
3) 게시문 변경
4) 게시글 목록
5) 게시글 읽기
*/
package com.geon.im.Controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.geon.im.Domain.imboard;
import com.geon.im.Service.BoardService;
import com.geon.im.ServiceImpl.BoardServiceImpl;
import com.geon.im.util.pagination;

@Controller("BoardController")
public class BoardController {
	
	private static ModelAndView mav = new ModelAndView();
	
	BoardService bs = new BoardServiceImpl();
	
	// 첫 접근
	@RequestMapping("/")
	public ModelAndView board() {
		try {
		File imboard = new File("C:\\imboard");
			if (!imboard.exists()) {	// C:\imboard 폴더가 없을 시 생성
				imboard.mkdirs();
				File flist = new File("C:\\imboard\\list.txt");	
				FileWriter fw = new FileWriter(flist);
				fw.write("");
				fw.flush();
				fw.close();
			}	
		}catch (IOException e) {
			e.printStackTrace();
		}
		mav.setViewName("redirect:boardlist.go");	// 들어올 시 boardlist.go로 보냄
		return mav;
	}
	
	// 게시판 목록 보기
	@RequestMapping("boardlist.go")
	public ModelAndView boardList(HttpServletRequest request, imboard imb) {
		pagination paging = new pagination();	
		if(request.getParameter("curpage") == null) paging.setPageNo(1);	// 첫 진입했을 시, 현재 페이지는 1
		else paging.setPageNo(Integer.parseInt(request.getParameter("curpage").toString()));	// 현재 페이지를 받아온다.
	    paging.setPageSize(5);	// 한번에 출력할 목록은 5개
	    paging.setTotalCount(bs.postCount());	// 총 게시글 수
	    request.setAttribute("paging", paging);

		bs.loadBoardlist(request, imb, bs.postCount());
		mav.setViewName("board_list");
		return mav;
	}
	
	// 게시글 쓰기 폼
	@RequestMapping("postWrite.go")
	public ModelAndView postWriteForm() {
		mav.setViewName("post_write");
		return mav;
	}	
	
	// 게시글 쓰기
	@RequestMapping(value="postWrite.do", method=RequestMethod.POST)
	public ModelAndView postWriteDo(HttpServletRequest request, imboard imb) {
		bs.regPost(imb);
		mav.setViewName("redirect:boardlist.go");
		return mav;
	}
	
	// 게시글 폼
	@RequestMapping("boardpost.go")
	public ModelAndView boardPostForm(HttpServletRequest request, imboard imb) {
		bs.loadPost(request, imb);
		mav.setViewName("board_post");
		return mav;
	}	

	// 게시글 수정을 위한 비밀번호 체크 폼
	@RequestMapping("postModifyPWCheck.go")
	public ModelAndView boardPostModifyPWCheckGo(HttpServletRequest request, imboard imb){
		request.setAttribute("postnum", imb.getPostnum());
		mav.setViewName("post_modify_pwcheck");
		return mav;
	}

	// 게시글 수정 폼
	@RequestMapping("postModify.go")
	public ModelAndView boardPostModify(HttpServletRequest request, HttpServletResponse response, imboard imb) throws Exception{
		if(bs.PasswordCheck(imb) == true) {
			bs.loadPost(request, imb);
			mav.setViewName("post_modify");
		}else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('비밀번호가 일치하지 않습니다.'); location.href='boardpost.go?postnum="+imb.getPostnum()+"';</script>");
			out.flush();				
			return null;
		}
		
		return mav;
	}

	// 게시글 수정 
	@RequestMapping("postModify.do")
	public ModelAndView boardPostModifyDo(HttpServletRequest request, HttpServletResponse response, imboard imb) throws Exception {
		bs.modifyPost(imb);
		bs.loadPost(request, imb);
		mav.setViewName("redirect:boardpost.go?postnum="+imb.getPostnum());
		return mav;
	}
	
	// 게시글 삭제를 위한 비밀번호 체크 폼
	@RequestMapping("postDeletePWCheck.go")
	public ModelAndView boardPostDeletePWCheckGo(HttpServletRequest request, imboard imb){
		request.setAttribute("postnum", imb.getPostnum());
		mav.setViewName("post_delete_pwcheck");
		return mav;
	}

	// 게시글 삭제
	@RequestMapping("postDelete.do")
	public ModelAndView boardPostDeleteDo(HttpServletResponse response, imboard imb) throws Exception  {
		if(bs.PasswordCheck(imb) == true) {
			bs.deletePost(imb);
		}else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('비밀번호가 일치하지 않습니다.'); location.href='boardpost.go?postnum="+imb.getPostnum()+"';</script>");
			out.flush();				
			return null;
		}
		mav.setViewName("redirect:boardlist.go");
		return mav;
	}
	
	
}
