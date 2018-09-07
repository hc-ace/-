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
	 * 判断用户名和密码的方法
	 * 
	 * @param Student
	 * 实例类Student的实例
	 */
	public static boolean okStudent(Student student) {
		Connection conn = null;
		try 
		{
			String Sno = student.getSno();
			String Spw = student.getSpw();
			conn = Dao.getConn();					//获取数据库连接
			//创建PrepareStatement对象，并传递SQL语句
			PreparedStatement Ps = conn.prepareStatement("select Spw from Student where Sno = ? ");
			Ps.setString(1, Sno);					//为参数赋值
			ResultSet rs = Ps.executeQuery();		//执行SQL语句，获得查询结果集
			if (rs.next() && (rs.getRow() > 0)) 	//查询到用户信息
			{		
				String password = rs.getString(1).trim();	//获得密码
				if (password.equals(Spw)) {
					return true;					//密码正确返回true
				} else {
					JOptionPane.showMessageDialog(null, "密码不正确");
					System.out.println("密码不正确");
					return false;					//密码错误返回false
				}
			} 
			else 
			{
				JOptionPane.showMessageDialog(null, "用户名不存在");
				return false;
			}
		} 
		catch (Exception e) 
		{
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "数据库异常！\n"+e.getMessage());
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

	//查询单个
	public static Student getStudent(String Sno){

		Student student = new Student();
		student.setSno(Sno);
		String sql = "select * from Student where Sno = ?";
		try {
			conn = Dao.getConn();					//获取数据库连接
			//创建PrepareStatement对象，并传递SQL语句
			PreparedStatement Ps = conn.prepareStatement(sql);
			Ps.setString(1, Sno);					//为参数赋值
			ResultSet rs = Ps.executeQuery();		//执行SQL语句，获得查询结果集
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
			JOptionPane.showMessageDialog(null, "数据库异常！\n"+e.getMessage());
			
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
	//查询全部
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
	//添加
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
	//修改信息
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
	//修改密码
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
	//删除
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
