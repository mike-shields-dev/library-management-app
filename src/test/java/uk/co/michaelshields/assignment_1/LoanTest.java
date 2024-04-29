package uk.co.michaelshields.assignment_1;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A suite of JUnit tests for the Loan class
 * 
 * @see <a target="_blank" href= "https://junit.org/junit5/">JUnit5</a>
 * @see <a target="_blank" href="Loan.html">Loan</a>
 * 
 * @author M. Shields
 * @version 1.0
 */
@DisplayName("Loan Tests")
public class LoanTest {
	/**
	 * Verifies that the Loan constructor throws if the member is null
	 */
	@Test
	@DisplayName("Loan constructor throws if member is null")
	void testConstructorNullMember() {
		Book book = new Book("C. Negus", "Linux Bible", "Non Fiction",
				"9781119578888", 1);
		LocalDate issueDate = LocalDate.now();
		LocalDate expiryDate = LocalDate.now().plusDays(21);

		assertThrows(IllegalArgumentException.class,
				() -> new Loan(null, book, issueDate, expiryDate),
				"constructor should throw if member is null");
	}

	/**
	 * Verifies that the Loan constructor throws if the book is null
	 */
	@Test
	@DisplayName("Loan constructor throws if book is null")
	void testConstructorNullBook() {
		try {
			Member member = new Member("Michael", "Shields", "abc@xyz.com",
					"07777777777", 1);
			int loanDurationInDays = 21;
			LocalDate issueDate = LocalDate.now();
			LocalDate expiryDate = LocalDate.now().plusDays(loanDurationInDays);

			assertThrows(IllegalArgumentException.class,
					() -> new Loan(member, null, issueDate, expiryDate),
					"constructor should throw if book is null");

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	/**
	 * Verifies that the Loan constructor throws if the issueDate is null
	 */
	@Test
	@DisplayName("Loan constructor throws if member is null")
	void testConstructorNullIssueDate() {
		try {
			Member member = new Member("Michael", "Shields", "abc@xyz.com",
					"07777777777", 1);
			Book book = new Book("C. Negus", "Linux Bible", "Non Fiction",
					"9781119578888", 1);
			LocalDate expiryDate = LocalDate.now();

			assertThrows(IllegalArgumentException.class,
					() -> new Loan(member, book, null, expiryDate),
					"constructor should throw if issueDate is null");

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	/**
	 * Verifies that the Loan constructor throws if the expiryDate is null
	 */
	@Test
	@DisplayName("Loan constructor throws if expiry date is null")
	void testConstructorNullExpiryDate() {
		try {
			Member member = new Member("Michael", "Shields", "abc@xyz.com",
					"07777777777", 1);
			Book book = new Book("C. Negus", "Linux Bible", "Non Fiction",
					"9781119578888", 1);
			LocalDate issueDate = LocalDate.now();

			assertThrows(IllegalArgumentException.class,
					() -> new Loan(member, book, issueDate, null),
					"constructor should throw if expiryDate is null");

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	/**
	 * Verifies that the Loan constructor throws if the expiryDate is not after
	 * the issueDate
	 */
	@Test
	@DisplayName("Loan constructor throws if expiryDate is not after issueDate")
	void testConstructorExpiryDateNotAfterIssue() {
		try {
			Member member = new Member("Michael", "Shields", "abc@xyz.com",
					"07777777777", 1);
			Book book = new Book("C. Negus", "Linux Bible", "Non Fiction",
					"9781119578888", 1);
			LocalDate issueDate = LocalDate.now();
			LocalDate expiryDate = LocalDate.now();

			assertThrows(IllegalArgumentException.class,
					() -> new Loan(member, book, issueDate, expiryDate),
					"constructor should throw if expiryDate is not after issueDate");

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	/**
	 * Verifies that the Loan constructor throws if member's ID number has not
	 * been set (having a default value of zero)
	 */
	@Test
	@DisplayName("Loan constructor throws if member's ID number has not been set")
	void testConstructorMemberNoID() {
		try {
			Member member = new Member("Michael", "Shields", "abc@xyz.com",
					"07777777777");
			Book book = new Book("C. Negus", "Linux Bible", "Non Fiction",
					"9781119578888", 1);
			LocalDate issueDate = LocalDate.now();
			LocalDate expiryDate = LocalDate.now();

			assertThrows(IllegalArgumentException.class,
					() -> new Loan(member, book, issueDate, expiryDate),
					"constructor should throw if member's ID number has not been set");

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	/**
	 * Verifies that the Loan constructor throws if book's ID number has not
	 * been set (having a default value of zero)
	 */
	@Test
	@DisplayName("Loan constructor throws if book's ID number has not been set")
	void testConstructorBookNoID() {
		try {
			Member member = new Member("Michael", "Shields", "abc@xyz.com",
					"07777777777", 1);
			Book book = new Book("C. Negus", "Linux Bible", "Non Fiction",
					"9781119578888");
			LocalDate issueDate = LocalDate.now();
			LocalDate expiryDate = LocalDate.now();

			assertThrows(IllegalArgumentException.class,
					() -> new Loan(member, book, issueDate, expiryDate),
					"constructor should throw if book's ID number has not been set");

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	/**
	 * Verifies that the Loan constructor instantiates a loan
	 */
	@Test
	@DisplayName("Loan constructor throws if expiryDate is not after issueDate")
	void testConstructor() {
		try {
			Member member = new Member("Michael", "Shields", "abc@xyz.com",
					"07777777777", 1);
			Book book = new Book("C. Negus", "Linux Bible", "Non Fiction",
					"9781119578888", 1);
			int loanDurationInDays = 21;
			LocalDate issueDate = LocalDate.now();
			LocalDate expiryDate = LocalDate.now().plusDays(loanDurationInDays);

			assertTrue(
					new Loan(member, book, issueDate,
							expiryDate) instanceof Loan,
					"constructor should instantiate a loan");

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	/**
	 * Verifies that getMember returns the member associated with the loan
	 */
	@Test
	@DisplayName("getMember returns the member associated with the loan")
	void getMember() {
		try {
			Member member = new Member("Michael", "Shields", "abc@xyz.com",
					"07777777777", 1);
			Book book = new Book("C. Negus", "Linux Bible", "Non Fiction",
					"9781119578888", 1);
			int loanDurationInDays = 21;
			LocalDate issueDate = LocalDate.now();
			LocalDate expiryDate = LocalDate.now().plusDays(loanDurationInDays);
			Loan loan = new Loan(member, book, issueDate, expiryDate);

			assertEquals(loan.getMember(), member,
					"the member associated with the loan should have been returned");
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	/**
	 * Verifies that getBook returns the book associated with the loan
	 */
	@Test
	@DisplayName("getBook returns the book associated with the loan")
	void getBook() {
		try {
			Member member = new Member("Michael", "Shields", "abc@xyz.com",
					"07777777777", 1);
			Book book = new Book("C. Negus", "Linux Bible", "Non Fiction",
					"9781119578888", 1);
			int loanDurationInDays = 21;
			LocalDate issueDate = LocalDate.now();
			LocalDate expiryDate = LocalDate.now().plusDays(loanDurationInDays);
			Loan loan = new Loan(member, book, issueDate, expiryDate);

			assertEquals(loan.getBook(), book,
					"the book associated with the loan should have been returned");
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

	}

	/**
	 * Verifies that the displayDetails prints the loans details
	 */
	@Test
	@DisplayName("displayDetails prints the loan's details")
	void displayDetails() {
		try {
			Member member = new Member("Michael", "Shields", "abc@xyz.com",
					"07777777777", 1);
			Book book = new Book("C. Negus", "Linux Bible", "Non Fiction",
					"9781119578888", 1);
			int loanDurationInDays = 21;
			LocalDate issueDate = LocalDate.now();
			LocalDate expiryDate = LocalDate.now().plusDays(loanDurationInDays);
			Loan loan = new Loan(member, book, issueDate, expiryDate);
			DateTimeFormatter formatter = DateTimeFormatter
					.ofPattern("d MMMM uuuu");

			// Create a test stream to accept a print stream
			ByteArrayOutputStream testStream = new ByteArrayOutputStream();

			// Redirect the standard out to the test stream via the print stream
			System.setOut(new PrintStream(testStream));

			loan.displayDetails();

			String expectedOutput = "Member:\r\n" + "ID Number:      "
					+ member.getIDNumber() + "\r\n" + "First Name:     "
					+ member.getFirstName() + "\r\n" + "Last Name:      "
					+ member.getLastName() + "\r\n" + "Email:          "
					+ member.getEmail() + "\r\n" + "Phone Number:   "
					+ member.getPhoneNumber() + "\r\n\r\n" + "Book:\r\n"
					+ "ID Number:      " + book.getIDNumber() + "\r\n"
					+ "Author:         " + book.getAuthor() + "\r\n"
					+ "Title:          " + book.getTitle() + "\r\n"
					+ "ISBN Number:    " + book.getISBNNumber() + "\r\n"
					+ "Genre:          " + book.getGenre() + "\r\n\r\n"
					+ "Issued:         " + issueDate.format(formatter) + "\r\n"
					+ "Expires:        " + expiryDate.format(formatter);

			assertEquals(expectedOutput, testStream.toString().trim(),
					"the loan's details should be printed");

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
}
