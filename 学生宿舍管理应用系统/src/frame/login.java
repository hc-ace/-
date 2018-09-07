package frame;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import Dao.dStudent;
import Dao.dAdministrator;
import model.Administrator;
import model.Student;

public class login extends JFrame
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField username = null;
	private JPasswordField password = null;
	private JRadioButton s = new JRadioButton("学生",true);
	private JRadioButton a = new JRadioButton("管理员");
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					login frame = new login();	//创建窗体
					frame.setVisible(true); 				//设置窗口可见
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		});
	}
	public login() {
		setTitle("宿舍信息管理系统");								//设置窗体标题
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			//单击关闭按钮是关闭
		setBounds(500, 200, 350, 250);
		contentPane = new JPanel();								//创建面板
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));		//设置边框
		setContentPane(contentPane); 							//应用面板
		contentPane.setLayout(new GridLayout(5, 1, 5, 5));		//设置布局管理器
		JPanel panel1 = new JPanel();							//创建面板
		FlowLayout flowLayout1 = (FlowLayout) panel1.getLayout();
		flowLayout1.setAlignment(FlowLayout.CENTER);
		contentPane.add(panel1);
		JLabel label1 = new JLabel("登录");						//标题
		panel1.add(label1);
		JPanel panel2 = new JPanel();							//创建面板
		FlowLayout flowLayout2 = (FlowLayout) panel2.getLayout();
		flowLayout2.setAlignment(FlowLayout.CENTER);
		contentPane.add(panel2);
		JLabel label2 = new JLabel("用户名：");
		panel2.add(label2);
		username = new JTextField();
		panel2.add(username);
		username.setColumns(18);
		JPanel panel3 = new JPanel();
		FlowLayout flowLayout3 = (FlowLayout) panel3.getLayout();
		flowLayout3.setAlignment(FlowLayout.CENTER);
		contentPane.add(panel3);
		JLabel label3 = new JLabel("密    码：");
		panel3.add(label3);
		password = new JPasswordField();
		password.setColumns(18);
		panel3.add(password);
		JPanel panel4 = new JPanel();
		FlowLayout flowLayout4 = (FlowLayout) panel4.getLayout();
		flowLayout4.setAlignment(FlowLayout.CENTER);
		contentPane.add(panel4);
		panel4.add(a);
		panel4.add(s);
		ButtonGroup group = new ButtonGroup();
		group.add(a);
		group.add(s);
		JPanel panel5 = new JPanel();
		FlowLayout flowLayout5 = (FlowLayout) panel5.getLayout();
		flowLayout5.setAlignment(FlowLayout.CENTER);
		contentPane.add(panel5);
		JButton login = new JButton("登录");
		login.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String uname = username.getText().trim();			//获得用户名
				String pswd = new String(password.getPassword());	//获得密码
				if (s.isSelected()) {
					Student student = new Student();
					student.setSno(uname);
					student.setSpw(pswd);
					if(dStudent.okStudent(student)) {						//如果用户名密码正确
						fStudent s1 = new fStudent(uname);		//创建管理员操作界面
						s1.setVisible(true);
						Toolkit toolkit = s1.getToolkit();	//获得Toolkit对象
						Dimension dm = toolkit.getScreenSize();		//获得屏幕的大小
						//使主屏幕居中
						s1.setLocation((dm.width - s1.getWidth())/2, (dm.height - s1.getHeight())/2);
						dispose();
					}
				} else if (a.isSelected()) {
					Administrator administrator = new Administrator();
					System.out.println("aaa");
					administrator.setAno(uname);
					administrator.setApw(pswd);
					if(dAdministrator.okAdministrator(administrator)) {						//如果用户名密码正确
						fAdministrator a1 = new fAdministrator(uname);		//创建管理员操作界面
						a1.setVisible(true);
						Toolkit toolkit = a1.getToolkit();	//获得Toolkit对象
						Dimension dm = toolkit.getScreenSize();		//获得屏幕的大小
						//使主屏幕居中
						a1.setLocation((dm.width - a1.getWidth())/2, (dm.height - a1.getHeight())/2);
						dispose();
					}
				}
			}
		});
		panel5.add(login);
	}
}
