-- 1. 계정생성 : system 계정생성
-- create user <계정이름> identified by <계정암호> default tablespace users;
create user springboot_ict05 identified by tiger default tablespace users;

-- 2. 사용자 권한 부여
-- grant [시스템 권한] to [계정];
grant connect, resource, create view to springboot_ict05;


-- 3. 락 해제
-- alter user <계정이름> account unlock;
alter user springboot_ict05 account unlock;

-- 4. 계정 잘못만든 경우 계정, 객체 삭제하기 
-- drop user <계정이름> cascade;
-- drop user mybatis_ict05 cascade; 

-- 5. 패스워드 변경
-- alter user <계정이름> identified by <패스워드>;
-- alter user mybatis_ict05 identified by tiger; 




-------------------------------------------------------------------------------------------------
DROP TABLE mvc_product_tbl CASCADE CONSTRAINTS;
CREATE TABLE mvc_product_tbl(
   id      number(5)    PRIMARY KEY,
   name    varchar2(50) NOT NULL,
   brand   varchar2(50) NOT NULL,
   madein  varchar2(50) NOT NULL,
   price   number(9)    NOT NULL
);

SELECT * FROM MVC_PRODUCT_TBL
 ORDER BY id ASC;

INSERT INTO mvc_product_tbl(id, name, brand, madein, price)
 VALUES(1, '김치냉장고', '삼성', 'korea', 1000000); 
INSERT INTO mvc_product_tbl(id, name, brand, madein, price)
 VALUES(2, '에어컨', '휘센', 'korea', 5500000); 
INSERT INTO mvc_product_tbl(id, name, brand, madein, price)
 VALUES(3, '트롬세탁기', 'LG', 'korea', 1200000); 
COMMIT; 