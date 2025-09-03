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
@Table(name="mvc_board_tbl")
public class BoardDTO {
	
	@Id
	private int b_num;				// 글번호
	private String b_title;			// 글제목
	private String b_content;		// 글내용
	private String b_writer;		// 작성자
	private String b_password;		// 수정, 삭제용 비밀번호
	private int b_readCnt;			// 조회수
	private Date b_regDate;			// 작성일		-- Date 는 sql걸로 import
	private int b_comment_count;	// 댓글수
	

}
