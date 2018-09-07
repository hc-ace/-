package frame;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import Dao.dStudent;
import model.Student;

public class fstudent1 extends JFrame
{
	private static final long serialVersionUID = 1L;
	JPanel contentPane;
	
	private JTextField Sno = null;
	private JTextField Sname = null;
	private JTextField Ssex = null;
	private JTextField Sdept = null;
	private JTextField Sgrade = null;
	private JTextField Dno = null;
	private JTextField Bno = null;
	private static Student s = new Student();
	public fstudent1(String sno)
	{
		setTitle("�ҵ���Ϣ");								//���ô������
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);			//�����رհ�ť�ǹر�
		setSize(350, 550);
		contentPane = new JPanel();								//�������
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));		//���ñ߿�
		setContentPane(contentPane); 							//Ӧ�����
		JPanel panel1 = new JPanel();
		JLabel label1 = new JLabel("ѧ�ţ�");
		panel1.add(label1);
		Sno = new JTextField();
		panel1.add(Sno);
		Sno.setColumns(10);
		panel1.setBorder(new EmptyBorder(10, 33, 10, 20));
		
		JPanel panel2 = new JPanel();
		JLabel label2 = new JLabel("������");
		panel2.add(label2);
		Sname = new JTextField();
		panel2.add(Sname);
		Sname.setColumns(10);
		panel2.setBorder(new EmptyBorder(10, 33, 10, 20));
		
		JPanel panel3 = new JPanel();
		JLabel label3 = new JLabel("�Ա�");
		panel3.add(label3);
		Ssex = new JTextField();
		panel3.add(Ssex);
		Ssex.setColumns(10);
		panel3.setBorder(new EmptyBorder(10, 33, 10, 20));
		
		JPanel panel5 = new JPanel();
		JLabel label6 = new JLabel("����ϵ��");
		panel5.add(label6);
		Sdept = new JTextField();
		panel5.add(Sdept);
		Sdept.setColumns(10);
		panel5.setBorder(new EmptyBorder(10, 33, 10, 20));
		
		JPanel panel6 = new JPanel();
		JLabel label5 = new JLabel("�꼶��");
		panel6.add(label5);
		Sgrade = new JTextField();
		panel6.add(Sgrade);
		Sgrade.setColumns(10);
		Sgrade.setColumns(10);
		panel6.setBorder(new EmptyBorder(10, 33, 10, 20));
		
		JPanel panel7 = new JPanel();
		JLabel label7 = new JLabel("����ţ�");
		panel7.add(label7);
		Dno = new JTextField();
		panel7.add(Dno);
		Dno.setColumns(10);
		panel7.setBorder(new EmptyBorder(10, 33, 10, 20));
		
		JPanel panel8 = new JPanel();
		JLabel label8 = new JLabel("����¥�ţ�");
		panel8.add(label8);
		Bno = new JTextField();
		panel8.add(Bno);
		Bno.setColumns(10);
		panel8.setBorder(new EmptyBorder(10, 33, 10, 20));
	
		contentPane.add(panel1);
		contentPane.add(panel2);
		contentPane.add(panel3);
		contentPane.add(panel5);
		contentPane.add(panel6);
		contentPane.add(panel7);
		contentPane.add(panel8);
		
		s = dStudent.getStudent(sno);
		Sno.setText(s.getSno());
		Sname.setText(s.getSname());
		Ssex.setText(s.getSsex());
		Sgrade.setText(String.valueOf(s.getSgrade()));
		Sdept.setText(s.getSdept());
		Dno.setText(s.getDno());
		Bno.setText(s.getBno());
		
	}
}
