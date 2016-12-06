/**
 * 
 */
package ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
implements ActionListener,TableModelListener {
	private static final long serialVersionUID = 643323L;
	private JButton mBtnAcademicList,mBtnAddEditAcademic, mBtnAddTrans;
	private List<AcademicRecord> mAcademicList;
	private List<TransferSchool> mTransferList;
	private Student mStudent;

	//variables for transfer table
	private String[] mAcademicStrings = {"program","degreeLevel","graduationTerm","graduationYear","uwEmail","externalEmail","GPA"};
	private JPanel mPnlList;
	private JPanel mTablePan;
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
	private JButton mBtnAddtrans;
	
	
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
				mData[i][1] = mTransferList.get(i).getGPA();
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
				mData[i][6] = mAcademicList.get(i).getGPA();
		
			}
		}


		return mAcademicList;
	}

	private void createComponents() {
		// The Top Most Panel That Allows Adding Employer and Listing Employer
				mPnlButtons = new JPanel();

				mBtnAcademicList = new JButton("Academic List");
				mBtnAcademicList.addActionListener(this);

				mBtnAddAcademic = new JButton("Add Academic");
				mBtnAddAcademic.addActionListener(this);

				mPnlButtons.add(mBtnAcademicList);
				mPnlButtons.add(mBtnAddAcademic);

				add(mPnlButtons, BorderLayout.NORTH);

				//main panel that list academic and transferSchools for a student
				mPnlList = new JPanel();
				mTablePan = new JPanel(new GridLayout(4,0));
				mLblAcademic = new JLabel("Academic Table:");
				mTable = new JTable(mData,mAcademicStrings);
				mLblTransfer = new JLabel("Transfer Table:");
				mTable2 = new JTable(mData,mTransferStrings);
				mScrollPane = new JScrollPane(mTable);
				mScrolltranPane = new JScrollPane(mTable2);
				
				mTablePan.add(mLblAcademic);
				mTablePan.add(mScrollPane);
				mTablePan.add(mLblTransfer);
				mTablePan.add(mScrolltranPane);
				
				mPnlList.add(mTablePan);
				mTable.getModel().addTableModelListener(this);
				mTable2.getModel().addTableModelListener(this);

				//Add Panel- allows User to add academic and transfer record
				mPnlAdd = new JPanel();
				mPnlAcademic = new JPanel();
				mPnlAddtrans = new JPanel();
				mPnlAcademic.setLayout(new GridLayout(7,0));
				
				String mLabelName[] = {"Enter Program Name:","Enter Degree Level:","Enter Graduation Term:","Enter Graduation Year:","Enter UW Email:",
												"Enter External Email:", "Enter GPA:"};

				mPnlAddtrans.setLayout(new GridLayout(3,0));
				String mLabelTransferName[] = {"Enter Name Of School:","Enter GPA:","Enter Degree Earned"};
				
				
				for (int i = 0; i < mLabelName.length; i++) {
					JPanel panel = new JPanel();
					
					panel.setLayout(new GridLayout(1, 0));
					txfLabel[i] = new JLabel(mLabelName[i]);
					txfField[i] = new JTextField(25);
					panel.add(txfLabel[i]);
					panel.add(txfField[i]);
					mPnlAcademic.add(panel);
				}
				
				
				
				for (int i = 0; i < mLabelTransferName.length; i++) {
					JPanel panel = new JPanel();
					
					panel.setLayout(new GridLayout(1, 0));
					txfLabel[i] = new JLabel(mLabelName[i]);
					txfField[i] = new JTextField(25);
					panel.add(txfLabel[i]);
					panel.add(txfField[i]);
					mPnlAddtrans.add(panel);	
				}

				JPanel mPanel = new JPanel();
				mBtnAddAcad = new JButton("Add Academic Info:");
				mBtnAddAcad.addActionListener(this);
				mPanel.add(mBtnAddAcad);
				
				
				JPanel mPanel3 = new JPanel();
				mBtnAddtrans = new JButton("Add Transfer Info:");
				mBtnAddtrans.addActionListener(this);
				mPanel3.add(mBtnAddtrans);
				
				mPnlAdd.add(mPnlAcademic);
				mPnlAdd.add(mPanel);
				mPnlAdd.add(mPnlAddtrans);
				mPnlAdd.add(mPanel3);
				
				add(mPnlList,BorderLayout.CENTER);
			}
		

//left off here goal tomorrow *finish this gui-student gui- student collection gui- report gui
	@Override
	public void tableChanged(TableModelEvent arg0) {
		// TODO Auto-generated method stub
		if(e.getSource() == mBtnAcademicList){
			mAcademicList = getAcadData(mStudentID);
			
			mPnlList.removeAll();
			mTable = new JTable(mData,mEmploymentColumnNames);
			mTable.getModel().addTableModelListener(this);
			mScrollPane = new JScrollPane(mTable);
			mPnlList.add(mScrollPane);
			mPnlList.revalidate();
			this.repaint();
			
		} else if(e.getSource() == mBtnAdd){
			mPnlList.removeAll();
			mPnlList.add(mPnlAdd);
			mPnlList.revalidate();
			this.repaint();
			
		} else if(e.getSource() == mBtnAddEmployment){
			performAddEmployment();
			
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
	
	
}
