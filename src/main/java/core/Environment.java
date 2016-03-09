package core;

public class Environment {
	private static final String BASE_PROTOCOL = "https://";
	private static final String BASE_URL = ".google.com";
	private static final String BASE_PORT = "";
	
	public static String getURL(String localURL) {
		return BASE_PROTOCOL + localURL + BASE_URL + BASE_PORT;
	}
}
