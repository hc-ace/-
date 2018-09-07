package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Administrator;
import model.Student;;

public class dAdministrator extends Dao 
{
	/**
	 * 判断用户名和密码的方法
	 * 
	 * @param Administrator
	 * 实例类Administrator的实例
	 */
	public static boolean okAdministrator(Administrator administrator) {
		Connection conn = null;
		try 
		{
			String Ano = administrator.getAno();
			String Apw = administrator.getApw();
			conn = Dao.getConn();					//获取数据库连接
			//创建PrepareStatement对象，并传递SQL语句
			PreparedStatement Ps = conn.prepareStatement("select Apw from Administrator where Ano = ? ");
			Ps.setString(1, Ano);					//为参数赋值
			ResultSet rs = Ps.executeQuery();		//执行SQL语句，获得查询结果集
			if (rs.next() && (rs.getRow() > 0)) 	//查询到用户信息
			{		
				String password = rs.getString(1).trim();	//获得密码
				if (password.equals(Apw)) {
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
	public static Administrator getAdministrator(String Ano){

		Administrator administrator = new Administrator();
		administrator.setAno(Ano);
		String sql = "select * from Administrator where Ano = ?";
		try {
			conn = Dao.getConn();					//获取数据库连接
			//创建PrepareStatement对象，并传递SQL语句
			PreparedStatement Ps = conn.prepareStatement(sql);
			Ps.setString(1, Ano);					//为参数赋值
			ResultSet rs = Ps.executeQuery();		//执行SQL语句，获得查询结果集
			if (rs.next() && rs.getRow() > 0) {	
				administrator.setAno(rs.getString("Ano"));
				administrator.setAname(rs.getString("Aname"));
				administrator.setBno(rs.getString("Bno"));	
				administrator.setAtel(rs.getString("Atel"));		
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
		return administrator;
	}
	//查询全部
	public static java.util.List<Administrator> getallAdministrator(){
		java.util.List<Administrator> list = new ArrayList<Administrator>();
		try {
			getConn();
			stmt = conn.createStatement();
			String sql = "select * from Administrator";
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Administrator it = new Administrator();
				it.setAno(rs.getString("Ano"));
				it.setAname(rs.getString("Aname"));
				it.setBno(rs.getString("Bno"));	
				it.setAtel(rs.getString("Atel"));	
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
	public static int addAdministrator(Administrator item) {
		int iRow = 0;
		try {
			getConn();
			String sql ="insert into Administrator(Ano,Aname,Bno,Atel,Apw)values (?,?,?,?,?)";
			pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, item.getAno());
			pStmt.setString(2, item.getAname());
			pStmt.setString(3, item.getBno());
			pStmt.setString(4, item.getAtel());
			pStmt.setString(5, item.getApw());
			iRow = pStmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			closeAll();
		}
		return iRow;
	}
	//修改
	public static int editAdministrator(Administrator item) {
		int iRow = 0;
		try {
			getConn();
			String sql = "update Administrator set Aname=?,Bno=?,Atel=?,Apw=? where Ano=?";
			pStmt = conn.prepareStatement(sql);	
			pStmt.setString(1, item.getAname());
			pStmt.setString(7, item.getBno());
			pStmt.setString(7, item.getAtel());
			pStmt.setString(8, item.getApw());
			pStmt.setString(9, item.getAno());
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
		public static int editAdministrator2(Administrator item) {
			int iRow = 0;
			try {
				getConn();
				String sql = "update Administrator set Apw=? where Ano=?";
				pStmt = conn.prepareStatement(sql);	
				pStmt.setString(1, item.getApw());
				pStmt.setString(2, item.getAno());
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
	public static int delAdministrator(String Ano) {
		int iRow = 0;
		try {
			getConn();
			String sql = "delete from Administrator where Ano = ?";
			pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, Ano);
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
