package testSuite;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import webPages.InboxPage;
import webPages.LoginPage;
import core.BaseTest;
import core.CsvImporter;

public class GmailTests extends BaseTest {
	
	
	@Test(priority = 2, dataProvider = "CsvImporter", dataProviderClass = CsvImporter.class)	
	public void failureLoginPass(Map<String, String> testData) {
		String login = testData.get("login");
		String pass = testData.get("pass");
		String actualMessage = 
				new LoginPage(driver)
				.load()
				.setLogin(login)
				.forwardToSetPassword()
				.setPassword(pass)
				.forwardToMail()
				.getErrorMessage();
		Assert.assertEquals(actualMessage, testData.get("expectedMessage"));
	}
	
	@Test(priority = 3)
	public void successLogin() {
		String login = "SeleniumWebDriver.Copyc";
		String pass = "$eleniumG1t" ;
		
		InboxPage inboxPage = 
				new LoginPage(driver)
				.load()
				.signInAs(login, pass);
		
		Assert.assertTrue(inboxPage.isAvailable());
	}
	
	@Test(priority = 12)	
	public void successSearchMail() {
		String login = "SeleniumWebDriver.Copyc";
		String pass = "$eleniumG1t";
		String emailText = "selenium";
		
		InboxPage inboxPage = 
				new LoginPage(driver)
				.load()
				.signInAs(login, pass);
		
		Assert.assertTrue(inboxPage.findEmails(emailText)>0);
	}
	@Test(priority = 10)	
	public void failureSearchMail() {
		String login = "SeleniumWebDriver.Copyc";
		String pass = "$eleniumG1t";
		String emailText = "aetrhwethw";
		
		InboxPage inboxPage = 
				new LoginPage(driver)
				.load()
				.signInAs(login, pass);
		
		Assert.assertTrue(inboxPage.findEmails(emailText)==0);
	}
}
