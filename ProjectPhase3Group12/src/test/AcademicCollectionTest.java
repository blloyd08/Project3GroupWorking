/**
 * 
 */
package test;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import academic.AcademicCollection;
import academic.AcademicRecord;
import academic.TransferSchool;
import student.Student;
import student.StudentCollection;

/**
 * @author Andrew,Brandon,Brian
 *
 */
public class AcademicCollectionTest {

	private AcademicRecord mRecord;
	private Student mStudent;
	
	
	private Student createStudent(){
		String firstName = "autoTestFirst";
		String lastName = "autoTestLast";
		StudentCollection.add(new Student(firstName, lastName));
		
		//Get the newly created student object that has an ID
		List<Student> students = StudentCollection.searchByName(firstName, lastName);
		return students.get(students.size() -1);
	}
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		StudentCollection.add(new Student("autoTestFirst", "autoTestLast"));
		mStudent = createStudent();
		mRecord = new AcademicRecord(mStudent.getID(),"autoTestProgram","autoTestDegree","autoTestTerm",
				"1999","autoTest@uw.edu", "autotest@autotext.com", 2.01);
	}

	/**
	 * Test method for {@link academic.AcademicCollection#add(academic.AcademicRecord)}.
	 */
	@Test
	public void testAddAcademicRecord() {
		assertTrue(AcademicCollection.add(mRecord));
	}

	/**
	 * Test method for {@link academic.AcademicCollection#add(academic.TransferSchool)}.
	 */
	@Test
	public void testAddTransferSchool() {
		TransferSchool school = new TransferSchool("autoTest", 1.0, "NA");
		
		school.setAcademicID();
		assertTrue(AcademicCollection.add(school));
	}

	/**
	 * Test method for {@link academic.AcademicCollection#update(academic.AcademicRecord, java.lang.String, java.lang.Object)}.
	 */
	@Test
	public void testUpdateAcademicRecordStringObject() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link academic.AcademicCollection#update(academic.TransferSchool, java.lang.String, java.lang.Object)}.
	 */
	@Test
	public void testUpdateTransferSchoolStringObject() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link academic.AcademicCollection#getAcademicRecord(java.lang.String)}.
	 */
	@Test
	public void testGetAcademicRecordString() {
		AcademicRecord record = AcademicCollection.getAcademicRecord("1");
		System.out.println(record.getID() + " " + record.getStudentID() + " " + record.getProgram() + " " + record.getGPA() + " " + record.getTransferSchools());
		assertNotNull(record);
	}

	/**
	 * Test method for {@link academic.AcademicCollection#getAcademicRecord()}.
	 */
	@Test
	public void testGetAcademicRecord() {
		List<AcademicRecord> records = AcademicCollection.getAcademicRecord();
		for (AcademicRecord record : records){
			System.out.println(record.getID() + " " + record.getStudentID() + " " + record.getProgram() + " " + record.getGPA() + " " + record.getTransferSchools());
		}
		assertNotNull(records);
	}

	/**
	 * Test method for {@link academic.AcademicCollection#getTransferSchools(java.lang.String)}.
	 */
	@Test
	public void testGetTransferSchoolsString() {
		List<TransferSchool> schools = AcademicCollection.getTransferSchools("1");
		for (TransferSchool school : schools){
			//Debug
			System.out.println(school.getID() + " " + school.getAcademicID() + " " + school.getName() + " " + school.getGPA());
		}
		assertNotNull(schools);
	}

	/**
	 * Test method for {@link academic.AcademicCollection#getTransferSchools()}.
	 */
	@Test
	public void testGetTransferSchools() {
		List<TransferSchool> schools = AcademicCollection.getTransferSchools();
		for (TransferSchool school : schools){
			//Debug
			System.out.println(school.getID() + " " + school.getAcademicID() + " " + school.getName() + " " + school.getGPA());
		}
		assertNotNull(schools);
	}

	/**
	 * Test method for {@link academic.AcademicCollection#getTransferSchool(java.lang.String)}.
	 */
	@Test
	public void testGetTransferSchool() {
		TransferSchool school = AcademicCollection.getTransferSchool("1");
		//Debug
		System.out.println(school.getID() + " " + school.getAcademicID() + " " + school.getName() + " " + school.getGPA());
		assertNotNull(school);
	}
	
	/**
	 * Gets a unique name for a category to allow successfully update or addtion of a category
	 * @param startString string before the appened random number.
	 * @return original string plus a random number that makes the string unique
	 */
	private String appendUniqueUWEmail(String startString) {
		// Get unique name
		Random rand = new Random();
		int uniqueKey = rand.nextInt();
		String uwEmail = startString + Integer.toString(uniqueKey);
		List<AcademicRecord> records = AcademicCollection.getAcademicRecord();
		while ((StudentCollection.searchByEmail(uwEmail)).si == true) {
			uniqueKey = rand.nextInt();
			categoryName = startString + Integer.toString(uniqueKey);
			categories = ItemCollection.getCategories();
		}
		
		return categoryName;
	}
//
//	/**
//	 * Check if a category name has already been used in a collection of ItemCategory objects
//	 * @param categories current list of ItemCategory objects
//	 * @param categoryName name of category to find
//	 * @return true if the categoryName already exists in the list.
//	 */
//	private boolean findCategory(List<ItemCategory> categories, String categoryName) {
//		for (ItemCategory cat : categories) {
//			if (cat.getCategory().equals(categoryName)) {
//				return true;
//			}
//		}
//		return false;
//	}

}
