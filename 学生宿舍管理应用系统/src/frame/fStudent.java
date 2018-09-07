package frame;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class fStudent extends JFrame
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public fStudent(String Sno) {
		setTitle("学生");								//设置窗体标题
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			//单击关闭按钮是关闭
		setSize(300,200);
		contentPane = new JPanel();								//创建面板
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));		//设置边框
		setContentPane(contentPane); 							//应用面板
		
		JPanel panel1 = new JPanel();
		panel1.setBorder(new EmptyBorder(0, 0, 0, 20));
		JButton selectdorm = new JButton("查询宿舍");
		panel1.add(selectdorm);
		
		JPanel panel2 = new JPanel();
		JButton baoxiu = new JButton("报    修");
		panel2.setBorder(new EmptyBorder(0, 20, 0, 0));
		panel2.add(baoxiu);
		
		JButton dengji = new JButton("离返校登记");
		JPanel panel3 = new JPanel();
		panel3.setBorder(new EmptyBorder(0, 0, 0, 20));
		panel3.add(dengji);
		JButton pw = new JButton("修改密码");
		JPanel panel4 = new JPanel();
		panel4.setBorder(new EmptyBorder(0, 20, 0, 0));
		panel4.add(pw);
		
		JPanel panel5 = new JPanel();
		panel5.setBorder(new EmptyBorder(0, 10, 0, 10));
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
			public void actionPerformed(ActionEvent e) 
			{
				// TODO Auto-generated method stub
				fstudent1 fs1 = new fstudent1(Sno);
				fs1.setVisible(true);
				Toolkit toolkit = fs1.getToolkit();	//获得Toolkit对象
				Dimension dm = toolkit.getScreenSize();		//获得屏幕的大小
				//使主屏幕居中
				fs1.setLocation((dm.width - fs1.getWidth())/2, (dm.height - fs1.getHeight())/2);
			}
		});
		
		baoxiu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				addRepair fs3 = new addRepair();
				fs3.setVisible(true);
				Toolkit toolkit = fs3.getToolkit();	//获得Toolkit对象
				Dimension dm = toolkit.getScreenSize();		//获得屏幕的大小
				//使主屏幕居中
				fs3.setLocation((dm.width - fs3.getWidth())/2, (dm.height - fs3.getHeight())/2);				
			}
		});
		
		dengji.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				addLR fs2 = new addLR();
				fs2.setVisible(true);
				Toolkit toolkit = fs2.getToolkit();	//获得Toolkit对象
				Dimension dm = toolkit.getScreenSize();		//获得屏幕的大小
				//使主屏幕居中
				fs2.setLocation((dm.width - fs2.getWidth())/2, (dm.height - fs2.getHeight())/2);		
			}			
		});
		
		pw.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				fstudentpw fspw = new fstudentpw(Sno);
				fspw.setVisible(true);
				Toolkit toolkit = fspw.getToolkit();	//获得Toolkit对象
				Dimension dm = toolkit.getScreenSize();		//获得屏幕的大小
				//使主屏幕居中
				fspw.setLocation((dm.width - fspw.getWidth())/2, (dm.height - fspw.getHeight())/2);
				
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
