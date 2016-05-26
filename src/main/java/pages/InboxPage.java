package pages;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import ru.yandex.qatools.allure.annotations.Step;
import blocks.ComposeEmailBlock;
import core.BasePage;

public class InboxPage extends BasePage {
	
	@FindBy(xpath = "//a[contains(@href,'/#inbox')]")
	private WebElement btnInbox;
	@FindBy(xpath = "//a[contains(@href,'SignOutOptions')]")
	private WebElement btnSignoutOption;
	@FindBy(xpath = "//a[contains(@href,'Logout')]")
	private WebElement btnSignOut;
	@FindBy(xpath = "//input[@id='gbqfq']")
	private WebElement inputSearch;
	@FindBy(xpath = "//button[@id='gbqfb']")
	private WebElement btnSearch;
	@FindBy(xpath = "//div[@data-tooltip='Refresh']")
	private WebElement btnRefresh;
	@FindBy(xpath = "//span[contains(@class,'v1')]")
	private WebElement msgLoading;
	@FindBy(xpath = "//div[@gh='cm']")
	private WebElement btnCompose;
	@FindBy(xpath = "//div[@gh='cm']")
	private WebElement emailDialog;

	public InboxPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		isAvailable(); 
	}
	
	@Override
	@Step("Loaging 'Inbox page'")
	public InboxPage load() {
		throw new RuntimeException("You cannot load inbox page");
	}
	@Override
	public boolean isAvailable() {
		try {
			explicitWait.until(ExpectedConditions.elementToBeClickable(btnInbox));
		} catch (TimeoutException e) {
			
			return false;
		}
		
		return true;
	}

	@Step("Sign out")
	public LoginPage signOut() {
		explicitWait.until(ExpectedConditions.elementToBeClickable(btnSignoutOption));
		btnSignoutOption.click();
		explicitWait.until(ExpectedConditions.elementToBeClickable(btnSignOut));
		btnSignOut.click();
		
		return new LoginPage(driver);
	}
	@Step("{method} with text '{0}'")
	public int getCountOfEmailsOnPage(String emailText) {
		int count = driver
				.findElements(
						By.xpath("//span[1]/descendant-or-self :: *[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'),'"
								+ emailText + "')]")).size();
		
		return count;
	}
	@Step("{method}")
	public int getCountOfUnreadEmails() {
		String txtInbox = btnInbox.getText();
		Pattern pattern = Pattern.compile("(\\d+\\s)*\\d+");
		Matcher matcher = pattern.matcher(txtInbox);
		while (matcher.find()) {
			
			return Integer.parseInt(matcher.group(0).replaceAll("\\s+",""));
		}
		
		return 0;
	}
	@Step("Click on 'Compose' button")
	public ComposeEmailBlock openComposeDialog() {
		btnCompose.click();
		return new ComposeEmailBlock(driver, emailDialog);
	}

	@Step("{method}")
	public InboxPage composeEmail(String email, String subject, String body) {
		
		return openComposeDialog()
				.withEmail(email)
				.withSubject(subject)
				.withBody(body)
				.sendEmail();
	}

	@Step("{method} with subject '{0}' and body '{1}'")
	public InboxPage findEmail(String email, String subject, String body) {
		By byEmailRow = By.xpath("//tr[descendant::span[@email='" + email.toLowerCase() 
								+ "']][descendant::span/b[text()='" + subject
								+ "']][descendant::span[contains(text(),'" + body + "')]]");
		WebElement emailRow = waitForElementToAppear(byEmailRow);
		emailRow.click();
		
		return this;
	}
	
}
