/**
 * 
 */
package test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import employment.Employer;
import employment.EmployerCollection;

/**
 * @author Andrew,Brandon,Brian
 *
 */
public class EmployerCollectionTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
	}

	/**
	 * Test method for {@link employment.EmployerCollection#add(employment.Employer, java.lang.String)}.
	 */
	@Test
	public void testAddEmployerString() {
		Employer newEmp = new Employer("Name","2000-01-23");
		newEmp.setPosition("newPos");
		assertTrue(EmployerCollection.add(newEmp, "4"));
		
	}

	/**
	 * Test method for {@link employment.EmployerCollection#add(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testAddSkill() {
		assertTrue(EmployerCollection.add("2", "NewSkill"));
	}

	/**
	 * Test method for {@link employment.EmployerCollection#update(employment.Employer, java.lang.String, java.lang.Object)}.
	 */
	@Test
	public void testUpdateEmployerStringObject() {
		Employer testEmployer = new Employer("Test2","2011-12-15", 0.00, "firstPosition");
		testEmployer.setID("2");
		//assertTrue(EmployerCollection.update(testEmployer, "name", (Object)"NewName"));
		//assertTrue(EmployerCollection.update(testEmployer, "salary", (Object)1.0));
		//assertTrue(EmployerCollection.update(testEmployer, "startDate", (Object)"2016-12-13"));
		assertTrue(EmployerCollection.update(testEmployer, "position", (Object)"newPos"));
	}

	/**
	 * Test method for {@link employment.EmployerCollection#update(java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testUpdateSkill() {
		assertTrue(EmployerCollection.update("2", "NewSkill", "NewerSkill"));
	}

	/**
	 * Test method for {@link employment.EmployerCollection#delete(employment.Employer)}.
	 */
	@Test
	public void testDeleteEmployer() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link employment.EmployerCollection#delete(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testDeleteSkill() {
		assertTrue(EmployerCollection.delete("1", "secondSKill"));
	}

	/**
	 * Test method for {@link employment.EmployerCollection#getEmployers(java.lang.String)}.
	 */
	@Test
	public void testGetEmployersString() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link employment.EmployerCollection#getEmployer(java.lang.String)}.
	 */
	@Test
	public void testGetEmployer() {
		List<Employer> employers = EmployerCollection.getEmployers();
		for (Employer employer : employers){
			System.out.println(employer.getCompanyName() + " " + employer.getSalary() + " " + employer.getStartDate() + " " + employer.getPosition() + "-" + employer.getSkills());
		}
	}

	/**
	 * Test method for {@link employment.EmployerCollection#getEmployers()}.
	 */
	@Test
	public void testGetEmployers() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link employment.EmployerCollection#getSkills(java.lang.String)}.
	 */
	@Test
	public void testGetSkillsString() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link employment.EmployerCollection#getSkills()}.
	 */
	@Test
	public void testGetSkills() {
		fail("Not yet implemented");
	}

}
