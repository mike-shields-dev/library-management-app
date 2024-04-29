package uk.co.michaelshields.assignment_1;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * A suite of JUnit tests for the Book class
 * 
 * @see <a target="_blank" href= "https://junit.org/junit5/">JUnit5</a>
 * @see <a target="_blank" href="Book.html">Book</a>
 * 
 * @author M. Shields
 * @version 25.02.2024
 */
public class BookTest {
	/**
	 * Verifies that the Book constructor throws if author is null
	 */
	@Test
	@DisplayName("Book constructor throws if author is null")
	public void testConstructorAuthorNull() {
		assertThrows(IllegalArgumentException.class, () -> new Book(null, "Musimathics Vol 1", "Non Fiction", "9780262516556"),
				"constructor should throw if author is null");
	}

	/**
	 * Verifies that the Book constructor throws if author is empty string
	 */
	@Test
	@DisplayName("Book constructor throws if author is empty string")
	public void testConstructorAuthorEmpytyString() {
		assertThrows(IllegalArgumentException.class, () -> new Book("", "Musimathics Vol 1", "Non Fiction", "9780262516556"),
				"constructor should throw if author is empty string");
	}

	/**
	 * Verifies that the Book constructor throws if author is whitespace
	 */
	@Test
	@DisplayName("Book constructor throws if author is whitespace")
	public void testConstructorAuthorIsWhitespace() {
		assertThrows(IllegalArgumentException.class, () -> new Book(" ", "Musimathics Vol 1", "Non Fiction", "9780262516556"),
				"constructor should throw if author is whitespace");
	}

	/**
	 * Verifies that the Book constructor throws if title is null
	 */
	@Test
	@DisplayName("Book constructor throws if title is null")
	public void testConstructorTitleIsNull() {
		assertThrows(IllegalArgumentException.class, () -> new Book("Gareth. Loy", null, "Non Fiction", "9780262516556"),
				"constructor should throw if title is null");
	}

	/**
	 * Verifies that the Book constructor throws if title is empty string
	 */
	@Test
	@DisplayName("Book constructor throws if title is empty string")
	public void testConstructorTitleIsEmptyString() {
		assertThrows(IllegalArgumentException.class, () -> new Book("Gareth. Loy", "", "Non Fiction", "9780262516556"),
				"constructor should throw if title is empty string");
	}

	/**
	 * Verifies that the Book constructor throws if title is whitespace
	 */
	@Test
	@DisplayName("Book constructor throws if title is whitespace")
	public void testConstructorTitleIsWhitespace() {
		assertThrows(IllegalArgumentException.class, () -> new Book("Gareth. Loy", " ", "Non Fiction", "9780262516556"),
				"constructor should throw if title is whitespace");
	}
	
	
	/**
	 * Verifies that the Book constructor throws if genre is null
	 */
	@Test
	@DisplayName("Book constructor throws if genre is null")
	public void testConstructorGenreIsNull() {
		assertThrows(IllegalArgumentException.class, () -> new Book("Gareth. Loy", "Musimathics Vol 1", null, "9780262516556"),
				"constructor should throw if genre is null");
	}

	/**
	 * Verifies that the Book constructor throws if genre is empty string
	 */
	@Test
	@DisplayName("Book constructor throws if genre is empty string")
	public void testConstructorGenreIsEmptyString() {
		assertThrows(IllegalArgumentException.class, () -> new Book("Gareth. Loy", "Musimathics Vol 1", "", "9780262516556"),
				"constructor should throw if genre is empty string");
	}

	/**
	 * Verifies that the Book constructor throws if genre is whitespace
	 */
	@Test
	@DisplayName("Book constructor throws if genre is whitespace")
	public void testConstructorGenreIsWhitespace() {
		assertThrows(IllegalArgumentException.class, () -> new Book("Gareth. Loy", " ", " ", "9780262516556"),
				"constructor should throw if genre is whitespace");
	}

	/**
	 * Verifies that the Book constructor throws if isbnNumber is null
	 */
	@Test
	@DisplayName("Book constructor throws if isbnNumber is null")
	public void testConstructorNullISBNNumber() {
		assertThrows(IllegalArgumentException.class, () -> new Book("Gareth. Loy", "Musimathics Vol 1", "Non Fiction", null),
				"constructor should throw if isbnNumber is null");
	}

	/**
	 * Verifies that the Book constructor throws if isbnNumber is empty string
	 */
	@Test
	@DisplayName("Book constructor throws if isbnNumber is empty string")
	public void testConstructorISBNNumberEmptyString() {
		assertThrows(IllegalArgumentException.class, () -> new Book("Gareth. Loy", "Musimathics Vol 1", "Non Fiction", ""),
				"constructor should throw if isbnNumber is empty string");
	}

	/**
	 * Verifies that the Book constructor throws if isbnNumber is whitespace
	 */
	@Test
	@DisplayName("Book constructor throws if isbnNumber is whitespace")
	public void testConstructorISBNNumberWhitespace() {
		assertThrows(IllegalArgumentException.class, () -> new Book("Gareth. Loy", "Musimathics Vol 1", "Non Fiction", " "),
				"constructor should throw if isbnNumber is whitespace");
	}

