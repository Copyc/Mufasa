package formation.listeners;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import core.BasePage;
import core.DriverMaster;
import formation.Screenshotting;

/**
 * 
 * @author Oleh.Bufan
 *
 */
public class TestListener extends TestListenerAdapter {

	@Override
	public void onTestFailure(ITestResult result) {

		WebDriver driver = DriverMaster.getExistedInstance();
		BasePage.logStepIntoReport("Catching screenshot");
		Screenshotting.takeWebBrowserScreenshotImage(driver);
	}

	private static String getTestName(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		return testName;
	}
}
