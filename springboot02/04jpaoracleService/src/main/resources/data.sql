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
