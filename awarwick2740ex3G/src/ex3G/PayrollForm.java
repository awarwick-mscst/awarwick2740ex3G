package ex3G;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;




import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PayrollForm extends JFrame {

	private JPanel contentPane;
	private JTextField txtHours;
	private JList employeeList;
	private JLabel lblTotal;
	private JLabel lblGross;
	private DefaultListModel  employeeListModel;
	private double totalHours = 0.00;
	private Payroll entry;
	private JTextField txtEmployeeID;
	private JTextField txtEmployeeName;
	private JTextField txtPayrate;
	private PayrollObjMapper payrollObjMapper;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PayrollForm frame = new PayrollForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PayrollForm() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				do_this_windowClosing(e);
			}
		});
		setTitle("Payroll Ex3G ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 504, 463);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSelectEmployee = new JLabel("Select employee:");
		lblSelectEmployee.setBounds(10, 11, 104, 14);
		contentPane.add(lblSelectEmployee);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(38, 54, 383, 159);
		contentPane.add(scrollPane);
		
/*		payRollModel = new DefaultListModel();                            /// Initialize the payRollModel, built in class so you use new
		payRollModel.addElement(new Payroll(101, "Aaron Warwick", 10.0));  // add data
		payRollModel.addElement(new Payroll(102, "Dakota Warwick", 20.0));
		payRollModel.addElement(new Payroll(103, "Mr Aaron Walter", 30.0));
		payRollModel.addElement(new Payroll(104, "Andrea Warwick", 40.0));
		payRollModel.addElement(new Payroll(105, "Susan Warwick", 50.0));*/
		payrollObjMapper = new PayrollObjMapper("Ex3G.txt");
		employeeListModel = payrollObjMapper.getAllPayroll();
		
		
		
		employeeList = new JList(employeeListModel);							// assign the payRollModel to the JList
		
		employeeList.addMouseListener(new MouseAdapter() {
			@Override
			
			public void mouseClicked(MouseEvent arg0) {
				do_employeeList_mouseClicked(arg0);
			}
		});
		
		scrollPane.setViewportView(employeeList);
		
		JLabel lblEmployeeId = new JLabel("Employee ID (>100):");
		lblEmployeeId.setBounds(10, 224, 134, 14);
		contentPane.add(lblEmployeeId);
		
		JLabel lblPayRate = new JLabel("Pay rate (7.25-100):");
		lblPayRate.setBounds(10, 274, 134, 14);
		contentPane.add(lblPayRate);
		
		JLabel lblEnterHours = new JLabel("Enter hours(0.1-20.0):");
		lblEnterHours.setBounds(10, 299, 134, 14);
		contentPane.add(lblEnterHours);
		
		JLabel lblTotalHours = new JLabel("Total hours:");
		lblTotalHours.setBounds(10, 320, 76, 14);
		contentPane.add(lblTotalHours);
		
		JLabel lblGrossPay = new JLabel("Gross Pay:");
		lblGrossPay.setBounds(10, 353, 76, 14);
		contentPane.add(lblGrossPay);
		
		JButton btnClearForm = new JButton("Clear Form");
		btnClearForm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				do_btnClearForm_actionPerformed(arg0);
			}
		});
		btnClearForm.setBounds(262, 391, 104, 23);
		contentPane.add(btnClearForm);
		
		JLabel lblNewLabel = new JLabel("Employee Name:");
		lblNewLabel.setBounds(10, 249, 95, 14);
		contentPane.add(lblNewLabel);
		
		txtHours = new JTextField();
		txtHours.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				do_txtHours_focusGained(arg0);
			}
		});
		txtHours.setHorizontalAlignment(SwingConstants.RIGHT);
		txtHours.setText("0.00");
		txtHours.setBounds(154, 296, 79, 20);
		contentPane.add(txtHours);
		txtHours.setColumns(10);
		
		lblTotal = new JLabel("0.00");
		lblTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotal.setBounds(147, 320, 86, 14);
		contentPane.add(lblTotal);
		
		lblGross = new JLabel("$0.00");
		lblGross.setHorizontalAlignment(SwingConstants.RIGHT);
		lblGross.setBounds(116, 353, 117, 14);
		contentPane.add(lblGross);
		
		JButton btnPlus = new JButton("+");       
		btnPlus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				do_btnPlus_actionPerformed(arg0);
			}
		});
		btnPlus.setBounds(276, 295, 46, 23);
		contentPane.add(btnPlus);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				do_btnClear_actionPerformed(arg0);
			}
		});
		btnClear.setBounds(332, 295, 89, 23);
		contentPane.add(btnClear);
		
		txtEmployeeID = new JTextField();
		txtEmployeeID.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				do_txtEmployeeID_focusGained(arg0);
			}
		});
		txtEmployeeID.setHorizontalAlignment(SwingConstants.RIGHT);
		txtEmployeeID.setText("000");
		txtEmployeeID.setBounds(187, 221, 46, 20);
		contentPane.add(txtEmployeeID);
		txtEmployeeID.setColumns(10);
		
		txtEmployeeName = new JTextField();
		txtEmployeeName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				do_txtEmployeeName_focusGained(e);
			}
		});
		txtEmployeeName.setHorizontalAlignment(SwingConstants.RIGHT);
		txtEmployeeName.setBounds(116, 246, 117, 20);
		contentPane.add(txtEmployeeName);
		txtEmployeeName.setColumns(10);
		
		txtPayrate = new JTextField();
		txtPayrate.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				do_txtPayrate_focusGained(e);
			}
		});
		txtPayrate.setHorizontalAlignment(SwingConstants.RIGHT);
		txtPayrate.setText("7.25");
		txtPayrate.setBounds(154, 271, 79, 20);
		contentPane.add(txtPayrate);
		txtPayrate.setColumns(10);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				do_btnUpdate_actionPerformed(arg0);
			}
		});
		btnUpdate.setBounds(144, 391, 89, 23);
		contentPane.add(btnUpdate);
	}
	protected void do_employeeList_mouseClicked(MouseEvent arg0) {   //selecting data from model and putting it in the form
		Payroll entry = (Payroll) employeeList.getSelectedValue();   // entry is an instance of Payroll class
		txtEmployeeID.setText(Integer.toString(entry.getId()));
		txtEmployeeName.setText(entry.getName());
		txtPayrate.setText(Double.toString(entry.getPayRate()));
		lblTotal.setText(Double.toString(entry.getHours()));
		lblGross.setText(Double.toString(entry.calcGrossPay()));
		
		
	}//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	protected void do_btnPlus_actionPerformed(ActionEvent arg0) {    // add if statement that will check if hours entered is between
		Payroll payroll = (Payroll) employeeList.getSelectedValue(); // .1 and 20 hours]
		if (payroll != null){
			
		
		double hours = Double.parseDouble(txtHours.getText());
		if (!payroll.addHours(hours)){
			JOptionPane.showMessageDialog(null, "Invalid hours. \nMust be more than .1\nLess than 20");
			txtEmployeeID.setText("101");
			txtEmployeeID.requestFocus();
		}
//		payroll.addHours(Double.parseDouble(txtHours.getText()));
		}
		DecimalFormat decimalfmt = new DecimalFormat("####.00");  // decimal format with no commas or $
		lblTotal.setText(decimalfmt.format(payroll.getHours()));
		DecimalFormat dollar = new DecimalFormat("$#,###.00");
		lblGross.setText(dollar.format(payroll.calcGrossPay()));
		txtHours.setText("0.00");
		
