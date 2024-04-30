package uk.co.michaelshields.assignment_1;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.validator.routines.EmailValidator;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

/**
 * A class that models a member's details:
 * <ul>
 * <li>First name</li>
 * <li>Last name</li>
 * <li>Email address</li>
 * <li>Phone number</li>
 * <li>ID Number</li>
 * </ul>
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
	 * Constructor: 
	 * Instantiates a Member with a default ID Number set to zero
	 * 
	 * @param firstName - The member's first name (String)
	 * 
	 * @param lastName - The member's last name (String)
	 * 
	 * @param email - The member's email (String)
	 * 
	 * @param phoneNumber - The member's phone number (String)
	 * 
	 * @throws Exception - If any parameters are invalid, 
	 *                     in accordance with the
	 *                     Member's setter methods
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
	 * Constructor:
	 * Instantiates a Member with the ID number set to the provided ID Number
	 * 
	 * @param firstName - The member's first name (String)
	 * 
	 * @param lastName - The member's last name (String)
	 * 
	 * @param email - The member's email (String)
	 * 
	 * @param phoneNumber - The member's phone number (String)
	 * 
	 * @param idNumber - The member's idNumber (int)
	 * 
	 * @throws IllegalArgumentException - If any parameters are invalid, 
	 * 									  in accordance with the
	 *                                    Member's setter methods
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
	 * Mutator Method:
	 * Sets the member's ID number
	 * 
	 * @param idNumber - The member's ID number (int)
	 * 
	 * @throws IllegalArgumentException - If the parameter is a negative integer
	 */
	@Override
	public void setIDNumber(int idNumber) {
		// Guard clause preventing negative ID numbers from being assigned
		if (idNumber < 0) {
			throw new IllegalArgumentException(
					"The member's ID number must be non-negative integer");
		}
		
		// Add set the ID Number for the member
		details.put("ID Number", idNumber);
	}

	/**
	 * Accessor Method:
	 * Returns the member's ID number
	 * 
	 * @return idNumber - The member's ID number (int)
	 */
	@Override
	public int getIDNumber() {
		return (int) details.get("ID Number");
	}

	/**
	 * Mutator Method:
	 * Sets the member's first name
	 * 
	 * @param firstName - The member's first name (String)
	 * 
	 * @throws IllegalArgumentException - If parameter is null, 
	 *                                    empty string or whitespace
	 */
	public void setFirstName(String firstName) {
		
		// Guard clause preventing null, empty string or whitespace values
		if (firstName == null || firstName.isBlank()) {
			throw new IllegalArgumentException(
					"The member's first name must not be blank");
		}
		// Set the member's first name
		// trim any surplus whitespace
		details.put("First Name", firstName.trim());
	}

	/**
	 * Accessor Method:
	 * Returns the member's first name
	 * 
	 * @return firstName - The member's first name (String)
	 */
	public String getFirstName() {
		return (String) details.get("First Name");
	}

	/**
	 * Mutator Method:
	 * Sets the member's last name
	 * 
	 * @param lastName - The member's last name (String)
	 * 
	 * @throws IllegalArgumentException - If the parameter is null, empty string or whitespace
	 */
	public void setLastName(String lastName) {
		// Guard clause preventing null, empty string or whitespace values
		if (lastName == null || lastName.isBlank()) {
			throw new IllegalArgumentException(
					"The member's last name must not be blank");
		}

		// Set the member's last name
		// trim any surplus whitespace
		details.put("Last Name", lastName.trim());
	}

	/**
	 * Accessor Method:
	 * Returns the member's last name
	 * 
	 * @return lastName - The member's last name (String)
	 */
	public String getLastName() {
		return (String) details.get("Last Name");
	}

	/**
	 * Mutator Method:
	 * Sets the member's email address
	 * 
	 * @param email - The member's email address (String)
	 * 
	 * @throws IllegalArgumentException - If the parameter is null, 
	 * 									  empty string, whitespace or fails
	 *             					      email validation
	 * 
	 * @see <a href=
	 *      "https://commons.apache.org/proper/commons-validator/apidocs/org/apache/commons/validator/routines/EmailValidator.html">org.apache.commons.validator.routines.emailvalidator</a>
	 */
	public void setEmail(String email) {
		// Guard clause preventing null, empty string or whitespace values
		if (email == null || email.isBlank()) {
			throw new IllegalArgumentException(
					"The member's email must not be blank");
		}

		// Create an EmailValidator instance
		EmailValidator validator = EmailValidator.getInstance();

		// Guard clause that ensures the email address syntax
		// is valid
		if (!validator.isValid(email)) {
			throw new IllegalArgumentException(
					"The provided email address is not valid");
		}

		// Set the member's email
		// trim any surplus whitespace
		details.put("Email", email.trim());
	}

	/**
	 * Accessor Method:
	 * Returns the member's email address
	 * 
	 * @return emailAddress - The member's email address (String)
	 */
	public String getEmail() {
		return (String) details.get("Email");
	}

	/**
	 * Mutator Method:
	 * Sets the member's phone number (only Phone number of the GB region are
	 * accepted
	 * 
	 * @param phoneNumber - The member's phone number (String)
	 * 
	 * @throws IllegalArgumentException - If the parameter is null, 
	 *                                    empty string, whitespace or fails
	 *                                    phone number validation requirements
	 * @see <a href=
	 *      "https://javadoc.io/doc/com.googlecode.libphonenumber/libphonenumber/latest/com/google/i18n/phonenumbers/package-summary.html">com.googlecode.libphonenumbers</a>
	 */
	public void setPhoneNumber(String phoneNumber) throws IllegalArgumentException {
		if (phoneNumber == null || phoneNumber.isBlank()) {
			throw new IllegalArgumentException(
					"The member's phone number must not be blank");
		}

		// Create a PhoneNumberUtil instance
		PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
		// Create a variable to store the parsed phone number
		PhoneNumber parsedPhoneNumber;

		try {
			// Attempt to parse the phone number
			parsedPhoneNumber = phoneNumberUtil.parse(phoneNumber, "GB");
		} catch (Exception e) {
			// If the parsing fails throw an error
			throw new IllegalArgumentException(
					"Error parsing phone number: " + e.getMessage());
		}

		// Check that the parsed phone number is valid
		if (!phoneNumberUtil.isValidNumber(parsedPhoneNumber)) {
			throw new IllegalArgumentException("The provided phone number: "
					+ phoneNumber + " is not valid");
		}

		// Set the member's phone number 
		details.put("Phone Number", phoneNumber);
	}

	/**
	 * Accessor Method:
	 * Returns the member's phone number
	 * 
	 * @return phoneNumber - The member's phone number (String)
	 */
	public String getPhoneNumber() {
		// Cast the phoneNumber from the PhoneNumber type to a String
		return (String) details.get("Phone Number");
	}

	/**
	 * Prints the member's details using the PrintUtil,s printDetails method
	 * @see <a href="PrintUtil.html">PrintUtil</a>
	 */
	@Override
	public void displayDetails() {
		PrintUtil.printDetails(details);
	}
}
