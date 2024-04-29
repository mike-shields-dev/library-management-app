package uk.co.michaelshields.assignment_1;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.validator.routines.EmailValidator;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

/**
 * A class that models a member's details.
 * 
 * This class utilises:<br>
 * <ul>
 * <li>The <b>EmailValidator</b> class of the
 * <b>org.apache.commons.validator.routines</b> lib</li>
 * <li>The <b>PhoneNumberUtil</b> and <b>PhoneNumber</b> classes of the
 * <b>com.google.i18n.phonenumbers</b> lib</li>
 * </ul>
 * 
 * 
 * The class also implements the Displayable and IDNumber interfaces
 *
 * @see <a href="Displayable.html">Displayable</a>
 * @see <a href="IDNumber.html">IDNumber</a>
 * 
 * @see <a href=
 *      "https://commons.apache.org/proper/commons-validator/apidocs/org/apache/commons/validator/routines/package-summary.html">org.apache.commons.validator.routines</a>
 * @see <a href=
 *      "https://javadoc.io/doc/com.googlecode.libphonenumber/libphonenumber/latest/com/google/i18n/phonenumbers/package-summary.html">com.googlecode.libphonenumbers</a>
 * 
 * @author M. Shields
 * @version 1.0
 */
public class Member implements Displayable, IDNumber {
	private Map<String, Object> details = new LinkedHashMap<>();

	/**
	 * <p>
	 * Constructs a Member with a default ID Number set to zero
	 * </p>
	 * 
	 * @param firstName
	 *            The member's first name
	 * @param lastName
	 *            The member's last name
	 * @param email
	 *            The member's email
	 * @param phoneNumber
	 *            The member's phone number
	 * @throws Exception
	 *             if any parameters are invalid, in accordance with the
	 *             Member's setter methods
	 */
	public Member(String firstName, String lastName, String email,
			String phoneNumber) throws Exception {
		setIDNumber(0);
		setFirstName(firstName);
		setLastName(lastName);
		setEmail(email);
		setPhoneNumber(phoneNumber);
	}

	/**
	 * <p>
	 * Constructs a Member and sets the ID number to the provided ID Number
	 * </p>
	 * 
	 * @param firstName
	 *            The member's first name
	 * @param lastName
	 *            The member's last name
	 * @param email
	 *            The member's email
	 * @param phoneNumber
	 *            The member's phone number
	 * @param idNumber
	 *            The member's idNumber
	 * @throws Exception
	 *             if any parameters are invalid, in accordance with the
	 *             Member's setter methods
	 */
	public Member(String firstName, String lastName, String email,
			String phoneNumber, int idNumber) throws Exception {
		setIDNumber(idNumber);
		setFirstName(firstName);
		setLastName(lastName);
		setEmail(email);
		setPhoneNumber(phoneNumber);
	}

	/**
	 * Sets the member's ID number<br>
	 * 
	 * @param idNumber
	 *            The member's ID number
	 * @throws IllegalArgumentException
	 *             if the parameter is a negative integer
	 */
	@Override
	public void setIDNumber(int idNumber) {
		if (idNumber < 0) {
			throw new IllegalArgumentException(
					"The member's ID number must be non-negative integer");
		}

		details.put("ID Number", idNumber);
	}

	/**
	 * Returns the member's ID number
	 * 
	 * @return The member's ID number
	 */
	@Override
	public int getIDNumber() {
		return (int) details.get("ID Number");
	}

	/**
	 * Sets the member's first name
	 * 
	 * @param firstName
	 *            The member's first name
	 * @throws IllegalArgumentException
	 *             if parameter is null, empty string or whitespace
	 */
	public void setFirstName(String firstName) {
		if (firstName == null || firstName.isBlank()) {
			throw new IllegalArgumentException(
					"The member's first name must not be blank");
		}
		details.put("First Name", firstName.trim());
	}

	/**
	 * Returns the member's first name
	 * 
	 * @return The member's first name
	 */
	public String getFirstName() {
		return (String) details.get("First Name");
	}

	/**
	 * Sets the member's last name
	 * 
	 * @param lastName
	 *            The member's last name
	 * @throws IllegalArgumentException
	 *             if the parameter is null, empty string or whitespace
	 */
	public void setLastName(String lastName) {
		if (lastName == null || lastName.isBlank()) {
			throw new IllegalArgumentException(
					"The member's last name must not be blank");
		}

		details.put("Last Name", lastName.trim());
	}

	/**
	 * Returns the member's last name
	 * 
	 * @return The member's last name
	 */
	public String getLastName() {
		return (String) details.get("Last Name");
	}

	/**
	 * Sets the member's email address
	 * 
	 * @param email
	 *            The member's email address
	 * @throws IllegalArgumentException
	 *             if the parameter is null, empty string, whitespace or fails
	 *             email validation
	 * 
	 * @see <a href=
	 *      "https://commons.apache.org/proper/commons-validator/apidocs/org/apache/commons/validator/routines/EmailValidator.html">org.apache.commons.validator.routines.emailvalidator</a>
	 */
	public void setEmail(String email) {
		if (email == null || email.isBlank()) {
			throw new IllegalArgumentException(
					"The member's email must not be blank");
		}

		EmailValidator validator = EmailValidator.getInstance();

		if (!validator.isValid(email)) {
			throw new IllegalArgumentException(
					"The provided email address is not valid");
		}

		details.put("Email", email.trim());
	}

	/**
	 * Returns the member's email address
	 * 
	 * @return The member's email address
	 */
	public String getEmail() {
		return (String) details.get("Email");
	}

	/**
	 * Sets the member's phone number (only Phone number of the GB region are
	 * accepted
	 * 
	 * @param phoneNumber
	 *            The member's phone number
	 * @throws IllegalArgumentException
	 *             if the parameter is null, empty string, whitespace or fails
	 *             phone number validation requirements
	 * @see <a href=
	 *      "https://javadoc.io/doc/com.googlecode.libphonenumber/libphonenumber/latest/com/google/i18n/phonenumbers/package-summary.html">com.googlecode.libphonenumbers</a>
	 */
	public void setPhoneNumber(String phoneNumber) {
		if (phoneNumber == null || phoneNumber.isBlank()) {
			throw new IllegalArgumentException(
					"The member's phone number must not be blank");
		}

		PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
		PhoneNumber parsedPhoneNumber;

		try {
			parsedPhoneNumber = phoneNumberUtil.parse(phoneNumber, "GB");
		} catch (Exception e) {
			throw new IllegalArgumentException(
					"Error parsing phone number: " + e.getMessage());
		}

		if (!phoneNumberUtil.isValidNumber(parsedPhoneNumber)) {
			throw new IllegalArgumentException("The provided phone number: "
					+ phoneNumber + " is not valid");
		}

		// Store the phone number as a string
		details.put("Phone Number", phoneNumber);
	}

	/**
	 * Returns the member's phone number
	 * 
	 * @return The member's phone number
	 */
	public String getPhoneNumber() {
		// Cast the value to String
		return (String) details.get("Phone Number");
	}

	/**
	 * Prints the member's details using the {@code PrintUtil}'s {@code printDetails} method
	 * @see <a href="PrintUtil.html">PrintUtil</a>
	 */
	@Override
	public void displayDetails() {
		PrintUtil.printDetails(details);
	}
}
