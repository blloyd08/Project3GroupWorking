/**
 * 
 */
package ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

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

import academic.AcademicCollection;
import academic.AcademicRecord;
import academic.TransferSchool;
import employment.Employer;
import employment.EmployerCollection;
import student.Student;

/**
 * @author Andrew,Brandon,Brian
 *
 */
public class AcademicGUI extends JPanel 
implements ActionListener,TableModelListener, Observer {
	
	private static final long serialVersionUID = 643323L;
	private JButton mBtnAcademicList,mBtnAddEditAcademic, mBtnAddTrans;
	private List<AcademicRecord> mAcademicList;
	private List<TransferSchool> mTransferList;
	private Student mStudent;

	//variables for transfer table
	private String[] mAcademicStrings = {"program","degreeLevel","graduationTerm","graduationYear","uwEmail","externalEmail","GPA"};
	private JPanel mPnlList;
	private JPanel mPnlButtons;
	private Object[][] mData;
	private JTable mTable;
	private JScrollPane mScrollPane;
	private JLabel mLblAcademic,mLblTransfer;
	
	//variables for academic record
	private JPanel mStudentCurrentAcademicPnl;
	private JLabel[] mAcaRecord = new JLabel[14];
	
	//variables to add new academic record
	private JPanel mPnlAdd;
	private JPanel mPnlAcademic;
	private JLabel[] txfLabel = new JLabel[10];
	private JTextField[] txfField = new JTextField[10];
	private JButton mBtnAddAcad;
	
	
	//variables for transfer table
	private String[] mTransferStrings = {"name","GPA","degreeEarned"};
	
	
	//variables to add/edit new transfer
	private JPanel mPnlAddtrans;
	private JLabel[] txfLabeltrans = new JLabel[4];
	private JTextField[] txfFieldtrans = new JTextField[4];
	private JButton mBtnAddtransfer;
	
	
	public AcademicGUI(Student theStudent){
		mStudent = theStudent;
		setLayout(new BorderLayout());
		//mAcademicList = getAcadData(theStudentID); 	
		mTransferList = getTransData(theStudent);
		createComponents();
		setVisible(true);
		setSize(500, 500);
		
	}
	
	
	


	private List<TransferSchool> getTransData(Student theStudent) {
		try{
			mTransferList = theStudent.getAcademicRecord().getTransferSchools();
		}catch(Exception e){	
		}
		if(mTransferList != null){
			mData = new Object[mTransferList.size()][mTransferStrings.length];
			for(int i = 0; i< mTransferList.size(); i++){
				mData[i][0] = mTransferList.get(i).getName();
				//TODO Maybe pars gpa as string
				mData[i][1] = Double.toString(mTransferList.get(i).getGPA());
				mData[i][2] = mTransferList.get(i).getName();
			}
			
			
		}
			
		
		return mTransferList;
	}



	//TODO Delete
	private List<AcademicRecord> getAcadData(String theStudentID) {
		//mAcademicList = AcademicCollection.getAcademicRecord(theStudentID);

		if(mAcademicList != null){
			mData = new Object[mAcademicList.size()][mAcademicStrings.length];
			for (int i = 0; i < mAcademicList.size(); i++) {
				mData[i][0] = mAcademicList.get(i).getProgram();
				mData[i][1] = mAcademicList.get(i).getDegreeLevel();
				mData[i][2] = mAcademicList.get(i).getGraduationTerm();
				mData[i][3] = mAcademicList.get(i).getGraduationYear();
				mData[i][4] = mAcademicList.get(i).getUWEmail();
				mData[i][5] = mAcademicList.get(i).getExternalEmail();
				mData[i][6] = Double.toString(mAcademicList.get(i).getGPA());
		
			}
		}


		return mAcademicList;
	}

	private void createComponents() {
		// The Top Most Panel That Allows Adding Employer and Listing Employer
				mPnlButtons = new JPanel();

				mBtnAcademicList = new JButton("Academic List");
				mBtnAcademicList.addActionListener(this);

				mBtnAddEditAcademic = new JButton("Add/Edit Academic");
				mBtnAddEditAcademic.addActionListener(this);
				
				mBtnAddTrans = new JButton("Add Transfer School");
				mBtnAddTrans.addActionListener(this);

				mPnlButtons.add(mBtnAcademicList);
				mPnlButtons.add(mBtnAddEditAcademic);
				mPnlButtons.add(mBtnAddTrans);

				add(mPnlButtons, BorderLayout.NORTH);

				//main panel that list academic and transferSchools for a student
				mPnlList = new JPanel();
				mStudentCurrentAcademicPnl=createAcaPnl();
				
				
				
				
				mTable = new JTable(mData,mTransferStrings);
				mScrollPane = new JScrollPane(mTable);
				
				
				
				mPnlList.add(mStudentCurrentAcademicPnl);
				mPnlList.add(mTable);
				
				mTable.getModel().addTableModelListener(this);
				

				//Add Panel- allows User to add/edit academic and tra
				mPnlAdd = new JPanel();
				
			
				mPnlAcademic = createEditAcaPnl();
				
				
				
				mBtnAddAcad = new JButton("Add/Edit Academic Info:");
				mBtnAddAcad.addActionListener(this);
				mPnlAdd.add(mPnlAcademic);
				mPnlAdd.add(mBtnAddAcad);
				
				//Create Transfer Add panel
				mPnlAddtrans = new JPanel();
				
				for (int i = 0; i < mTransferStrings.length; i++) {
					JPanel panel = new JPanel();
					
					panel.setLayout(new GridLayout(1, 0));
					txfLabeltrans[i] = new JLabel(mTransferStrings[i]);
					txfFieldtrans[i] = new JTextField(10);
					panel.add(txfLabeltrans[i]);
					panel.add(txfFieldtrans[i]);
					mPnlAddtrans.add(panel);
				}
				
				mBtnAddtransfer = new JButton("Add Transfer Information:");
				mBtnAddtransfer.addActionListener(this);
				mPnlAddtrans.add(mBtnAddtransfer);
				
				
				
				add(mPnlList,BorderLayout.CENTER);
			}
		

private JPanel createEditAcaPnl() {
	
	
	String mLabelName[] = {"Enter Program Name:","Enter Degree Level:","Enter Graduation Term:","Enter Graduation Year:","Enter UW Email:",
									"Enter External Email:", "Enter GPA:"};
	mPnlAcademic = new JPanel();
	mPnlAcademic.setLayout(new GridLayout(7,0));
	for (int i = 0; i < mLabelName.length; i++) {
		JPanel panel = new JPanel();
		
		panel.setLayout(new GridLayout(1, 0));
		txfLabel[i] = new JLabel(mLabelName[i]);
		txfField[i] = new JTextField(25);
		panel.add(txfLabel[i]);
		panel.add(txfField[i]);
		mPnlAcademic.add(panel);
	}
		
		txfField[0].setText(mStudent.getAcademicRecord().getProgram());
		txfField[1].setText(mStudent.getAcademicRecord().getDegreeLevel());
		txfField[2].setText(mStudent.getAcademicRecord().getGraduationTerm());
		txfField[3].setText(mStudent.getAcademicRecord().getGraduationYear());
		txfField[4].setText(mStudent.getAcademicRecord().getUWEmail());
		txfField[5].setText(mStudent.getAcademicRecord().getExternalEmail());
		txfField[6].setText(Double.toString(mStudent.getAcademicRecord().getGPA()));
		return null;
	}





private JPanel createAcaPnl() {
	mStudentCurrentAcademicPnl = new JPanel(new GridLayout(7,2));
	
	mAcaRecord[0] = new JLabel("Program: ");
	mAcaRecord[1] = new JLabel(mStudent.getAcademicRecord().getProgram());
	mAcaRecord[2] = new JLabel("Degree Level: ");
	mAcaRecord[3] = new JLabel(mStudent.getAcademicRecord().getDegreeLevel());
	mAcaRecord[4] = new JLabel("Graduation Term: ");
	mAcaRecord[5] = new JLabel(mStudent.getAcademicRecord().getGraduationTerm());
	mAcaRecord[6] = new JLabel("Graduation Year: ");
	mAcaRecord[7] = new JLabel(mStudent.getAcademicRecord().getGraduationYear());
	mAcaRecord[8] = new JLabel("UW Email: ");
	mAcaRecord[9] = new JLabel(mStudent.getAcademicRecord().getUWEmail());
	mAcaRecord[10] = new JLabel("External Email: ");
	mAcaRecord[11] = new JLabel(mStudent.getAcademicRecord().getExternalEmail());
	mAcaRecord[12] = new JLabel("GPA: ");
	mAcaRecord[13] = new JLabel(Double.toString(mStudent.getAcademicRecord().getGPA()));
	
	for(int j =0;j < mAcaRecord.length; j++){
		mStudentCurrentAcademicPnl.add(mAcaRecord[j]);
	}
		
	return mStudentCurrentAcademicPnl;
	}





	
	@Override
	public void tableChanged(TableModelEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == mBtnAcademicList){
			mTransferList = getTransData(mStudent);
			
			mPnlList.removeAll();
			mStudentCurrentAcademicPnl = createAcaPnl();
			
			mTable = new JTable(mData,mTransferStrings);
			mTable.getModel().addTableModelListener(this);
			mScrollPane = new JScrollPane(mTable);
			mPnlList.add(mStudentCurrentAcademicPnl);
			mPnlList.add(mScrollPane);
			mPnlList.revalidate();
			this.repaint();
			
		} else if(e.getSource() == mBtnAddEditAcademic){
			mPnlList.removeAll();
			mPnlAdd = new JPanel();
			mPnlAcademic = createEditAcaPnl();
			mBtnAddAcad = new JButton("Add/Edit Academic Info:");
			mBtnAddAcad.addActionListener(this);
			mPnlAdd.add(mPnlAcademic);
			mPnlAdd.add(mBtnAddAcad);
			mPnlList.add(mPnlAdd);
			mPnlList.revalidate();
			this.repaint();
			
		}else if(e.getSource() == mBtnAddTrans){
			mPnlList.removeAll();
			mPnlList.add(mPnlAddtrans);
			mPnlList.revalidate();
			this.repaint();
			
		} 
		else if(e.getSource() == mBtnAddAcad){
			performAddAcad();
			
		} 
		else if(e.getSource() == mBtnAddtransfer){
			performAddTransfer();
			
		}
	}





	private void performAddTransfer() {
		String mprogram = txfFieldtrans[0].getText();
		if (mprogram.length() == 0) {
			JOptionPane.showMessageDialog(null, "Enter a school name");
			txfFieldtrans[0].setFocusable(true);
			return;
		}
		String mGPA = txfFieldtrans[1].getText();
		double mGPADO = 0.0;
		if (mGPA.length() != 0) {
			try {
				mGPADO = Double.parseDouble(mGPA);
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Enter GPA as decimal");
				txfFieldtrans[1].setText("");
				txfFieldtrans[1].setFocusable(true);
				return;
			}
		}
		String mDegreeLvl = txfFieldtrans[2].getText();
		if (mDegreeLvl.length() == 0) {
			JOptionPane.showMessageDialog(null, "Enter a Degree Level");
			txfFieldtrans[2].setFocusable(true);
			return;
		}
		
		//Adding To the collection
		AcademicRecord mAC = mStudent.getAcademicRecord();
		TransferSchool mTF = new TransferSchool("0",mAC.getID(),mprogram,mGPADO,mDegreeLvl);
		mAC.addTransferSchool(mTF);
		
		String message = "Transfer School add failed";
		if (mStudent.addAcademicRecord(mAC)) {
			message = "School added";
		}
		JOptionPane.showMessageDialog(null, message);
		
		
		
	}





	private void performAddAcad() {
		txfField[0].setText(mStudent.getAcademicRecord().getProgram());
		txfField[1].setText(mStudent.getAcademicRecord().getDegreeLevel());
		txfField[2].setText(mStudent.getAcademicRecord().getGraduationTerm());
		txfField[3].setText(mStudent.getAcademicRecord().getGraduationYear());
		txfField[4].setText(mStudent.getAcademicRecord().getUWEmail());
		txfField[5].setText(mStudent.getAcademicRecord().getExternalEmail());
		txfField[6].setText(Double.toString(mStudent.getAcademicRecord().getGPA()));
		// TODO Auto-generated method stub
		String mprogram = txfField[0].getText();
		if (mprogram.length() == 0) {
			JOptionPane.showMessageDialog(null, "Enter a program name");
			txfField[0].setFocusable(true);
			return;
		}
		String mGPA = txfField[6].getText();
		double mGPADO = 0.0;
		if (mGPA.length() != 0) {
			try {
				mGPADO = Double.parseDouble(mGPA);
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Enter GPA as decimal");
				txfField[6].setText("");
				txfField[6].setFocusable(true);
				return;
			}
		}
		String mDegreeLvl = txfField[1].getText();
		if (mDegreeLvl.length() == 0) {
			JOptionPane.showMessageDialog(null, "Enter a Degree Level");
			txfField[0].setFocusable(true);
			return;
		}
		String mGradTerm = txfField[2].getText();
		if (mGradTerm.length() == 0) {
			JOptionPane.showMessageDialog(null, "Enter a graduation term");
			txfField[2].setFocusable(true);
			return;
		}
		String mGradYear =txfField[3].getText();
		if (mGradYear.length() == 0) {
			JOptionPane.showMessageDialog(null, "Enter a graduation year");
			txfField[3].setFocusable(true);
			return;
		}
				
		
		String muwEmail = txfField[4].getText();
		if (muwEmail.length() == 0) {
			JOptionPane.showMessageDialog(null, "Enter A UW Email:");
			txfField[4].setFocusable(true);
			return;
		}
		String mexEmail = txfField[5].getText();
		if (mexEmail.length() == 0) {
			JOptionPane.showMessageDialog(null, "Enter A External Email:");
			txfField[5].setFocusable(true);
			return;
		}
		
		//Adding To the collection
		//Is this what you wanted here Andrew?
		AcademicRecord mAC;
		mAC = new AcademicRecord(mStudent.getStudentID(), mprogram, mDegreeLvl, 
				mGradTerm, mGradYear, muwEmail, mexEmail, mGPADO, null);
		
		
		String message = "Employer add failed";
		if (mStudent.addAcademicRecord(mAC)) {
			message = "Item added";
		}
		JOptionPane.showMessageDialog(null, message);

	}





	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

	
	
	
}
