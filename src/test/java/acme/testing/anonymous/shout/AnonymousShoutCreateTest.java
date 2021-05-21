package acme.testing.anonymous.shout;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AnonymousShoutCreateTest extends AcmePlannerTest {
	
	// Lifeycle management ------------------------------
	
	// Test cases ---------------------------------------
	
	@DisplayName("Write shouts and list them")
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
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
	
	@DisplayName("Write shouts, publish it is long as it is not considered spam")
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(21)
	public void createNegative(final int recordIndex, final String author, final String text, final String info) {		
		super.clickOnMenu("Anonymous", "New shout");

		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("info", info);
		super.clickOnSubmitButton("Shout!");
		
		super.checkErrorsExist();
	}

}
