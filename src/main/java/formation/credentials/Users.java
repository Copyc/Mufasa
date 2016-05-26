package formation.credentials;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Users {
	private String username;
	private User user;
	
	@JsonCreator
	public Users(@JsonProperty("username") String username, @JsonProperty("user") User user) {
		this.username = username;
		this.user = user;
	}
	
	public String getUsername() {
		return username;
	}
	public User getUser() {
		return user;
	}
}
