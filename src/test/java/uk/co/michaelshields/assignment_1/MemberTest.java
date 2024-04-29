package uk.co.michaelshields.assignment_1;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * A suite of JUnit tests for the Member class.
 * 
 * @see <a target="_blank" href="https://junit.org/junit5/">JUnit5</a>
 * @see <a target="_blank" href="Member.html">Member</a>
 * 
 * @author M. Shields
 * @version 25.02.2024
 */
public class MemberTest {
	/**
	 * Verifies that the Member constructor throws if firstName is null
	 */
	@Test
	@DisplayName("Member constructor throws if firstName is null")
	public void testConstructorFirstNameNull() {
		assertThrows(IllegalArgumentException.class,
				() -> new Member(null, "Jones", "abc@xyz.com", "O7777777777"),
				"an error should have been thrown if firstName is null");
	}

	/**
	 * Verifies that the Member constructor throws if firstName is empty string
	 */
	@Test
	@DisplayName("Member constructor throws if firstName is empty string")
	public void testConstructorFirstNameEmptyString() {
		assertThrows(IllegalArgumentException.class,
				() -> new Member("", "Jones", "abc@xyz.com", "O7777777777"),
				"an error should have been thrown if firstName is empty string");
	}

	/**
	 * Verifies that the Member constructor throws if firstName is whitespace
	 */
	@Test
	@DisplayName("Member constructor throws if firstName is whitespace")
	public void testConstructorFirstNameWhiteSpace() {
		assertThrows(IllegalArgumentException.class,
				() -> new Member(" ", "Jones", "abc@xyz.com", "O7777777777"),
				"an error should have been thrown if firstName is whitespace");
	}

	/**
	 * Verifies that the Member constructor throws if lastName is null
	 */
	@Test
	@DisplayName("Member constructor throws if lastName is null")
	public void testConstructorLastNameNull() {
		assertThrows(IllegalArgumentException.class,
				() -> new Member("Jim", null, "abc@xyz.com", "O7777777777"),
				"an error should have been thrown if lastName is null");
	}

	/**
	 * Verifies that the Member constructor throws if lastName is empty string
	 */
	@Test
	@DisplayName("Member constructor throws if lastName is empty string")
	public void testConstructorLastNameEmptyString() {
		assertThrows(IllegalArgumentException.class,
				() -> new Member("Jim", "", "abc@xyz.com", "O7777777777"),
				"an error should have been thrown if lastName is empty string");
	}

	/**
	 * Verifies that the Member constructor throws if lastName is whitespace
	 */
	@Test
	@DisplayName("Member constructor throws if lastName is whitespace")
	public void testConstructorLastNameWhitespace() {
		assertThrows(IllegalArgumentException.class,
				() -> new Member("Jim", " ", "abc@xyz.com", "O7777777777"),
				"an error should have been thrown if lastName is whitespace");
	}

	/**
	 * Verifies that the Member constructor throws if email is null
	 */
	@Test
	@DisplayName("Member constructor throws if email is null")
	public void testConstructorEmailNull() {
		assertThrows(IllegalArgumentException.class,
				() -> new Member("Jim", "Jones", null, "O7777777777"),
				"an error should have been thrown if email is null");
	}

	/**
	 * Verifies that the Member constructor throws if email is empty string
	 */
	@Test
	@DisplayName("Member constructor throws if email is empty string")
	public void testConstructorEmailEmptyString() {
		assertThrows(IllegalArgumentException.class,
				() -> new Member("Jim", "Jones", "", "O7777777777"),
				"an error should have been thrown if email is empty string");
	}

	/**
	 * Verifies that the Member constructor throws if email is whitespace
	 */
	@Test
	@DisplayName("Member constructor throws if email is whitespace")
	public void testConstructorEmailWhitespace() {
		assertThrows(IllegalArgumentException.class,
				() -> new Member("Jim", "Jones", " ", "O7777777777"),
				"an error should have been thrown if email is whitespace");
	}

	/**
	 * Verifies that the Member constructor throws if email is invalid
	 */
	@Test
	@DisplayName("Member constructor throws if email is invalid")
	public void testConstructorEmailInvalid() {
		assertThrows(IllegalArgumentException.class,
				() -> new Member("Jim", "Jones", "abc.xyz@com", "O7777777777"),
				"an error should have been thrown if email is invalid");
	}

