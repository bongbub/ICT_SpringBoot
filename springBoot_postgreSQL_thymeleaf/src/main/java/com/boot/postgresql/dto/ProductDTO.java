package com.boot.postgresql.dto;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Builder				// 매개변수 생성자에, 순서를 맞춰서 넣어주지 않아도 마지막에 build()를 작동. 즉 같은 타입의 다른 변수의 값을 서로 바꿔넣는 것을 방지한다.
@Entity					// ROM(table의 컬럼과 Object 의 멤버변수를 매핑)
@Table(name="mvc_product_tbl")
public class ProductDTO {
	
	// PK(id) 를 AUTO_INCREMENT 즉, postgreSQL에서 SERIAL로 생성한 경우이며, 생략시 키 중복 오류 발생
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id               // pk
	private int id;
	private String name;
	private String brand;
	private String madein;
	private int price;
	
	
}

