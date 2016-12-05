package employment;

import data.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * EmployerCollection class represents a collection of student's employers and
 * the skills used.
 * 
 * @author Brian Lloyd
 */
public class EmployerCollection {

	private static EmployerDB mEmployerDB;

	/**
	 * Adds a new Employer to the data.
	 * 
	 * @param employer
	 *            Employer of a student.
	 * @param studentID
	 * 			Unique ID of student that worked for this employer
	 * @return true or false
	 */
	public static boolean add(Employer employer, String studentID) {
		if (mEmployerDB == null) {
			mEmployerDB = new EmployerDB();
		}

		String message = mEmployerDB.addEmployer(employer, studentID);

		if (message.startsWith("Error adding employer:")) {
			return false;
		}

		// Add employer skills
		List<String> skills = employer.getSkills();
		if (skills != null || skills.size() > 0) {
			for (String skill : skills) {
				if (!EmployerCollection.add(employer.getID(), skill)) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Adds a new skill to the data.
	 * 
	 * @param employerID
	 * 			Unique id of the employer who is adding the skill
	 * @param Skill
	 *            Skill name that is related to an employer
	 * @return true or false
	 */
	public static boolean add(String employerID, String skill) {
		if (mEmployerDB == null) {
			mEmployerDB = new EmployerDB();
		}

		String message = mEmployerDB.addSkill(employerID, skill);
		if (message.startsWith("Error adding skill:")) {
			return false;
		}
		return true;
	}

	/**
	 * Modify the particular column of the employer with the given data.
	 * 
	 * @param employer
	 *            Employer to modify
	 * @param column
	 *            the field in the table to modify
	 * @param data
	 *            the value for the field
	 * @return true or false
	 */
	public static boolean update(Employer employer, String column, Object data) {
		if (mEmployerDB == null) {
			mEmployerDB = new EmployerDB();
		}
		String message = mEmployerDB.updateEmployer(employer, column, data);
		if (message.startsWith("Error updating employer: ")) {
			return false;
		}
		return true;
	}

	/**
	 * Modify the name of a skill.
	 * 
	 * @param employerID
	 * 			Unique id of the employer where the skill is used
	 * @param oldName
	 * 			The name of the skill before the update
	 * @param newName
	 * 			The name that will replace the current name of the skill 
	 * @return true or false
	 */
	public static boolean update(String employerID,String oldName, String newName) {
		if (mEmployerDB == null) {
			mEmployerDB = new EmployerDB();
		}
		String message = mEmployerDB.updateSkill(employerID, oldName, newName);
		if (message.startsWith("Error updating skill: ")) {
			return false;
		}
		return true;
	}

	/**
	 * Deletes the particular employer and all skill related to the employer
	 * 
	 * @param employer
	 *            Employer to be delete
	 * @return true or false
	 */
	public static boolean delete(Employer employer) {
		if (mEmployerDB == null) {
			mEmployerDB = new EmployerDB();
		}

		// Handle no employer or no ID
		if (employer == null || employer.getID() == null) {
			return false;
		}
		
		//Handle deleting related skills
		List<String> skills = employer.getSkills();
		if (skills != null && skills.size() > 0){
			for (String skill : skills){
				if (!EmployerCollection.delete(employer.getID(), skill)){
					return false;
				}
			}
		}
		
		String message = mEmployerDB.deleteEmployer(employer);
		if (message.startsWith("Error deleting employer: ")) {
			return false;
		}
		return true;
	}
	
	/**
	 * Deletes the particular skill from the database
	 * 
	 * @param employerID
	 * 			Unique ID of the employer
	 * @param skill
	 *            Skill name to be deleted
	 * @return true or false
	 */
	public static boolean delete(String employerID, String skill) {
		if (mEmployerDB == null) {
			mEmployerDB = new EmployerDB();
		}

		// Handle no employer or no ID
		if (employerID == null || skill == null) {
			return false;
		}
		
		String message = mEmployerDB.deleteSkill(employerID, skill);
		if (message.startsWith("Error deleting skill: ")) {
			return false;
		}
		return true;
	}

	/**
	 * Return an employer that matches the student id, null otherwise.
	 * 
	 * @param studentID
	 *            Id of the student that the employer is related to.
	 * @return List of employers that the student worked for
	 */
	public static ArrayList<Employer> getEmployers(String studentID) {
		if (mEmployerDB == null) {
			mEmployerDB = new EmployerDB();
		}
		try {
			return mEmployerDB.getEmployers(studentID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Return an employer that matches the employer ID
	 * 
	 * @param employerID
	 *            Id of the employer.
	 * @return Employer with the given employerID
	 */
	public static Employer getEmployer(String employerID) {
		if (mEmployerDB == null) {
			mEmployerDB = new EmployerDB();
		}
		try {
			return mEmployerDB.getEmployer(employerID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * Return all employers
	 * 
	 * @return List of employers
	 */
	public static ArrayList<Employer> getEmployers() {
		if (mEmployerDB == null) {
			mEmployerDB = new EmployerDB();
		}
		try {
			return mEmployerDB.getEmployers();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * Return's all skills related to an employer.
	 * 
	 * @param employerID
	 *            Id of the employer that the skill is related to.
	 * @return list of skills used while working for an employer
	 */
	public static ArrayList<String> getSkills(String employerID) {
		if (mEmployerDB == null) {
			mEmployerDB = new EmployerDB();
		}
		try {
			return mEmployerDB.getSkills(employerID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * Return's a list of all skills in the Skills Table
	 * 
	 * @return list of all skills
	 */
	public static ArrayList<String> getSkills() {
		if (mEmployerDB == null) {
			mEmployerDB = new EmployerDB();
		}
		try {
			return mEmployerDB.getSkills();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

//	/**
//	 * 
//	 * Return's a skill that matches the unique skill id.
//	 * 
//	 * @param skillID
//	 *            Id of the skill
//	 * @return Skill that matches the skill id
//	 */
//	public static String getSkill(String skillID) {
//		if (mEmployerDB == null) {
//			mEmployerDB = new EmployerDB();
//		}
//		try {
//			return mEmployerDB.getSkill(skillID);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
}
