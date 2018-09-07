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
			setTitle("�޸�����");
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);			//�����رհ�ť�ǹر�
			setSize(300, 270);
			setVisible(true);
			
			contentPane = new JPanel();								//�������
			setContentPane(contentPane); 
			
			JPanel panel1 = new JPanel();
			JLabel label1 = new JLabel("�����룺");
			panel1.add(label1);
			psd = new JPasswordField();
			panel1.add(psd);
			psd.setColumns(15);
			panel1.setBorder(new EmptyBorder(15, 13, 0, 0));

			JPanel panel2 = new JPanel();
			JLabel label2 = new JLabel("�����룺");
			panel2.add(label2);
			npsd = new JPasswordField();
			panel2.add(npsd);
			npsd.setColumns(15);
			panel2.setBorder(new EmptyBorder(10, 13, 0, 0));

			JPanel panel3 = new JPanel();
			JLabel label3 = new JLabel("ȷ�����룺");
			panel3.add(label3);
			npsd2 = new JPasswordField();
			panel3.add(npsd2);
			npsd2.setColumns(15);
			panel3.setBorder(new EmptyBorder(10, 0, 0, 0));
			
			JPanel panel4 = new JPanel();
			JButton change = new JButton("�޸�");
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
							JOptionPane.showMessageDialog(null, "�޸ĳɹ�,�����µ�¼");
							dispose();
						}else {
							JOptionPane.showMessageDialog(null, "�������벻һ��");
						}		
				}
			});
		}
}
