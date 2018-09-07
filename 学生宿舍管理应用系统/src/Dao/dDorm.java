package Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Dorm;

public class dDorm extends Dao{
	//查询单个
	public static Dorm getDorm(String dno){
		Dorm dorm = new Dorm();
		dorm.setDno(dno);
		String sql = "select * from Dorm where Dno = ?";
		try {
			conn = Dao.getConn();					//获取数据库连接
			//创建PrepareStatement对象，并传递SQL语句
			PreparedStatement Ps = conn.prepareStatement(sql);
			Ps.setString(1, dno);					//为参数赋值
			ResultSet rs = Ps.executeQuery();		//执行SQL语句，获得查询结果集
			if (rs.next() && rs.getRow() > 0) {	
				dorm.setDno(rs.getString("Dno"));
				dorm.setDtype(rs.getString("Dtype"));
				dorm.setDmaxnum(rs.getInt("Dmaxnum"));
				dorm.setBno(rs.getString("Bno"));		
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
		return dorm;
	}
	//查询全部
	public static java.util.List<Dorm> getallDorm(){
		java.util.List<Dorm> list = new ArrayList<Dorm>();
		try {
			getConn();
			stmt = conn.createStatement();
			String sql = "select * from Dorm";
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Dorm it = new Dorm();
				it.setDno(rs.getString("Dno"));
				it.setDtype(rs.getString("Dtype"));
				it.setDmaxnum(rs.getInt("Dmaxnum"));
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
	public static int addDorm(Dorm item) {
		int iRow = 0;
		try {
			getConn();
			String sql ="insert into Dorm(Dno,Dtype,Dmaxnum,Bno) values (?,?,?,?)";
			pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, item.getDno());
			pStmt.setString(2, item.getDtype());
			pStmt.setInt(3, item.getDmaxnum());
			pStmt.setString(4, item.getBno());
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
	public static int editDorm(Dorm item) {
		int iRow = 0;
		try {
			getConn();
			String sql = "update Dorm set Dtype=?,Dmaxnum=? where Bno=?,Dno=?";
			pStmt = conn.prepareStatement(sql);	
			pStmt.setString(1, item.getDtype());
			pStmt.setInt(2, item.getDmaxnum());
			pStmt.setString(3, item.getBno());			
			pStmt.setString(4, item.getDno());
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
	public static int delDorm(String Dno) {
		int iRow = 0;
		try {
			getConn();
			String sql = "delete from Dorm where Dno = ?";
			pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, Dno);
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
