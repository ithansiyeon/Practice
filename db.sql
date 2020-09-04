create table tblBoard(
    seq number Primary key,
    title varchar2(1000) not null,
    content varchar2(2000) not null,
    regdate date default sysdate not null,
    readcount number default 0 not null
);
ALTER TABLE tblBoard ADD name varchar2(30);

create sequence seqBoard;

insert into tblBoard values(seqBoard.nextVal,'안녕하세요?','반가워요',default,default);
insert into tblBoard values(seqBoard.nextVal,'코딩은 재미있어?','웅웅',default,default);
insert into tblBoard values(seqBoard.nextVal,'키보드를 새로 샀어용','이쁘다',default,default);
insert into tblBoard values(seqBoard.nextVal,'오늘이 몇요일 인가요?','수요일입니다.',default,default);
insert into tblBoard values(seqBoard.nextVal,'안녕하세요?','반가워요',default,default);
insert into tblBoard values(seqBoard.nextVal,'코딩은 재미있어?','웅웅',default,default);
insert into tblBoard values(seqBoard.nextVal,'키보드를 새로 샀어용','이쁘다',default,default);
insert into tblBoard values(seqBoard.nextVal,'오늘이 몇요일 인가요?','수요일입니다.',default,default);
insert into tblBoard values(seqBoard.nextVal,'안녕하세요?','반가워요',default,default);
insert into tblBoard values(seqBoard.nextVal,'코딩은 재미있어?','웅웅',default,default);
insert into tblBoard values(seqBoard.nextVal,'키보드를 새로 샀어용','이쁘다',default,default);
insert into tblBoard values(seqBoard.nextVal,'오늘이 몇요일 인가요?','수요일입니다.',default,default);

update tblboard set name = '홍길동';
select * from tblboard;
delete tblboard where seq = 13;
commit;
select * from vwboard;
commit;
select b.* from (select a.*, rownum as rnum from (select * from tblboard order by seq desc) a) b;

create or replace view vwBoard
as 
select c.*,(sysdate - regdate)*24 as gap from tblboard c order by seq desc;

select b.* from (select a.*, rownum as rnum from (select * from vwboard order by seq desc) a) b;
select b.* from (select a.*, rownum as rnum from (select * from vwboard order by seq desc) a) b where rnum >= 1 and rnum <= 10;
delete tblboard where seq = 21;
commit;