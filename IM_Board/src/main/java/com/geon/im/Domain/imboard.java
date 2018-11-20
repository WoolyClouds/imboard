package com.geon.im.Domain;

public class imboard {
		
	private int postnum=1;
	private String writer="";
	private String password="";
	private String subject="";	
	private String content="";
	private String regdate="";
	private int curpage=1;
	
	public imboard() {
	}

	public imboard(int postnum, String writer, String password, String subject, String content, String regdate,
			int curpage) {
		super();
		this.postnum = postnum;
		this.writer = writer;
		this.password = password;
		this.subject = subject;
		this.content = content;
		this.regdate = regdate;
		this.curpage = curpage;
	}

	public int getPostnum() {
		return postnum;
	}

	public void setPostnum(int postnum) {
		this.postnum = postnum;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public int getCurpage() {
		return curpage;
	}

	public void setCurpage(int curpage) {
		this.curpage = curpage;
	}

}
