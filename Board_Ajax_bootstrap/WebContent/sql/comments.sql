drop table comments;

create table comments(
 num  number primary key,
 id  varchar2(30) references member(id),
 content varchar2(200),
 reg_date date,
 board_re_ref number references board(board_num) on delete cascade
);
--������ ���� �ϸ� �� �������� �����ϴ� ��۵� �����˴ϴ�.--

--�������� �����մϴ�.
create sequence com_seq;

drop table commebts;
delete COMMENTS
drop sequence com_seq;
select * from comments;