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
	 * �ж��û���������ķ���
	 * 
	 * @param Administrator
	 * ʵ����Administrator��ʵ��
	 */
	public static boolean okAdministrator(Administrator administrator) {
		Connection conn = null;
		try 
		{
			String Ano = administrator.getAno();
			String Apw = administrator.getApw();
			conn = Dao.getConn();					//��ȡ���ݿ�����
			//����PrepareStatement���󣬲�����SQL���
			PreparedStatement Ps = conn.prepareStatement("select Apw from Administrator where Ano = ? ");
			Ps.setString(1, Ano);					//Ϊ������ֵ
			ResultSet rs = Ps.executeQuery();		//ִ��SQL��䣬��ò�ѯ�����
			if (rs.next() && (rs.getRow() > 0)) 	//��ѯ���û���Ϣ
			{		
				String password = rs.getString(1).trim();	//�������
				if (password.equals(Apw)) {
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
	public static Administrator getAdministrator(String Ano){

		Administrator administrator = new Administrator();
		administrator.setAno(Ano);
		String sql = "select * from Administrator where Ano = ?";
		try {
			conn = Dao.getConn();					//��ȡ���ݿ�����
			//����PrepareStatement���󣬲�����SQL���
			PreparedStatement Ps = conn.prepareStatement(sql);
			Ps.setString(1, Ano);					//Ϊ������ֵ
			ResultSet rs = Ps.executeQuery();		//ִ��SQL��䣬��ò�ѯ�����
			if (rs.next() && rs.getRow() > 0) {	
				administrator.setAno(rs.getString("Ano"));
				administrator.setAname(rs.getString("Aname"));
				administrator.setBno(rs.getString("Bno"));	
				administrator.setAtel(rs.getString("Atel"));		
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
		return administrator;
	}
	//��ѯȫ��
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
	//���
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
	//�޸�
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
	//�޸�����
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
	//ɾ��
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
