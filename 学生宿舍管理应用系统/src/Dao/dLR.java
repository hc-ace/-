package Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.LR;

public class dLR extends Dao
{//��ѯ����
	public static LR getLR(String sno){
		LR lr = new LR();
		lr.setSno(sno);
		String sql = "select * from LR where Sno = ?";
		try {
			conn = Dao.getConn();					//��ȡ���ݿ�����
			//����PrepareStatement���󣬲�����SQL���
			PreparedStatement Ps = conn.prepareStatement(sql);
			Ps.setString(1, sno);					//Ϊ������ֵ
			ResultSet rs = Ps.executeQuery();		//ִ��SQL��䣬��ò�ѯ�����
			if (rs.next() && rs.getRow() > 0) {	
				lr.setSno(rs.getString("Sno"));
				lr.setDno(rs.getString("Dno"));
				lr.setBno(rs.getString("Bno"));		
				lr.setLeave(rs.getTimestamp("Leave"));
				lr.setLreturn(rs.getTimestamp("Lreturn"));
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
		return lr;
	}
	//��ѯȫ��
	public static java.util.List<LR> getallLR(){
		java.util.List<LR> list = new ArrayList<LR>();
		try {
			getConn();
			stmt = conn.createStatement();
			String sql = "select * from LR";
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				LR it = new LR();
				it.setSno(rs.getString("Sno"));
				it.setDno(rs.getString("Dno"));
				it.setBno(rs.getString("Bno"));		
				it.setLeave(rs.getTimestamp("Leave"));
				it.setLreturn(rs.getTimestamp("Lreturn"));		
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
	public static int addLR(LR item) {
		int iRow = 0;
		try {
			getConn();
			String sql ="insert into LR(Sno,Dno,Bno,Leave,Lreturn) values (?,?,?,?,?)";
			pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, item.getSno());
			pStmt.setString(2, item.getDno());
			pStmt.setString(4, item.getBno());
			pStmt.setTimestamp(5,item.getLeave());
			pStmt.setTimestamp(6,item.getLreturn());
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
	public static int editLR(LR item) {
		int iRow = 0;
		try {
			getConn();
			String sql = "update LR set Leave=?,Lreturn=? where Sno=? and Dno=? and Bno=? ";
			pStmt = conn.prepareStatement(sql);	
			pStmt.setTimestamp(1,item.getLeave());
			pStmt.setTimestamp(2,item.getLreturn());
			pStmt.setString(3, item.getSno());
			pStmt.setString(4, item.getBno());			
			pStmt.setString(5, item.getDno());
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
	public static int delLR(LR item) {
		int iRow = 0;
		try {
			getConn();
			String sql = "delete from LR where  Sno=? and Dno=? and Bno=? ";
			pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, item.getSno());
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
