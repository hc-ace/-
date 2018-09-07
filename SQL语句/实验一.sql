create database Student_System			--创建数据库
use Student_System
create table Student								--学生
(
	Sno CHAR(9) PRIMARY KEY,						--学号，主值
	Sname CHAR(20) UNIQUE ,							--姓名，取唯一
	Spassword CHAR(20) DEFAULT('123456'),			--密码，设置初始值
	Ssex CHAR(2)	CHECK(Ssex IN ('男','女') ),	--性别									
	Sbirthday DATE,									--出生年月
	Sdept CHAR(20)	,								--所在系
	Sclass numeric(2,0)								--所在班级，例01
)
create table Course									--课程
(
	Cno CHAR(4) PRIMARY KEY,						--课程号
	Cname CHAR(40) NOT NULL,						--课程名，非空									
	Ccredit SMALLINT,								--学分
	Cpno  CHAR(4),									
	FOREIGN KEY(Cpno) REFERENCES Course(Cno)		--一个表中的 FOREIGN KEY 指向另一个表中的 PRIMARY KEY。
)
create table Teacher								--教师
(
	Tno CHAR(9)PRIMARY KEY,							--工号
	Tname CHAR(20) NOT NULL ,						--姓名
	Tbirthday DATE,									--出生年月
	Ttitle CHAR(9),									--职称
	Tpassword CHAR(20) DEFAULT('123456'),							--密码
	Tsex CHAR(2)	CHECK(Tsex IN ('男','女') ),	--性别		
)
create table SC										--学生选课
(
	Sno CHAR(9),
	Cno CHAR(4),
	Tno CHAR(9),
	Grade SMALLINT CHECK(Grade>=0 and Grade<=100),	--学生成绩
	PRIMARY KEY(Sno,Cno),							--主码两个，必须作为表级完整性定义
	FOREIGN KEY(Tno) REFERENCES Teacher(Tno)
	ON DELETE CASCADE
	ON UPDATE CASCADE,
	FOREIGN KEY(Sno) REFERENCES Student(Sno)
	ON DELETE CASCADE
	ON UPDATE CASCADE,
	FOREIGN KEY(Cno) REFERENCES Course(Cno)
	ON DELETE CASCADE
	ON UPDATE CASCADE								--为了更新数据和删除数据都可以实现同步
)
create table TC										--老师教课
(
	Tno CHAR(9),						
	Cno CHAR(4),
	Csite CHAR(20),									--地点
	Ctime CHAR(20),									--时间
	PRIMARY KEY(Tno,Cno),							--主码两个，必须作为表级完整性定义
	FOREIGN KEY(Tno) REFERENCES Teacher(Tno)
	ON DELETE CASCADE
	ON UPDATE CASCADE,
	FOREIGN KEY(Cno) REFERENCES Course(Cno)
	ON DELETE CASCADE
	ON UPDATE CASCADE								--为了更新数据和删除数据都可以实现同步
)
ALTER TABLE SC ADD Type NCHAR(2) NULL		--添加
ALTER TABLE SC ALTER COLUMN Type NCHAR(2)	--修改
ALTER TABLE SC ADD CONSTRAINT C1 CHECK(Type IN ('必修',' 重修',' 选修'))--CONSTRAINT命名
ALTER TABLE SC DROP CONSTRAINT C1
ALTER TABLE SC DROP COLUMN Type --删除约束跟列值
--创建索引
create unique index  Course on Course(Cname)
create index Student on Student(Sclass)
insert into Student(Sno,Sname,Ssex,Sbirthday,Sclass,Sdept) values('031602601','蔡莫某','男','1998-11-03',01,'计算机科学与技术') 
insert into Student(Sno,Sname,Ssex,Sbirthday,Sclass,Sdept) values('031602401','王莫某','女','1998-06-24',04,'计算机科学与技术') 
insert into Student(Sno,Sname,Ssex,Sbirthday,Sclass,Sdept) values('031602501','赵莫某','男','1999-07-07',05,'计算机科学与技术') 
select *from Student
insert into Teacher(Tno,Tname,Tbirthday,Ttitle,Tsex) values('10001','莫某','1978-11-03','教授','男')
insert into Teacher(Tno,Tname,Tbirthday,Ttitle,Tsex) values('10002','叶某','1980-11-03','副教授','女')
insert into Teacher(Tno,Tname,Tbirthday,Ttitle,Tsex) values('10003','陈某','1987-11-03','讲师','女')

insert into Course(Cno,Cname,Ccredit,Cpno) values('001','高等数学上',4,NULL)
insert into Course(Cno,Cname,Ccredit,Cpno) values('002','线代',3,NULL)
insert into Course(Cno,Cname,Ccredit,Cpno) values('003','高等数学下',5,'003')
select *from  Course
INSERT into SC(Sno,Cno,Tno,Grade) values('031602501','001','10001',0);
INSERT into SC(Sno,Cno,Tno,Grade) values('031602501','002','10001',0);
INSERT into SC(Sno,Cno,Tno,Grade) values('031602501','003','10001',0);
UPDATE Student 
SET Sclass=6 WHERE Sno='031602601' ;
select *from Student
delete from Student where Sno ='031602501'; --删除数据
UPDATE SC SET Grade=70 WHERE Sno='031602101' ;
--单表查询
select Sno,Sname,Sdept from Student		--查询学生的学号、姓名和专业
select TOP 3 * from Student--查询学生表的前三列属性
select * from student			
order by Sdept, Sbirthday desc;--ORDER BY子句
SELECT Sno, Count(*)		--Count(*)为选课门数
FROM SC
GROUP BY Sno
HAVING COUNT(*) >=2			--用聚集函数的分组单表查询
select Student.Sno,Grade from Student,SC
where Student.Sno=SC.Sno and SC.Cno='003';--查询课程号为003的所有学生的成绩
SELECT Cno,AVG(Grade)
FROM SC GROUP BY Cno
HAVING Cno != '001';--查询课程号不为001的学生平均成绩

select Sclass,COUNT(Sno) from Student
group by Sclass --查询每个班的人数
--查询002课程中成绩大于课程平均成绩的学生及成绩
select Sname,Grade
from Student,SC
where Student.Sno=SC.Sno and Cno='002' and Grade >(select AVG(Grade) FROM SC)
--创建视图
create view St(Sno,Sname,Cname,Cno,Tname,Grade)
	AS
	select Student.Sno,Sname,Cname,Course.Cno,Teacher.Tname,Grade
	From Student,Course,Teacher,SC
	WHERE Student.Sno=SC.Sno and
		Course.Cno=SC.Cno and Teacher.Tno=SC.Tno and SC.Cno='002';
select * from St