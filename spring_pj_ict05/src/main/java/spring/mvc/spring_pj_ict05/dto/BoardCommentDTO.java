package spring.mvc.spring_pj_ict05.dto;

import java.sql.Date;

public class BoardCommentDTO {
		
	private int c_comment_num;		// PK, 일련번호
	private int c_board_num;		// FK, 게시글 번호
	private String c_writer;		// 작성자
	private String c_content;		// 댓글 내용
	private Date c_regDate;			// 등록일
	
	public BoardCommentDTO() {
		super();
	}
	
	public BoardCommentDTO(int c_comment_num, int c_board_num, String c_writer, String c_content, Date c_regDate) {
		super();
		this.c_comment_num = c_comment_num;
		this.c_board_num = c_board_num;
		this.c_writer = c_writer;
		this.c_content = c_content;
		this.c_regDate = c_regDate;
	}
	
	
	public int getC_comment_num() {
		return c_comment_num;
	}
	public void setC_comment_num(int c_comment_num) {
		this.c_comment_num = c_comment_num;
	}
	public int getC_board_num() {
		return c_board_num;
	}
	public void setC_board_num(int c_board_num) {
		this.c_board_num = c_board_num;
	}
	public String getC_writer() {
		return c_writer;
	}
	public void setC_writer(String c_writer) {
		this.c_writer = c_writer;
	}
	public String getC_content() {
		return c_content;
	}
	public void setC_content(String c_content) {
		this.c_content = c_content;
	}
	public Date getC_regDate() {
		return c_regDate;
	}
	public void setC_regDate(Date c_regDate) {
		this.c_regDate = c_regDate;
	}
	@Override
	public String toString() {
		return "BoardCommentDTO [c_comment_num=" + c_comment_num + ", c_board_num=" + c_board_num + ", c_writer="
				+ c_writer + ", c_content=" + c_content + ", c_regDate=" + c_regDate + "]";
	}
	
	
	
/*
	-- 게시판 댓글테이블
	DROP TABLE mvc_comment_tbl  CASCADE CONSTRAINTS;
	CREATE TABLE mvc_comment_tbl(  
	    c_comment_num     NUMBER(7)  PRIMARY KEY,      -- PK, 댓글 일련번호
	   c_board_num       NUMBER(7)  REFERENCES   mvc_board_tbl(b_num),   -- FK, 게시글 번호
	    c_writer          VARCHAR2(30)  NOT NULL,       -- 작성자
	    c_content         CLOB  NOT NULL,              -- 글내용
	   c_regDate         Date  DEFAULT sysdate       -- 등록일
	);

	SELECT * FROM mvc_comment_tbl;
	 -- 댓글 작성페이지
	INSERT INTO mvc_comment_tbl(c_comment_num, c_board_num, c_writer, c_content, c_regDate)
	 VALUES((SELECT NVL(MAX(c_comment_num)+1, 1) FROM mvc_comment_tbl), 991, '작성자1', '글내용1', sysdate);
	COMMIT;  
*/
}
