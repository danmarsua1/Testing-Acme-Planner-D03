package acme.testing.anonymous.shout;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AnonymousShoutCreateTest extends AcmePlannerTest {
	
	// Lifeycle management ------------------------------
	
	// Test cases ---------------------------------------
	
	/*
	 * @Feature: Anonymous can write shouts and list them
	 * @Violated constraints: None
	 * @Results: Create shouts without any errors
	 */
	
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createPositive(final int recordIndex, final String author, final String text, final String info) {		
		super.clickOnMenu("Anonymous", "New shout");

		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("info", info);
		super.clickOnSubmitButton("Shout!");
		
		super.clickOnMenu("Anonymous", "List shouts");

		super.checkColumnHasValue(0, 1, author);
		super.checkColumnHasValue(0, 2, text);
	}
	
	/*
	 * @Feature: Anonymous can write shouts, publish it is long as it is not considered spam
	 * @Violated constraints: 
	 * 1. Author with spam
	 * 2. Text with spam
	 * 3. Info with spam
	 * @Results: Not to create any shouts
	 */
	
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(11)
	public void createNegative(final int recordIndex, final String author, final String text, final String info) {		
		super.clickOnMenu("Anonymous", "New shout");

		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("info", info);
		super.clickOnSubmitButton("Shout!");
		
		super.checkErrorsExist();
	}

}
