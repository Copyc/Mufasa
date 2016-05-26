package blocks;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.InboxPage;
import core.BaseBlock;

public class ComposeEmailBlock extends BaseBlock{
	private final String parentXpath = "//";
	
	@FindBy(xpath = parentXpath + "td[contains(@class,'Hm')]/img[3]")
	WebElement btnSaveAndClose;
	@FindBy(xpath = parentXpath + "")
	WebElement btnDiscard;
	@FindBy(xpath = parentXpath + "")
	WebElement btnSend;
	@FindBy(xpath = parentXpath + "")
	WebElement inputTo;
	@FindBy(xpath = parentXpath + "")
	WebElement inputSubject;
	@FindBy(xpath = parentXpath + "")
	WebElement inputMessageBody;

	public ComposeEmailBlock(WebDriver driver, WebElement parentElement) {
		super(driver, parentElement);
		this.paretnElement = parentElement;
//		parentXPath = getXPath(parentElement) + parentXpath + "descendant::";
		PageFactory.initElements(driver, this);
	}

	@Override
	public boolean isAvailable() {
		return true;
	}
	
	public InboxPage sendEmail() {
		btnSend.click();
		
		return new InboxPage(driver);
	}

	/**
	 * Enter recipient
	 * @param email
	 */
	public ComposeEmailBlock withEmail(String email) {
		inputTo.clear();
		inputTo.sendKeys(email);
		
		return this;
	}
	/**
	 * Enter subject
	 * @param subject
	 */
	public ComposeEmailBlock withSubject(String subject) {
		inputTo.clear();
		inputTo.sendKeys(subject);
		
		return this;
	}
	/**
	 * Enter recipient
	 * @param body
	 */
	public ComposeEmailBlock withBody(String body) {
		inputTo.clear();
		inputTo.sendKeys(body);
		
		return this;
	}

}
