/**
 * 
 */
package student;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import academic.AcademicRecord;
import employment.Employer;

/**
 * @author Andrew,Brandon,Brian
 *
 */
public class Student extends Observable {

	private String myID;
	private String myFirstName;
	private String myLastName;
	private AcademicRecord myAcademicRecord;
	private ArrayList<Employer> myEmployers;
	
	/**
	 * This Constructor should be used in most cases.
	 * @param theName for the students name.
	 * @param theRecord for any existing academic record.
	 * @param theEmployers for any employers the student already has.
	 */
	public Student(String theFirstName, String theLastName, AcademicRecord theRecord, ArrayList<Employer> theEmployers) {
		
		this.setFirstName(theFirstName);
		this.setLastName(theLastName);
		addAcademicRecord(theRecord);
		
		myEmployers = new ArrayList<Employer>();
		
		for(Employer e : theEmployers) {
			addEmployer(e);
		}
	}
	
	/**
	 * WARNING- This constructor is ONLY for use when a student is to be added, but no information is available.
	 * @param theName is the student's name.
	 */
	public Student(String theFirstName, String theLastName) {
		
		this.setFirstName(theFirstName);
		this.setLastName(theLastName);
		
		/**
		 * This allows a record to be created, and editable for future use.
		 */
		myEmployers = new ArrayList<Employer>();
		myAcademicRecord = null;
	}
	
	public boolean addAcademicRecord(AcademicRecord theRecord) {
		
		boolean flag = false;
		
		try {
			myAcademicRecord = new AcademicRecord(theRecord.getStudentID(), theRecord.getProgram(), theRecord.getDegreeLevel(),
					theRecord.getGraduationTerm(), theRecord.getGraduationYear(), theRecord.getUWEmail(), 
						theRecord.getExternalEmail(), theRecord.getGPA());
				
				flag = true;
			} catch(Exception e) {
				
				flag = false;
			}
			
			return flag;
	}
	
	public boolean addEmployer(Employer theEmployer) {
		
		boolean flag = false;
		
		try {
			myEmployers.add(theEmployer);
			flag = true;
			
		} catch(Exception e) {
				
				flag = false;
		}
			
			return flag;
	}
	
	public String getFirstName() {
		return myFirstName;
	}
	
	public void setFirstName(String theFirstName) {
		this.myFirstName = theFirstName;
	}
	
	public String getLastName() {
		return myLastName;
	}
	
	public void setLastName(String theFirstName) {
		this.myLastName = theFirstName;
	}
	
	public AcademicRecord getAcademicRecord() {
		return myAcademicRecord;
	}
	
	public void setAcademicRecord(AcademicRecord theAcademicRecord) {
		this.myAcademicRecord = theAcademicRecord;
	}
	
	public ArrayList<Employer> getEmployers() {
		return myEmployers;
	}
	
	public Employer getEmployer(String theEmployerID) {

		int ndx = -1;
		for(Employer e : myEmployers) {
			if(e.getID() == theEmployerID) {
				ndx = myEmployers.indexOf(e);
			}
		}
		
		if(ndx == -1) {
			return null;
		} else {
			
			return myEmployers.get(ndx);
		}
	}
	
	public void setEmployers(ArrayList<Employer> theEmployers) {
		myEmployers = theEmployers;
	}
	
	public void setEmployers(Employer theEmployer) {
		myEmployers = new ArrayList<Employer>();
		myEmployers.add(theEmployer);
	}

	public String getID() {
		return myID;
	}

	public void setID(String theID) {
		this.myID = theID;
	}

	
}
