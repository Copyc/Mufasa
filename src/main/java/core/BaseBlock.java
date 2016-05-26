package core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class BaseBlock extends BasePage {
	protected WebElement paretnElement;

	public BaseBlock(WebDriver driver, WebElement parentElement) {
		super(driver);
		this.paretnElement = parentElement;
	}

	@Override
	public abstract boolean isAvailable();

	@Override
	public final BaseBlock load() {
		throw new RuntimeException("Please load block from page");
	}

}
