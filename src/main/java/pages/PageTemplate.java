package pages;

import org.openqa.selenium.WebDriver;

import ru.yandex.qatools.allure.annotations.Step;
import core.BasePage;
import formation.Environment;

public class PageTemplate extends BasePage {

	private static String URL;
	
	public PageTemplate(WebDriver driver) {
		super(driver);
		URL = Environment.getURL("");
	}

	@Override
	@Step("Loaging template page")
	public PageTemplate load() {
		driver.get(URL);
		return this;
	}
	@Override
	public boolean isAvailable() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Step(value = "{method}{0}")
	public PageTemplate method() {
		return this;
	}

}
