package com.boot.springBoot_basic.dto;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;



@Data					
@AllArgsConstructor		
@NoArgsConstructor		
@ToString				
@Builder				
@Entity					
@Table(name="mvc_comment_tbl")
public class BoardCommentDTO {
	
	@Id
	private int c_comment_num;		// PK, 일련번호
	private int c_board_num;		// FK, 게시글 번호
	private String c_writer;		// 작성자
	private String c_content;		// 댓글 내용
	private Date c_regDate;			// 등록일
	
	
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
