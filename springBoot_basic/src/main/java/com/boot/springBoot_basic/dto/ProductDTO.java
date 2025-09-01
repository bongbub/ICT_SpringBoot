package com.boot.springBoot_basic.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

// JSP가 추가되어야 @Entity, @Table import가 가능하다
// javax.persistence <-- 사용\

// 롬복 추가된 DTO


@Data					// getter setter 생략 가능 (lombok)
@AllArgsConstructor		// 매개변수 생성자 대체
@NoArgsConstructor		// 디폴트 생성자 대신
@ToString				// ToString() 대신 사용
@Builder				// 
@Entity					// ROM(table의 컬럼과 Object 의 멤버변수를 매핑)
@Table(name="mvc_product_tbl")
public class ProductDTO {
	
	@Id               // pk
	private int id;
	private String name;
	private String brand;
	private String madein;
	private int price;
	
	
	
	
}
