package acme.testing.usermanager.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagerTaskUpdateTest extends AcmePlannerTest {
	
	// Lifeycle management ------------------------------
	
	// Test cases ---------------------------------------
	
	/*
	 * @Feature: Manager can update his/her tasks
	 * @Violated constraints: None
	 * @Results: Update tasks without any errors
	 */
	
	@ParameterizedTest
	@CsvFileSource(resources = "/usermanager/task/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(70)
	public void updatePositive(final int recordIndex, final String executionStart, final String executionEnd,
		final String workLoad, final String title, final String description, final String link) {		
		
		super.signIn("manager1", "manager1");
		
		super.clickOnMenu("Manager", "List tasks");

		super.clickOnListingRecord(0);
		
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("executionStart", executionStart);
		super.fillInputBoxIn("executionEnd", executionEnd);
		super.fillInputBoxIn("link", link);
		
		super.clickOnSubmitButton("Update");

		super.checkColumnHasValue(0, 0, executionStart);
		super.checkColumnHasValue(0, 1, executionEnd);
		super.checkColumnHasValue(0, 2, workLoad);
		super.checkColumnHasValue(0, 3, title);
		
		super.signOut();
	}
	
	/*
	 * @Feature: Manager can update his/her tasks and publish it is long as it is not considered spam
	 * @Violated constraints: 
	 * 1. Title with spam
	 * 2. Description with spam
	 * 3. Link with spam
	 * 4. Start date before present
	 * 5. End date before present
	 * 6. Start date older than end date
	 * @Results: Not to update any tasks
	 */
	
	@ParameterizedTest
	@CsvFileSource(resources = "/usermanager/task/update-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(71)
	public void updateNegative(final int recordIndex, final String executionStart, final String executionEnd, 
		final String title, final String description, final String link) {		
		
		super.signIn("manager1", "manager1");
		
		super.clickOnMenu("Manager", "List tasks");
		
		super.clickOnListingRecord(0);

		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("executionStart", executionStart);
		super.fillInputBoxIn("executionEnd", executionEnd);
		super.fillInputBoxIn("link", link);
		
		super.clickOnSubmitButton("Update");
		
		super.checkErrorsExist();
		
		super.signOut();
	}

}
