package acme.testing.usermanager.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagerTaskCreateTest extends AcmePlannerTest {
	
	// Lifeycle management ------------------------------
	
	// Test cases ---------------------------------------
	
	/*
	 * @Feature: Manager can write tasks and list them
	 * @Violated constraints: None
	 * @Results: Create tasks without any errors
	 */
	
	@ParameterizedTest
	@CsvFileSource(resources = "/usermanager/task/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(50)
	public void createPositive(final int recordIndex, final String executionStart, final String executionEnd,
		final String workLoad, final String title, final String description, final String link) {		
		
		super.signIn("manager1", "manager1");
		
		super.clickOnMenu("Manager", "Create task");

		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("executionStart", executionStart);
		super.fillInputBoxIn("executionEnd", executionEnd);
		super.fillInputBoxIn("link", link);
		super.clickOnSubmitButton("Create");
		
		super.clickOnMenu("Manager", "List tasks");

		super.checkColumnHasValue(recordIndex, 0, executionStart);
		super.checkColumnHasValue(recordIndex, 1, executionEnd);
		super.checkColumnHasValue(recordIndex, 2, workLoad);
		super.checkColumnHasValue(recordIndex, 3, title);
		
		super.signOut();
	}
	
	/*
	 * @Feature: Manager can create tasks and publish it is long as it is not considered spam
	 * @Violated constraints: 
	 * 1. Title with spam
	 * 2. Description with spam
	 * 3. Link with spam
	 * 4. Start date before present
	 * 5. End date before present
	 * 6. Start date older than end date
	 * @Results: Not to create any tasks
	 */
	
	@ParameterizedTest
	@CsvFileSource(resources = "/usermanager/task/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(51)
	public void createNegative(final int recordIndex, final String executionStart, final String executionEnd, 
		final String title, final String description, final String link) {		
		
		super.signIn("manager1", "manager1");
		
		super.clickOnMenu("Manager", "Create task");

		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("executionStart", executionStart);
		super.fillInputBoxIn("executionEnd", executionEnd);
		super.fillInputBoxIn("link", link);
		super.clickOnSubmitButton("Create");
		
		super.checkErrorsExist();
		
		super.signOut();
	}

}
