package testSuite;

import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import core.BaseTest;
import core.CsvImporter;

public class TemplateTest extends BaseTest {
	@BeforeMethod
	public void init() {
	}
	
	@Test(enabled = false, priority = 0)
	@Parameters({ "parameter" })
	public void test1(@Optional("") String string) {	
	}
	
	@Test(enabled = false, priority = 0, dataProvider = "CsvImporter", dataProviderClass = CsvImporter.class)	
	public void test2(Map<String, String> testData) {
	}
}
