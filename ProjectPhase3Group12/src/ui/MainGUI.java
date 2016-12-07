/**
 * 
 * 
 * 
 * andrew Klonitsko
 * Brian Lloyd
 */
package ui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import academic.AcademicRecord;
import academic.TransferSchool;
import employment.Employer;
import student.Student;

/**
 * @author Andrew,Brandon,Brian
 *
 */
public class MainGUI extends JFrame implements PropertyChangeListener {


	private static final long serialVersionUID = 1L;
	private JTabbedPane myTabbedPane;
	private static Student mStudent;
	private static Student mRealStudent;
	private StudentGUI sGUI;
	private String mUser;
	private String nNewUser;
	int onlyOnce = 0;
	static StudentSearchGUI ssg;
	
	public static void main(String[] args) {
		MainGUI frame = new MainGUI();
		ssg = new StudentSearchGUI();
		ssg.addPropertyChangeListener(frame);
		ssg.setVisible(false);
		UserSelectorGUI userSelector = new UserSelectorGUI();
		

		mStudent = new Student("andrew", "klonitsko");
		mStudent.addEmployer(new Employer("THIS", "HERE"));
		mStudent.addEmployer(new Employer("Now", "HERE"));
		mStudent.addAcademicRecord(new AcademicRecord("Hello1", "Hello2", "Hello3", "Hello4", "Hello5", "Hello6", "Hello7", "hello1", 3.2, new ArrayList<TransferSchool>()));
		
		
		// Display User selector GUI and listen to user selection
		userSelector.addPropertyChangeListener(frame);
		userSelector.setVisible(true);
		
		

	}

	/**
	 * Launches the GUI, starting point of application
	 */

	public MainGUI() {
		super("Student Database System");
		createComponents();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(700, 700);
		setLocationRelativeTo(null);
	}

	/**
	 * Create GUI components. This includes the UserSelectorGUI
	 */
	private void createComponents() {
		// Create the main tabbed pane
		myTabbedPane = new JTabbedPane();

		// Create the user selector GUI

	}

	private void createStaffComponents() {
		createTabbedGUI(new String[] { "Student", "Academic", "Employment", "Reports" });
	}

	private void createFacultyComponents() {
		createTabbedGUI(new String[] { "Reports" });
	}

	private void createStudentComponents() {
		createTabbedGUI(new String[] { "Employment" });
	}

	private void createTabbedGUI(String[] tabs) {
		for(String tabName : tabs) {
			JComponent newPanel = makeTextPanel(tabName);
			myTabbedPane.addTab(tabName, newPanel);
		}

		this.add(myTabbedPane);
		this.setVisible(true);
	}
	
	//Something

	private JComponent makeTextPanel(String type) {
		
		JPanel panel = new JPanel();
		mRealStudent = ssg.getCurrentStudent();
		if(type.equalsIgnoreCase("Student")) {
			sGUI = new StudentGUI();
			panel.add(sGUI);
		} 
		else if(type.equalsIgnoreCase("Employment")) {
			panel.add(new EmploymentGUI(mRealStudent));
			System.out.println("after panel add");
		}  
		else if(type.equalsIgnoreCase("Academic")) {
			panel.add(new AcademicGUI(mRealStudent));
		}
		else {
			panel.add(new JLabel("Needs to be implemented!"));
		}
		
		
		return panel;
	}

	@Override
	public void propertyChange(PropertyChangeEvent e) {
		// TODO Auto-generated method stub
		
		// Debug only
		if(onlyOnce == 2){
		mUser = e.getPropertyName();
		nNewUser =  (String) e.getNewValue();
		ssg.setVisible(true);
		}
		System.out.println(e.getPropertyName());
		if(e.getPropertyName() == "studentToReturn" ){
			createTabs();
		
		}
		onlyOnce++;

	}
	
	public void createTabs(){
		
		if (mUser.equalsIgnoreCase("user")){
			if (nNewUser.equalsIgnoreCase("Staff")) {
				createStaffComponents();
			} else if (nNewUser.equalsIgnoreCase("Faculty")) {
				createFacultyComponents();
			} else {
				// Must be student if not Staff or Faculty
				createStudentComponents();
			}
		}
	}


	
	
	
}