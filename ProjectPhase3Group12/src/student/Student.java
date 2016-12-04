/**
 * 
 */
package student;

import java.util.List;

import academic.AcademicRecord;
import employment.Employer;

/**
 * @author Andrew,Brandon,Brian
 *
 */
public class Student {

	private String myID;
	private String myFirstName;
	private String myLastName;
	private AcademicRecord myAcademicRecord;
	private List<Employer> myEmployers;
	
	/**
	 * This Constructor should be used in most cases.
	 * @param theName for te students name.
	 * @param theRecord for any existing academic record.
	 * @param theEmployers for any employers the student already has.
	 */
	public Student(String theFirstName, String theLastName, AcademicRecord theRecord, List<Employer> theEmployers) {
		
		this.setFirstName(theFirstName);
		this.setLastName(theLastName);
		addAcademicRecord(theRecord);
		
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
		addAcademicRecord(new AcademicRecord("none", "undecided", "undecided", "none",
					"none" , "none", "none", 0));
	}
	
	protected boolean addAcademicRecord(AcademicRecord theRecord) {
		
		boolean flag = false;
		
		try {
			myAcademicRecord = new AcademicRecord(theRecord.getStudentID(), theRecord.getProgram(), theRecord.getDegreeLevel(),
					theRecord.getGraduationTerm(), theRecord.getGraduationYear(), theRecord.getUWEmail(), theRecord.getExternalEmail(), theRecord.getGPA());
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
	
	public List<Employer> getEmployers() {
		return myEmployers;
	}
	
	public void setEmployers(List<Employer> theEmployers) {
		this.myEmployers = theEmployers;
	}

	public String getID() {
		return myID;
	}

	public void setID(String theID) {
		this.myID = theID;
	}

	
}