	/**
	 * Verifies that the Member constructor throws if phoneNumber is null
	 */
	@Test
	@DisplayName("Member constructor throws if phoneNumber is null")
	public void testConstructorPhoneNumberNull() {
		assertThrows(IllegalArgumentException.class,
				() -> new Member("Jim", "Jones", "abc@xyz.com", null),
				"an error should have been thrown if phoneNumber is null");
	}

	/**
	 * Verifies that the Member constructor throws if phoneNumber is empty
	 * string
	 */
	@Test
	@DisplayName("Member constructor throws if phoneNumber is empty string")
	public void testConstructorPhoneNumberEmptyString() {
		assertThrows(IllegalArgumentException.class,
				() -> new Member("Jim", "Jones", "abc@xyz.com", ""),
				"an error should have been thrown if phoneNumber is empty string");
	}

	/**
	 * Verifies that the Member constructor throws if phoneNumber is whitespace
	 */
	@Test
	@DisplayName("Member constructor throws if phoneNumber is whitespace")
	public void testConstructorPhoneNumberWhitespace() {
		assertThrows(IllegalArgumentException.class,
				() -> new Member("Jim", "Jones", "abc@xyz.com", " "),
				"an error should have been thrown if phoneNumber is whitespace");
	}

	/**
	 * Verifies that the Member constructor throws if phoneNumber is invalid
	 */
	@Test
	@DisplayName("Member constructor throws if phoneNumber is invalid")
	public void testConstructorPhoneNumberInvalid() {
		assertThrows(IllegalArgumentException.class,
				() -> new Member("Jim", "Jones", "abc@xyz.com", "99999999999"),
				"an error should have been thrown if phoneNumber is invalid");
	}

	/**
	 * Verifies that the Member constructor throws if idNumber is negative
	 */
	@Test
	@DisplayName("Member constructor throws if idNumber is negative")
	public void testConstructorIDNumberNonPositiveInt() {
		assertThrows(IllegalArgumentException.class,
				() -> new Member("Jim", "Jones", "abc@xyz.com", "O7777777777",
						-1),
				"an error should have been thrown if idNumber is negative");
	}

