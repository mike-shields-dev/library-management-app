package uk.co.michaelshields.assignment_1;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;

/**
 * A suite of JUnit tests for the Library class
 * 
 * @see <a target="_blank" href= "https://junit.org/junit5/">JUnit5</a>
 * @see <a target="_blank" href="Library.html">Library</a>
 * 
 * @author M. Shields
 * @version 1.0
 */
@DisplayName("Library Tests")
public class LibraryTest {
	Library library;

	/**
	 * <p>
	 * Verifies that the {@code Library} constructor instantiates a library
	 * </p>
	 * <p>
	 * Assertions:
	 * </p>
	 * <ul>
	 * <li>Instantiates a {@code Library} object with an empty members
	 * collection</li>
	 * <li>Instantiates a {@code >Library} object with an empty books
	 * collection</li>
	 * <li>Instantiates a {@code Library} object with an empty loans
	 * collection</li>
	 * <li>ID numbers should start at 1</li>
	 * <li>The duration of a loan should be 21 days</li>
	 * </ul>
	 */
	@Test
	@DisplayName("Library constructor")
	public void testConstructor() {
		Library library = new Library();

		assertTrue(library instanceof Library,
				"constructor should instanciate a library");

		assertTrue(library.getMembers().isEmpty(),
				"the member collection should be empty");
		assertTrue(library.getBooks().isEmpty(),
				"the book collection should be empty");
		assertTrue(library.getLoans().isEmpty(),
				"the loan collection should be empty");
		assertEquals(library.getNextIDNumber(), 1,
				"ID numbers should start at 1");
		assertEquals(library.getLoanDurationInDays(), 21,
				"the loan duration should be 21");
	}

	@BeforeEach
	void instantiateLibrary() {
		library = new Library();
	}

	/**
	 * Verifies that addMember throws if the member is null
	 */
	@Test
	@DisplayName("addMember throws with null member")
	public void addMemberThrowsWithNullMember() {
		assertThrows(IllegalArgumentException.class, () -> {
			library.addMember(null);
		}, "an error should be thrown if member is null");
	}

