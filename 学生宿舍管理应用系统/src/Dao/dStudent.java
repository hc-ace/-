package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Student;

public class dStudent extends Dao
{
	/**
	 * �ж��û���������ķ���
	 * 
	 * @param Student
	 * ʵ����Student��ʵ��
	 */
	public static boolean okStudent(Student student) {
		Connection conn = null;
		try 
		{
			String Sno = student.getSno();
			String Spw = student.getSpw();
			conn = Dao.getConn();					//��ȡ���ݿ�����
			//����PrepareStatement���󣬲�����SQL���
			PreparedStatement Ps = conn.prepareStatement("select Spw from Student where Sno = ? ");
			Ps.setString(1, Sno);					//Ϊ������ֵ
			ResultSet rs = Ps.executeQuery();		//ִ��SQL��䣬��ò�ѯ�����
			if (rs.next() && (rs.getRow() > 0)) 	//��ѯ���û���Ϣ
			{		
				String password = rs.getString(1).trim();	//�������
				if (password.equals(Spw)) {
					return true;					//������ȷ����true
				} else {
					JOptionPane.showMessageDialog(null, "���벻��ȷ");
					System.out.println("���벻��ȷ");
					return false;					//������󷵻�false
				}
			} 
			else 
			{
				JOptionPane.showMessageDialog(null, "�û���������");
				return false;
			}
		} 
		catch (Exception e) 
		{
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "���ݿ��쳣��\n"+e.getMessage());
			return false;
		} 
		finally 
		{
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		}
	}

	//��ѯ����
	public static Student getStudent(String Sno){

		Student student = new Student();
		student.setSno(Sno);
		String sql = "select * from Student where Sno = ?";
		try {
			conn = Dao.getConn();					//��ȡ���ݿ�����
			//����PrepareStatement���󣬲�����SQL���
			PreparedStatement Ps = conn.prepareStatement(sql);
			Ps.setString(1, Sno);					//Ϊ������ֵ
			ResultSet rs = Ps.executeQuery();		//ִ��SQL��䣬��ò�ѯ�����
			if (rs.next() && rs.getRow() > 0) {	
				student.setSno(rs.getString("Sno"));
				student.setSname(rs.getString("Sname"));
				student.setSsex(rs.getString("Ssex"));
				student.setSbirthday(rs.getTimestamp("Sbirthday"));
				student.setSgrade(rs.getInt("Sgrade"));
				student.setSdept(rs.getString("Sdept"));
				student.setDno(rs.getString("Dno"));
				student.setBno(rs.getString("Bno"));				
			}
		}catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "���ݿ��쳣��\n"+e.getMessage());
			
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		}
		return student;
	}
	//��ѯȫ��
	public static java.util.List<Student> getallStudent(){
		java.util.List<Student> list = new ArrayList<Student>();
		try {
			getConn();
			stmt = conn.createStatement();
			String sql = "select * from Student";
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Student it = new Student();
				it.setSno(rs.getString("Sno"));
				it.setSname(rs.getString("Sname"));
				it.setSsex(rs.getString("Ssex"));
				it.setSbirthday(rs.getTimestamp("Sbirthday"));
				it.setSgrade(rs.getInt("Sgrade"));
				it.setSdept(rs.getString("Sdept"));
				it.setDno(rs.getString("Dno"));
				it.setBno(rs.getString("Bno"));			
				list.add(it);			
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			closeAll();
		}
		return list;
	}
	//���
	public static int addStudent(Student item) {
		int iRow = 0;
		try {
			getConn();
			String sql ="insert into Student(Sno,Sname,Ssex,Sbirthday,Sgrade,Sdept,Dno,Bno,Spw)values (?,?,?,?,?,?,?,?,?)";
			pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, item.getSno());
			pStmt.setString(2, item.getSname());
			pStmt.setString(3, item.getSsex());
			pStmt.setTimestamp(4, item.getSbirthday());
			pStmt.setInt(5, item.getSgrade());
			pStmt.setString(6, item.getSdept());
			pStmt.setString(7, item.getDno());
			pStmt.setString(8, item.getBno());
			pStmt.setString(9, item.getSpw());
			iRow = pStmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			closeAll();
		}
		return iRow;
	}
	//�޸���Ϣ
	public static int editStudent(Student item) {
		int iRow = 0;
		try {
			getConn();
			String sql = "update Student set Sname=?,Ssex=?,Sbirthday=?,Sgrade=?,Sdept=?,Dno=?,Bno=?,Spw=? where Sno=?";
			pStmt = conn.prepareStatement(sql);	
			pStmt.setString(1, item.getSname());
			pStmt.setString(2, item.getSsex());
			pStmt.setTimestamp(3, item.getSbirthday());
			pStmt.setInt(4, item.getSgrade());
			pStmt.setString(5, item.getSdept());
			pStmt.setString(6, item.getDno());
			pStmt.setString(7, item.getBno());
			pStmt.setString(8, item.getSpw());
			pStmt.setString(9, item.getSno());
			iRow = pStmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			closeAll();
		}
		return iRow;
	}
	//�޸�����
	public static int editStudent2(Student item) {
		int iRow = 0;
		try {
			getConn();
			String sql = "update Student set Spw=? where Sno=?";
			pStmt = conn.prepareStatement(sql);	
			pStmt.setString(1, item.getSpw());
			pStmt.setString(2, item.getSno());
			iRow = pStmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			closeAll();
		}
		return iRow;
	}
	//ɾ��
	public static int delStudent(String Sno) {
		int iRow = 0;
		try {
			getConn();
			String sql = "delete from Student where Sno = ?";
			pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, Sno);
			iRow = pStmt.executeUpdate();
			System.out.println("delete");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			closeAll();
		}
		return iRow;
	}
}
