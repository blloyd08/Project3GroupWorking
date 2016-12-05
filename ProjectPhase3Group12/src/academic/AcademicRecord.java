/**
Brandon Gibbons
 * 
 */
package academic;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * @author Andrew,Brandon,Brian
 *
 */
public class AcademicRecord extends Observable {
	
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

	public void setID(String myID) {
		this.myID = myID;
	}

	public String getProgram() {
		return myProgram;
	}

	public void setProgram(String myProgram) {
		this.myProgram = myProgram;
	}

	public String getGraduationTerm() {
		return myGraduationTerm;
	}

	public void setGraduationTerm(String myGraduationTerm) {
		this.myGraduationTerm = myGraduationTerm;
	}

	public String getGraduationYear() {
		return myGraduationYear;
	}

	public void setGraduationYear(String myGraduationYear) {
		this.myGraduationYear = myGraduationYear;
	}

	public String getUWEmail() {
		return myUWEmail;
	}

	public void setUWEmail(String myUWEmail) {
		this.myUWEmail = myUWEmail;
	}

	public String getExternalEmail() {
		return myExternalEmail;
	}

	public void setExternalEmail(String myExternalEmail) {
		this.myExternalEmail = myExternalEmail;
	}

	public double getGPA() {
		return myGPA;
	}

	public void setGPA(double myGPA) {
		this.myGPA = myGPA;
	}

	public List<TransferSchool> getTransferSchools() {
		return myTransferSchools;
	}
	
	public void setTransferSchools(ArrayList<TransferSchool> thePreviousSchools) {
		
		for( TransferSchool t : thePreviousSchools) {
			myTransferSchools.add(t);
		}
	}

	public boolean addTransferSchool(TransferSchool theTransferSchool){
		boolean flag = false;
		
		try {
			
			myTransferSchools.add(theTransferSchool);
			flag = true;
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
	}

	public String getStudentID() {
		return myStudentID;
	}

	public void setStudentID(String studentID) {
		this.myStudentID = studentID;
	}

}
