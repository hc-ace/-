package Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Dorm;

public class dDorm extends Dao{
	//��ѯ����
	public static Dorm getDorm(String dno){
		Dorm dorm = new Dorm();
		dorm.setDno(dno);
		String sql = "select * from Dorm where Dno = ?";
		try {
			conn = Dao.getConn();					//��ȡ���ݿ�����
			//����PrepareStatement���󣬲�����SQL���
			PreparedStatement Ps = conn.prepareStatement(sql);
			Ps.setString(1, dno);					//Ϊ������ֵ
			ResultSet rs = Ps.executeQuery();		//ִ��SQL��䣬��ò�ѯ�����
			if (rs.next() && rs.getRow() > 0) {	
				dorm.setDno(rs.getString("Dno"));
				dorm.setDtype(rs.getString("Dtype"));
				dorm.setDmaxnum(rs.getInt("Dmaxnum"));
				dorm.setBno(rs.getString("Bno"));		
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
		return dorm;
	}
	//��ѯȫ��
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
	//���
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
	//�޸�
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
	//ɾ��
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
