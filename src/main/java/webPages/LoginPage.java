package webPages;

import core.Environment;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import core.BasePage;

public class LoginPage extends BasePage {
	
	private static String URL;
	
	private static By signinContainer = By.xpath("//div[contains(@class,'signin-card')]");
	private static By aSignin = By.xpath("//a[@id='gmail-sign-in']");
	private static By inputLogin = By.xpath("//input[@id='Email']");
	private static By inputPassHidden = By.xpath("//input[@id='Passwd-hidden']");
	private static By btnForwardToSetPass = By.xpath("//input[@id='next']");
	private static By btnBackwardToSetEmail = By.xpath("//a/img[contains(@id,'back-arrow')]");
	private static By inputPass = By.xpath("//input[@id='Passwd']");
	private static By btnSignin = By.xpath("//input[@id='signIn']");
	private static By lblErrorPass = By.xpath("//div[@id='password-shown']/following::span[contains(@id,'errormsg')]");

	public LoginPage(WebDriver driver) {
		super(driver);
		URL = Environment.getURL("mail");
	}
	
	@Override
	public LoginPage load() {
		driver.get(URL);
		return this;
	}
	@Override
	public boolean isAvailable() {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(signinContainer));
		} catch (TimeoutException e) {
			return false;
		}
		return true;
	}
	@Override
	public String getExpectedUrl() {
		return URL;
	}
	
	public LoginPage setLogin(String login) {
		wait.until(ExpectedConditions.elementToBeClickable(inputLogin));
		driver.findElement(inputLogin).clear();
		driver.findElement(inputLogin).sendKeys(login);
		return this;
	}
	public LoginPage forwardToSetPassword() {
		wait.until(ExpectedConditions.elementToBeClickable(btnForwardToSetPass));
		driver.findElement(btnForwardToSetPass).click();
		return this;
	}
	public LoginPage backwardToSetEmail() {
		wait.until(ExpectedConditions.elementToBeClickable(btnBackwardToSetEmail));
		driver.findElement(btnBackwardToSetEmail).click();
		return this;
	}
	public LoginPage setPassword(String pass) {
		wait.until(ExpectedConditions.elementToBeClickable(inputPass));
		driver.findElement(inputPass).clear();
		driver.findElement(inputPass).sendKeys(pass);
		return this;
	}
	public LoginPage forwardToMail() {
		wait.until(ExpectedConditions.elementToBeClickable(btnSignin));
		driver.findElement(btnSignin).click();
		return this;
	}
	public InboxPage signInAs(String login, String pass) {
		setLogin(login)
				.forwardToSetPassword()
				.setPassword(pass)
				.forwardToMail();
		return new InboxPage(driver);	
	}
	
	@Override
	public String getErrorMessage() {
		wait.until(ExpectedConditions.presenceOfElementLocated(lblErrorPass));
		return driver.findElement(lblErrorPass).getText();
	}
}
