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



-- 시퀀스 생성(부트에서 product Insert시, id 자동 증가)
CREATE SEQUENCE PRODUCT_ID_SEQ			-- 시퀀스 명은 ProductDTO @SequenceGenerat
START WITH 1 NOCACHE ORDER;

DROP SEQUENCE PRODUCT_ID_SEQ

DELETE FROM mvc_product_tbl;


-----------------------------------------------------------------
-- [ 게시판 테이블 ]
DROP TABLE mvc_board_tbl CASCADE CONSTRAINTS;
CREATE TABLE mvc_board_tbl(
   b_num      number(7) PRIMARY KEY,   -- 글번호
   b_title      varchar2(50) NOT NULL,   -- 글제목
   b_content    clob NOT NULL,         -- 글내용
   b_writer      varchar2(30) NOT NULL,   -- 작성자
   b_password  varchar2(30) NOT NULL,   -- 수정,삭제용 비밀번호
   b_readCnt   number(6)   DEFAULT 0,   -- 조회수
   b_regDate   DATE    DEFAULT sysdate, -- 작성일
   b_comment_count   number(6) DEFAULT 0   -- 댓글갯수
);

-- 삭제시 b_show 컬럼을 'N'으로 수정, 리스트에서 b_show 컬럼이 'Y'인 것만 조회
ALTER TABLE mvc_board_tbl
  ADD b_show VARCHAR2(1) DEFAULT 'Y';


SELECT * FROM mvc_board_tbl 
 ORDER BY b_num;

SELECT * FROM mvc_board_tbl 
 WHERE b_num = 990;

-- 게시글 입력(다건) => DECLARE~END;까지 블록잡아서 실행
DECLARE   -- 선언문
   i    NUMBER := 1;    -- 변수 i에 1을 대입
BEGIN
   WHILE i<=991 LOOP
      INSERT INTO    mvc_board_tbl(b_num, b_title, b_content, b_writer, b_password, b_readCnt, b_regDate, b_comment_count)   
      VALUES(i, '글제목'||i, '글내용'||i, '작성자'||i, 1234, 0, sysdate, 0);
      i := i+1;
   END LOOP;   
END;   
COMMIT;

SELECT COUNT(*) AS cnt FROM mvc_board_tbl;    -- 991

-- 게시글 목록
SELECT *
  FROM 
     (SELECT A.*
    , rownum AS rn
      FROM 
          (SELECT * FROM mvc_board_tbl
           ORDER BY b_num DESC) A
    ) 
 WHERE rn BETWEEN 21 AND 30;  -- 1p => rn 1~10

-- 조회수
UPDATE mvc_board_tbl 
   SET b_readCnt = b_readCnt + 1
 WHERE b_num = 991; 
COMMIT;

-- 게시글 상세페이지
SELECT * FROM mvc_board_tbl
 WHERE b_num = 991;


-- 게시글 수정삭제 버튼 클릭시 - 비밀번호 인증처리
SELECT count(*) FROM mvc_board_tbl
 WHERE b_num = 991
   AND b_password = 1234;

-- 게시글 수정
UPDATE mvc_board_tbl
   SET b_password = '12345'
     , b_title = '글제목991_u'
     , b_content = '글내용991_u'
 WHERE b_num = 991;    
COMMIT;   

-- 게시글 삭제
DELETE FROM mvc_board_tbl
WHERE b_num = 991; 


----------- [ 댓글 ] ------------------------------

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
 VALUES((SELECT NVL(MAX(c_comment_num)+1, 1) FROM mvc_comment_tbl), 990, '작성자1', '글내용1', sysdate);
COMMIT;  

-- 댓글 목록
SELECT *
  FROM 
     (SELECT A.*
    , rownum AS rn
      FROM 
          (SELECT * 
             FROM mvc_comment_tbl c, 
                  mvc_board_tbl b
            WHERE b.b_num = c.c_board_num      
              AND b_num = 990
           ORDER BY c_comment_num DESC) A
    ) 
 ORDER BY rn DESC;  