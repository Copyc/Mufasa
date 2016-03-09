package core;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

/**
 * A class that returns a singleton of WebDriver object.
 */
public class DriverMaster {

	private static final String CHROME = "chrome";
	private static final String FIREFOX = "firefox";

	private static WebDriver driver;

	private DriverMaster() {}

	/**
	 * Gets the single instance of WebDriver.
	 *
	 * @param browser the browser name set in properties
	 * @return single instance of WebDriverFactory
	 * @throws Exception the exception of invalid browser property
	 */
	public static WebDriver getInstance(String browser) throws Exception {
		if (driver == null) {
			if (CHROME.equals(browser)) {
				setChromeDriver();
				
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--ignore-certificate-errors");
				driver = new ChromeDriver(options);
				
			} else if (FIREFOX.equals(browser)) {
				FirefoxProfile profile = new FirefoxProfile();
				profile.setAcceptUntrustedCertificates(true);
				driver = new FirefoxDriver(profile);
				
			} else
				throw new Exception("Invalid browser name property set in configuration file");
			driver.manage().window().maximize();
		}

		return driver;
	}

	/**
	 * Kill driver instance.
	 * @throws Exception 
	 */
	public static void killDriverInstance() throws Exception {
		driver.quit();
		driver = null;
	}

	/**
	 * Sets the chrome driver path for specific OS.
	 *
	 * @throws Exception the exception
	 */
	private static void setChromeDriver() throws Exception {
		String osName = System.getProperty("os.name").toLowerCase();
		StringBuffer chromeBinaryPath = new StringBuffer(
				"src/main/resources/drivers/");

		if (osName.startsWith("win")) {
			chromeBinaryPath.append("chrome-win/chromedriver.exe");
		} else if (osName.startsWith("lin")) {
			chromeBinaryPath.append("chrome-lin/chromedriver");
		} else if (osName.startsWith("mac")) {
			chromeBinaryPath.append("chrome-mac/chromedriver");
		} else
			throw new Exception("Your OS is invalid for webdriver tests");

		System.setProperty("webdriver.chrome.driver",
				chromeBinaryPath.toString());
	}

}