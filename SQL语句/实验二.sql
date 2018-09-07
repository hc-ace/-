--drop login student2
--create login student1 with password='123456'
--use Student_Registration_System
--create user student for login student1
--drop user student
--grant select on SC to R1
--revoke select on student from student
--deny UPDATE on SC to student
--select *from SC
--update SC set Grade=60 where Sno='031602101' and Cno ='001';
--create role R1
--exec sp_addrolemember 'R1','student'
--exec sp_droprolemember 'R1','student'
--drop role R1
create proc s_AvgGrade
	@avg_sname char(20) ,
	@avg_grade int output
AS
	select @avg_grade = AVG(Grade)
	from SC,Student
	where Student.Sno=sc.Sno and Sname=@avg_sname	
alter procedure s_AvgGrade 
	@avg_sname char(20) ,
	@avg_grade int output,
	@coursecount int output
as
	select @coursecount=count(*),@avg_grade = AVG(Grade)
	from SC,Student
	where Student.Sno=sc.Sno and Sname=@avg_sname

declare @Avg_Grade int,@Coursecount int,@sname char(20) ='陈莫某'
exec s_AvgGrade @sname,  @Avg_Grade output, @Coursecount output
print @sname
print @Avg_Grade
print @Coursecount
drop proc  s_AvgGrade
create trigger s1
 on Student instead of update
as select *from SC

update Student set Sno='031602604' where Sname='蔡莫某'
create trigger s2
on Student after update
as select *from Student
alter trigger s1
on Student instead of delete
as select * from SC
--delete from Student where Sno='031602604'
--drop trigger s1

--exec sp_addumpdevice 'disk' ,'st_backup','d:\data\st_backup.bak'
use Student_Registration_System
drop table SC
use master
backup database Student_Registration_System to st_backup with differential

use Student_Registration_System
delete from Course
select *from Course

backup log Student_Registration_System to disk='d:\data\st_log.dat' with init
use Student_Registration_System
insert into Course(Cno,Cname,Ccredit,Cpno) values('004','高等数学',7,NULL)
insert into Course(Cno,Cname,Ccredit,Cpno) values('005','大学物理',6,NULL)
insert into Course(Cno,Cname,Ccredit,Cpno) values('006','数据库系统',12,'004')
insert into Course(Cno,Cname,Ccredit,Cpno) values('007','操作系统',6,NULL)
insert into Course(Cno,Cname,Ccredit,Cpno) values('008','人工智能',12,'006')
----backup log Student_Registration_System to disk='d:\data\st_log2.dat'with init
----RESTORE DATABASE Student_Registration_System WITH RECOVERY--如果一直显示正在还原，可以使用
use master
restore database Student_Registration_System from st_backup with file=1,replace,norecovery
restore database Student_Registration_System from st_backup with file=2,norecovery
restore log Student_Registration_System from disk='d:\data\st_log.dat' with norecovery
restore log Student_Registration_System from disk='d:\data\st_log2.dat' with recovery
use Student_Registration_System
select *from  Course

