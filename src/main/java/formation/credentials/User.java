package formation.credentials;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
	private String login;
	private String password;
	private Emails emails;
	
	@JsonCreator
	public User(@JsonProperty("login") String login, @JsonProperty("password") String password, @JsonProperty("emails") Emails emails) {
		this.login = login;
		this.password = password;
		this.emails = emails;
	}

	/**
	 * @return login
	 */
	public String getLogin() {
		return login;
	}
	/**
	 * @return password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @return emails object
	 */
	public Emails getEmails() {
		return emails;
	}
}
