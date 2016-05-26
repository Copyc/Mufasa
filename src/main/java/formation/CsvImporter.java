package formation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;

import com.opencsv.CSVReader;


public class CsvImporter {

	@DataProvider(name = "CsvImporter")
	public static Iterator<Object[]> importData(Method method) {
		List<Object[]> list = new ArrayList<Object[]>();
//		testData / {test package name} / {test class name} .csv
		String pathname = "testData" + File.separator
				+ method.getDeclaringClass().getPackage().getName() + File.separator
				+ method.getDeclaringClass().getSimpleName() + ".csv";
		InputStream dataStream = CsvImporter.class.getClassLoader().getResourceAsStream(pathname);
		try {
//			Comma is separator by default for CSVReader
			CSVReader reader = new CSVReader(new InputStreamReader(dataStream , "UTF-8"));
			String[] keys = reader.readNext();

			String[] dataRow;
			while ((dataRow = reader.readNext()) != null) {
				Map<String, String> testData = new HashMap<String, String>();
				for (int i = 0; i < keys.length; i++) {
					testData.put(keys[i], dataRow[i]);
				}
				list.add(new Object[] { testData });
			}
			reader.close();

		} catch (FileNotFoundException e) {
			throw new RuntimeException(".csv file cannot be found");
		} catch (IOException e) {
			throw new RuntimeException(".csv file can't be read");
		}
		return list.iterator();
	}

}
