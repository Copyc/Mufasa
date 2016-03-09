package core;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {
	protected static WebDriver driver;
	protected WebDriverWait wait;
	public abstract boolean isAvailable();
	
	@SuppressWarnings("static-access")
	public BasePage(WebDriver driver){
		wait = new WebDriverWait(driver, 15);
		this.driver = driver;
	}
	
	public abstract <T extends BasePage> BasePage load();
	public abstract String getExpectedUrl();
	
	public String getActualUrl() {
		return driver.getCurrentUrl();
	}
	
	
	public BasePage manageToMobile() {
   		driver.manage().window().setSize(new Dimension(640, 960));
   		return this;
	}
   	public BasePage manageMaximize() {
   		driver.manage().window().maximize();
   		return this;
	}
   	public BasePage refreshPage() {
   		driver.navigate().refresh();
   		return this;
	}
   	
   	public String getErrorMessage() {
   		return "";
   	}
}
