
use Management_of_dorm
create table Buliding								--����¥ʵ��
(
	Bno CHAR(5) PRIMARY KEY,						--����¥�ţ���ֵ
	Bnum Int DEFAULT(6)								--����¥��
)
create table Dorm									--����ʵ��
(	
	Dno CHAR(10) ,									--�����
	Dtype CHAR(10)	CHECK(Dtype IN ('��','Ů') ),	--��������													
	Dmaxnum Int DEFAULT(4)	,						--������������
	PRIMARY KEY(Dno,Bno),							
	Bno  CHAR(5),
	FOREIGN KEY(Bno) REFERENCES Buliding(Bno)						
)
create table Student								--ѧ��ʵ��
(
	Sno CHAR(10) PRIMARY KEY,						--ѧ�ţ���ֵ
	Sname CHAR(20)  NOT NULL,						--�������ǿ�
	Ssex CHAR(2)	CHECK(Ssex IN ('��','Ů') ),	--�Ա�	
	Sbirthday DATE,									--��������
	Sdept CHAR(20)	NOT NULL,						--����ϵ
	Sgrade Int,										--�꼶
	Spw CHAR(20),
	Dno	 CHAR(10),									
	Bno  CHAR(5),
	FOREIGN KEY(Dno,Bno) REFERENCES Dorm(Dno,Bno),				
)
create table Goods									--��Ʒʵ��
(
	Gno CHAR(5) PRIMARY KEY,						--��Ʒ��
	Gname Char(20) NOT NULL,						--��Ʒ��
)
create table Repair									--���ޱ�
(
	Dno	 CHAR(10),
	Gno CHAR(5),
	Bno  CHAR(5),
	Rsubmit Date NOT NULL,							--����ʱ��
	Rreason Char(50)NOT NULL,						--����ԭ��
	Rstate Char(10) CHECK(Rstate IN ('���','��δ')),
	PRIMARY KEY(Dno,Gno,Bno),						--����������������Ϊ�������Զ���
	FOREIGN KEY(Gno) REFERENCES Goods(Gno),
	FOREIGN KEY(Dno,Bno) REFERENCES Dorm(Dno,Bno),	
)
create table  Administrator							--����Աʵ���
(
	Ano Char(20) PRIMARY KEY,						--����
	Aname Char(10) NOT NULL,						--����Ա����
	Atel Char(20),									--��ϵ��ʽ
	Bno	 Char(5),
	Apw Char(20),
	FOREIGN KEY(Bno) REFERENCES Buliding(Bno),
)
create table LR										--��У��
(
	Dno	 CHAR(10),
	Sno CHAR(10),
	Bno  CHAR(5),
	Leave Date NOT NULL,							--��Уʱ��
	Lreturn Date NOT NULL,							--��Уʱ��
	PRIMARY KEY(Dno,Sno,Bno),				
	FOREIGN KEY(Dno,Bno) REFERENCES Dorm(Dno,Bno),
	FOREIGN KEY(Sno) REFERENCES Student(Sno)
)


