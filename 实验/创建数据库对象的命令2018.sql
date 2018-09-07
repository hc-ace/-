/*�������ݿ�*/
create database S_T

/*�����ݿ�*/
use S_T

/*�������Ż�����*/

CREATE TABLE Student          
 (Sno   CHAR(9) PRIMARY KEY�� /* �м�������Լ������*/                    
  Sname  CHAR(20) UNIQUE��  /* SnameȡΨһֵ*/
  Ssex    CHAR(2)��
  Sage   SMALLINT��
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
      /* �������������Թ��ɣ�������Ϊ�������Խ��ж���*/
      /* ��������Լ��������Sno�����룬�����ձ���Student */
     	FOREIGN KEY (Sno) REFERENCES Student(Sno)
        ON DELETE CASCADE     /*����ɾ��SC������Ӧ��Ԫ��*/
        ON UPDATE CASCADE,  /*��������SC������Ӧ��Ԫ��*/
     /* ��������Լ�������� Cno�����룬�����ձ���Course*/
        FOREIGN KEY (Cno) REFERENCES Course(Cno) 	
        ON DELETE NO ACTION 	
     /*��ɾ��course ���е�Ԫ���������SC��һ��ʱ�ܾ�ɾ��*/
        ON UPDATE CASCADE)
  	/*������course���е�cnoʱ����������SC������Ӧ��Ԫ��*/

		
/*�޸ı�ṹ*/
ALTER TABLE SC  
  ADD Type  NCHAR(1) NULL

ALTER TABLE SC 
 ALTER COLUMN Type NCHAR(2)

ALTER TABLE SC
  ADD CHECK(Type IN ('����', '����', 'ѡ��') )


ALTER TABLE SC 
  DROP CONSTRAINT ck_sc_type_oea330e9

ALTER TABLE SC  
DROP COLUMN Type

ALTER TABLE SC  
  ADD Type  NCHAR(2) NULL

ALTER TABLE SC
  ADD CONSTRAINT C1 CHECK(Type IN ('����', '����', 'ѡ��') )

ALTER TABLE SC 
  DROP CONSTRAINT C1

ALTER TABLE SC 
  DROP COLUMN Type


/*��������*/

insert into Student(Sno,Sname,Ssex) values('221002210','����','Ů')
insert into Student(Sno,Sname,Ssex) values('221002208','��ΰ�','��')
insert into Student(Sno,Sname,Ssex) values('221002130','�¾�','Ů')

select * from student

insert into Course(Cno,Cname) values('001','���ݿ�ϵͳԭ��')
insert into Course(Cno,Cname) values('002','��ɢ��ѧA')
insert into Course(Cno,Cname) values('003','�ߵ���ѧB')
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

/*�޸�ɾ������*/
UPDATE course
SET  cno='004'
WHERE  cno='003'
/*�ܼ����޸�*/

delete from course
    WHERE  cno='004'
/*�������ܾ�*/

UPDATE student
    SET  sno='221302130'
WHERE  sno='221002130'
/*�ܼ����޸�*/

delete from student
    WHERE  sno='221302130'
/*�ܼ���ɾ��*/

/*���������ļ�*/
create unique index Course on Course(Cname)

/*������ͼ*/
CREATE VIEW IS_V(Sno,Sname,Cname,Grade)
        AS 
        SELECT Student.Sno,Sname,Cname,Grade
        FROM  Student,SC,Course
        WHERE Student.Sno=SC.Sno AND
              Course.Cno=SC.Cno
             
SELECT * FROM IS_V

