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
		
	}

	/**
	 * Test method for {@link employment.EmployerCollection#add(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testAddStringString() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link employment.EmployerCollection#update(employment.Employer, java.lang.String, java.lang.Object)}.
	 */
	@Test
	public void testUpdateEmployerStringObject() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link employment.EmployerCollection#update(java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testUpdateStringStringString() {
		fail("Not yet implemented");
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
	public void testDeleteStringString() {
		fail("Not yet implemented");
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
			System.out.println(employer.getCompanyName());
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
