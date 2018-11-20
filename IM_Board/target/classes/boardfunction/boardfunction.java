package boardfunction;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.geon.im.Domain.imboard;

public class boardfunction {
	
	// 게시글 개수
	public int postCount() {
		int postcount = 0;
		String list="";	// 게시글 목록 정보
		File f = new File("C:\\imboard\\list.txt");	// 게시글 리스트
		try {
			BufferedReader br = new BufferedReader(new FileReader(f));
			while((list = br.readLine()) != null) {
				postcount++;
			}
			br.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return postcount;
	}
	
	// 게시판 목록불러오기
	public void loadBoardlist(HttpServletRequest request, imboard imb, int postcount) {
		String list="";	// 게시글 목록 정보
		String[] postarr = new String[5]; // 각 게시글 정보 담을 배열
		List postnum = new ArrayList();
		List subject = new ArrayList();
		List writer = new ArrayList();
		List regdate = new ArrayList();
		
		try {
			File f = new File("C:\\imboard\\list.txt");	// 게시글 리스트
			BufferedReader br = new BufferedReader(new FileReader(f));
			try {
				// 한 줄 씩 \t 기준으로 나눠 배열에 담아 각각 리스트에 담는다
				while((list = br.readLine()) != null) {
					postarr = list.split("\t");
					if((postcount-(5*imb.getCurpage()) < Integer.parseInt(postarr[0])) && (Integer.parseInt(postarr[0]) <= postcount-((5*imb.getCurpage())-5)) ) {
						postnum.add(postarr[0]);
						subject.add(postarr[3]);
						writer.add(postarr[1]);
						regdate.add(postarr[4]);
					}
				}	
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		//request.setAttribute("maxpage", (int) Math.ceil((double)postcount/5) );
		request.setAttribute("postnum", postnum);
		request.setAttribute("subject", subject);
		request.setAttribute("writer", writer);
		request.setAttribute("regdate", regdate);
	}
	
	// 마지막 게시글 번호
	public int lastPostnum() {
		String lastpost="";	// 마지막 게시글 정보
		String[] lastpostarr = new String[5];	// 마지막 게시글 정보 배열
		int lastpostnum =0;	// 마지막 게시글 번호를 담을 변수
		
		try {
			File f = new File("C:\\imboard\\list.txt");	// 게시글 리스트
			if(f.length()==0) {	// 게시글이 하나도 없을 때, 글 번호 0으로 반환 
				return 0;
			}else {
				BufferedReader br = new BufferedReader(new FileReader(f));
				try {
					lastpost = br.readLine();
					lastpostarr = lastpost.split("\t");
					lastpostnum = Integer.parseInt(lastpostarr[0]);
				
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		
		return lastpostnum;
	}

	// 게시글 등록
	public void regPost(imboard imb) {
		imb.setPostnum(lastPostnum() + 1); // 게시글고유번호 마지막 게시글 번호에 1을 더해 저장
		
		File flist = new File("C:\\imboard\\list.txt"); // 글번호, 작성자, 글비밀번호, 글제목, 글등록일을 저장
		File ftemp = new File("C:\\imboard\\temp.txt"); // 글 목록을 역순으로 저장하기 위한 임시 저장소
		File fcontent = new File("C:\\imboard\\" + imb.getPostnum() + ".txt"); // 파일명을 글번호로 저장하여 글 번호에 맞게 내용 저장
		String str = "";
		BufferedReader br = null;
		
		try {
			// 리스트를 역순으로 목록화 하기위한 임시 공간에 원본 리스트 파일 복사
			br = new BufferedReader(new FileReader(flist));
			FileWriter ft = new FileWriter(ftemp);
			while((str = br.readLine()) != null) {
				ft.write(str+"\r\n");
			}
			ft.flush();
			ft.close();
			br.close();
			
			// 글번호, 작성자, 글비밀번호, 글제목, 글등록일 저장
			FileWriter fw1 = new FileWriter(flist);
			fw1.write(imb.getPostnum() + "\t" + imb.getWriter() + "\t" + imb.getPassword() + "\t" + imb.getSubject() + "\t" + imb.getRegdate());	// 작성한 게시글 리스트에 추가
			fw1.flush();
			fw1.close();
			
			// 리스트를 역순으로 목록화 하기위한 원본 리스트 파일에 임시파일 이어 붙이기
			br = new BufferedReader(new FileReader(ftemp));
			FileWriter fl = new FileWriter(flist, true);
			while((str = br.readLine()) != null) {
				fl.write("\r\n"+str);
			}			
			fl.flush();
			fl.close();
			br.close();
			
			// 글내용 저장
			FileWriter fw2 = new FileWriter(fcontent);
			fw2.write(imb.getContent());
			fw2.flush();
			fw2.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 게시글 불러오기
	public void loadPost(HttpServletRequest request, imboard imb) {
		BufferedReader br = null;
		String str="";
		String post="";	// 게시글	
		String content="";	// 게시글 내용 담을 변수
		String[] postarr = new String[5];	// 게시글 정보 배열
		File flist = new File("C:\\imboard\\list.txt");	// 게시글 목록
		File fcontent = new File("C:\\imboard\\"+imb.getPostnum()+".txt");	// 선택한 게시글 내용
		try {
			br = new BufferedReader(new FileReader(flist));
			try {
				while((post = br.readLine()) != null) {
					postarr = post.split("\t");
					if(Integer.parseInt(postarr[0]) == imb.getPostnum()) {
						request.setAttribute("postnum", postarr[0]);
						request.setAttribute("writer", postarr[1]);
						request.setAttribute("regdate", postarr[4]);
						request.setAttribute("subject", postarr[3]);
						request.setAttribute("writer", postarr[1]);
						
						br = new BufferedReader(new FileReader(fcontent));
						while((str = br.readLine()) != null) {
							content+=str;
							content+="\n";
						}
						request.setAttribute("content", content);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	// 게시글 수정, 삭제 시 비밀번호 확인
	public boolean PasswordCheck(imboard imb) {
		File flist = new File("C:\\imboard\\list.txt");	// 게시글 목록
		String post="";	// 게시글
		String[] postarr = new String[5];	// 게시글 정보 배열
		try {
			BufferedReader br = new BufferedReader(new FileReader(flist));
			while((post = br.readLine()) != null) {
				postarr = post.split("\t");
				int postnum = Integer.parseInt(postarr[0]);
				if(postnum == imb.getPostnum()) {
					if(postarr[2].equals(imb.getPassword())) 	return true;
					else return false;
				}
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	// 게시글 수정
	public void modifyPost(imboard imb) {
		BufferedReader br = null;
		File flist = new File("C:\\imboard\\list.txt");	// 게시글 목록
		File ftemp = new File("C:\\imboard\\temp.txt");	// 임시 저장소
		File fcontent = new File("C:\\imboard\\"+imb.getPostnum()+".txt");	// 게시글 내용
		String post="";	// 게시글
		String[] postarr = new String[5];	// 게시글 정보 배열
		
		try {
			// 수정할 게시글 라인만 갱신하기 위한 게시글 리스트 복사
			br = new BufferedReader(new FileReader(flist));
			FileWriter ft = new FileWriter(ftemp);
			while((post = br.readLine()) != null) {
				ft.write(post+"\r\n");
			}
			ft.flush();
			ft.close();
			br.close();
			
			br = new BufferedReader(new FileReader(ftemp));
			FileWriter fw = new FileWriter(flist);
			while((post = br.readLine()) != null) {	
				postarr = post.split("\t");
				if(Integer.parseInt(postarr[0]) == imb.getPostnum()) {	// 수정할 게시글 라인만 갱신
					fw.write(imb.getPostnum()+"\t"+imb.getWriter()+"\t"+imb.getPassword()+"\t"+imb.getSubject()+"\t"+imb.getRegdate()+"\r\n");
				}else {
					System.out.println(post);
					fw.write(post+"\r\n"); // 수정할 게시글을 제외하고 한 줄씩 덮어쓰기					
				}
			}
			fw.flush();
			fw.close();
			br.close();
			
			FileWriter fwc = new FileWriter(fcontent);
			fwc.write(imb.getContent());	// 게시글 내용 갱신
			fwc.flush();
			fwc.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
	}

	// 게시물 삭제
	public void deletePost(imboard imb) {
		BufferedReader br = null;
		File flist = new File("C:\\imboard\\list.txt");	// 게시글 목록
		File ftemp = new File("C:\\imboard\\temp.txt");	// 임시 저장소
		File fcontent = new File("C:\\imboard\\"+imb.getPostnum()+".txt");	// 게시글 내용
		String post="";	// 게시글
		String[] postarr = new String[5];	// 게시글 정보 배열
		try {
			// 삭제할 게시글을 빼고 갱신하기 위한 게시글 리스트 복사
			br = new BufferedReader(new FileReader(flist));
			FileWriter ft = new FileWriter(ftemp);
			while((post = br.readLine()) != null) {
				ft.write(post+"\r\n");
			}
			ft.flush();
			ft.close();
			br.close();
			
			br = new BufferedReader(new FileReader(ftemp));
			FileWriter fw = new FileWriter(flist);
			while((post = br.readLine()) != null) {	
				postarr = post.split("\t");
				if(Integer.parseInt(postarr[0]) != imb.getPostnum()) { 
					fw.write(post+"\r\n");	// 삭제할 게시글을 제외하고 한 줄씩 덮어쓰기
				}
			}
			fw.flush();
			fw.close();
			br.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		if(fcontent.exists()){	// 게시글 내용 파일 삭제
            if(fcontent.delete()) 	System.out.println("파일삭제 성공");
            else System.out.println("파일삭제 실패");
        }
	}
	
}
