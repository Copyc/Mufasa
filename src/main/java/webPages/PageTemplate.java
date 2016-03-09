package webPages;

import org.openqa.selenium.WebDriver;

import core.BasePage;
import core.Environment;

public class PageTemplate extends BasePage {

	private static String URL;
	
	public PageTemplate(WebDriver driver) {
		super(driver);
		URL = Environment.getURL("");
	}

	@Override
	public PageTemplate load() {
		driver.get(URL);
		return this;
	}
	@Override
	public boolean isAvailable() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public String getExpectedUrl() {
		return URL;
	}

}
