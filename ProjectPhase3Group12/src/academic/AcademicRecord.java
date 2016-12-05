/**
Brandon Gibbons
 * 
 */
package academic;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * @author Andrew,Brandon,Brian
 *
 */
public class AcademicRecord extends Observable {
	
	public static final String ID = "ID";
	public static final String STUDENT_ID = "STUDENT_ID";
	public static final String PROGRAM = "PROGRAM";
	public static final String DEGREE_LEVEL = "DEGREE_LEVEL";
	public static final String GRADUATION_TERM = "GRADUATION_TERM";
	public static final String GRADUATION_YEAR = "GRADUATION_YEAR";
	public static final String UW_EMAIL = "UW_EMAIL";
	public static final String EXTERNAL_EMAIL = "EXTERNAL_EMAIL";
	public static final String GPA = "GPA";
	public static final String TRANSFER_SCHOOLS = "TRANSFER_SCHOOLS";
	
	private String myID;
	private String myStudentID;
	private String myProgram;
	private String myDegreeLevel;
	private String myGraduationTerm;
	private String myGraduationYear;
	private String myUWEmail;
	private String myExternalEmail;
	private double myGPA;
	private ArrayList<TransferSchool> myTransferSchools;
	
	public AcademicRecord(String theStudentID, String theProgram, String theDegreeLevel, String theGraduationTerm, String theGraduationYear,
			String theUWEmail, String myExternalEmail, double theGPA) {
		this.setStudentID(theStudentID);
		this.setProgram(theProgram);
		this.setDegreeLevel(theDegreeLevel);
		this.setGraduationTerm(theGraduationTerm);
		this.setGraduationYear(theGraduationYear);
		this.setUWEmail(theUWEmail);
		this.setExternalEmail(myExternalEmail);
		this.setGPA(theGPA);
		
		myTransferSchools = new ArrayList<TransferSchool>();
	}
	
	public AcademicRecord(String studentID, String theProgram, String theDegreeLevel, String theGraduationTerm, String theGraduationYear,
			String theUWEmail, String theExternalEmail, double theGPA, ArrayList<TransferSchool> thePreviousSchools){
		
		//Chained Constructor
		this(studentID, theProgram, theDegreeLevel, theGraduationTerm, theGraduationYear,
			theUWEmail, theExternalEmail,theGPA);
		
		myTransferSchools = new ArrayList<TransferSchool>();
		for(TransferSchool t : thePreviousSchools) {
			myTransferSchools.add(t);
		}
	}
	
	public String getID() {
		return myID;
	}

	public void setID(String theID) {
		this.myID = theID;
		hasChanged();
		notifyObservers(ID);
		AcademicCollection.update(this, "01", myID);
	}

	public String getProgram() {
		return myProgram;
	}

	public void setProgram(String myProgram) {
		this.myProgram = myProgram;
		hasChanged();
		notifyObservers(PROGRAM);
	}

	public String getGraduationTerm() {
		return myGraduationTerm;
	}

	public void setGraduationTerm(String myGraduationTerm) {
		this.myGraduationTerm = myGraduationTerm;
		hasChanged();
		notifyObservers(GRADUATION_TERM);
	}

	public String getGraduationYear() {
		return myGraduationYear;
	}

	public void setGraduationYear(String myGraduationYear) {
		this.myGraduationYear = myGraduationYear;
		hasChanged();
		notifyObservers(GRADUATION_YEAR);
	}

	public String getUWEmail() {
		return myUWEmail;
	}

	public void setUWEmail(String myUWEmail) {
		this.myUWEmail = myUWEmail;
		hasChanged();
		notifyObservers(UW_EMAIL);
	}

	public String getExternalEmail() {
		return myExternalEmail;
	}

	public void setExternalEmail(String myExternalEmail) {
		this.myExternalEmail = myExternalEmail;
		hasChanged();
		notifyObservers(EXTERNAL_EMAIL);
	}

	public double getGPA() {
		return myGPA;
	}

	public void setGPA(double myGPA) {
		this.myGPA = myGPA;
		hasChanged();
		notifyObservers(GPA);
	}

	public List<TransferSchool> getTransferSchools() {
		return myTransferSchools;
	}
	
	public void setTransferSchools(ArrayList<TransferSchool> thePreviousSchools) {
		
		for( TransferSchool t : thePreviousSchools) {
			myTransferSchools.add(t);
		}
		hasChanged();
		notifyObservers(TRANSFER_SCHOOLS);
	}

	public boolean addTransferSchool(TransferSchool theTransferSchool){
		boolean flag = false;
		
		try {
			
			myTransferSchools.add(theTransferSchool);
			flag = true;
			hasChanged();
			notifyObservers(TRANSFER_SCHOOLS);
			
		} catch(Exception e) {
			
			flag = false;
		}
		
		return flag;
	}

	public String getDegreeLevel() {
		return myDegreeLevel;
	}

	public void setDegreeLevel(String myDegreeLevel) {
		this.myDegreeLevel = myDegreeLevel;
		hasChanged();
		notifyObservers(DEGREE_LEVEL);
	}

	public String getStudentID() {
		return myStudentID;
	}

	public void setStudentID(String studentID) {
		this.myStudentID = studentID;
		hasChanged();
		notifyObservers(STUDENT_ID);
	}
	
	public void addToObservers(Observer observerObj) {
		this.addObserver(observerObj);
	}

}