	/**
	 * Verifies that the Member constructor creates a member with a default
	 * idNumber of zero.
	 */
	@Test
	@DisplayName("Member constructor creates a member with a idNumber of zero")
	public void testConstructorDefaultID() {
		try {
			Member member = new Member("Jim", "Jones", "abc@xyz.com",
					"O7777777777");

			assertEquals(0, member.getIDNumber(),
					"the member's idNumber should be the default of zero");

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	/**
	 * Verifies that the Member constructor creates a member with the idNumber
	 * set.
	 */
	@Test
	@DisplayName("Member constructor creates a member with ID number set")
	public void testConstructorSetIDNumber() {
		try {
			Member member = new Member("Jim", "Jones", "abc@xyz.com",
					"O7777777777", 10);

			assertEquals(10, member.getIDNumber(),
					"the member's idNumber should have been set");

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	/**
	 * Verifies that getIDNumber returns the member's idNumber
	 */
	@Test
	@DisplayName("getIDNumber returns the member's idNumber")
	public void testGetIDNumber() {
		try {
			int idNumber = 1;
			Member member = new Member("Jim", "Jones", "abc@xyz.com",
					"O7777777777", idNumber);

			assertEquals(member.getIDNumber(), idNumber,
					"the member's idNumber should have been returned");

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	/**
	 * Verifies that setIDNumber throws if the idNumber is negative integer
	 */
	@Test
	@DisplayName("setIDNumber throws if idNumber is negative")
	public void testSetIDNumberNonPositive() {
		try {
			Member member = new Member("Jim", "Jones", "abc@xyz.com",
					"O7777777777");

			assertThrows(IllegalArgumentException.class,
					() -> member.setIDNumber(-1),
					"an error should have been thrown if the idNumber is negative integer");

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	/**
	 * Verifies that setIDNumber does not update member's idNumber if provided
	 * idNumber is negative
	 */
	@Test
	@DisplayName("setIDNumber does not update member's idNumber if idNumber is negative")
	public void testSetIDNumber() {
		try {
			Member member = new Member("Jim", "Jones", "abc@xyz.com",
					"O7777777777", 10);

			member.setIDNumber(-1);

			assertEquals(10, member.getIDNumber(),
					"The member's idNumber should be unchanged if idNumber is negative");
		} catch (Exception e) {

		}
	}

	/**
	 * Verifies that setIDNumber updates the member's idNumber if provided
	 * idNumber is positive
	 */
	@Test
	@DisplayName("setIDNumber updates member's idNumber")
	public void testSetIDNumberPositiveInt() {
		try {
			Member member = new Member("Jim", "Jones", "abc@xyz.com",
					"O7777777777", 10);

			member.setIDNumber(11);

			assertEquals(11, member.getIDNumber(),
					"The member's idNumber should have been changed if idNumber is positive");
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	/**
	 * Verifies that getFirstName returns the member's firstName
	 */
	@Test
	@DisplayName("getFirstName returns the member's firstName")
	public void testGetFirstName() {
		try {
			Member member = new Member("Jim", "Jones", "abc@xyz.com",
					"O7777777777");

			assertEquals("Jim", member.getFirstName(),
					"the member's first name should have been returned");

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	/**
	 * Verifies that setFirstName throws if firstName is null
	 */
	@Test
	@DisplayName("setFirstName throws if firstName is null")
	public void testSetFirstNameNull() {
		try {
			Member member = new Member("Jim", "Jones", "abc@xyz.com",
					"O7777777777");

			assertThrows(IllegalArgumentException.class,
					() -> member.setFirstName(null),
					"an error should have been thrown if firstName is null");

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	/**
	 * Verifies that setFirstName does not update member's firstName if
	 * firstName is null
	 */
	@Test
	@DisplayName("setFirstName does not update firstName if firstName is null")
	public void testSetFirstNameNullNoUpdate() {
		try {
			Member member = new Member("Jim", "Jones", "abc@xyz.com",
					"O7777777777");

			member.setFirstName(null);

			assertEquals("Jim", member.getFirstName(),
					"The member's firstName should be unchanged if idNumber is null");
		} catch (Exception e) {

		}
	}

	/**
	 * Verifies that setFirstName throws if firstName is empty string
	 */
	@Test
	@DisplayName("setFirstName throws if firstName is empty string")
	public void testSetFirstNameEmptyString() {
		try {
			Member member = new Member("Jim", "Jones", "abc@xyz.com",
					"O7777777777");

			assertThrows(IllegalArgumentException.class,
					() -> member.setFirstName(""),
					"an error should have been thrown if firstName is empty string");

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	/**
	 * Verifies that setFirstName does not update member's firstName if provided
	 * firstName is empty string
	 */
	@Test
	@DisplayName("setFirstName does not update firstName if firstName is empty string")
	public void testSetFirstNameEmptyStringNoUpdate() {
		try {
			Member member = new Member("Jim", "Jones", "abc@xyz.com",
					"O7777777777");

			member.setFirstName("");

			assertEquals("Jim", member.getFirstName(),
					"The member's firstName should be unchanged if firstName is empty string");
		} catch (Exception e) {

		}

	}

	/**
	 * Verifies that setFirstName throws if firstName is whitespace
	 */
	@Test
	@DisplayName("setFirstName throws if firstName is whitespace")
	public void testSetFirstNameWhitespace() {
		try {
			Member member = new Member("Jim", "Jones", "abc@xyz.com",
					"O7777777777");

			assertThrows(IllegalArgumentException.class,
					() -> member.setFirstName(" "),
					"an error should have been thrown if firstName is whitespace");

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	/**
	 * Verifies that setFirstName does not update member's firstName if provided
	 * firstName is whitespace
	 */
	@Test
	@DisplayName("setFirstName does not update firstName if firstName is whitespace")
	public void testSetFirstNameWhitespaceNoUpdate() {
		try {
			Member member = new Member("Jim", "Jones", "abc@xyz.com",
					"O7777777777");

			member.setFirstName(" ");

			assertEquals("Jim", member.getFirstName(),
					"The member's firstName should be unchanged if firstName is whitespace");
		} catch (Exception e) {

		}
	}

	/**
	 * Verifies that setFirstName updates the member's firstName
	 */
	@Test
	@DisplayName("setFirstName updates the member's firstName")
	public void testSetFirstName() {
		try {
			Member member = new Member("Jim", "Jones", "abc@xyz.com",
					"O7777777777");

			member.setFirstName("James");

			assertEquals("James", member.getFirstName(),
					"The member's firstName should have been changed");
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	/**
	 * Verifies that getLastName returns the member's lastName
	 */
	@Test
	@DisplayName("getLastName returns the member's firstName")
	public void testGetLastName() {
		try {
			Member member = new Member("Jim", "Jones", "abc@xyz.com",
					"O7777777777");

			assertEquals("Jones", member.getLastName(),
					"the member's last name should be returned");

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	/**
	 * Verifies that setLastName throws if lastName is null
	 */
	@Test
	@DisplayName("setLastName throws if lastName is null")
	public void testSetLastNameNull() {
		try {
			Member member = new Member("Jim", "Jones", "abc@xyz.com",
					"O7777777777");

			assertThrows(IllegalArgumentException.class,
					() -> member.setLastName(null),
					"an error should have been thrown if the lastName is null");

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	/**
	 * Verifies that setLastName does not update member's lastName if provided
	 * lastName is null
	 */
	@Test
	@DisplayName("setLastName should not update lastName if lastName is null")
	public void testSetLastNameNullNoUpdate() {
		try {
			Member member = new Member("Jim", "Jones", "abc@xyz.com",
					"O7777777777");

			member.setLastName(null);

			assertEquals("Jones", member.getLastName(),
					"The member's lastName should be unchanged if lastName is null");
		} catch (Exception e) {

		}
	}

	/**
	 * Verifies that setLastName throws if lastName is empty string
	 */
	@Test
	@DisplayName("setLastName throws if lastName is empty string")
	public void testSetLastNameEmptyString() {
		try {
			Member member = new Member("Jim", "Jones", "abc@xyz.com",
					"O7777777777");

			assertThrows(IllegalArgumentException.class,
					() -> member.setLastName(""),
					"an error should have been thrown if lastName is empty string");

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	/**
	 * Verifies that setLastName() does not update member's lastName if provided
	 * lastName is empty string
	 */
	@Test
	@DisplayName("setLastName does not update lastName if lastName is empty string")
	public void testSetLastNameEmptyStringNoUpdate() {
		try {
			Member member = new Member("Jim", "Jones", "abc@xyz.com",
					"O7777777777");

			member.setLastName("");

			assertEquals("Jones", member.getLastName(),
					"The member's lastName should be unchanged if lastName is empty string");
		} catch (Exception e) {

		}
	}

	/**
	 * Verifies that setLastName throws if lastName is whitespace
	 */
	@Test
	@DisplayName("setLastName(String lastName) throws if lastName is whitespace")
	public void testSetLastNameWhitespace() {
		try {
			Member member = new Member("Jim", "Jones", "abc@xyz.com",
					"O7777777777");

			assertThrows(IllegalArgumentException.class,
					() -> member.setLastName(" "),
					"an error should have been thrown if lastName is whitespace");

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	/**
	 * Verifies that setLastName does not update member's lastName if provided
	 * lastName is whitespace
	 */
	@Test
	@DisplayName("setLastName(String lastName) throws if lastName is whitespace")
	public void testSetLastNameWhitespaceNoUpdate() {
		try {
			Member member = new Member("Jim", "Jones", "abc@xyz.com",
					"O7777777777");

			member.setLastName(" ");

			assertEquals("Jones", member.getLastName(),
					"The member's lastName should be unchanged if lastName is whitespace");
		} catch (Exception e) {

		}
	}

	/**
	 * Verifies that setLastName updates the member's lastName
	 */
	@Test
	@DisplayName("setLastName throws if lastName is empty string")
	public void testSetLastName() {
		try {
			Member member = new Member("Jim", "Jones", "abc@xyz.com",
					"O7777777777");

			member.setLastName("Jackson");

			assertEquals("Jackson", member.getLastName(),
					"The member's lastName should have been changed");
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	/**
	 * Verifies that setEmail throws if email is null
	 */
	@Test
	@DisplayName("setEmail throws if email is null")
	public void testSetEmailNull() {
		try {
			Member member = new Member("Jim", "Jones", "abc@xyz.com",
					"O7777777777");

			assertThrows(IllegalArgumentException.class,
					() -> member.setEmail(null),
					"an error should have been thrown if email is null");

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	/**
	 * Verifies that setEmail does not update member's email if provided email
	 * is null
	 */
	@Test
	@DisplayName("setEmail does not update member's email if email is null")
	public void testSetEmailNullNoUpdate() {
		try {
			Member member = new Member("Jim", "Jones", "abc@xyz.com",
					"O7777777777");

			member.setEmail(null);

			assertEquals("abc@xyz.com", member.getEmail(),
					"The member's email should be unchanged if email is null");
		} catch (Exception e) {

		}
	}

	/**
	 * Verifies that setEmail throws if email is empty string
	 */
	@Test
	@DisplayName("setEmail throws if email is empty string")
	public void testSetEmailEmptyString() {
		try {
			Member member = new Member("Jim", "Jones", "abc@xyz.com",
					"O7777777777");

			assertThrows(IllegalArgumentException.class,
					() -> member.setEmail(""),
					"an error should have been thrown if email is empty string");

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	/**
	 * Verifies that setEmail does not update member's email if provided email
	 * is empty string
	 */
	@Test
	@DisplayName("setEmail does not update member's email if email is empty string")
	public void testSetEmailEmptyStringNoUpdate() {
		try {
			Member member = new Member("Jim", "Jones", "abc@xyz.com",
					"O7777777777");

			member.setEmail("");

			assertEquals("abc@xyz.com", member.getEmail(),
					"The member's email should be unchanged if email is empty string");
		} catch (Exception e) {

		}
	}

	/**
	 * Verifies that setEmail throws if email is whitespace
	 */
	@Test
	@DisplayName("setEmail(String email) throws if email is whitespace")
	public void testSetEmailWhitespace() {
		try {
			Member member = new Member("Jim", "Jones", "abc@xyz.com",
					"O7777777777");

			assertThrows(IllegalArgumentException.class,
					() -> member.setEmail(" "),
					"an error should have been thrown if email is whitespace");

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	/**
	 * Verifies that setEmail does not update member's email if provided email
	 * is whitespace
	 */
	@Test
	@DisplayName("setEmail should not update member's email if email is whitespace")
	public void testSetEmailWhitespaceNoUpdate() {
		try {
			Member member = new Member("Jim", "Jones", "abc@xyz.com",
					"O7777777777");

			member.setEmail(" ");

			assertEquals("abc@xyz.com", member.getEmail(),
					"The member's email should be unchanged if email is whitespace");
		} catch (Exception e) {

		}
	}

	/**
	 * Verifies that setEmail throws if email is invalid
	 */
	@Test
	@DisplayName("setEmail throws if email is invalid")
	public void testSetEmailInvalid() {
		try {
			Member member = new Member("Jim", "Jones", "abc@xyz.com",
					"O7777777777");

			assertThrows(IllegalArgumentException.class,
					() -> member.setEmail("abc.xyz.com"),
					"an error should have been thrown if email is invalid");

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	/**
	 * Verifies that setEmail does not update member's email if provided email
	 * is invalid
	 */
	@Test
	@DisplayName("setEmail does not update email if email is invalid")
	public void testSetEmailInvalidUpdate() {
		try {
			Member member = new Member("Jim", "Jones", "abc@xyz.com",
					"O7777777777");

			member.setEmail("abc.xyz@com");

			assertEquals("abc@xyz.com", member.getEmail(),
					"The member's email should be unchanged if email is invalid");
		} catch (Exception e) {

		}
	}

	/**
	 * Verifies that setEmail updates the member's email
	 */
	@Test
	@DisplayName("setEmail updates the member's email")
	public void testSetEmail() {
		try {
			Member member = new Member("Jim", "Jones", "abc@xyz.com",
					"O7777777777");

			member.setEmail("new@email.com");

			assertEquals("new@email.com", member.getEmail(),
					"The member's email should have changed");
		} catch (Exception e) {

		}
	}

	/**
	 * Verifies that getPhoneNumber returns the member's phone number
	 */
	@Test
	@DisplayName("getPhoneNumber returns the member's phone number")
	public void testGetPhoneNumber() {
		try {
			Member member = new Member("Jim", "Jones", "abc@xyz.com",
					"O7777777777");

			assertEquals("O7777777777", member.getPhoneNumber(),
					"the member's phone number should have been returned");

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	/**
	 * Verifies that setPhoneNumber throws if phoneNumber is null
	 */
	@Test
	@DisplayName("setPhoneNumber throws if phoneNumber is null")
	public void testSetPhoneNumberNull() {
		try {
			Member member = new Member("Jim", "Jones", "abc@xyz.com",
					"O7777777777");

			assertThrows(IllegalArgumentException.class,
					() -> member.setPhoneNumber(null),
					"an error should have been thrown if phoneNumber is null");

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	/**
	 * Verifies that setPhoneNumber does not update member's phoneNumber if
	 * provided phoneNumber is null
	 */
	@Test
	@DisplayName("setPhoneNumber does not update member's phoneNumber if phoneNumber is null")
	public void testSetPhoneNumberNullNoUpdate() {
		try {
			Member member = new Member("Jim", "Jones", "abc@xyz.com",
					"O7777777777");

			member.setPhoneNumber(null);

			assertEquals("Jones", member.getPhoneNumber(),
					"The member's phone number should be unchanged if phoneNumber is null");
		} catch (Exception e) {

		}
	}

	/**
	 * Verifies that setPhoneNumber throws if phoneNumber is empty string
	 */
	@Test
	@DisplayName("setPhoneNubmer throws if phoneNumber is empty string")
	public void testSetPhoneNumberEmptyString() {
		try {
			Member member = new Member("Jim", "Jones", "abc@xyz.com",
					"O7777777777");

			assertThrows(IllegalArgumentException.class,
					() -> member.setPhoneNumber(""),
					"an error should have been thrown if phoneNumber is empty string");

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	/**
	 * Verifies that setPhoneNumber does not update member's phone number if
	 * provided phoneNumber is empty string
	 */
	@Test
	@DisplayName("setPhoneNumber does not update member's phoneNumber if phoneNumber is empty string")
	public void testSetPhoneNumberEmptyStringNoUpdate() {
		try {
			Member member = new Member("Jim", "Jones", "abc@xyz.com",
					"O7777777777");

			member.setPhoneNumber("");

			assertEquals("Jones", member.getPhoneNumber(),
					"The member's phone number should be unchanged if phoneNumber is empty string");
		} catch (Exception e) {

		}
	}

	/**
	 * Verifies that setPhoneNumber throws if phoneNumber is whitespace
	 */
	@Test
	@DisplayName("setPhoneNumber() throws if phoneNumber is whitespace")
	public void testSetPhoneNumberWhitespace() {
		try {
			Member member = new Member("Jim", "Jones", "abc@xyz.com",
					"O7777777777");

			assertThrows(IllegalArgumentException.class,
					() -> member.setPhoneNumber(" "),
					"an error should have been thrown if phoneNumber is whitespace");

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	/**
	 * Verifies that setPhoneNumber does not update member's phone number if
	 * provided phoneNumber is invalid
	 */
	@Test
	@DisplayName("setPhoneNumber does not update member's phoneNumber if phoneNumber is invalid")
	public void testSetPhoneNumberWhitespaceNoUpdate() {
		try {
			Member member = new Member("Jim", "Jones", "abc@xyz.com",
					"O7777777777");

			member.setPhoneNumber(" ");

			assertEquals("Jones", member.getPhoneNumber(),
					"The member's phoneNumber should be unchanged if phoneNumber is invalid");
		} catch (Exception e) {

		}
	}

	/**
	 * Verifies that displayDetails prints the member's details in the correct
	 * format.
	 */
	@Test
	@DisplayName("displayDetails prints the member's details correctly")
	public void testDisplayDetails() {
		try {
			// Create a test stream to capture print stream
			ByteArrayOutputStream testStream = new ByteArrayOutputStream();

			// Create a new print stream set System.out to output to it
			// Then set the output of the print stream to the test stream
			System.setOut(new PrintStream(testStream));

			String firstName = "Jim";
			String lastName = "Jones";
			String email = "abc@xyz.com";
			String phoneNumber = "07777777777";
			int validIdNumber = 1;

			Member member = new Member(firstName, lastName, email, phoneNumber,
					validIdNumber);

			String expectedOutput = "ID Number:      1\r\n"
					+ "First Name:     Jim\r\n" + "Last Name:      Jones\r\n"
					+ "Email:          abc@xyz.com\r\n"
					+ "Phone Number:   07777777777";

			testStream.reset();

			member.displayDetails();

			assertEquals(expectedOutput, testStream.toString().trim(),
					"The member's details should have been printed as expected");

			// Reinstate the default System.out stream
			System.setOut(System.out);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
}
