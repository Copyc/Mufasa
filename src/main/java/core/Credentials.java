package core;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import formation.credentials.User;
import formation.credentials.Users;

public class Credentials {
	private static List<Users> getAllUsers() throws JsonParseException, JsonMappingException, IOException {
		InputStream fileInput;
		try {
			String dataFile = "Credentials.json";
			fileInput = new FileInputStream(
					"src/test/resources/testData/" + dataFile);
		} catch (Exception e) {
			throw new RuntimeException("Data file was not found");
		}
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
		TypeFactory typeFactory = TypeFactory.defaultInstance();
		return objectMapper.readValue(fileInput, typeFactory.constructCollectionType(ArrayList.class, Users.class));
	}
	/**
	 * Gets specified user from Credentials file
	 * @param username of user
	 * @return specified user
	 */
	public static User getUser(String username) {
		List<Users> users;
		try {
		users = getAllUsers();
		} catch (JsonParseException e) {
			throw new RuntimeException(e.getLocalizedMessage());
		} catch (JsonMappingException e) {
			throw new RuntimeException(e.getLocalizedMessage());
		} catch (IOException e) {
			throw new RuntimeException(e.getLocalizedMessage());
		}
		for (Users user : users) {
			if (user.getUsername().equals(username)) {
				return user.getUser();
			}
		}
		throw new RuntimeException("User '" + username + "' was not found");
	}
	/**
	 * Gets first user from Credentials file
	 * @return first user
	 */
	public static User getUser() {
		try {
			return getAllUsers().get(0).getUser();
		} catch (JsonParseException e) {
			throw new RuntimeException(e.getLocalizedMessage());
		} catch (JsonMappingException e) {
			throw new RuntimeException(e.getLocalizedMessage());
		} catch (IOException e) {
			throw new RuntimeException(e.getLocalizedMessage());
		}
	}
}
