package formation.credentials;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Emails {
	private List<String> positive;
	private List<String> negative;
	
	@JsonCreator
	public Emails(@JsonProperty("positive") List<String> positive, @JsonProperty("negative") List<String> negative) {
		this.positive = positive;
		this.negative = negative;
	}
	
	/**
	 * @return list of positive mails
	 */
	public List<String> getAllPositive() {
		return positive;
	}

	/**
	 * @param index of 
	 * @return specified positive email
	 */
	public String getPositive(int index) {
		return positive.get(0);
	}

	/**
	 * @return first positive email
	 */
	public String getPositive() {
		return getPositive(0);
	}
	
	/**
	 * @return list of negative mails
	 */
	public List<String> getAllNegative() {
		return negative;
	}
	/**
	 * @param index of 
	 * @return specified negative email
	 */
	public String getNegative(int index) {
		return negative.get(index);
	}
	/**
	 * @return first negative email
	 */
	public String getNegative() {
		return getNegative(0);
	}

}
