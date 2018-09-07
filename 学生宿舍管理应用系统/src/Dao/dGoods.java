package Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Goods;

public class dGoods extends Dao
{ 
	//查询单个
	public static Goods getGoods(String gno){
		Goods goods = new Goods();
		goods.setGno(gno);
		String sql = "select * from Goods where Gno = ?";
		try {
			conn = Dao.getConn();					//获取数据库连接
			//创建PrepareStatement对象，并传递SQL语句
			PreparedStatement Ps = conn.prepareStatement(sql);
			Ps.setString(1, gno);					//为参数赋值
			ResultSet rs = Ps.executeQuery();		//执行SQL语句，获得查询结果集
			if (rs.next() && rs.getRow() > 0) {	
				goods.setGno(rs.getString("Gno"));
				goods.setGname(rs.getString("Gname"));	
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
		return goods;
	}
	//查询全部
	public static java.util.List<Goods> getallGoods(){
		java.util.List<Goods> list = new ArrayList<Goods>();
		try {
			getConn();
			stmt = conn.createStatement();
			String sql = "select * from Goods";
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Goods it = new Goods();
				it.setGno(rs.getString("Gno"));
				it.setGname(rs.getString("Gname"));		
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
	public static int addGoods(Goods item) {
		int iRow = 0;
		try {
			getConn();
			String sql ="insert into Goods(Gno,Gname) values (?,?)";
			pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, item.getGno());
			pStmt.setString(2, item.getGname());
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
	public static int editGoods(Goods item) {
		int iRow = 0;
		try {
			getConn();
			String sql = "update Goods set Gname=? where Gno=?";
			pStmt = conn.prepareStatement(sql);	
			pStmt.setString(1, item.getGname());
			pStmt.setString(2, item.getGno());
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
	public static int delGoods(Goods item) {
		int iRow = 0;
		try {
			getConn();
			String sql = "delete from Goods where  Gno=?";
			pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, item.getGno());	
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
