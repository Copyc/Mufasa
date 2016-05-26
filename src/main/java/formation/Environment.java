package formation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import ru.yandex.qatools.allure.annotations.Step;

public class Environment {
	
	@Step("Loading {0} environment")
	public static String getURL(String environmentName) {
		try {
			return loadPropertyFile().getProperty(environmentName);
		} catch (Exception e) {
			throw new RuntimeException("Environment '" + environmentName + "' was not found");
		}
	}
	
	private static Properties loadPropertyFile() {
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream("src/test/resources/testData/environment.properties"));
		} catch (FileNotFoundException e) {
			throw new RuntimeException("property file cannot be found");
		} catch (IOException e) {
			throw new RuntimeException("property file can't be read");
		}
		return properties;
	}

}
