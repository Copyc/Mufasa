package core;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.Annotations;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

import ru.yandex.qatools.allure.annotations.Step;

public abstract class BasePage {
	protected static WebDriver driver;
		
	protected WebDriverWait explicitWait;
	protected Wait<WebDriver> fluentWait;
	public abstract boolean isAvailable();
	
	@SuppressWarnings("static-access")
	public BasePage(WebDriver driver){
		explicitWait = new WebDriverWait(driver, 15);
		fluentWait= new FluentWait<WebDriver>(driver)
	       .withTimeout(60, TimeUnit.SECONDS)
	       .pollingEvery(3, TimeUnit.SECONDS)
	       .ignoring(NoSuchElementException.class);
		this.driver = driver;
	}
	
	/**
	 * Log into report
	 * @param string to be logged in report as step
	 */
	@Step("{0}")
	public static void logStepIntoReport(String value) {}
	
	protected abstract BasePage load();
	
	public String getActualUrl() {
		return driver.getCurrentUrl();
	}
	
	
	@Step("{method} width:{0}, height:{1}")
	public BasePage manageToDimension(int width, int height) {
   		driver.manage().window().setSize(new Dimension(width, height));
   		return this;
	}
	public BasePage manageToMobile() {
   		return manageToDimension(640, 960);
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
   	
   	
	protected String getXPath(WebElement webElement) {
		Pattern pattern = Pattern.compile(".*xpath: (.+)");
		Matcher matcher = pattern.matcher(webElement.toString());
		while (matcher.find()) {
			return matcher.group(1);
		}
		return "";
	}
	
	@SuppressWarnings("unused")
	private By getBy(String fieldName) {
		try {
			return new Annotations(this.getClass().getDeclaredField(fieldName))
					.buildBy();
		} catch (NoSuchFieldException e) {
			return null;
		}
	}
	
	
	protected List<String> getTextFromListOfElements(List<WebElement> listOfWebElements) {
		List<String> listOfText = new ArrayList<String>();
		for (WebElement webElement : listOfWebElements) {
			listOfText.add(webElement.getText());
		}
		return listOfText;
	}
	
	/**
	 * Waits for an element to appear on the page before returning. Example:
	 * WebElement waitElement =
	 * fluentWait(By.cssSelector(div[class='someClass']));
	 * 
	 * @param locator
	 * @return
	 */
	protected WebElement waitForElementToAppear(final By locator) throws NoSuchElementException
	{
	  WebElement element = null;
		try {
			element = fluentWait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver driver) {
					return driver.findElement(locator);
				}
			});
		}
	  catch (TimeoutException e) {
	    try {
	      driver.findElement(locator);
	    }
	    catch (NoSuchElementException exception) {
	      exception.addSuppressed(e);
	      throw exception;
	    }
	    e.addSuppressed(e);
	    throw new NoSuchElementException("Timeout reached when searching for element!", e);
	  }
	  return element;
	}
}
