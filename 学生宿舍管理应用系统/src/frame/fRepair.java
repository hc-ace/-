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
import Dao.dRepair;
import model.Repair;

public class fRepair extends JFrame
{
	private static final long serialVersionUID = 1L;
	private JTextField dno = null;
	private JTextField gno = null;
	private JTextField bno = null;
	private JTextField Rsubmit = null;
	private JTextField Rreason = null;
	private JTextField Rstate = null;
	private JPanel contentPane;
	private int j = 0;
	private int row = -1;
	private String gdno;
	private String ggno;
	private String gbno;
	private String gRsubmit;
	private String gRreason;
	private String gRstate;
	public static DefaultTableModel dtm;	
	public fRepair() 
	{
		setTitle("报修管理");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);			//单击关闭按钮是关闭
		setSize(800, 600);
		setVisible(true);
		contentPane = new JPanel();
		setContentPane(contentPane);	
		JTable table = new JTable();
		String[] tableHeads = new String[]{"宿舍号","宿舍楼号","物品号","报修日期","报修原因","状态"};
		dtm = (DefaultTableModel)table.getModel();
		dtm.setColumnIdentifiers(tableHeads);
		ArrayList<Repair> list = (ArrayList<Repair>) dRepair.getallRepair();
		for(j = 0; j < list.size(); j++) {
			Vector v = new Vector();
			v.add(list.get(j).getDno());
			v.add(list.get(j).getBno());
			v.add(list.get(j).getGno());
			v.add(list.get(j).getRsubmit());
			v.add(list.get(j).getRreason());	
			v.add(list.get(j).getRstate());
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
		
		JLabel label7 = new JLabel("宿舍楼号：");
		panel7.add(label7);
		panel9.setBorder(new EmptyBorder(0, 20, 0, 0));
		
		bno = new JTextField();
		bno.setColumns(10);
		panel7.add(bno);
		
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
				if (!bno.getText().equals("")) {
					bno.setText("");
				}			
				dtm.setRowCount(0);
				ArrayList<Repair> list = (ArrayList<Repair>) dRepair.getallRepair();
				  
				for(j = 0; j < list.size(); j++) {
					Vector v = new Vector();
					v.add(list.get(j).getDno());
					v.add(list.get(j).getBno());
					v.add(list.get(j).getGno());
					v.add(list.get(j).getRsubmit());
					v.add(list.get(j).getRreason());	
					v.add(list.get(j).getRstate());
					dtm.addRow(v);
				}	  
			}
		});
		select.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (bno.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "宿舍楼号不能为空");
				}else {
					dtm.setRowCount(0);
					gbno = bno.getText().trim();
					ArrayList<Repair> list = (ArrayList<Repair>) dRepair.getRepair(gbno);
					  
					for(j = 0; j < list.size(); j++) {
						Vector v = new Vector();
						v.add(list.get(j).getDno());
						v.add(list.get(j).getBno());
						v.add(list.get(j).getGno());
						v.add(list.get(j).getRsubmit());
						v.add(list.get(j).getRreason());	
						v.add(list.get(j).getRstate());
						dtm.addRow(v);	
					}
				}
			}
		});
	}
}
