package com.boot.thymeleaf.dto;


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

// JSP가 추가되어야 @Entity, @Table import가 가능하다
// javax.persistence <-- 사용\

// 롬복 추가된 DTO


@Data					// getter setter 생략 가능 (lombok)
@AllArgsConstructor		// 매개변수 생성자 대체
@NoArgsConstructor		// 디폴트 생성자 대신
@ToString				// ToString() 대신 사용
@Builder				// 매개변수 생성자에, 순서를 맞춰서 넣어주지 않아도 마지막에 build()를 작동. 즉 같은 타입의 다른 변수의 값을 서로 바꿔넣는 것을 방지한다.
@Entity					// ROM(table의 컬럼과 Object 의 멤버변수를 매핑)
@Table(name="mvc_product_tbl")
public class ProductDTO {
	
	// 시퀀스를 주엇다면 아래 두 줄의 시퀀스문을 추가해야함
	@SequenceGenerator(schema="springboot_ict05", name="PRODUCT_ID_SEQ", sequenceName="PRODUCT_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PRODUCT_ID_SEQ")
	@Id               // pk
	private int id;
	private String name;
	private String brand;
	private String madein;
	private int price;
	
	
}

// 1. 시퀀스를 먼저 생성(부트에서 product INSERT시 id 자동증가) 후
// DB에서 아래의 시퀀스를 먼저 생성 후,  ProductDTO에서 전략을 추가한다.
//CREATE SEQUENCE PRODUCT_SEQ			-- 시퀀스 명은 ProductDTO @SequenceGenerator에서 선언한 sequenceName과 일치
//START WITH 1 NOCACHE ORDER;
// 2. ProductDTO에 전략추가
// 3. 기존 데이터 삭제 (pk가 겹치는 경우 UNIQUE 에러 방지) DELETE FROM mvc_product_tbl;
// 4. 기존 데이터때문에 시퀀스 번호가 1번부터가 아닌 경우, 시퀀스를 드롭하고 (DROP SEQUENCE 시퀀스명) 다시 CREATE 한다.
