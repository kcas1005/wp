create table login_entity(
 id number,
 name nvarchar2(100),
 password nvarchar2(100)
 );

 create sequence login_id_seq;

insert into login_entity values(login_id_seq.nextVal,'park1','11234');
insert into login_entity values(login_id_seq.nextVal,'park2','21234');
insert into login_entity values(login_id_seq.nextVal,'park3','31234');
commit;
select * from login_entity;


drop table comment_entity;
create table comment_entity(
    id number,
    login_entity_id number,
    name nvarchar2(200),
    contents nvarchar2(1000)
);

create sequence comment_id_seq;

select * from login_entity;
insert into comment_entity values(comment_id_seq.nextval,1,'p1','코멘트');
commit;

select * from comment_entity;

