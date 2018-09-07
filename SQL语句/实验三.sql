
use Management_of_dorm
create table Buliding								--宿舍楼实体
(
	Bno CHAR(5) PRIMARY KEY,						--宿舍楼号，主值
	Bnum Int DEFAULT(6)								--宿舍楼层
)
create table Dorm									--宿舍实体
(	
	Dno CHAR(10) ,									--宿舍号
	Dtype CHAR(10)	CHECK(Dtype IN ('男','女') ),	--宿舍类型													
	Dmaxnum Int DEFAULT(4)	,						--宿舍上限人数
	PRIMARY KEY(Dno,Bno),							
	Bno  CHAR(5),
	FOREIGN KEY(Bno) REFERENCES Buliding(Bno)						
)
create table Student								--学生实体
(
	Sno CHAR(10) PRIMARY KEY,						--学号，主值
	Sname CHAR(20)  NOT NULL,						--姓名，非空
	Ssex CHAR(2)	CHECK(Ssex IN ('男','女') ),	--性别	
	Sbirthday DATE,									--出生年月
	Sdept CHAR(20)	NOT NULL,						--所在系
	Sgrade Int,										--年级
	Spw CHAR(20),
	Dno	 CHAR(10),									
	Bno  CHAR(5),
	FOREIGN KEY(Dno,Bno) REFERENCES Dorm(Dno,Bno),				
)
create table Goods									--物品实体
(
	Gno CHAR(5) PRIMARY KEY,						--物品号
	Gname Char(20) NOT NULL,						--物品名
)
create table Repair									--报修表
(
	Dno	 CHAR(10),
	Gno CHAR(5),
	Bno  CHAR(5),
	Rsubmit Date NOT NULL,							--报修时间
	Rreason Char(50)NOT NULL,						--报修原因
	Rstate Char(10) CHECK(Rstate IN ('完成','尚未')),
	PRIMARY KEY(Dno,Gno,Bno),						--主码两个，必须作为表级完整性定义
	FOREIGN KEY(Gno) REFERENCES Goods(Gno),
	FOREIGN KEY(Dno,Bno) REFERENCES Dorm(Dno,Bno),	
)
create table  Administrator							--管理员实体表
(
	Ano Char(20) PRIMARY KEY,						--工号
	Aname Char(10) NOT NULL,						--管理员名字
	Atel Char(20),									--联系方式
	Bno	 Char(5),
	Apw Char(20),
	FOREIGN KEY(Bno) REFERENCES Buliding(Bno),
)
create table LR										--离校表
(
	Dno	 CHAR(10),
	Sno CHAR(10),
	Bno  CHAR(5),
	Leave Date NOT NULL,							--离校时间
	Lreturn Date NOT NULL,							--返校时间
	PRIMARY KEY(Dno,Sno,Bno),				
	FOREIGN KEY(Dno,Bno) REFERENCES Dorm(Dno,Bno),
	FOREIGN KEY(Sno) REFERENCES Student(Sno)
)


insert into Buliding(Bno,Bnum) values ('32',6)
insert into Buliding(Bno,Bnum) values ('33',6)
insert into Buliding(Bno,Bnum) values ('34',7)
insert into Buliding(Bno,Bnum) values ('35',5)
insert into Dorm(Dno,Dtype,Dmaxnum,Bno) values('108','男','6','32')
insert into Dorm(Dno,Dtype,Dmaxnum,Bno) values('107','男','6','32')
insert into Dorm(Dno,Dtype,Dmaxnum,Bno) values('201','男','4','32')
insert into Dorm(Dno,Dtype,Dmaxnum,Bno) values('108','女','4','33')
insert into Dorm(Dno,Dtype,Dmaxnum,Bno) values('110','女','4','35')
insert into Student(Sno,Sname,Ssex,Sbirthday,Sgrade,Sdept,Dno,Bno,Spw) values('031602601','蔡莫某','男','1998-11-03',01,'计算机科学与技术','107','32','123456')
insert into Student(Sno,Sname,Ssex,Sbirthday,Sgrade,Sdept,Dno,Bno,Spw) values('031602602','陈莫某','男','1998-11-04',02,'计算机科学与技术','107','32','123456')
insert into Student(Sno,Sname,Ssex,Sbirthday,Sgrade,Sdept,Dno,Bno,Spw) values('031602603','方莫某','男','1998-11-05',01,'数学','201','32','123456')
insert into Student(Sno,Sname,Ssex,Sbirthday,Sgrade,Sdept,Dno,Bno,Spw)values('031602604','丁莫某','女','1998-11-06',01,'建筑','110','35','123456')
insert into Student(Sno,Sname,Ssex,Sbirthday,Sgrade,Sdept,Dno,Bno,Spw)values('031602605','吴莫某','女','1998-11-07',01,'计算机科学与技术','110','35','123456')
insert into Goods(Gno,Gname)values('001','门')
insert into Goods(Gno,Gname)values('002','风扇')
insert into Goods(Gno,Gname)values('003','水管')
insert into Goods(Gno,Gname)values('004','桌子')
insert into Goods(Gno,Gname)values('005','空调')
insert into Repair(Dno,Gno,Bno,Rsubmit,Rreason,Rstate)values('107','001','32','2018/05/20','bad','完成')
insert into Repair(Dno,Gno,Bno,Rsubmit,Rreason,Rstate)values('108','005','33','2018/05/21','bad','完成')
insert into Repair(Dno,Gno,Bno,Rsubmit,Rreason,Rstate)values('201','004','32','2018/05/22','bad','尚未')
insert into LR(Sno,Dno,Bno,Leave,Lreturn)values('031602601','107','32','2018/05/20','2018/05/21')
insert into LR(Sno,Dno,Bno,Leave,Lreturn)values('031602602','107','32','2018/05/01','2018/05/07')
insert into LR(Sno,Dno,Bno,Leave,Lreturn)values('031602605','110','35','2018/05/20','2018/05/21')
insert into Administrator(Ano,Aname,Atel,Bno,Apw)values('001','柯某林','123456','32','123456')
insert into Administrator(Ano,Aname,Atel,Bno,Apw)values('002','林某','102456','33','123456')
insert into Administrator(Ano,Aname,Atel,Bno,Apw)values('003','刘某','113456','34','123456')
insert into Administrator(Ano,Aname,Atel,Bno,Apw)values('004','魏某','223456','35','123456')
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