	/**
	 * Verifies that addMember adds a member
	 */
	@Test
	@DisplayName("addMember(Member member) adds a member")
	public void addMember() {
		try {
			Member member = new Member("Michael", "Shields", "abc@xyz.com",
					"07777777777");

			library.addMember(member);

			assertTrue(library.getMemberByID(member.getIDNumber()) == member,
					"a member should have been added");
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	/**
	 * Verifies that addMember sets the member's ID number
	 */
	@Test
	@DisplayName("addMember sets the member's ID number")
	public void addMemberSetsMemberID() {
		try {
			Member member = new Member("Michael", "Shields", "abc@xyz.com",
					"07777777777");
			int defaultID = 0;
			library.addMember(member);

			assertTrue(member.getIDNumber() != defaultID,
					"the member's ID number should have been set");

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	/**
	 * Verifies that addMember sets the member's ID number to the next
	 * sequential number
	 */
	@Test
	@DisplayName("addMember sets member ID sequentially")
	public void addMemberSetsSequentialIDNumbers() {
		try {
			Member member1 = new Member("Michael", "Shields", "abc@xyz.com",
					"07777777777");
			Member member2 = new Member("Jim", "Jones", "xyz@abc.com",
					"07777777777");

			library.addMember(member1);
			library.addMember(member2);

			assertTrue(member2.getIDNumber() - member1.getIDNumber() == 1,
					"successive members ID numbers should be incremented by 1");

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	/**
	 * Verifies that addMember throws if member with email already exists
	 */
	@Test
	@DisplayName("addMember throws if email is duplicate")
	public void addMemberWithDuplicateEmail() {
		try {
			Member member = new Member("Michael", "Shields", "abc@xyz.com",
					"07777777777");
			Member memberDuplicateEmail = new Member("Jim", "Jones",
					"abc@xyz.com", "07777777777");

			assertThrows(IllegalArgumentException.class, () -> {
				library.addMember(member);
				library.addMember(memberDuplicateEmail);
			}, "an error should be thrown if member's email is duplicate");

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	/**
	 * Verifies that getMemberByID throws if the ID number is invalid
	 */
	@Test
	@DisplayName("getMemberById throws if ID number is invalid")
	public void getMemberByIDInvalidID() {
		try {
			Member member = new Member("Michael", "Shields", "abc@xyz.com",
					"07777777777");
			
			library.addMember(member);
			
			assertThrows(IllegalArgumentException.class, () -> {
				library.getMemberByID(-1);
			}, "an error should be thrown if the ID number is invalid");
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		
	}

	/**
	 * Verifies that getMemberByID throws if member with ID number is not found
	 */
	@Test
	@DisplayName("getMemberById throws if member is empty")
	public void getMemberByIdUnknown() {
		assertThrows(NoSuchElementException.class, () -> {
			library.getMemberByID(1);
		}, "an error should be thrown if a member with ID is not found");
	}

	/**
	 * Verifies that <code>getMemberByID(int idNumber)</code> returns a member
	 */
	@Test
	@DisplayName("getMemberById returns a member")
	void getMemberById() {
		try {
			Member member = new Member("Michael", "Shields", "abc@xyz.com",
					"07777777777");
			library.addMember(member);

			assertEquals(library.getMemberByID(member.getIDNumber()), member,
					"member by ID number should have been returned");
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	
	/**
	 * Verifies that removeMemberById throws if the ID number is invalid
	 */
	@Test
	@DisplayName("removeMemberByID throws if ID is invalid")
	void removeMemberByIdInvalidIDNumber() {
		try {
			Member member = new Member("Michael", "Shields", "abc@xyz.com",
					"07777777777");

			library.addMember(member);

			assertThrows(IllegalArgumentException.class,
					() -> library.removeMemberByID(-1),
					"an error should be thrown if ID number is invalid");
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	/**
	 * Verifies that removeMemberByID throws if members collection is empty
	 */
	@Test
	@DisplayName("removeMemberByID throws if member collection is empty")
	void removeMemberByIDNoMembers() {
		assertThrows(IllegalStateException.class, () -> {
			library.removeMemberByID(1);
		}, "an error should be thrown if the members collection is empty");
	}

	/**
	 * Verifies that removeMemberById throws if member with ID number is not
	 * found
	 */
	@Test
	@DisplayName("removeMemberByID throws if ID is not found")
	void removeMemberByIdUnknownIDNumber() {
		try {
			int memberID = 1;
			int nonExistentID = 2;
			Member member = new Member("Michael", "Shields", "abc@xyz.com",
					"07777777777", memberID);

			library.addMember(member);

			assertThrows(NoSuchElementException.class,
					() -> library.removeMemberByID(nonExistentID),
					"an error should be thrown if ID number not found");
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	/**
	 * Verifies that removeMemberById throws if member has loans
	 */
	@Test
	@DisplayName("removeMemberByID throws if member has loans")
	public void removeMemberByIDMemberHasLoans() {
		try {
			Member member = new Member("Michael", "Shields", "abc@xyz.com",
					"07777777777");
			Book book = new Book("G. Loy", "Musimathics Vol 1", "Non Fiction",
					"9780262516556");
			library.addMember(member);
			library.addBook(book);
			library.addLoan(member, book);

			assertThrows(IllegalStateException.class,
					() -> library.removeMemberByID(member.getIDNumber()),
					"an error should be thrown if member has outstanding loans");

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	/**
	 * Verifies that removeMemberById removes member
	 */
	@Test
	@DisplayName("removeMemberByID removes member")
	public void removeMemberByID() {
		try {
			Member member = new Member("Michael", "Shields", "abc@xyz.com",
					"07777777777");
			library.addMember(member);
			library.removeMemberByID(member.getIDNumber());
			
			assertFalse(library.getMembers().contains(member));

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	/**
	 * Verifies that removeMemberByID removes a member
	 */
	@Test
	@DisplayName("removeMemberByID removes a member")
	public void getMemberByIdUnknownIDNumber() {
		try {
			Member member1 = new Member("Michael", "Shields", "abc@xyz.com",
					"07777777777");
			Member member2 = new Member("Jim", "Jones", "xyz@abc.com",
					"07777777777");
			library.addMember(member1);
			library.addMember(member2);

			library.removeMemberByID(member1.getIDNumber());

			assertThrows(NoSuchElementException.class,
					() -> library.getMemberByID(member1.getIDNumber()),
					"the member should have been removed");

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	/**
	 * Verifies that displayMemberBySurnameQuery throws if members collection is
	 * empty
	 */
	@Test
	@DisplayName("displayMemberBySurnameQuery throws if members collection is empty")
	public void displayMembersBySurnameQuery() {
		assertThrows(IllegalStateException.class,
				() -> library.displayMembersByLastNameQuery("hi"),
				"an error should be thrown if members collection is empty");
	}
	
	/**
	 * Verifies that displayMemberBySurnameQuery throws if the query is null
	 */
	@Test
	@DisplayName("displayMemberByLastNameQuery if query is null")
	public void displayMembersByLastNameQueryNullQuery() {
		try {
			Member member = new Member("Michael", "Shields", "abc@xyz.com",
					"07777777777");
			library.addMember(member);

			assertThrows(IllegalArgumentException.class,
					() -> library.displayMembersByLastNameQuery(null),
					"an error should be thrown if query is null");

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	/**
	 * Verifies that displayMemberBySurnameQuery throws if the query is empty string
	 */
	@Test
	@DisplayName("displayMemberByLastNameQuery if query is empty string")
	public void displayMembersByLastNameQueryEmptyString() {
		try {
			Member member = new Member("Michael", "Shields", "abc@xyz.com",
					"07777777777");
			library.addMember(member);

			assertThrows(IllegalArgumentException.class,
					() -> library.displayMembersByLastNameQuery(""),
					"an error should be thrown if query is empty string");

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	/**
	 * Verifies that displayMemberBySurnameQuery throws if the query is whitespace
	 */
	@Test
	@DisplayName("displayMemberByLastNameQuery if query is whitespace")
	public void displayMembersByLastNameQueryWhiteSpace() {
		try {
			Member member = new Member("Michael", "Shields", "abc@xyz.com",
					"07777777777");
			library.addMember(member);

			assertThrows(IllegalArgumentException.class,
					() -> library.displayMembersByLastNameQuery(" "),
					"an error should be thrown if query is whitespace");

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	/**
	 * Verifies that displayMemberBySurnameQuery throws if no matching member
	 * found
	 */
	@Test
	@DisplayName("displayMemberByLastNameQuery if no matching members found")
	public void displayMembersByLastNameQueryNoMatches() {
		try {
			Member member = new Member("Michael", "Shields", "abc@xyz.com",
					"07777777777");
			library.addMember(member);

			assertThrows(NoSuchElementException.class,
					() -> library.displayMembersByLastNameQuery("zzz"),
					"an error should be thrown if no matching members found");

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	/**
	 * Verifies that displayMemberBySurnameQuery prints details of matching
	 * members
	 */
	@Test
	@DisplayName("displayMemberByLastNameQuery prints a details of matching members")
	public void displayMemberByLastNameQuery() {
		try {
			Member member1 = new Member("Michael", "Shields", "abc@xyz.com",
					"07777777777");
			Member member2 = new Member("Jim", "Jones", "xyz@abc.com",
					"07777777777");
			library.addMember(member1);
			library.addMember(member2);

			// Create a test stream to accept a print stream
			ByteArrayOutputStream testStream = new ByteArrayOutputStream();

			// Redirect the standard out to the test stream via a new print
			// stream
			System.setOut(new PrintStream(testStream));

			library.displayMembersByLastNameQuery("hi");

			String expectedOutput = "ID Number:      " + member1.getIDNumber()
					+ "\r\n" + "First Name:     " + member1.getFirstName()
					+ "\r\n" + "Last Name:      " + member1.getLastName()
					+ "\r\n" + "Email:          " + member1.getEmail() + "\r\n"
					+ "Phone Number:   " + member1.getPhoneNumber();

			assertEquals(expectedOutput, testStream.toString().trim(),
					"the details of matching members should be printed");

			testStream.reset();

			library.displayMembersByLastNameQuery("on");

			expectedOutput = "ID Number:      " + member2.getIDNumber() + "\r\n"
					+ "First Name:     " + member2.getFirstName() + "\r\n"
					+ "Last Name:      " + member2.getLastName() + "\r\n"
					+ "Email:          " + member2.getEmail() + "\r\n"
					+ "Phone Number:   " + member2.getPhoneNumber();

			assertEquals(expectedOutput, testStream.toString().trim(),
					"the details of matching members should be printed");

			// Reinstate the standard output stream
			System.setOut(System.out);

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	/**
	 * Verifies that addBook throws if the book is null
	 */
	@Test
	@DisplayName("addBook throws with null book")
	public void addBookThrowsWithNullBook() {
		assertThrows(IllegalArgumentException.class, () -> {
			library.addBook(null);
		}, "an error should be thrown if book is null");
	}

	/**
	 * Verifies that addBook throws if a book already exists with the provided
	 * ISBN number
	 */
	@Test
	@DisplayName("addBook throws if ISBN already exists")
	public void addBookThrowsExistingISBNNumber() {
		Book book1 = new Book("G. Loy", "Musimathics Vol 1", "Non Fiction",
				"9780262516556");
		Book book2 = new Book("G. Loy", "Musimathics Vol 1", "Non Fiction",
				"9780262516556");

		library.addBook(book1);

		assertThrows(IllegalStateException.class, () -> {
			library.addBook(book2);
		}, "an error should be thrown if the books ISBN number already exists");
	}

	/**
	 * Verifies that addBook adds a book
	 */
	@Test
	@DisplayName("addBook adds a book")
	public void addBook() {
		Book book = new Book("G. Loy", "Musimathics Vol 1", "Non Fiction",
				"9780262516556");

		library.addBook(book);

		assertTrue(library.getBookByID(book.getIDNumber()) == book,
				"a book should have been added");
	}

	/**
	 * Verifies that addBook sets the book's ID number
	 */
	@Test
	@DisplayName("addBook sets the book's ID number")
	public void addBookSetsBookID() {
		Book book = new Book("G. Loy", "Musimathics Vol 1", "Non Fiction",
				"9780262516556");
		int defaultIDNumber = 0;

		library.addBook(book);

		assertTrue(book.getIDNumber() != defaultIDNumber,
				"the book's ID number should have been set");
	}

	/**
	 * Verifies that addBook sets the books's ID number to the next available
	 * sequential ID number
	 */
	@Test
	@DisplayName("addBook sets ID numbers sequentially")
	public void addBookSetsSequentialIDNumbers() {
		Book book1 = new Book("G. Loy", "Musimathics Vol 1", "Non Fiction",
				"9780262516556");
		Book book2 = new Book("C. Negus", "Linux Bible", "Non Fiction",
				"9781119578888");

		library.addBook(book1);
		library.addBook(book2);

		assertTrue(book2.getIDNumber() - book1.getIDNumber() == 1,
				"successive book's ID numbers should be incremented by 1");
	}

	/**
	 * Verifies that addBook throws if book with ISBN number already exists
	 */
	@Test
	@DisplayName("addBook throws if ISBN number is duplicate")
	public void addBookWithDuplicateISBN() {

		String duplicateISBN = "9780262516556";
		Book book1 = new Book("G. Loy", "Musimathics Vol 1", "Non Fiction",
				duplicateISBN);
		Book book2 = new Book("C. Negus", "Linux Bible", "Non Fiction",
				duplicateISBN);

		assertThrows(IllegalStateException.class, () -> {
			library.addBook(book1);
			library.addBook(book2);
		}, "an error should be thrown if book's ISBN number is duplicate");
	}

	/**
	 * Verifies that getBookByID throws if books collection is empty
	 */
	@Test
	@DisplayName("getBookById throws if books collection is empty")
	public void getBookByIDBooksEmpty() {
		assertThrows(NoSuchElementException.class, () -> {
			library.getBookByID(1);
		}, "an error should be thrown if books collection is empty");
	}

	/**
	 * Verifies that getBookByID throws if the provided ID number 
	 * is not valid
	 */
	@Test
	@DisplayName("getBookById throws if ID number invalid")
	public void getBookByIDInvalidIDNumber() {
		assertThrows(IllegalArgumentException.class, () -> {
			library.getBookByID(-1);
		}, "an error should be thrown if books collection is empty");
	}
	
	/**
	 * Verifies that getBookByID throws if book is not found
	 */
	@Test
	@DisplayName("getBookById throws if book is not found")
	public void getBookByIDUnknownID() {
		Book book1 = new Book("G. Loy", "Musimathics Vol 1", "Non Fiction",
				"9780262516556");
		Book book2 = new Book("C. Negus", "Linux Bible", "Non Fiction",
				"9781119578888");
		library.addBook(book1);
		library.addBook(book2);
		library.removeBookByID(book2.getIDNumber());

		assertThrows(NoSuchElementException.class, () -> {
			library.getBookByID(book2.getIDNumber());
		}, "an error should be thrown if book not found");
	}

	/**
	 * Verifies that removeBookById throws if books collection is empty
	 */
	@Test
	@DisplayName("removeBookByID throws if book with ID is not found")
	public void removeBookByIDNoBooks() {
		int bookID = 1;

		assertThrows(NoSuchElementException.class,
				() -> library.removeBookByID(bookID),
				"an error should be thrown if books collection is empty");
	}

	/**
	 * Verifies that removeBookById throws if book with ID number is not found
	 */
	@Test
	@DisplayName("removeBookByID throws if book with ID is not found")
	public void removeBookByIDUnknown() {
		int bookID = 1;

		assertThrows(NoSuchElementException.class,
				() -> library.removeBookByID(bookID),
				"an error should be thrown if ID number not found");
	}

	/**
	 * Verifies that removeBookByID removes a book
	 */
	@Test
	@DisplayName("removeBookById removes a book")
	public void removeBookByID() {
		Book book = new Book("G. Loy", "Musimathics Vol 1", "Non Fiction",
				"9780262516556");
		library.addBook(book);
		assertTrue(library.getBookByID(book.getIDNumber()) == book);

		library.removeBookByID(book.getIDNumber());

		assertThrows(NoSuchElementException.class,
				() -> library.getBookByID(book.getIDNumber()),
				"a book should have been removed");
	}

	/**
	 * Verifies that removeBookById throws if book is on loan
	 */
	@Test
	@DisplayName("removeBookByID throws if book is on loan")
	public void removeBookByIDBorrowedBook() {
		try {
			Member member = new Member("Michael", "Shields", "abc@xyz.com",
					"07777777777");
			Book book = new Book("G. Loy", "Musimathics Vol 1", "Non Fiction",
					"9780262516556");
			library.addMember(member);
			library.addBook(book);
			library.addLoan(member, book);

			assertThrows(IllegalStateException.class, () -> {
				library.removeBookByID(book.getIDNumber());
			}, "an error should be thrown if the book is on loan");

		} catch (Exception e) {

		}
	}

	/**
	 * Verifies that getBookById throws if book with ID number is not found
	 */
	@Test
	@DisplayName("getBookByID throws if book with ID is not found")
	public void getBookByIDUnknown() {
		int bookId = 1;

		assertThrows(NoSuchElementException.class,
				() -> library.getBookByID(bookId),
				"an error should be thrown if ID number not found");
	}

	/**
	 * Verifies that getBookByID returns a book
	 */
	@Test
	@DisplayName("getBookByID returns a book")
	public void getBookById() {
		Book book = new Book("G. Loy", "Musimathics Vol 1", "Non Fiction",
				"9780262516556");
		library.addBook(book);

		assertEquals(library.getBookByID(book.getIDNumber()), book,
				"a book should have been returned");
	}

	/**
	 * Verifies that displayBookByID prints a book's details
	 */
	@Test
	@DisplayName("displayBookByID prints a book's details")
	public void displayBookById() {
		Book book = new Book("G. Loy", "Musimathics Vol 1", "Non Fiction",
				"9780262516556");
		library.addBook(book);

		// Create a test stream to accept a print stream
		ByteArrayOutputStream testStream = new ByteArrayOutputStream();

		// Redirect the standard out to the test stream via a new print stream
		System.setOut(new PrintStream(testStream));

		library.displayBookByID(book.getIDNumber());

		String expectedOutput = "ID Number:      " + book.getIDNumber() + "\r\n"
				+ "Author:         " + book.getAuthor() + "\r\n"
				+ "Title:          " + book.getTitle() + "\r\n"
				+ "ISBN Number:    " + book.getISBNNumber() + "\r\n"
				+ "Genre:          " + book.getGenre();

		assertEquals(expectedOutput, testStream.toString().trim(),
				"the book's details should have been printed as expected");

		// Reinstate the standard output stream
		System.setOut(System.out);
	}

	/**
	 * Verifies that displayBooksByTitleQuery throws if no matching books found
	 */
	@Test
	@DisplayName("displayBooksByTitleQuery if no matching books found")
	public void displayBooksByTitleQueryNoMatches() {
		Book book = new Book("G. Loy", "Musimathics Vol 1", "Non Fiction",
				"9780262516556");
		library.addBook(book);

		assertThrows(NoSuchElementException.class,
				() -> library.displayBooksByTitleQuery("zzz"),
				"an error should be thrown if no matching books found");
	}

	/**
	 * Verifies that displayMemberBySurnameQuery prints a member's details
	 */
	@Test
	@DisplayName("displayBookByTitleQuery prints details of matching books")
	public void displayBookByTitleQuery() {
		Book book1 = new Book("G. Loy", "Musimathics Vol 1", "Non Fiction",
				"9780262516556");
		Book book2 = new Book("C. Negus", "Linux Bible", "Non Fiction",
				"9781119578888");
		library.addBook(book1);
		library.addBook(book2);

		// Create a test stream to accept a print stream
		ByteArrayOutputStream testStream = new ByteArrayOutputStream();

		// Redirect the standard out to the test stream via a new print stream
		System.setOut(new PrintStream(testStream));

		library.displayBooksByTitleQuery("mat");

		String expectedOutput = "ID Number:      " + book1.getIDNumber()
				+ "\r\n" + "Author:         " + book1.getAuthor() + "\r\n"
				+ "Title:          " + book1.getTitle() + "\r\n"
				+ "ISBN Number:    " + book1.getISBNNumber() + "\r\n"
				+ "Genre:          " + (book1.getGenre());

		assertEquals(expectedOutput, testStream.toString().trim(),
				"the details of matching books should have been printed");

		testStream.reset();

		expectedOutput = "ID Number:      " + book2.getIDNumber() + "\r\n"
				+ "Author:         " + book2.getAuthor() + "\r\n"
				+ "Title:          " + book2.getTitle() + "\r\n"
				+ "ISBN Number:    " + book2.getISBNNumber() + "\r\n"
				+ "Genre:          " + (book2.getGenre());

		library.displayBooksByTitleQuery("lin");

		assertEquals(expectedOutput, testStream.toString().trim(),
				"the details of matching books should have been printed");

		// Reinstate the standard output stream
		System.setOut(System.out);
	}

	/**
	 * Verifies that addLoan throws if member is unknown
	 */
	@Test
	@DisplayName("addLoan if member is unknown")
	public void addLoanUnknownMember() {
		try {
			Member member = new Member("Michael", "Shields", "abc@xyz.com",
					"07777777777");
			Book book = new Book("G. Loy", "Musimathics Vol 1", "Non Fiction",
					"9780262516556");
			library.addBook(book);

			assertThrows(NoSuchElementException.class,
					() -> library.addLoan(member, book),
					"an error should have been thrown if member is unknown");

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	/**
	 * Verifies that addLoan throws if book is unknown
	 */
	@Test
	@DisplayName("addLoan if book is unknown")
	public void addLoanUnknownBook() {
		try {
			Member member = new Member("Michael", "Shields", "abc@xyz.com",
					"07777777777");
			Book book = new Book("G. Loy", "Musimathics Vol 1", "Non Fiction",
					"9780262516556");
			library.addMember(member);

			assertThrows(NoSuchElementException.class,
					() -> library.addLoan(member, book),
					"an error should have been thrown if book is unknown");

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	/**
	 * Verifies that addLoan throws if book is already on loan
	 */
	@Test
	@DisplayName("addLoan if book is on loan")
	public void addLoanBookOnLoan() {
		try {
			Member member1 = new Member("Michael", "Shields", "abc@xyz.com",
					"07777777777");
			Member member2 = new Member("Jim", "Jones", "xyz@abc.com",
					"07777777777");
			Book book = new Book("G. Loy", "Musimathics Vol 1", "Non Fiction",
					"9780262516556");
			library.addMember(member1);
			library.addMember(member2);
			library.addBook(book);
			library.addLoan(member1, book);

			assertThrows(IllegalStateException.class,
					() -> library.addLoan(member2, book),
					"an error should have been thrown if book is on loan");

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	/**
	 * Verifies that getExpiredLoans throws if loans collection is empty
	 */
	@Test
	@DisplayName("getExpiredLoans throws if loans collection is empty")
	void getExpiredLoansNoLoans() {
		assertThrows(IllegalStateException.class,
				() -> library.getExpiredLoans(LocalDate.now()),
				"an error should have been thrown if loans collection is empty");
	}

	/**
	 * Verifies that getLoan throws if loans collection is empty
	 */
	@Test
	@DisplayName("getLoan throws if loans collection is empty")
	public void getLoanNoLoans() {
		try {
			Member member = new Member("Michael", "Shields", "abc@xyz.com",
					"07777777777");
			Book book = new Book("G. Loy", "Musimathics Vol 1", "Non Fiction",
					"9780262516556");
			library.addMember(member);
			library.addBook(book);

			assertThrows(IllegalStateException.class,
					() -> library.getLoan(member, book),
					"an error should have been thrown if loans collection is empty");

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	/**
	 * Verifies that getLoan throws if loan loan is unknown
	 */
	@Test
	@DisplayName("getLoan(Member member, Book book) throws if loan is unknown")
	public void getLoanUnknownLoan() {
		try {
			Member member1 = new Member("Michael", "Shields", "abc@xyz.com",
					"07777777777");
			Member member2 = new Member("Jim", "Jones", "xyz@abc.com",
					"07777777777");
			Book book1 = new Book("G. Loy", "Musimathics Vol 1", "Non Fiction",
					"9780262516556");
			Book book2 = new Book("C. Negus", "Linux Bible", "Non Fiction",
					"9781119578888");
			library.addMember(member1);
			library.addMember(member2);
			library.addBook(book1);
			library.addBook(book2);
			library.addLoan(member1, book1);

			assertThrows(NoSuchElementException.class,
					() -> library.getLoan(member2, book2),
					"an error should have been thrown if loans collection is empty");

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	/**
	 * Verifies that getLoan returns a loan
	 */
	@Test
	@DisplayName("getLoan(Member member, Book book) returns a loan")
	public void getLoan() {
		try {
			Member member = new Member("Michael", "Shields", "abc@xyz.com",
					"07777777777");
			Book book = new Book("G. Loy", "Musimathics Vol 1", "Non Fiction",
					"9780262516556");
			library.addMember(member);
			library.addBook(book);
			library.addLoan(member, book);

			assertTrue(library.getLoan(member, book) instanceof Loan,
					"a loan should have been returned");

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	/**
	 * Verifies that removeLoan throws if loans collection is empty
	 */
	@Test
	@DisplayName("removeLoan throws if loans is empty")
	public void removeLoanNoLoans() {
		try {
			Member member = new Member("Michael", "Shields", "abc@xyz.com",
					"07777777777");
			Book book = new Book("G. Loy", "Musimathics Vol 1", "Non Fiction",
					"9780262516556");
			library.addMember(member);
			library.addBook(book);

			assertThrows(IllegalStateException.class,
					() -> library.removeLoan(member, book),
					"an error should have been thrown if loans is empty");

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	/**
	 * Verifies removeLoan throws if no matching loan found
	 */
	@Test
	@DisplayName("removeLoan throws if loan not found")
	public void removeLoanUnknownLoan() {
		try {
			Member member1 = new Member("Michael", "Shields", "abc@xyz.com",
					"07777777777");
			Member member2 = new Member("Jim", "Jones", "xyz@abc.com",
					"07777777777");
			Book book = new Book("G. Loy", "Musimathics Vol 1", "Non Fiction",
					"9780262516556");
			library.addMember(member1);
			library.addBook(book);
			library.addLoan(member1, book);

			assertThrows(NoSuchElementException.class,
					() -> library.removeLoan(member2, book),
					"an error should have been thrown if loan not found");

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	/**
	 * Verifies that removeLoan removes a loan with the provided member and book
	 */
	@Test
	@DisplayName("removeLoan throws if no loans found")
	public void removeLoan() {
		try {
			Member member = new Member("Michael", "Shields", "abc@xyz.com",
					"07777777777");
			Book book = new Book("G. Loy", "Musimathics Vol 1", "Non Fiction",
					"9780262516556");
			library.addMember(member);
			library.addBook(book);
			library.addLoan(member, book);

			assertDoesNotThrow(() -> library.removeLoan(member, book),
					"an error should have been thrown if loans is empty");

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	/**
	 * Verifies that displayExpiredLoans if expiryDate is null
	 */
	@Test
	@DisplayName("displayExpiredLoans throws if expiryDate is null")
	public void displayExpiredLoansNullExpiryDate() {
		try {
			Member member1 = new Member("Michael", "Shields", "abc@xyz.com",
					"07777777777");
			Book book1 = new Book("G. Loy", "Musimathics Vol 1", "Non Fiction",
					"9780262516556");
			library.addMember(member1);
			library.addBook(book1);
			library.addLoan(member1, book1);

			assertThrows(IllegalArgumentException.class,
					() -> library.displayExpiredLoans(null),
					"an error should have been thrown if expiryDate is null");
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	/**
	 * Verifies that displayExpiredLoans throws if no expired loans are found
	 */
	@Test
	@DisplayName("displayExpiredLoans throws if no expired loans found")
	public void displayExpiredLoansNonExpired() {
		try {
			Member member1 = new Member("Michael", "Shields", "abc@xyz.com",
					"07777777777");
			Member member2 = new Member("Jim", "Jones", "xyz@abc.com",
					"07777777777");
			Book book1 = new Book("G. Loy", "Musimathics Vol 1", "Non Fiction",
					"9780262516556");
			Book book2 = new Book("C. Negus", "Linux Bible", "Non Fiction",
					"9781119578888");
			library.addMember(member1);
			library.addMember(member2);
			library.addBook(book1);
			library.addBook(book2);
			library.addLoan(member1, book1);
			library.addLoan(member2, book2);
			int loanDurationInDays = 21;

			assertThrows(NoSuchElementException.class,
					() -> library.displayExpiredLoans(
							LocalDate.now().plusDays(loanDurationInDays)),
					"an error should have been thrown if no expiredLoans found");
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

	}

	/**
	 * Verifies that displayExpiredLoans throws if loans is empty
	 */
	@Test
	@DisplayName("displayExpiredLoans(LocalDate expiryDate) throws if loans is empty")
	public void displayExpiredLoansEmptyLoans() {
		assertThrows(IllegalStateException.class,
				() -> library.displayExpiredLoans(LocalDate.now()),
				"an error should have been thrown if loans collection is empty");
	}

	/**
	 * Verifies that displayLoans prints the details of all expired loans
	 */
	@Test
	@DisplayName("displayExpiredLoans prints details of all expired loans")
	public void displayExpiredLoans() {
		try {
			Member member1 = new Member("Michael", "Shields", "abc@xyz.com",
					"07777777777");
			Member member2 = new Member("Jim", "Jones", "xyz@abc.com",
					"07777777777");
			Book book1 = new Book("G. Loy", "Musimathics Vol 1", "Non Fiction",
					"9780262516556");
			Book book2 = new Book("C. Negus", "Linux Bible", "Non Fiction",
					"9781119578888");
			library.addMember(member1);
			library.addMember(member2);
			library.addBook(book1);
			library.addBook(book2);
			library.addLoan(member1, book1);
			library.addLoan(member2, book2);
			int loanDurationInDays = 21;
			LocalDate issueDate = LocalDate.now();
			LocalDate expiryDate = LocalDate.now().plusDays(loanDurationInDays);
			DateTimeFormatter formatter = DateTimeFormatter
					.ofPattern("d MMMM uuuu");
			// Create a test stream to accept a print stream
			ByteArrayOutputStream testStream = new ByteArrayOutputStream();

			// Redirect the standard out to the test stream via a new print
			// stream
			System.setOut(new PrintStream(testStream));

			library.displayExpiredLoans(
					LocalDate.now().plusDays(loanDurationInDays + 1));

			String expectedOutput = "Member:\r\nID Number:      "
					+ member1.getIDNumber() + "\r\nFirst Name:     "
					+ member1.getFirstName() + "\r\nLast Name:      "
					+ member1.getLastName() + "\r\nEmail:          "
					+ member1.getEmail() + "\r\nPhone Number:   "
					+ member1.getPhoneNumber()
					+ "\r\n\r\nBook:\r\nID Number:      " + book1.getIDNumber()
					+ "\r\nAuthor:         " + book1.getAuthor()
					+ "\r\nTitle:          " + book1.getTitle()
					+ "\r\nISBN Number:    " + book1.getISBNNumber()
					+ "\r\nGenre:          " + book1.getGenre()
					+ "\r\n\r\nIssued:         " + issueDate.format(formatter)
					+ "\r\nExpires:        " + expiryDate.format(formatter)
					+ "\r\n\r\nMember:\r\nID Number:      "
					+ member2.getIDNumber() + "\r\nFirst Name:     "
					+ member2.getFirstName() + "\r\nLast Name:      "
					+ member2.getLastName() + "\r\nEmail:          "
					+ member2.getEmail() + "\r\nPhone Number:   "
					+ member2.getPhoneNumber()
					+ "\r\n\r\nBook:\r\nID Number:      " + book2.getIDNumber()
					+ "\r\nAuthor:         " + book2.getAuthor()
					+ "\r\nTitle:          " + book2.getTitle()
					+ "\r\nISBN Number:    " + book2.getISBNNumber()
					+ "\r\nGenre:          " + book2.getGenre()
					+ "\r\n\r\nIssued:         " + issueDate.format(formatter)
					+ "\r\nExpires:        " + expiryDate.format(formatter);

			assertEquals(expectedOutput, testStream.toString().trim(),
					"the details of all expired loans should be printed as expected");

			// Reinstate the standard output stream
			System.setOut(System.out);

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	/**
	 * Verifies that displayAll throws an error if the provided collection is
	 * null
	 */
	@Test
	@DisplayName("displayAll throws if the provided collection is null")
	public void displayAllNullCollection() {
		assertThrows(IllegalArgumentException.class,
				() -> library.displayAll(null),
				"an error should be thrown if the collection is null");
	}

	/**
	 * Verifies that displayAll throws an error if the provided collection is
	 * empty
	 */
	@Test
	@DisplayName("displayAll throws if the provided collection is empty")
	public void displayAllEmptyCollection() {
		assertThrows(IllegalStateException.class,
				() -> library.displayAll(library.getBooks()),
				"an error should be thrown if the collection is empty");
	}

	/**
	 * /** Verifies that displayAll prints the details of all members when
	 * provided with the members collection
	 */
	@Test
	@DisplayName("displayAll prints details all members")
	public void displayAllMembers() {
		try {
			Member member1 = new Member("Michael", "Shields", "abc@xyz.com",
					"07777777777", 1);
			Member member2 = new Member("Jim", "Jones", "xyz@abc.com",
					"07777777777", 2);

			library.addMember(member1);
			library.addMember(member2);

			// Create a test stream to accept a print stream
			ByteArrayOutputStream testStream = new ByteArrayOutputStream();

			// Redirect the standard out to the test stream via a new print
			// stream
			System.setOut(new PrintStream(testStream));

			String expectedOutput = "ID Number:      1\r\n"
					+ "First Name:     Michael\r\n"
					+ "Last Name:      Shields\r\n"
					+ "Email:          abc@xyz.com\r\n"
					+ "Phone Number:   07777777777\r\n" + "\r\n"
					+ "ID Number:      2\r\n" + "First Name:     Jim\r\n"
					+ "Last Name:      Jones\r\n"
					+ "Email:          xyz@abc.com\r\n"
					+ "Phone Number:   07777777777";

			library.displayAll(library.getMembers());

			assertEquals(expectedOutput, testStream.toString().trim(),
					"the details of all member should be printed");

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

		// Reinstate the standard output stream
		System.setOut(System.out);
	}

	/**
	 * Verifies that displayAll prints the details of all books when provided
	 * with the books collection
	 */
	@Test
	@DisplayName("displayAll prints details of all books")
	public void displayAllBooks() {
		try {
			Book book1 = new Book("G. Loy", "Musimathics Vol 1", "Non Fiction",
					"9780262516556");
			Book book2 = new Book("C. Negus", "Linux Bible", "Non Fiction",
					"9781119578888");

			library.addBook(book1);
			library.addBook(book2);

			// Create a test stream to accept a print stream
			ByteArrayOutputStream testStream = new ByteArrayOutputStream();

			// Redirect the standard out to the test stream via a new print
			// stream
			System.setOut(new PrintStream(testStream));

			String expectedOutput = "ID Number:      " + book1.getIDNumber()
					+ "\r\n" + "Author:         " + book1.getAuthor() + "\r\n"
					+ "Title:          " + book1.getTitle() + "\r\n"
					+ "ISBN Number:    " + book1.getISBNNumber() + "\r\n"
					+ "Genre:          " + book1.getGenre() + "\r\n" + "\r\n"
					+ "ID Number:      " + book2.getIDNumber() + "\r\n"
					+ "Author:         " + book2.getAuthor() + "\r\n"
					+ "Title:          " + book2.getTitle() + "\r\n"
					+ "ISBN Number:    " + book2.getISBNNumber() + "\r\n"
					+ "Genre:          " + book2.getGenre();

			library.displayAll(library.getBooks());

			assertEquals(expectedOutput, testStream.toString().trim(),
					"the details of all books should be printed as expected");

			// Reinstate the standard output stream
			System.setOut(System.out);

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

	}

	/**
	 * Verifies that displayByID throws error if the provided collection is
	 * empty
	 */
	@Test
	@DisplayName("displayByID throws if the collection is empty")
	public void displayByIDEmptyCollection() {
		try {
			assertThrows(IllegalArgumentException.class,
					() -> library.displayByID(library.getMembers(), 1),
					"an error should be thrown if the collection is empty");

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	/**
	 * Verifies that displayByID throws error if the provided ID
	 * number is not valid
	 */
	@Test
	@DisplayName("displayByID throws if the ID number is not valid")
	public void displayByIDInvalidID() {
		try {
			Member member = new Member("Michael", "Shields", "abc@xyz.com",
					"07777777777");
			library.addMember(member);

			assertThrows(IllegalArgumentException.class,
					() -> library.displayByID(library.getMembers(), -1),
					"an error should be thrown if the ID number is invalid");

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	/**
	 * Verifies that displayByID throws error if the provided collection
	 * is null
	 */
	@Test
	@DisplayName("displayByID throws if the collection is null")
	public void displayByIDNullCollection() {
		try {
			assertThrows(IllegalArgumentException.class,
					() -> library.displayByID(null,1),
					"an error should be thrown if the collection is null");

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	/**
	 * Verifies that displayByID throws error if an object with the provided ID
	 * number is not found in the collection
	 */
	@Test
	@DisplayName("displayByID throws if the collection is empty")
	public void displayByIDNotFound() {
		try {
			int memberIDNumber = 1;
			int nonExistentIDNumber = 2;
			Member member = new Member("Michael", "Shields", "abc@xyz.com",
					"07777777777", memberIDNumber);

			library.addMember(member);

			assertThrows(NoSuchElementException.class,
					() -> library.displayByID(library.getMembers(),
							nonExistentIDNumber),
					"an error should be thrown if an object with the ID number is not found");

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	/**
	 * Verifies that displayByID prints the details of an object in the provided
	 * collection that has the provided ID Number
	 */
	@Test
	@DisplayName("displayByID prints details of any type that implements the Displayable interface")
	public void displayByID() {
		try {
			int memberIDNumber = 1;
			Member member = new Member("Michael", "Shields", "abc@xyz.com",
					"07777777777", memberIDNumber);
			library.addMember(member);

			// Create a test stream to accept a print stream
			ByteArrayOutputStream testStream = new ByteArrayOutputStream();

			// Redirect the standard out to the test stream via a new print
			// stream
			System.setOut(new PrintStream(testStream));

			String expectedOutput = "ID Number:      1\r\n"
					+ "First Name:     Michael\r\n"
					+ "Last Name:      Shields\r\n"
					+ "Email:          abc@xyz.com\r\n"
					+ "Phone Number:   07777777777";

			library.displayByID(library.getMembers(), memberIDNumber);

			assertEquals(expectedOutput, testStream.toString().trim(),
					"the objects details should have been printed as expected");

			// Reinstate the standard output stream
			System.setOut(System.out);

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
}
