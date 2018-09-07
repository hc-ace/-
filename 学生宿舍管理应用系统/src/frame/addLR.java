package frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Dao.dLR;
import model.LR;

public class addLR extends JFrame
{
	private static final long serialVersionUID = 1L;
	JPanel contentPane;
	
	private JTextField Dno = null;
	private JTextField Sno = null;
	private JTextField Bno = null;
	private JTextField Leave = null;
	private JTextField Lreturn = null;
	
	private static LR r = new LR();
	
	public addLR(){
		setTitle("离返校申请");									//设置窗体标题
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);		//单击关闭按钮是关闭
		setSize(350, 550);
		contentPane = new JPanel();								//创建面板
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));		//设置边框
		setContentPane(contentPane); 							//应用面板
		
		JPanel panel1 = new JPanel();
		JLabel label1 = new JLabel("宿舍号：");
		panel1.add(label1);
		Dno = new JTextField();
		panel1.add(Dno);
		Dno.setColumns(10);
		panel1.setBorder(new EmptyBorder(10, 13, 10, 20));
		
		JPanel panel2 = new JPanel();
		JLabel label2 = new JLabel("宿舍楼号：");
		panel2.add(label2);
		Bno = new JTextField();
		panel2.add(Bno);
		Bno.setColumns(10);
		panel2.setBorder(new EmptyBorder(10, 33, 10, 20));
		
		JPanel panel3 = new JPanel();
		JLabel label3 = new JLabel("学号：");
		panel3.add(label3);
		Sno = new JTextField();
		panel3.add(Sno);
		Sno.setColumns(10);
		panel3.setBorder(new EmptyBorder(10, 33, 10, 20));
		
		JPanel panel4 = new JPanel();
		JLabel label4 = new JLabel("离校时间：");
		panel4.add(label4);
		Leave = new JTextField();
		panel4.add(Leave);
		Leave.setColumns(10);
		panel4.setBorder(new EmptyBorder(10, 33, 10, 20));
		
		JPanel panel5 = new JPanel();
		JLabel label6 = new JLabel("返校原因：");
		panel5.add(label6);
		Lreturn = new JTextField();
		panel5.add(Lreturn);
		Lreturn.setColumns(10);
		panel5.setBorder(new EmptyBorder(10, 23, 10, 20));
		
		JButton add = new JButton("添加");
		JPanel panel6 = new JPanel();
		panel6.add(add);
		panel6.setBorder(new EmptyBorder(5, 33, 10, 20));
		
		contentPane.add(panel1);
		contentPane.add(panel2);
		contentPane.add(panel3);
		contentPane.add(panel4);
		contentPane.add(panel5);
		contentPane.add(panel6);
		
		add.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				r.setBno(Bno.getText());
				r.setSno(Sno.getText());
				r.setDno(Dno.getText());
				r.setLeave(Timestamp.valueOf(Leave.getText()));
				r.setLreturn(Timestamp.valueOf(Leave.getText()));
				dLR.addLR(r);
				JOptionPane.showMessageDialog(null, "添加成功");
				dispose();
			}
		});
	}
}
