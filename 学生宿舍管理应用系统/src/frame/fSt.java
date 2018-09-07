package frame;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Dao.dStudent;
import model.Student;

public class fSt extends JFrame
{
	private static final long serialVersionUID = 1L;
	private JTextField sno = null;
	private JTextField gno = null;
	private JTextField bno = null;
	private JTextField Sname = null;
	private JTextField Ssex = null;
	private JTextField Sbirthday = null;
	private JTextField Sgrade = null;
	private JTextField Sdept = null;
	private JTextField Spw = null;
	private JPanel contentPane;
	private int j = 0;
	private int row = -1;
	private String gdno;
	private String gsno;
	private String gbno;
	public static DefaultTableModel dtm;	
	public fSt() 
	{
		setTitle("学生管理");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);			//单击关闭按钮是关闭
		setSize(800, 600);
		setVisible(true);
		contentPane = new JPanel();
		setContentPane(contentPane);	
		JTable table = new JTable();
		String[] tableHeads = new String[]{"学号","姓名","性别","出生月日","年级","院系","宿舍号","宿舍楼号","密码"};
		dtm = (DefaultTableModel)table.getModel();
		dtm.setColumnIdentifiers(tableHeads);
		ArrayList<Student> list = (ArrayList<Student>) dStudent.getallStudent();
		for(j = 0; j < list.size(); j++) {
			Vector v = new Vector();
			v.add(list.get(j).getSno());
			v.add(list.get(j).getSname());
			v.add(list.get(j).getSsex());
			v.add(list.get(j).getSbirthday());
			v.add(list.get(j).getSdept());
			v.add(list.get(j).getDno());	
			v.add(list.get(j).getBno());
			v.add(list.get(j).getSpw());
			dtm.addRow(v);
		}	  
		table.setPreferredScrollableViewportSize(new Dimension(760,350));	  
		table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);;
		table.getTableHeader().setReorderingAllowed(false);
		  	
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setSize(700, 500);
		JPanel panel = new JPanel();
		panel.add(scrollPane);	
		JPanel panel7 = new JPanel();
		JPanel panel8 = new JPanel();
		JPanel panel9 = new JPanel();
		
		JLabel label7 = new JLabel("学号：");
		panel7.add(label7);
		panel9.setBorder(new EmptyBorder(0, 20, 0, 0));
		
		sno = new JTextField();
		sno.setColumns(10);
		panel7.add(sno);
		
		JButton select = new JButton("查找");
		panel8.add(select);
		panel8.setBorder(new EmptyBorder(0, 20, 0, 0));
		
		JButton clear = new JButton("清空");
		panel9.add(clear);
		panel9.setBorder(new EmptyBorder(0, 20, 0, 0));
		contentPane.add(panel);
		contentPane.add(panel7);
		contentPane.add(panel8);
		contentPane.add(panel9);
		
		clear.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub				
				if (!sno.getText().equals("")) {
					sno.setText("");
				}			
				dtm.setRowCount(0);
				ArrayList<Student> list = (ArrayList<Student>) dStudent.getallStudent();
				for(j = 0; j < list.size(); j++) {
					Vector v = new Vector();
					v.add(list.get(j).getSno());
					v.add(list.get(j).getSname());
					v.add(list.get(j).getSdept());
					v.add(list.get(j).getSbirthday());
					v.add(list.get(j).getSgrade());	
					v.add(list.get(j).getSdept());
					v.add(list.get(j).getDno());	
					v.add(list.get(j).getBno());
					v.add(list.get(j).getSpw());
					dtm.addRow(v);
				}	  
			}
		});
		select.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println(sno.getText());
				if (sno.getText().equals("")) 
				{
					JOptionPane.showMessageDialog(null, "学号不能为空");
				}else 
				{
					dtm.setRowCount(0);
					gsno = sno.getText().trim();
					Student r = new Student();		
					dtm.setRowCount(0);					
					r = dStudent.getStudent(sno.getText());	  
					Vector v = new Vector();
					v.add(r.getSno());
					v.add(r.getSname());
					v.add(r.getSdept());
					v.add(r.getSbirthday());
					v.add(r.getSgrade());	
					v.add(r.getSdept());
					v.add(r.getDno());	
					v.add(r.getBno());
					v.add(r.getSpw());				  
					dtm.addRow(v);	
				}			
			}
		});
	}
}
