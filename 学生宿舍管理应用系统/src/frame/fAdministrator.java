package frame;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Dao.dLR;
import Dao.dAdministrator;
import model.Administrator;
import model.LR;

public class fAdministrator extends JFrame
{
	private static final long serialVersionUID = 1L;
	private int i = 0;
	private int j = 0;
	private int row = -1;
	public static DefaultTableModel dtm;
	private JPanel contentPane;
	public fAdministrator(String Ano) {
		setTitle("管理员");								//设置窗体标题
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			//单击关闭按钮是关闭
		setSize(300,200);
		contentPane = new JPanel();								//创建面板
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));		//设置边框
		setContentPane(contentPane); 							//应用面板
		
		JPanel panel1 = new JPanel();
		panel1.setBorder(new EmptyBorder(0, 0, 0, 20));
		JButton selectdorm = new JButton("学生管理");
		panel1.add(selectdorm);
		
		JPanel panel2 = new JPanel();
		JButton baoxiu = new JButton("报修管理");
		panel2.setBorder(new EmptyBorder(0, 20, 0, 20));
		panel2.add(baoxiu);
		
		JButton dengji = new JButton("离返校管理");
		JPanel panel3 = new JPanel();
		panel3.setBorder(new EmptyBorder(0, 20, 0, 20));
		panel3.add(dengji);
		
		JButton pw = new JButton("修改密码");
		JPanel panel4 = new JPanel();
		panel4.setBorder(new EmptyBorder(0, 10, 0, 20));
		panel4.add(pw);
		
		JPanel panel5 = new JPanel();
		panel5.setBorder(new EmptyBorder(0, 20, 0, 0));
		JButton exit  = new JButton("退    出");
		panel5.add(exit);
		

		JPanel panel = new JPanel();
		JPanel panel0 = new JPanel();
		panel.add(panel1);
		panel.add(panel2);
		panel0.add(panel3);
		panel0.add(panel4);
		contentPane.add(panel);
		contentPane.add(panel0);
		contentPane.add(panel5);
		
		selectdorm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				fSt faa = new fSt();
				faa.setVisible(true);
				Toolkit toolkit = faa.getToolkit();	//获得Toolkit对象
				Dimension dm = toolkit.getScreenSize();		//获得屏幕的大小
				//使主屏幕居中
				faa.setLocation((dm.width - faa.getWidth())/2, (dm.height - faa.getHeight())/2);	
			}
		});
		
		baoxiu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				fRepair fa = new fRepair();
				fa.setVisible(true);
				Toolkit toolkit = fa.getToolkit();	//获得Toolkit对象
				Dimension dm = toolkit.getScreenSize();		//获得屏幕的大小
				//使主屏幕居中
				fa.setLocation((dm.width - fa.getWidth())/2, (dm.height - fa.getHeight())/2);	
			}
		});
		
		dengji.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				fLR f = new fLR();
				f.setVisible(true);
				Toolkit toolkit = f.getToolkit();	//获得Toolkit对象
				Dimension dm = toolkit.getScreenSize();		//获得屏幕的大小
				//使主屏幕居中
				f.setLocation((dm.width - f.getWidth())/2, (dm.height - f.getHeight())/2);	
			}
		});
		
		pw.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				fapw fa = new fapw(Ano);
				fa.setVisible(true);
				Toolkit toolkit = fa.getToolkit();	//获得Toolkit对象
				Dimension dm = toolkit.getScreenSize();		//获得屏幕的大小
				//使主屏幕居中
				fa.setLocation((dm.width - fa.getWidth())/2, (dm.height - fa.getHeight())/2);			
			}
		});
		
		exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				login loginFrame = new login();
				loginFrame.setVisible(true);
				Toolkit toolkit = loginFrame.getToolkit();	//获得Toolkit对象
				Dimension dm = toolkit.getScreenSize();		//获得屏幕的大小
				//使主屏幕居中
				loginFrame.setLocation((dm.width - loginFrame.getWidth())/2, (dm.height - loginFrame.getHeight())/2);
				dispose();
			}
		});
		
	}
}
