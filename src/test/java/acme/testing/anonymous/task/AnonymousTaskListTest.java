package acme.testing.anonymous.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AnonymousTaskListTest extends AcmePlannerTest {
	
	// Lifeycle management ------------------------------
	
	// Test cases ---------------------------------------
	
	/*
	 * @Feature: Anonymous can list the public tasks that are non-finished
	 * @Violated constraints: None
	 * @Results: List public tasks that are non-finished
	 */
	
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/task/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(30)
	public void list(final int recordIndex, final String executionStart, final String executionEnd,
		final String workLoad, final String title, final String description, final String link) {		
		super.clickOnMenu("Anonymous", "List tasks");

		super.checkColumnHasValue(recordIndex, 0, executionStart);
		super.checkColumnHasValue(recordIndex, 1, executionEnd);
		super.checkColumnHasValue(recordIndex, 2, workLoad);
		super.checkColumnHasValue(recordIndex, 3, title);
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("executionStart", executionStart);
		super.checkInputBoxHasValue("executionEnd", executionEnd);
		super.checkInputBoxHasValue("workLoad", workLoad);
		super.checkInputBoxHasValue("link", link);
	}

}
