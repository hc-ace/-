create database Student_System			--�������ݿ�
use Student_System
create table Student								--ѧ��
(
	Sno CHAR(9) PRIMARY KEY,						--ѧ�ţ���ֵ
	Sname CHAR(20) UNIQUE ,							--������ȡΨһ
	Spassword CHAR(20) DEFAULT('123456'),			--���룬���ó�ʼֵ
	Ssex CHAR(2)	CHECK(Ssex IN ('��','Ů') ),	--�Ա�									
	Sbirthday DATE,									--��������
	Sdept CHAR(20)	,								--����ϵ
	Sclass numeric(2,0)								--���ڰ༶����01
)
create table Course									--�γ�
(
	Cno CHAR(4) PRIMARY KEY,						--�γ̺�
	Cname CHAR(40) NOT NULL,						--�γ������ǿ�									
	Ccredit SMALLINT,								--ѧ��
	Cpno  CHAR(4),									
	FOREIGN KEY(Cpno) REFERENCES Course(Cno)		--һ�����е� FOREIGN KEY ָ����һ�����е� PRIMARY KEY��
)
create table Teacher								--��ʦ
(
	Tno CHAR(9)PRIMARY KEY,							--����
	Tname CHAR(20) NOT NULL ,						--����
	Tbirthday DATE,									--��������
	Ttitle CHAR(9),									--ְ��
	Tpassword CHAR(20) DEFAULT('123456'),							--����
	Tsex CHAR(2)	CHECK(Tsex IN ('��','Ů') ),	--�Ա�		
)
create table SC										--ѧ��ѡ��
(
	Sno CHAR(9),
	Cno CHAR(4),
	Tno CHAR(9),
	Grade SMALLINT CHECK(Grade>=0 and Grade<=100),	--ѧ���ɼ�
	PRIMARY KEY(Sno,Cno),							--����������������Ϊ�������Զ���
	FOREIGN KEY(Tno) REFERENCES Teacher(Tno)
	ON DELETE CASCADE
	ON UPDATE CASCADE,
	FOREIGN KEY(Sno) REFERENCES Student(Sno)
	ON DELETE CASCADE
	ON UPDATE CASCADE,
	FOREIGN KEY(Cno) REFERENCES Course(Cno)
	ON DELETE CASCADE
	ON UPDATE CASCADE								--Ϊ�˸������ݺ�ɾ�����ݶ�����ʵ��ͬ��
)
create table TC										--��ʦ�̿�
(
	Tno CHAR(9),						
	Cno CHAR(4),
	Csite CHAR(20),									--�ص�
	Ctime CHAR(20),									--ʱ��
	PRIMARY KEY(Tno,Cno),							--����������������Ϊ�������Զ���
	FOREIGN KEY(Tno) REFERENCES Teacher(Tno)
	ON DELETE CASCADE
	ON UPDATE CASCADE,
	FOREIGN KEY(Cno) REFERENCES Course(Cno)
	ON DELETE CASCADE
	ON UPDATE CASCADE								--Ϊ�˸������ݺ�ɾ�����ݶ�����ʵ��ͬ��
)
ALTER TABLE SC ADD Type NCHAR(2) NULL		--���
ALTER TABLE SC ALTER COLUMN Type NCHAR(2)	--�޸�
ALTER TABLE SC ADD CONSTRAINT C1 CHECK(Type IN ('����',' ����',' ѡ��'))--CONSTRAINT����
ALTER TABLE SC DROP CONSTRAINT C1
ALTER TABLE SC DROP COLUMN Type --ɾ��Լ������ֵ
--��������
create unique index  Course on Course(Cname)
create index Student on Student(Sclass)
insert into Student(Sno,Sname,Ssex,Sbirthday,Sclass,Sdept) values('031602601','��Īĳ','��','1998-11-03',01,'�������ѧ�뼼��') 
insert into Student(Sno,Sname,Ssex,Sbirthday,Sclass,Sdept) values('031602401','��Īĳ','Ů','1998-06-24',04,'�������ѧ�뼼��') 
insert into Student(Sno,Sname,Ssex,Sbirthday,Sclass,Sdept) values('031602501','��Īĳ','��','1999-07-07',05,'�������ѧ�뼼��') 
select *from Student
insert into Teacher(Tno,Tname,Tbirthday,Ttitle,Tsex) values('10001','Īĳ','1978-11-03','����','��')
insert into Teacher(Tno,Tname,Tbirthday,Ttitle,Tsex) values('10002','Ҷĳ','1980-11-03','������','Ů')
insert into Teacher(Tno,Tname,Tbirthday,Ttitle,Tsex) values('10003','��ĳ','1987-11-03','��ʦ','Ů')

insert into Course(Cno,Cname,Ccredit,Cpno) values('001','�ߵ���ѧ��',4,NULL)
insert into Course(Cno,Cname,Ccredit,Cpno) values('002','�ߴ�',3,NULL)
insert into Course(Cno,Cname,Ccredit,Cpno) values('003','�ߵ���ѧ��',5,'003')
select *from  Course
INSERT into SC(Sno,Cno,Tno,Grade) values('031602501','001','10001',0);
INSERT into SC(Sno,Cno,Tno,Grade) values('031602501','002','10001',0);
INSERT into SC(Sno,Cno,Tno,Grade) values('031602501','003','10001',0);
UPDATE Student 
SET Sclass=6 WHERE Sno='031602601' ;
select *from Student
delete from Student where Sno ='031602501'; --ɾ������
UPDATE SC SET Grade=70 WHERE Sno='031602101' ;
--�����ѯ
select Sno,Sname,Sdept from Student		--��ѯѧ����ѧ�š�������רҵ
select TOP 3 * from Student--��ѯѧ�����ǰ��������
select * from student			
order by Sdept, Sbirthday desc;--ORDER BY�Ӿ�
SELECT Sno, Count(*)		--Count(*)Ϊѡ������
FROM SC
GROUP BY Sno
HAVING COUNT(*) >=2			--�þۼ������ķ��鵥���ѯ
select Student.Sno,Grade from Student,SC
where Student.Sno=SC.Sno and SC.Cno='003';--��ѯ�γ̺�Ϊ003������ѧ���ĳɼ�
SELECT Cno,AVG(Grade)
FROM SC GROUP BY Cno
HAVING Cno != '001';--��ѯ�γ̺Ų�Ϊ001��ѧ��ƽ���ɼ�

select Sclass,COUNT(Sno) from Student
group by Sclass --��ѯÿ���������
--��ѯ002�γ��гɼ����ڿγ�ƽ���ɼ���ѧ�����ɼ�
select Sname,Grade
from Student,SC
where Student.Sno=SC.Sno and Cno='002' and Grade >(select AVG(Grade) FROM SC)
--������ͼ
create view St(Sno,Sname,Cname,Cno,Tname,Grade)
	AS
	select Student.Sno,Sname,Cname,Course.Cno,Teacher.Tname,Grade
	From Student,Course,Teacher,SC
	WHERE Student.Sno=SC.Sno and
		Course.Cno=SC.Cno and Teacher.Tno=SC.Tno and SC.Cno='002';
select * from St