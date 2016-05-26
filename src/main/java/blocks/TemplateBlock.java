package blocks;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import core.BaseBlock;

public class TemplateBlock extends BaseBlock{

	public TemplateBlock(WebDriver driver, WebElement parentElement) {
		super(driver, parentElement);
		PageFactory.initElements(driver, this);
	}

	@Override
	public boolean isAvailable() {
		// TODO Auto-generated method stub
		return false;
	}

}
