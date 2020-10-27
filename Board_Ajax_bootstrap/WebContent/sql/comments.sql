drop table comments;

create table comments(
 num  number primary key,
 id  varchar2(30) references member(id),
 content varchar2(200),
 reg_date date,
 board_re_ref number references board(board_num) on delete cascade
);
--원문글 삭제 하면 이 원문글을 참조하는 댓글도 삭제됩니다.--

--시퀀스를 생성합니다.
create sequence com_seq;

drop table commebts;
delete COMMENTS
drop sequence com_seq;
select * from comments;