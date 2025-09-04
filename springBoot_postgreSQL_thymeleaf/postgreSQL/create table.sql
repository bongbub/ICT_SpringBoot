CREATE TABLE mvc_product_tbl(
	id SERIAL PRIMARY KEY,
	name varchar(50) NOT NULL,
	brand varchar(50) NOT NULL,
	madein varchar(50),
	price INTEGER
);

insert into mvc_product_tbl(name, brand, madein, price)
values('베지밀고단백검은콩두유', '정식품', '한국', 1500)

insert into mvc_product_tbl(name, brand, madein, price)
values('아침햇살', '웅진', '한국', 2100)

commit;

select * from mvc_product_tbl;