	/**
	 * Verifies that the Book constructor throws if isbnNumber is invalid
	 */
	@Test
	@DisplayName("Book constructor throws if isbnNumber is not valid")
	public void testConstructorISBNInvalid() {
		String invalidISBNNumber = "9780262516555";

		assertThrows(IllegalArgumentException.class,
				() -> new Book("Gareth. Loy", "Musimathics Vol 1", "Non Fiction", invalidISBNNumber),
				"constructor should throw if isbnNumber is whitespace");
	}

	/**
	 * Verifies that the Book constructor instantiates a book with default ID number
	 * of zero, if no ID number is provided
	 */
	@Test
	@DisplayName("Book constructor instantiates a book with default ID number")
	public void testConstructorDefaultIDNumber() {
		Book book = new Book("Gareth. Loy", "Musimathics Vol 1", "Non Fiction", "9780262516556");

		assertEquals(0, book.getIDNumber(), "the constructor should instantiate a book with a default ID number");
	}

	/**
	 * Verifies that the Book constructor throws if idNumber is negative integer
	 */
	@Test
	@DisplayName("Book constructor throws if idNumber is negative integer")
	public void testConstructorNegativeIDNumber() {
		assertThrows(IllegalArgumentException.class,
				() -> new Book("Gareth. Loy", "Musimathics Vol 1", "Non Fiction", "9780262516556", -1),
				"constructor should throw if idNumber is negative integer");
	}

	/**
	 * Verifies that the Book constructor instantiates a book with ID number set
	 */
	@Test
	@DisplayName("Book constructor instantiates a book with the ID number set")
	public void testConstructorIDNumberIsSet() {
		int idNumber = 1;
		Book book = new Book("Gareth. Loy", "Musimathics Vol 1", "Non Fiction", "9780262516556", idNumber);

		assertEquals(idNumber, book.getIDNumber(), "the constructor should instantiate a book with ID number set");
	}

	/**
	 * Verifies that getAuthor() returns the book's author
	 */
	@Test
	@DisplayName("getAuthor() returns the author")
	public void getAuthor() {
		String author = "Gareth. Loy";
		Book book = new Book(author, "Musimathics Vol 1", "Non Fiction", "9780262516556", 1);

		assertEquals(author, book.getAuthor(), "getAuthor() returns the book's author");
	}

	/**
	 * Verifies that getTitle() returns the book's title
	 */
	@Test
	@DisplayName("getTitle() returns the book's title")
	public void getTitle() {
		String title = "Musimathics Vol 1";
		Book book = new Book("Gareth. Loy", title, "Non Fiction", "9780262516556", 1);

		assertEquals(title, book.getTitle(), "getTitle() returns the book's title");
	}

	/**
	 * Verifies that getGenre() returns the book's genre
	 */
	@Test
	@DisplayName("getGenre() returns the book's genre")
	public void getGenre() {
		String genre = "Non Fiction";
		Book book = new Book("Gareth. Loy", "Musimathics Vol 1", genre, "9780262516556", 1);

		assertEquals(genre, book.getGenre(), "getGenre() returns the book's genre");
	}

	/**
	 * Verifies that getISBNNumber() returns the book's ISBN number
	 */
	@Test
	@DisplayName("getISBNNumber() returns the book's ISBN number")
	public void getISBNNumber() {
		String isbnNumber = "9780262516556";
		Book book = new Book("Gareth. Loy", "Musimathics Vol 1", "Non Fiction", isbnNumber, 1);

		assertEquals(isbnNumber, book.getISBNNumber(), "getISBNNumber() returns the book's ISBN number");
	}

	/**
	 * Verifies that getIDNumber() returns the book's ID number
	 */
	@Test
	@DisplayName("getIDNumber() returns the book's ID number")
	public void getIDNumber() {
		int idNumber = 1;
		Book book = new Book("Gareth. Loy", "Musimathics Vol 1", "Non Fiction", "9780262516556", idNumber);

		assertEquals(idNumber, book.getIDNumber(), "getISBNNumber() returns the book's ID number");
	}

	/**
	 * Verifies that <code>displayDetails()</code> prints the book's details
	 */
	@Test
	@DisplayName("displayDetails()")
	public void testDisplayDetails() {
		// Create a test stream to capture print stream
		ByteArrayOutputStream testStream = new ByteArrayOutputStream();

		// Create a new print stream set System.out to output to it
		// Then set the output of the print stream to the test stream
		System.setOut(new PrintStream(testStream));

		String author = "Robert C. Martin";
		String title = "Clean Code";
		String genre = "Non Fiction";
		String isbnNumber = "9780132350884";
		int idNumber = 1;

		Book book = new Book(author, title, genre, isbnNumber, idNumber);

		String expectedOutput = 
				"ID Number:      " + idNumber + "\r\n" + 
				"Author:         " + author + "\r\n" + 
				"Title:          " + title + "\r\n" + 
				"ISBN Number:    " + isbnNumber + "\r\n" + 
				"Genre:          " + genre;

		book.displayDetails();

		assertEquals(expectedOutput, testStream.toString().trim(), "displayDetails should print the book's details");

		System.setOut(System.out); // Reinstate the standard output stream
	}
}
