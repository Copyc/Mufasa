package testSuite;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.InboxPage;
import pages.LoginPage;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;
import utilities.DataOperations;
import core.BaseTest;
import core.Credentials;
import formation.CsvImporter;

public class GmailTests extends BaseTest {
	
	@Features("Login")
	@Stories("Failure login")
	@Test(dataProvider = "CsvImporter", dataProviderClass = CsvImporter.class)
	public void failureLoginPass(Map<String, String> testData) {
		String login = testData.get("login");
		String pass = testData.get("password");
		
		String actualMessage = 
				new LoginPage(driver)
				.load()
				.signInAsUnsuccessfully(login, pass)
				.getErrorMessage();
		Assert.assertEquals(actualMessage, testData.get("expectedMessage"));
	}
	
	@Features("Login")
	@Stories("Success login")
	@Test
	public void successLogin() {
		String login = Credentials.getUser().getLogin();
		String pass =  Credentials.getUser().getPassword();
		
		InboxPage inboxPage = 
				new LoginPage(driver)
				.load()
				.signInAs(login, pass);
		Assert.assertTrue(inboxPage.isAvailable(), "Inbox page cannot be found");
	}
	
	@Features("Inbox")
	@Stories("Search")
	@Test
	public void successSearchMail() {
		String login = Credentials.getUser().getLogin();
		String pass =  Credentials.getUser().getPassword();
		String emailText = Credentials.getUser().getEmails().getPositive();
		
		InboxPage inboxPage = 
				new LoginPage(driver)
				.load()
				.signInAs(login, pass);
		
		Assert.assertTrue(inboxPage.getCountOfEmailsOnPage(emailText) > 0, "Count of found emails are less than 1");
	}
	@Features("Inbox")
	@Stories("Search")
	@Test
	public void failureSearchMail() {
		String login = Credentials.getUser().getLogin();
		String pass =  Credentials.getUser().getPassword();
		String emailText =  Credentials.getUser().getEmails().getNegative();
		
		InboxPage inboxPage = 
				new LoginPage(driver)
				.load()
				.signInAs(login, pass);
		Assert.assertEquals(inboxPage.getCountOfEmailsOnPage(emailText), 0, "Count of found emails are not equal 0");
	}
	
	@Features("Inbox")
	@Stories("Email")
	@Test
	public void composeEmailToMyself() {
		String login = Credentials.getUser().getLogin();
		String pass =  Credentials.getUser().getPassword();
		
		InboxPage inboxPage = 
				new LoginPage(driver)
				.load()
				.signInAs(login, pass);
		String expectedSubject = DataOperations.randomString(10);
		String expectedBody = DataOperations.randomString(10);
		
		Assert.assertTrue(inboxPage
				.composeEmail(login + "@gmail.com", expectedSubject, expectedBody) //TODO
				.findEmail(login + "@gmail.com", expectedSubject, expectedBody)
				.isAvailable());
	}
	
	@Description("This test fails just for checking report")
	@Features("Failed Tests")
	@Stories("Login")
	@Test
	public void failedSuccessLogin() {
		String login = Credentials.getUser().getLogin();
		String pass =  "wergweg";
		
		InboxPage inboxPage = 
				new LoginPage(driver)
				.load()
				.signInAs(login, pass);
		
		Assert.assertTrue(inboxPage.isAvailable(), "Inbox page cannot be found");
	}
	
	@Features("Pending")
	@Test(enabled = false)
	public void disabledTest() {}
}
