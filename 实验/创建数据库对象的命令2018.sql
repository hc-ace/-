/*创建数据库*/
create database S_T

/*打开数据库*/
use S_T

/*创建三张基本表*/

CREATE TABLE Student          
 (Sno   CHAR(9) PRIMARY KEY， /* 列级完整性约束条件*/                    
  Sname  CHAR(20) UNIQUE，  /* Sname取唯一值*/
  Ssex    CHAR(2)，
  Sage   SMALLINT，
  Sdept  CHAR(20))
           

CREATE TABLE  Course
( Cno  CHAR(4) PRIMARY KEY,
  Cname  CHAR(40),            
  Cpno  CHAR(4),                                    
  Ccredit  SMALLINT,
  FOREIGN KEY (Cpno) REFERENCES  Course(Cno))

CREATE TABLE  SC
     (  Sno  CHAR(9), 
     	Cno  CHAR(4),  
     	Grade  SMALLINT,
    	PRIMARY KEY (Sno,Cno),  
      /* 主码由两个属性构成，必须作为表级完整性进行定义*/
      /* 表级完整性约束条件，Sno是外码，被参照表是Student */
     	FOREIGN KEY (Sno) REFERENCES Student(Sno)
        ON DELETE CASCADE     /*级联删除SC表中相应的元组*/
        ON UPDATE CASCADE,  /*级联更新SC表中相应的元组*/
     /* 表级完整性约束条件， Cno是外码，被参照表是Course*/
        FOREIGN KEY (Cno) REFERENCES Course(Cno) 	
        ON DELETE NO ACTION 	
     /*当删除course 表中的元组造成了与SC表不一致时拒绝删除*/
        ON UPDATE CASCADE)
  	/*当更新course表中的cno时，级联更新SC表中相应的元组*/

		
/*修改表结构*/
ALTER TABLE SC  
  ADD Type  NCHAR(1) NULL

ALTER TABLE SC 
 ALTER COLUMN Type NCHAR(2)

ALTER TABLE SC
  ADD CHECK(Type IN ('必修', '重修', '选修') )


ALTER TABLE SC 
  DROP CONSTRAINT ck_sc_type_oea330e9

ALTER TABLE SC  
DROP COLUMN Type

ALTER TABLE SC  
  ADD Type  NCHAR(2) NULL

ALTER TABLE SC
  ADD CONSTRAINT C1 CHECK(Type IN ('必修', '重修', '选修') )

ALTER TABLE SC 
  DROP CONSTRAINT C1

ALTER TABLE SC 
  DROP COLUMN Type


/*插入数据*/

insert into Student(Sno,Sname,Ssex) values('221002210','王丽','女')
insert into Student(Sno,Sname,Ssex) values('221002208','张伟楠','男')
insert into Student(Sno,Sname,Ssex) values('221002130','郝静','女')

select * from student

insert into Course(Cno,Cname) values('001','数据库系统原理')
insert into Course(Cno,Cname) values('002','离散数学A')
insert into Course(Cno,Cname) values('003','高等数学B')
select * from Course

INSERT INTO SC(Sno,Cno) VALUES('221002210','001')
INSERT INTO SC(Sno,Cno) VALUES('221002208','001')
INSERT INTO SC(Sno,Cno) VALUES('221002130','001')
INSERT INTO SC(Sno,Cno) VALUES('221002210','002')
INSERT INTO SC(Sno,Cno) VALUES('221002208','002')
INSERT INTO SC(Sno,Cno) VALUES('221002130','002')
INSERT INTO SC(Sno,Cno) VALUES('221002210','003')
INSERT INTO SC(Sno,Cno) VALUES('221002208','003')
INSERT INTO SC(Sno,Cno) VALUES('221002130','003')
select * from sC

/*修改删除数据*/
UPDATE course
SET  cno='004'
WHERE  cno='003'
/*能级联修改*/

delete from course
    WHERE  cno='004'
/*操作被拒绝*/

UPDATE student
    SET  sno='221302130'
WHERE  sno='221002130'
/*能级联修改*/

delete from student
    WHERE  sno='221302130'
/*能级联删除*/

/*创建索引文件*/
create unique index Course on Course(Cname)

/*创建视图*/
CREATE VIEW IS_V(Sno,Sname,Cname,Grade)
        AS 
        SELECT Student.Sno,Sname,Cname,Grade
        FROM  Student,SC,Course
        WHERE Student.Sno=SC.Sno AND
              Course.Cno=SC.Cno
             
SELECT * FROM IS_V

