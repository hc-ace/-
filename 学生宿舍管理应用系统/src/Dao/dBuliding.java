package Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Buliding;

public class dBuliding extends Dao
{
	//查询单个
	public static Buliding getBuliding(String bno){
		Buliding buliding = new Buliding();
		buliding.setBno(bno);
		String sql = "select * from Buliding where Bno = ?";
		try {
			conn = Dao.getConn();					//获取数据库连接
			//创建PrepareStatement对象，并传递SQL语句
			PreparedStatement Ps = conn.prepareStatement(sql);
			Ps.setString(1, bno);					//为参数赋值
			ResultSet rs = Ps.executeQuery();		//执行SQL语句，获得查询结果集
			if (rs.next() && rs.getRow() > 0) {	
				buliding.setBno(rs.getString("Bno"));	
				buliding.setBnum(rs.getInt("Bnum"));
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
		return buliding;
	}
	//查询全部
	public static java.util.List<Buliding> getallBuliding(){
		java.util.List<Buliding> list = new ArrayList<Buliding>();
		try {
			getConn();
			stmt = conn.createStatement();
			String sql = "select * from Buliding";
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Buliding it = new Buliding();
				it.setBno(rs.getString("Bno"));			
				it.setBnum(rs.getInt("Bnum"));
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
	public static int addBuliding(Buliding item) {
		int iRow = 0;
		try {
			getConn();
			String sql ="insert into Buliding(Bno,Bnum)values(?,?)";
			pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, item.getBno());
			pStmt.setInt(2,item.getBnum());
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
	public static int delBuliding(String Bno) {
		int iRow = 0;
		try {
			getConn();
			String sql = "delete from Buliding where Bno=? ";
			pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, Bno);			
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