//		totalHours = totalHours + Double.parseDouble(txtHours.getText());
////		entry.setHours(totalHours);
//		lblTotal.setText(Double.toString(totalHours));
//		double payRate = Double.parseDouble(lblPay.getText());
//		double gross = totalHours * payRate;
//		lblGross.setText(Double.toString(gross));
		
	}
	protected void do_txtHours_focusGained(FocusEvent arg0) {
		txtHours.selectAll();
	}
	protected void do_btnClear_actionPerformed(ActionEvent arg0) {    // clear the hours in selected instance and sets focus on txtHours
		Payroll payroll = (Payroll) employeeList.getSelectedValue();  // initializing a created class you use cast and not new.
		if (payroll != null){
			payroll.addHours(Double.parseDouble(txtHours.getText()));
			payroll.setHours(0);
			lblTotal.setText(Double.toString(payroll.getHours()));
			lblGross.setText(Double.toString(payroll.calcGrossPay()));
		}
		this.txtHours.requestFocus();
		
	}
	protected void do_btnClearForm_actionPerformed(ActionEvent arg0) {
		txtEmployeeID.setText("0");                                   /// clear all the labels and text box on form
		txtEmployeeName.setText("Name");
		txtPayrate.setText("7.25");
		txtHours.setText("0.00");
		lblTotal.setText("0.00");
		lblGross.setText("$0.00");
		
		this.employeeList.clearSelection();
	}
	protected void do_btnUpdate_actionPerformed(ActionEvent arg0) {
		int id = Integer.parseInt(txtEmployeeID.getText());
		double rate = Double.parseDouble(txtPayrate.getText());
		String name = txtEmployeeName.getText();
		Payroll payroll = (Payroll) employeeList.getSelectedValue();
		if (payroll != null){
			
		
			if (!payroll.setId(id)) {
				JOptionPane.showMessageDialog(null, "Invalid employee ID. \nMust be > 100");
				txtEmployeeID.setText("101");
				txtEmployeeID.requestFocus();
			}
			
			if (!payroll.setName(name)) {
				JOptionPane.showMessageDialog(null, "Invalid Name, must have atleast 1 char");
				
			}
			else {
				txtEmployeeName.setText(payroll.getName());
				
			}
			
			
			if (!payroll.setPayRate(rate)) {
				JOptionPane.showMessageDialog(null, "Invalid Pay rate. \nMust be 7.25 to 100");
			}
			else {
				txtPayrate.setText(Double.toString(payroll.getPayRate()));
				lblGross.setText(Double.toString(payroll.calcGrossPay()));
			}
			
			employeeList.repaint();
		}
	}
	protected void do_txtEmployeeID_focusGained(FocusEvent arg0) {
	txtEmployeeID.selectAll();
	}
	protected void do_txtEmployeeName_focusGained(FocusEvent e) {
	txtEmployeeName.selectAll();
	}
	protected void do_txtPayrate_focusGained(FocusEvent e) {
	txtPayrate.selectAll();
	
	}
	protected void do_this_windowClosing(WindowEvent e) {
		if (payrollObjMapper != null) 
			payrollObjMapper.writeAllPayroll(employeeListModel);
	}
}
