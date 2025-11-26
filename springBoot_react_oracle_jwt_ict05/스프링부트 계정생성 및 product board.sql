
-- SPRINGBOOT_ICT05 / tiger
DROP TABLE react_login_tbl CASCADE CONSTRAINTS;
CREATE TABLE react_login_tbl (
   -- login     VARCHAR2(100)  PRIMARY KEY,
    id          VARCHAR2(100)  PRIMARY KEY,
    password    VARCHAR2(100) NOT NULL,
    first_name  VARCHAR2(100) NOT NULL,
    last_name   VARCHAR2(100) NOT NULL,
    token       VARCHAR2(500)
);

SELECT * FROM react_login_tbl;



