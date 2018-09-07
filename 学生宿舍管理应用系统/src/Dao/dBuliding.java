package Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Buliding;

public class dBuliding extends Dao
{
	//��ѯ����
	public static Buliding getBuliding(String bno){
		Buliding buliding = new Buliding();
		buliding.setBno(bno);
		String sql = "select * from Buliding where Bno = ?";
		try {
			conn = Dao.getConn();					//��ȡ���ݿ�����
			//����PrepareStatement���󣬲�����SQL���
			PreparedStatement Ps = conn.prepareStatement(sql);
			Ps.setString(1, bno);					//Ϊ������ֵ
			ResultSet rs = Ps.executeQuery();		//ִ��SQL��䣬��ò�ѯ�����
			if (rs.next() && rs.getRow() > 0) {	
				buliding.setBno(rs.getString("Bno"));	
				buliding.setBnum(rs.getInt("Bnum"));
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
		return buliding;
	}
	//��ѯȫ��
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
	//���
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
	//ɾ��
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
