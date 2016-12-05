/**
 * 
 */
package employment;

import java.util.ArrayList;
import java.util.Observable;

/**
 * @author Andrew,Brandon,Brian
 *
 */
public class Employer extends Observable {
	private String myID;
	private String myCompanyName;
	private String myStartDate;
	private double mySalary;
	private String myPosition;
	private ArrayList<String> mySkillsList;
	
	/**
	 * This is the first constructor that takes a single skill to add to the list.
	 * 
	 * @param theCompanyName for the name of the company.
	 * @param theStartDate to see if this was an internship (i.e. startDate < graduationDate).
	 * @param theSalary for the yearly pay.
	 * @param thePosition for the title of their position.
	 * @param theSkill for a skill the student has.
	 */
	public Employer(String theCompanyName, String theStartDate, double theSalary, String thePosition, String theSkill) {
		this.setCompanyName(theCompanyName);
		this.setStartDate(theStartDate);
		this.setSalary(theSalary);
		this.setPosition(thePosition);
		
		mySkillsList = new ArrayList<String>();
		
		this.mySkillsList.add(theSkill);
	}
	
	/**
	 * This overloaded constructor takes no skills, but everything else.
	 * 
	 * @param theCompanyName
	 * @param theStartDate
	 * @param theSalary
	 * @param thePosition
	 */
	public Employer(String theCompanyName, String theStartDate, double theSalary, String thePosition) {
		
		this.setCompanyName(theCompanyName);
		this.setStartDate(theStartDate);
		this.setSalary(theSalary);
		this.setPosition(thePosition);
		
		mySkillsList = new ArrayList<String>();
		
	}
	
	public Employer(String theCompanyName, String theStartDate) {
		this.myCompanyName = theCompanyName;
		this.myStartDate = theStartDate;
		this.mySalary = 0;
		this.myPosition = null;
		
		mySkillsList = new ArrayList<String>();
	}

	public void setPosition(String thePosition) {
		
		this.myPosition = thePosition;
	}

	public void setSalary(double theSalary) {
		
		this.mySalary = theSalary;
	}

	public void setStartDate(String theStartDate) {
		
		this.myStartDate = theStartDate;
	}

	public void setCompanyName(String theCompanyName) {
		
		this.myCompanyName = theCompanyName;
	}
	
	public String getPosition() {
		
		return myPosition;
	}
	
	public double getSalary() {
		
		return mySalary;
	}
	
	public String getStartDate() {
		
		return myStartDate;
	}
	
	public String getCompanyName() {
		
		return myCompanyName;
	}

	public void addSkill(String theSkill) {
		
		mySkillsList.add(theSkill);
	}
	
	public void addSkill(ArrayList<String> theSkills) {
		
		for(String s : theSkills) {
			mySkillsList.add(s);
		}
	}
	
	public ArrayList<String> getSkills() {
		
		ArrayList<String> tempList = new ArrayList<String>();
		
		for(String s : mySkillsList) {
			tempList.add(s);
		}
		
		return tempList;
	}

	public String getID() {
		return myID;
	}

	public void setID(String myID) {
		this.myID = myID;
	}

}
