package core;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import core.DriverMaster;

public class BaseTest {
	protected WebDriver driver;

	@BeforeMethod
	@Parameters({ "browserName" })
	public void setup(@Optional ("firefox") String browserName, Method method) throws Exception {
		System.out.println("\n\nTest name: " + method.getName()); 
		driver = DriverMaster.getInstance(browserName);
	}

	@AfterMethod
	public void tearDown() throws Exception {
		if (driver != null) {
			DriverMaster.killDriverInstance();
		}
	}
}
