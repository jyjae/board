drop table board;
create table board(
BOARD_NUM NUMBER,
BOARD_NAME VARCHAR2(30),
BOARD_PASS VARCHAR2(30),
BOARD_SUBJECT VARCHAR2(300),
BOARD_CONTENT VARCHAR2(4000),
BOARD_FILE VARCHAR2(50),
BOARD_RE_REF NUMBER,
BOARD_RE_LEV NUMBER,
BOARD_RE_SEQ NUMBER,
BOARD_READCOUNT NUMBER,
BOARD_DATE DATE,
PRIMARY KEY(BOARD_NUM)
);

select * from board;
select * from member;

delete from board where BOARD_NUM=4; 

select count(*) from member where id!='admin' and id like '%Asdd%';
select * from (select rownum rnum, id, name, age, gender, email  from (select * from member where id=!'admin' and id like '%A%' order by id)) where rnum between 1 and 10;
			

delete board;