create view St(Sno,Sname,Ssex,Sdept,Dno,Bno)			--学生信息
	AS
	select Student.Sno,Sname,Ssex,Sdept,Dorm.Bno,Dorm.Dno
	From Student,Dorm
WHERE Student.Dno=Dorm.Dno and Student.Bno=Dorm.Bno;
create view GR(Dno,Bno,Gname,Rsubmit,Rstate)			--物品报修
	AS
	select Goods.Goods.Gname,Repair.Bno,Dno,Rsubmit,Rstate
	From Goods,Repair
	WHERE  Goods.Gno=Repair.Gno 
create view Sl (Sno,Sname,Ssex,Sdept,Dno,Bno,Leave)		--学生离校
	AS
	select Student.Sno,Sname,Ssex,Sdept,LR.Dno,LR.Bno,LR.Leave
	From Student,LR
	WHERE Student.Dno=LR.Dno and Student.Bno=LR.Bno and Student.Sno=LR.Sno;
create view Sr (Sno,Sname,Ssex,Sdept,Dno,Bno,Lreturn)		--学生返校
	AS
	select Student.Sno,Sname,Ssex,Sdept,LR.Dno,LR.Bno,LR.Lreturn
	From Student,LR
  WHERE Student.Dno=LR.Dno and Student.Bno=LR.Bno and Student.Sno=LR.Sno;
create view Sstay(Sno,Sname,Ssex,Sdept,Dno,Bno)			--在校学生信息
	AS
	select Student.Sno,Sname,Ssex,Sdept,Dorm.Bno,Dorm.Dno
	From Student,Dorm
	WHERE Student.Dno=Dorm.Dno and Student.Bno=Dorm.Bno and 
		  Student.Sno not in
		  (select Sno
		   from LR);
*/
	create proc R_unsolved		--某栋宿舍楼未维修数
			@bno char(20) ,
			@r_unsolved int output
			AS
			select @r_unsolved = count(*)
			from Repair
			where Bno=@bno and Rstate='尚未'
	create proc R_unsolvedsum		--总未维修数
			@r_unsolved int output
			AS
			select @r_unsolved = count(*)
			from Repair
			where Rstate='尚未'
create trigger d1
on Dorm after delete
as select Dno,Bno,Dmaxnum from Dorm	
--创建账户
create login s1 with password='123456'
create login Admin1 with password='123456'
use Management_of_dorm
--创建用户
create user s for login  s1
create user Adminor for login  Admin1
create role stu
create role Adm
--管理者角色授权
grant create table to Adm
grant select,insert on Administrator to Adm
grant select,insert,update on Buliding to Adm
grant select,insert,delete,update on Dorm to Adm
grant select on LR to Adm
grant select,insert,delete,update on Goods to Adm
grant select,insert,delete,update on Repair to Adm
grant select,update on Student to Adm
--学生角色授权
grant select on Administrator to stu
grant select on Buliding to stu
grant select on Dorm to stu
grant select on LR to stu
grant select on Goods to stu
grant select,insert,delete,update on Repair to stu
grant select on Student to stu
--用户添加到用户定义角色中
exec sp_addrolemember 'stu','s'
exec sp_addrolemember 'Adm','Adminor'

