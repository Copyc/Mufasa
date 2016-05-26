package pages;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import ru.yandex.qatools.allure.annotations.Step;
import core.BasePage;
import core.BaseTest;
import formation.Environment;

public class LoginPage extends BasePage {
		
	@FindBy (xpath = "//div[contains(@class,'signin-card')]")
	private WebElement signinContainer;
	@FindBy (xpath = "//a[@id='gmail-sign-in']")
	private WebElement aSignin;
	@FindBy (xpath = "//input[@id='Email']")
	private WebElement inputLogin;
	@FindBy (xpath = "//input[@id='Passwd-hidden']")
	private WebElement inputPassHidden;
	@FindBy (xpath = "//input[@id='next']")
	private WebElement btnForwardToSetPass;
	@FindBy (xpath = "//a/img[contains(@id,'back-arrow')]")
	private WebElement btnBackwardToSetEmail;
	@FindBy (xpath = "//input[@id='Passwd']")
	private WebElement inputPass;
	@FindBy (xpath = "//input[@id='signIn']")
	private WebElement btnSignin;
	@FindBy (xpath = "//div[@id='password-shown']/following::span[contains(@id,'errormsg')]")
	private WebElement lblErrorPass;

	public LoginPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	@Override
	@Step("Loaging 'Login page'")
	public LoginPage load() {
		driver.get(Environment.getURL(BaseTest.getEnvironmentName()));
		
		return this;
	}
	@Override
	public boolean isAvailable() {
		try {
			explicitWait.until(ExpectedConditions.elementToBeClickable(signinContainer));
		} catch (TimeoutException e) {
			
			return false;
		}
		
		return true;
	}
	@Override
	public String getErrorMessage() {
//		wait.until(ExpectedConditions.presenceOfElementLocated(lblErrorPass));
		
		return lblErrorPass.getText();
	}
	
	@Step("{method}: '{0}'")
	public LoginPage setLogin(String login) {
		inputLogin.clear();
		inputLogin.sendKeys(login);
		
		return this;
	}
	@Step("{method}")
	public LoginPage forwardToSetPassword() {
		btnForwardToSetPass.click();
		
		return this;
	}
	@Step("{method}")
	public LoginPage backwardToSetEmail() {
		btnBackwardToSetEmail.click();
		
		return this;
	}
	@Step("{method}: '{0}'")
	public LoginPage setPassword(String pass) {
		explicitWait.until(ExpectedConditions.elementToBeClickable(inputPass));
		inputPass.clear();
		inputPass.sendKeys(pass);
		
		return this;
	}
	@Step("{method}")
	public LoginPage forwardToMail() {
		btnSignin.click();
		
		return this;
	}
	/**
	 * Sign into 
	 * @param login
	 * @param pass
	 * @return Inbox page
	 */
	@Step("{method} User: '{0}', Password: '{1}'")
	public InboxPage signInAs(String login, String pass) {
		setLogin(login)
				.forwardToSetPassword()
				.setPassword(pass)
				.forwardToMail();
		
		return new InboxPage(driver);	
	}

	public LoginPage signInAsUnsuccessfully(String login, String pass) {
		
		return setLogin(login)
				.forwardToSetPassword()
				.setPassword(pass)
				.forwardToMail();
	}
}
