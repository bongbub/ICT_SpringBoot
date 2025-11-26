package com.boot.react_jpa.dto;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
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
	@SequenceGenerator(schema="springboot_ict05", name="BOARD_NUM_SEQ", sequenceName="BOARD_NUM_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="BOARD_NUM_SEQ")   
	private int b_num;				// 글번호
	private String b_title;			// 글제목
	private String b_content;		// 글내용
	private String b_writer;		// 작성자
	private String b_password;		// 수정, 삭제용 비밀번호
	private int b_read_cnt;			// 조회수
	private Date b_reg_date;		// 작성일		-- Date 는 sql걸로 import
	private int b_comment_count;	// 댓글수

}
