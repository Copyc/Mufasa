package core;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import ru.yandex.qatools.allure.annotations.Parameter;
import core.DriverMaster;
import formation.listeners.TestListener;

@Listeners({TestListener.class})
public class BaseTest {
	protected WebDriver driver;

	@Parameter("Environment")
	private static String environmentName;
	@Parameter("Browser")
	private String browserName;

	@BeforeMethod
	@Parameters({ "browserName", "environmentName"})
	public void setup(@Optional("firefox") String browserName,
			@Optional("production") String environmentName, Method method)
			throws Exception {
		System.out.println("\n\nTest name: " + method.getName()); 
		driver = DriverMaster.getInstance(browserName);
		this.browserName = browserName;
		BaseTest.environmentName = environmentName;
	}

	@AfterMethod
	public void tearDown() throws Exception {
		if (driver != null) {
			DriverMaster.killDriverInstance();
		}
	}
	
	public static String getEnvironmentName() {
		return environmentName;
	}
}
