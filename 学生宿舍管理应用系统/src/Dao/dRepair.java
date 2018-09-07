package Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Repair;;

public class dRepair extends Dao
{
	//查询单个
	public static java.util.List<Repair> getRepair(String bno){
		java.util.List<Repair> list = new ArrayList<Repair>();
		try {
			getConn();
			String sql = "select * from Repair where Bno = ?";
			pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, bno);
			rs = pStmt.executeQuery();
			while(rs.next()) {
				Repair it = new Repair();
				it.setGno(rs.getString("Gno"));
				it.setDno(rs.getString("Dno"));
				it.setBno(rs.getString("Bno"));			
				it.setRsubmit(rs.getTimestamp("Rsubmit"));
				it.setRreason(rs.getString("Rreason"));
				it.setRstate(rs.getString("Rstate"));
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
	//查询全部
	public static java.util.List<Repair> getallRepair(){
		java.util.List<Repair> list = new ArrayList<Repair>();
		try {
			getConn();
			stmt = conn.createStatement();
			String sql = "select * from Repair";
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Repair it = new Repair();
				it.setGno(rs.getString("Gno"));
				it.setDno(rs.getString("Dno"));
				it.setBno(rs.getString("Bno"));			
				it.setRsubmit(rs.getTimestamp("Rsubmit"));
				it.setRreason(rs.getString("Rreason"));
				it.setRstate(rs.getString("Rstate"));
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
	public static int addRepair(Repair item) {
		int iRow = 0;
		try {
			getConn();
			String sql ="insert into Repair(Dno,Gno,Bno,Rsubmit,Rreason,Rstate)values(?,?,?,?,?,?)";
			pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, item.getDno());
			pStmt.setString(2, item.getGno());
			pStmt.setString(3, item.getBno());
			pStmt.setTimestamp(4, item.getRsubmit());
			pStmt.setString(5, item.getRreason());
			pStmt.setString(6, item.getRstate());
			iRow = pStmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			closeAll();
		}
		return iRow;
	}
	//修改报修状态
	public static int editRepair(Repair item) {
		int iRow = 0;
		try {
			getConn();
			String sql = "update Repair set Rstate=? where Gno=? and Dno=? and Bno=? ";
			pStmt = conn.prepareStatement(sql);	
			pStmt.setString(1, item.getRstate());
			pStmt.setString(2, item.getGno());
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
	public static int delRepair(Repair item) {
		int iRow = 0;
		try {
			getConn();
			String sql = "delete from Repair where  Gno=? and Dno=? and Bno=? ";
			pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, item.getGno());
			pStmt.setString(2, item.getBno());			
			pStmt.setString(3, item.getDno());		
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
