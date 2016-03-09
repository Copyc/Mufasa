package webPages;

import core.Environment;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import core.BasePage;

public class InboxPage extends BasePage {
	
	private static String URL;
	
	private static By btnInbox = By.xpath("//a[contains(@href,'/#inbox')]");
	private static By btnSignoutOption = By.xpath("//a[contains(@href,'SignOutOptions')]");
	private static By btnSignOut = By.xpath("//a[contains(@href,'Logout')]");
	private static By inputSearch = By.xpath("//input[@id='gbqfq']");
	private static By btnSearch  = By.xpath("//button[@id='gbqfb']");

	public InboxPage(WebDriver driver) {
		super(driver);
		URL = Environment.getURL("mail");
	}
	
	@Override
	public InboxPage load() {
		driver.get(URL);
		return this;
	}
	@Override
	public boolean isAvailable() {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(btnInbox));
		} catch (TimeoutException e) {
			return false;
		}
		return true;
	}
	@Override
	public String getExpectedUrl() {
		return URL;
	}

	public LoginPage signOut() {
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(btnSignoutOption)));
		driver.findElement(btnSignoutOption).click();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(btnSignOut)));
		driver.findElement(btnSignOut).click();
		return new LoginPage(driver);
	}
	public int findEmails(String emailText) {
		int count = driver
				.findElements(
						By.xpath("//span[1]/descendant-or-self :: *[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'),'"
								+ emailText + "')]")).size();
		return count;
	}
}
