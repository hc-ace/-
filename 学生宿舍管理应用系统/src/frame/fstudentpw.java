package frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import Dao.dStudent;
import model.Student;

public class fstudentpw extends JFrame
{
	private static final long serialVersionUID = 1L;
	private JPasswordField psd = null;
	private JPasswordField npsd = null;
	private JPasswordField npsd2 = null;
	private JPanel contentPane;
	private String gpsd;
	private String gnpsd;
	private String gnpsd2;
	
		public fstudentpw(String pw) {
			setTitle("修改密码");
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);			//单击关闭按钮是关闭
			setSize(300, 270);
			setVisible(true);
			
			contentPane = new JPanel();								//创建面板
			setContentPane(contentPane); 
			
			JPanel panel1 = new JPanel();
			JLabel label1 = new JLabel("旧密码：");
			panel1.add(label1);
			psd = new JPasswordField();
			panel1.add(psd);
			psd.setColumns(15);
			panel1.setBorder(new EmptyBorder(15, 13, 0, 0));

			JPanel panel2 = new JPanel();
			JLabel label2 = new JLabel("新密码：");
			panel2.add(label2);
			npsd = new JPasswordField();
			panel2.add(npsd);
			npsd.setColumns(15);
			panel2.setBorder(new EmptyBorder(10, 13, 0, 0));

			JPanel panel3 = new JPanel();
			JLabel label3 = new JLabel("确认密码：");
			panel3.add(label3);
			npsd2 = new JPasswordField();
			panel3.add(npsd2);
			npsd2.setColumns(15);
			panel3.setBorder(new EmptyBorder(10, 0, 0, 0));
			
			JPanel panel4 = new JPanel();
			JButton change = new JButton("修改");
			panel4.add(change);
			panel4.setBorder(new EmptyBorder(10, 0, 0, 0));
			
			contentPane.add(panel1);
			contentPane.add(panel2);
			contentPane.add(panel3);
			contentPane.add(panel4);
			
			change.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					gpsd = new String(psd.getPassword());
					gnpsd = new String(npsd.getPassword());
					gnpsd2 = new String(npsd2.getPassword());
					Student s = new Student();
					s.setSno(pw);
					s.setSpw(gpsd);
						if (gnpsd.equals(gnpsd2)) {
							s.setSpw(gnpsd);
							dStudent.editStudent2(s);
							JOptionPane.showMessageDialog(null, "修改成功,请重新登录");
							dispose();
						}else {
							JOptionPane.showMessageDialog(null, "两次密码不一致");
						}		
				}
			});
		}
}
