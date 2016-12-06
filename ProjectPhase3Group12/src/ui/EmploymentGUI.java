/**
 * 
 */
package ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import employment.Employer;
import employment.Skill;
import student.Student;

/**
 * @author Andrew,Brandon,Brian
 *
 */
public class EmploymentGUI extends JPanel 
implements ActionListener,TableModelListener {
	
	
	private Employer emptoaddSkills;
	private static final long serialVersionUID = 644843L;
	private JButton mBtnEmploymentList,mBtnAdd, mBtnAddSkills;
	private ArrayList<Employer> mEmployerList;
	private Student mStudent;

	//variables for emp. table
	private String[] mEmploymentColumnNames = {"name","salary","startDate","position"};
	private JPanel mPnlList;
	private JPanel mPnlBothList;
	private JPanel mPnlButtons;
	private Object[][] mData;
	private JTable mTable;
	private JScrollPane mScrollPane;
	private JLabel mEmpArrayLbl,mSkillArrayLbl;
	
	//variables for skill table
	private String[] mSkillColumnNames  = {"employerName","name"};
	private Object[][] mSkillData;
	private JTable mSkillTable;
	private JScrollPane mSkillScrollPane;
	
	//variables to add new Skill
	private JPanel mPnlAddSkill;
	private JLabel mTxfLabelEmployerText;
	private JLabel mTxfLabelEmployerID;
	private JLabel mTxfSkillLabel;
	private JTextField mTxfSkill;
	private JButton mBtnAddSkillToEmp;

	//variables to add new employment
	private JPanel mPnlAdd;
	private JLabel[] txfLabel = new JLabel[10];
	private JTextField[] txfField = new JTextField[10];
	private JButton mBtnAddEmployment;


	public EmploymentGUI(Student theStudent){
		mStudent = theStudent;
		setLayout(new BorderLayout());
		mEmployerList = getData(mStudent); 
		
		getSkillData();
		
		createComponents();
		
		setVisible(true);
		setSize(500, 500);
	}


	private void getSkillData() {
		int i = 0;
		mSkillData= new Object[25][2];
		for(Employer emp: mEmployerList ){
			
			mSkillData[i][0] = emp.getCompanyName();
			for(String skill:emp.getSkills() ){
				
				mSkillData[i][1]= skill;
				i++;
			}
			
		}
		
	}


	private ArrayList<Employer> getData(Student theStudent) {
		System.out.println("made it here1");
		try{
		mEmployerList = theStudent.getEmployers();
		}catch(Exception e){}
		if(mEmployerList != null){
			
			mData = new Object[mEmployerList.size()][mEmploymentColumnNames.length];
			for (int i = 0; i < mEmployerList.size(); i++) {
				mData[i][0] = mEmployerList.get(i).getCompanyName();
				//TODO maybe need to make parse sal as string
				mData[i][1] = mEmployerList.get(i).getSalary();
				mData[i][2] = mEmployerList.get(i).getStartDate();
				mData[i][3] = mEmployerList.get(i).getPosition();
			}
		}


		return mEmployerList;
	}


	private void createComponents() {

		// The Top Most Panel That Allows Adding Employer and Listing Employer
		mPnlButtons = new JPanel();

		mBtnEmploymentList = new JButton("Employment List");
		mBtnEmploymentList.addActionListener(this);

		mBtnAdd = new JButton("Add Employer");
		mBtnAdd.addActionListener(this);
		
		mBtnAddSkills = new JButton("Add Skills");
		mBtnAddSkills.addActionListener(this);

		mPnlButtons.add(mBtnEmploymentList);
		mPnlButtons.add(mBtnAdd);
		mPnlButtons.add(mBtnAddSkills);

		add(mPnlButtons, BorderLayout.NORTH);

		//main panel that list employers for a student
		mPnlList = new JPanel();
		
		//create a panel for center of main panel
		mPnlBothList = new JPanel(new GridLayout(2,0));
		//mEmpArrayLbl = new JLabel("Employers:");
		mTable = new JTable(mData,mEmploymentColumnNames);
		
		mScrollPane = new JScrollPane(mTable);
	
		//mPnlBothList.add(mEmpArrayLbl);
		mPnlBothList.add(mScrollPane);
		mTable.getModel().addTableModelListener(this);
	
		//mSkillArrayLbl = new JLabel("Skills:");
		mSkillTable = new JTable(mSkillData,mSkillColumnNames);
		mSkillScrollPane = new JScrollPane(mSkillTable);
		//mPnlBothList.add(mSkillArrayLbl);
		mPnlBothList.add(mSkillScrollPane);
		
		mPnlList.add(mPnlBothList);
		System.out.println("after center panel");
		//Add Panel- allows input to add a new employer
		mPnlAdd = new JPanel();
		mPnlAdd.setLayout(new GridLayout(0,1));
		String mLabelName[] = {"Enter Employer Name:","Enter Salary:","Enter Start Date:","Enter Position:"};

		System.out.println("before for loop");
		for (int i = 0; i < mLabelName.length; i++) {
			JPanel panel = new JPanel();
			;
			if(mLabelName[i] == "Enter Start Date:"){
			JPanel datePanel = new JPanel();
			datePanel.setLayout(new GridLayout(1, 7));	
			
			txfLabel[i] = new JLabel(mLabelName[i]);
			txfLabel[6] = new JLabel("   Month(mm):");
			txfField[6] = new JTextField(2);
			txfLabel[7] = new JLabel("   Day(dd):");
			txfField[7] = new JTextField(2);
			txfLabel[8] = new JLabel("   Year(yyyy):");
			txfField[8] = new JTextField(2);
			
			datePanel.add(txfLabel[i]);
			datePanel.add(txfLabel[6]);
			datePanel.add(txfField[6]);
			datePanel.add(txfLabel[7]);
			datePanel.add(txfField[7]);
			datePanel.add(txfLabel[8]);
			datePanel.add(txfField[8]);
			
			mPnlAdd.add(datePanel);
			}else{
			panel.setLayout(new GridLayout(1, 1));
			txfLabel[i] = new JLabel(mLabelName[i]);
			txfField[i] = new JTextField(25);
			panel.add(txfLabel[i]);
			panel.add(txfField[i]);
			mPnlAdd.add(panel);
			}
			
			
		}
		//Add new Skill
		mPnlAddSkill = new JPanel();
		mTxfLabelEmployerText = new JLabel("You are adding skills to:    ");
		mTxfLabelEmployerID= new JLabel(emptoaddSkills.getCompanyName());
		mTxfSkillLabel= new JLabel("Add A Skill: ");;
		mTxfSkill = new JTextField(25);
		mBtnAddSkillToEmp = new JButton("Add Skill");
		mBtnAddSkillToEmp.addActionListener(this);
		mPnlAddSkill.setLayout(new GridLayout(3,2));
		mPnlAddSkill.add(mTxfLabelEmployerText);
		mPnlAddSkill.add(mTxfLabelEmployerID);
		mPnlAddSkill.add(mTxfSkillLabel);
		mPnlAddSkill.add(mTxfSkill);
		mPnlAddSkill.add(mBtnAddSkillToEmp);
		
		
		System.out.println("before add to Jpanel");
		JPanel mPanel = new JPanel();
		mBtnAddEmployment = new JButton("Add");
		mBtnAddEmployment.addActionListener(this);
		mPanel.add(mBtnAddEmployment);
		mPnlAdd.add(mPanel);
		System.out.println("after add to Jpanel");
		add(mPnlList,BorderLayout.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	
		
		if(e.getSource() == mBtnEmploymentList){
			mEmployerList = getData(mStudent);
			mPnlList.removeAll();
			
			mPnlList.add(mPnlBothList);
			
			mPnlList.revalidate();
			this.repaint();
			
		} else if(e.getSource() == mBtnAdd){
			mPnlList.removeAll();
			mPnlList.add(mPnlAdd);
			mPnlList.revalidate();
			this.repaint();
			
		}  else if(e.getSource() == mBtnAddSkills){
			if(emptoaddSkills.getCompanyName() != null){
			mPnlList.removeAll();
			mPnlList.add(mPnlAddSkill);
			
			mPnlList.revalidate();
			this.repaint();}
			else{
				JOptionPane.showMessageDialog(null, "Select an employer from the list");
			}
			
		} else if(e.getSource() == mBtnAddEmployment){
			performAddEmployment();
			
		}  else if(e.getSource() == mBtnAddSkillToEmp){
			performAddSkills();
			
		}
			
	}

	private void performAddSkills() {
		String mEmpName = mTxfSkill.getText();
		if (mEmpName.length() == 0) {
			JOptionPane.showMessageDialog(null, "Enter a valid skill");
			mTxfSkill.setFocusable(true);
			return;
		}
		emptoaddSkills.addSkill(mEmpName);
		
	}


	private void performAddEmployment() {
		String mEmpName = txfField[0].getText();
		if (mEmpName.length() == 0) {
			JOptionPane.showMessageDialog(null, "Enter a valid Employer name");
			txfField[0].setFocusable(true);
			return;
		}
		String salaryStr = txfField[1].getText();
		double salary = 0.0;
		if (salaryStr.length() != 0) {
			try {
				salary = Double.parseDouble(salaryStr);
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Enter Salary as decimal");
				txfField[1].setText("");
				txfField[1].setFocusable(true);
				return;
			}
		}
		
		
		String mMonth = txfField[6].getText();
		if (mMonth.length() != 2) {
			JOptionPane.showMessageDialog(null, "Enter Month As mm");
			txfField[6].setFocusable(true);
			return;
		}
		String mDay = txfField[7].getText();
		if (mDay.length() != 2) {
			JOptionPane.showMessageDialog(null, "Enter Day As dd");
			txfField[7].setFocusable(true);
			return;
		}
		String mYear =txfField[8].getText();
		if (mMonth.length() != 4) {
			JOptionPane.showMessageDialog(null, "Enter Month As yyyy");
			txfField[8].setFocusable(true);
			return;
		}
		
		String mDate = mYear + "-" + mMonth + "-" + mDay; 
		
		
	
		String mPosition = txfField[3].getText();
		if (mPosition.length() == 0) {
			JOptionPane.showMessageDialog(null, "Enter A Position:");
			txfField[3].setFocusable(true);
			return;
		}
		
		//Adding To the collection
		Employer mEmp;
		mEmp = new Employer(mEmpName, mDate, salary,mPosition);
		
		
		String message = "Employer add failed";
		if (mStudent.addEmployer(mEmp)) {
			message = "Item added";
		}
		JOptionPane.showMessageDialog(null, message);

		// Clear all text fields.
		for (int i = 0; i < txfField.length; i++) {
			if (txfField[i].getText().length() != 0) {
				txfField[i].setText("");
			}
		}
		
	}


	@Override
	public void tableChanged(TableModelEvent e) {
		// TODO Auto-generated method stub
		int row = e.getFirstRow();
		int column = e.getColumn();
		TableModel model = (TableModel) e.getSource();
		String columnName = model.getColumnName(column);
		Object data = model.getValueAt(row, column);
		if (data != null && ((String) data).length() != 0) {
			
			try{
			Employer item = mEmployerList.get(row);
			emptoaddSkills = item;
			if (columnName == "name") {
				mStudent.getEmployer(item.getID()).setCompanyName((String)data);
			}
			if (columnName == "salary") {
				mStudent.getEmployer(item.getID()).setSalary((Double.parseDouble((String)data)));
			}
			if (columnName == "startDate") {
				mStudent.getEmployer(item.getID()).setStartDate((String)data);
			}
			if (columnName == "position") {
				mStudent.getEmployer(item.getID()).setPosition((String)data);
			}
			}catch(Exception e4){
				JOptionPane.showMessageDialog(null, "Update failed");
			}
			
		}

	}
		
		

}
