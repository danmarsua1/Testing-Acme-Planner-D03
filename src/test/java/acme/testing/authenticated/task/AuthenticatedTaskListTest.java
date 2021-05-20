package acme.testing.authenticated.task;

import acme.testing.AcmePlannerTest;

public class AuthenticatedTaskListTest extends AcmePlannerTest {
	
	// Lifeycle management ------------------------------
	
	// Test cases ---------------------------------------
	
	/*@DisplayName("List the public tasks that are finished")
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/task/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(40)
	public void list(final int recordIndex, final String executionStart, final String executionEnd,
		final String workLoad, final String title, final String description, final String link) {	
		
		this.signIn("administrator", "administrator");
		
		super.clickOnMenu("Authenticated", "List tasks");

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
		
		super.signOut();
		
	}*/

}
