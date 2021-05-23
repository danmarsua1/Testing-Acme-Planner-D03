package acme.testing.usermanager.task;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagerTaskListTest extends AcmePlannerTest {
	
	// Lifeycle management ------------------------------
	
	// Test cases ---------------------------------------
	
	/*
	 * @Feature: Manager can list his/her tasks
	 * @Violated constraints: None
	 * @Results: List his/her tasks
	 */
	
	@DisplayName("Manager can list his/her tasks")
	@ParameterizedTest
	@CsvFileSource(resources = "/usermanager/task/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(60)
	public void list(final int recordIndex, final String executionStart, final String executionEnd,
		final String workLoad, final String title, final String description, final String link) {	
		
		super.signIn("manager1", "manager1");
		
		super.clickOnMenu("Manager", "List tasks");

		super.checkColumnHasValue(recordIndex, 0, executionStart);
		super.checkColumnHasValue(recordIndex, 1, executionEnd);
		super.checkColumnHasValue(recordIndex, 2, workLoad);
		super.checkColumnHasValue(recordIndex, 3, title);
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("executionStart", executionStart);
		super.checkInputBoxHasValue("executionEnd", executionEnd);
		super.checkInputBoxHasValue("link", link);
		
		super.signOut();
		
	}

}