insert into Buliding(Bno,Bnum) values ('32',6)
insert into Buliding(Bno,Bnum) values ('33',6)
insert into Buliding(Bno,Bnum) values ('34',7)
insert into Buliding(Bno,Bnum) values ('35',5)
insert into Dorm(Dno,Dtype,Dmaxnum,Bno) values('108','��','6','32')
insert into Dorm(Dno,Dtype,Dmaxnum,Bno) values('107','��','6','32')
insert into Dorm(Dno,Dtype,Dmaxnum,Bno) values('201','��','4','32')
insert into Dorm(Dno,Dtype,Dmaxnum,Bno) values('108','Ů','4','33')
insert into Dorm(Dno,Dtype,Dmaxnum,Bno) values('110','Ů','4','35')
insert into Student(Sno,Sname,Ssex,Sbirthday,Sgrade,Sdept,Dno,Bno,Spw) values('031602601','��Īĳ','��','1998-11-03',01,'�������ѧ�뼼��','107','32','123456')
insert into Student(Sno,Sname,Ssex,Sbirthday,Sgrade,Sdept,Dno,Bno,Spw) values('031602602','��Īĳ','��','1998-11-04',02,'�������ѧ�뼼��','107','32','123456')
insert into Student(Sno,Sname,Ssex,Sbirthday,Sgrade,Sdept,Dno,Bno,Spw) values('031602603','��Īĳ','��','1998-11-05',01,'��ѧ','201','32','123456')
insert into Student(Sno,Sname,Ssex,Sbirthday,Sgrade,Sdept,Dno,Bno,Spw)values('031602604','��Īĳ','Ů','1998-11-06',01,'����','110','35','123456')
insert into Student(Sno,Sname,Ssex,Sbirthday,Sgrade,Sdept,Dno,Bno,Spw)values('031602605','��Īĳ','Ů','1998-11-07',01,'�������ѧ�뼼��','110','35','123456')
insert into Goods(Gno,Gname)values('001','��')
insert into Goods(Gno,Gname)values('002','����')
insert into Goods(Gno,Gname)values('003','ˮ��')
insert into Goods(Gno,Gname)values('004','����')
insert into Goods(Gno,Gname)values('005','�յ�')
insert into Repair(Dno,Gno,Bno,Rsubmit,Rreason,Rstate)values('107','001','32','2018/05/20','bad','���')
insert into Repair(Dno,Gno,Bno,Rsubmit,Rreason,Rstate)values('108','005','33','2018/05/21','bad','���')
insert into Repair(Dno,Gno,Bno,Rsubmit,Rreason,Rstate)values('201','004','32','2018/05/22','bad','��δ')
insert into LR(Sno,Dno,Bno,Leave,Lreturn)values('031602601','107','32','2018/05/20','2018/05/21')
insert into LR(Sno,Dno,Bno,Leave,Lreturn)values('031602602','107','32','2018/05/01','2018/05/07')
insert into LR(Sno,Dno,Bno,Leave,Lreturn)values('031602605','110','35','2018/05/20','2018/05/21')
insert into Administrator(Ano,Aname,Atel,Bno,Apw)values('001','��ĳ��','123456','32','123456')
insert into Administrator(Ano,Aname,Atel,Bno,Apw)values('002','��ĳ','102456','33','123456')
insert into Administrator(Ano,Aname,Atel,Bno,Apw)values('003','��ĳ','113456','34','123456')
insert into Administrator(Ano,Aname,Atel,Bno,Apw)values('004','κĳ','223456','35','123456')
create index S1 on Student(Sname)
create index S2 on Student(Sdept)
create index D1 on Dorm(Bno)
create index G1 on Goods(Gname)
create index A1 on Administrator(Bno)
create index A2 on Administrator(Aname)
create index R1 on Repair(Rsubmit)
select * from Student
/*
go
exec sp_rename 'PK__Student__CA1FE46447991B3C','S'
exec sp_rename 'PK__Administ__C697D3634692CDBD','A'
exec sp_rename 'PK__Buliding__C6D139AA20C9A8C9','B'
exec sp_rename 'PK__Dorm__7C5D96E0C89ABB5A','D'
exec sp_rename 'PK__LR__3757AA051DE0E517','l'
exec sp_rename 'PK__Goods__D2DA6CC3D021F50A','G'
exec sp_rename 'PK__Repair__A7A7AF19FB78F860','R'

create view St(Sno,Sname,Ssex,Sdept,Dno,Bno)			--ѧ����Ϣ
	AS
	select Student.Sno,Sname,Ssex,Sdept,Dorm.Bno,Dorm.Dno
	From Student,Dorm
WHERE Student.Dno=Dorm.Dno and Student.Bno=Dorm.Bno;
create view GR(Dno,Bno,Gname,Rsubmit,Rstate)			--��Ʒ����
	AS
	select Goods.Goods.Gname,Repair.Bno,Dno,Rsubmit,Rstate
	From Goods,Repair
	WHERE  Goods.Gno=Repair.Gno 
create view Sl (Sno,Sname,Ssex,Sdept,Dno,Bno,Leave)		--ѧ����У
	AS
	select Student.Sno,Sname,Ssex,Sdept,LR.Dno,LR.Bno,LR.Leave
	From Student,LR
	WHERE Student.Dno=LR.Dno and Student.Bno=LR.Bno and Student.Sno=LR.Sno;
create view Sr (Sno,Sname,Ssex,Sdept,Dno,Bno,Lreturn)		--ѧ����У
	AS
	select Student.Sno,Sname,Ssex,Sdept,LR.Dno,LR.Bno,LR.Lreturn
	From Student,LR
  WHERE Student.Dno=LR.Dno and Student.Bno=LR.Bno and Student.Sno=LR.Sno;
create view Sstay(Sno,Sname,Ssex,Sdept,Dno,Bno)			--��Уѧ����Ϣ
	AS
	select Student.Sno,Sname,Ssex,Sdept,Dorm.Bno,Dorm.Dno
	From Student,Dorm
	WHERE Student.Dno=Dorm.Dno and Student.Bno=Dorm.Bno and 
		  Student.Sno not in
		  (select Sno
		   from LR);
*/
	create proc R_unsolved		--ĳ������¥δά����
			@bno char(20) ,
			@r_unsolved int output
			AS
			select @r_unsolved = count(*)
			from Repair
			where Bno=@bno and Rstate='��δ'
	create proc R_unsolvedsum		--��δά����
			@r_unsolved int output
			AS
			select @r_unsolved = count(*)
			from Repair
			where Rstate='��δ'
create trigger d1
on Dorm after delete
as select Dno,Bno,Dmaxnum from Dorm	
--�����˻�
create login s1 with password='123456'
create login Admin1 with password='123456'
use Management_of_dorm
--�����û�
create user s for login  s1
create user Adminor for login  Admin1
create role stu
create role Adm
--�����߽�ɫ��Ȩ
grant create table to Adm
grant select,insert on Administrator to Adm
grant select,insert,update on Buliding to Adm
grant select,insert,delete,update on Dorm to Adm
grant select on LR to Adm
grant select,insert,delete,update on Goods to Adm
grant select,insert,delete,update on Repair to Adm
grant select,update on Student to Adm
--ѧ����ɫ��Ȩ
grant select on Administrator to stu
grant select on Buliding to stu
grant select on Dorm to stu
grant select on LR to stu
grant select on Goods to stu
grant select,insert,delete,update on Repair to stu
grant select on Student to stu
--�û���ӵ��û������ɫ��
exec sp_addrolemember 'stu','s'
exec sp_addrolemember 'Adm','Adminor'

