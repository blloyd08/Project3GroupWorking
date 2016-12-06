/**
 * 
 */
package academic;

import java.util.Observable;

/**
 * @author Andrew,Brandon,Brian
 *
 */
public class TransferSchool extends Observable {
	
	public static final String ACADEMIC_ID = "ACADEMIC_ID";
	public static final String NAME = "NAME";
	public static final String GPA = "GPA";
	public static final String DEGREE_EARNED = "DEGREE_EARNED";
	
	private String myID;
	private String myAcademicID;
	private String myName;
	private double myGPA;
	private String myDegreeEarned;
	
	
	public TransferSchool(String theTransferID, String theAcademicID, String theName,
			double theGPA, String theDegreeEarned){
		myID = theTransferID;
		myAcademicID = theAcademicID;
		myName = theName;
		myGPA = theGPA;
		myDegreeEarned = theDegreeEarned;
	}
	
	public String getName() {
		return myName;
	}
	
	public void setName(String myName) {
		this.myName = myName;
		AcademicCollection.update(this, "name", myName);
		hasChanged();
		notifyObservers(NAME);
	}
	
	public double getGPA() {
		return myGPA;
	}
	
	public void setGPA(double myGPA) {
		this.myGPA = myGPA;
		AcademicCollection.update(this, "GPA", myGPA);
		hasChanged();
		notifyObservers(GPA);
	}
	
	public String getDegreeEarned() {
		return myDegreeEarned;
	}
	
	public void setDegreeEarned(String myDegreeEarned) {
		this.myDegreeEarned = myDegreeEarned;
		AcademicCollection.update(this, "degreeEarned", myDegreeEarned);
		hasChanged();
		notifyObservers(DEGREE_EARNED);
	}

	public String getAcademicID() {
		return myAcademicID;
	}

	public void setAcademicID(String theAcademicID) {
		this.myAcademicID = theAcademicID;
		AcademicCollection.update(this, "academicID", myAcademicID);
		hasChanged();
		notifyObservers(ACADEMIC_ID);
	}

	public String getID() {
		return myID;
	}

	public void setID(String theID) {
		this.myID = theID;
	}
	

}
