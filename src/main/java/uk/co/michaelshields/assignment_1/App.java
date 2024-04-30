package uk.co.michaelshields.assignment_1;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.function.Consumer;

/**
 * <p>
 * A CLI based Application to demonstrate how the classes: Library, Member,
 * Book and Loan can be used to create a Library Management System.
 * </p>
 * <p>
 * The Application also makes use of the Scanner class of the Java.util library
 * and a PrintUtil class that contains methods for applying certain formatting
 * when output is printed to the Command Line.
 * </p>
 * 
 * @see <a href="PrintUtil.html">PrintUtil</a>
 * @author M. Shields
 * @version 1.0
 */
public class App {
	/*
	 * The Library System that the Application interacts with
	 * 
	 * @see <a href="Library.html">Library</a>
	 */
	private static Library library = new Library();

	/**
	 * An instance of the Scanner class that is used to read user input via the
	 * Command Line
	 */
	private static Scanner inputScanner = new Scanner(System.in);
	/**
	 * A string containing a generic prompt message that is displayed in each
	 * menu
	 */
	private static String genericPrompt = "Please select an option: ";
	/**
	 * A string that caches the current menu context facilitating navigation of
	 * the menu tree (see switch statement in the main method below)
	 */
	private static String currentMenu = "Main";

	// QUIT MENU SETUP

	/*
	 * The quit menu prompts the user to confirm that they would like to quit
	 * the application.
	 */

	// A HashMap where each key is a menu choice and each value is a lambda
	// function that is executed when a specific choice is selected
	private static Map<String, Consumer<Void>> quitMenuChoices = new LinkedHashMap<>();

	// Populate the HashMap of choices and associated functions
	static {
		quitMenuChoices.put("Yes", (Void) -> quit());
		quitMenuChoices.put("No", (Void) -> {
			return;
		});
	}

	// Create a Menu instance, providing the Menu's Header Text, Prompt Message
	// and the HashMap of choices
	private static Menu quitMenu = new Menu("QUIT MENU",
			"Are you sure you want to quit?", quitMenuChoices);

	// MEMBERS MENU SETUP

	/*
	 * The members menu prompts the user to select an action associated with the
	 * members collection, from a numbered list of choices.
	 */

	// Creates a HashMap where each key is a menu choice and each value is a
	// lambda function that is executed when a specific choice is selected
	private static Map<String, Consumer<Void>> membersMenuChoices = new LinkedHashMap<>();

	// Populate the HashMap of choices and associated functions
	static {
		membersMenuChoices.put("Add a Member", (Void) -> addMember());
		membersMenuChoices.put("Remove a Member", (Void) -> removeMember());
		membersMenuChoices.put("Display a Member by ID number",
				(Void) -> displayMember());
		membersMenuChoices.put("Display a Member by Surname Query",
				(Void) -> searchMembers());
		membersMenuChoices.put("Display all Members",
				(Void) -> displayAllMembers());
		membersMenuChoices.put("Go Back", (Void) -> currentMenu = "Main");
		membersMenuChoices.put("Quit", (Void) -> quitMenu.run());
	}

	// Create a Menu instance, providing the Menu's Header Text, Prompt Message
	// and HashMap of choices
	private static Menu membersMenu = new Menu("MEMBERS MENU", genericPrompt,
			membersMenuChoices);

	// BOOKS MENU SETUP

	/*
	 * The books menu prompts the user to select an action associated with the
	 * books collection, from a numbered list.
	 */

	// Creates a HashMap where each key is a menu choice and each value is a
	// lambda function that is executed when a specific choice is selected
	private static Map<String, Consumer<Void>> booksMenuChoices = new LinkedHashMap<>();

	// Populate the HashMap of choices and associated functions
	static {
		booksMenuChoices.put("Add a Book", (Void) -> addBook());
		booksMenuChoices.put("Remove a Book", (Void) -> removeBook());
		booksMenuChoices.put("Display a Book by ID number",
				(Void) -> displayBook());
		booksMenuChoices.put("Display a book by Title Query",
				(Void) -> searchBooks());
		booksMenuChoices.put("Display All Books", (Void) -> displayAllBooks());
		booksMenuChoices.put("Go Back", (Void) -> currentMenu = "Main");
		booksMenuChoices.put("Quit", (Void) -> quitMenu.run());
	}

	// Create a Menu instance, providing the Menu's Header Text, Prompt Message
	// and HashMap of choices
	private static Menu booksMenu = new Menu("BOOKS MENU", genericPrompt,
			booksMenuChoices);

	// LOANS MENU SETUP

	/*
	 * The loans menu prompts the user to select an action associated with the
	 * books collection, from a numbered list.
	 */

	// Creates a HashMap where each key is a menu choice and each value is a
	// lambda function that is executed when a specific choice is selected
	private static Map<String, Consumer<Void>> loansMenuChoices = new LinkedHashMap<>();

	// Populate the HashMap of choices and associated functions
	static {
		loansMenuChoices.put("Add a Loan", (Void) -> addLoan());
		loansMenuChoices.put("Remove a Loan", (Void) -> removeLoan());
		loansMenuChoices.put("Display All Loans", (Void) -> displayAllLoans());
		loansMenuChoices.put("Display Overdue Loans",
				(Void) -> displayExpiredLoans());
		loansMenuChoices.put("Go Back", (Void) -> currentMenu = "Main");
		loansMenuChoices.put("Quit", (Void) -> quitMenu.run());
	}

	// Create a Menu instance, providing the Menu's Header Text, Prompt Message
	// and HashMap of choices
	private static Menu loansMenu = new Menu("LOANS MENU", genericPrompt,
			loansMenuChoices);

	// MAIN MENU SETUP

	/*
	 * The main menu is the top-level menu, prompting the user to select a
	 * sub-menu from those defined above (members, books, loans or quit).
	 */

	// Creates a HashMap where each key is a menu choice and each value is a
	// lambda function that is executed when a specific choice is selected
	private static Map<String, Consumer<Void>> mainMenuChoices = new LinkedHashMap<>();

	// Populate the HashMap of choices and associated functions
	static {
		mainMenuChoices.put("Members", (Void) -> {
			currentMenu = "Members";
			membersMenu.run();
		});
		mainMenuChoices.put("Books", (Void) -> {
			currentMenu = "Books";
			booksMenu.run();
		});
		mainMenuChoices.put("Loans", (Void) -> {
			currentMenu = "Loans";
			loansMenu.run();
		});
		mainMenuChoices.put("Quit", (Void) -> quitMenu.run());
	}

	/*
	 * Create a Menu instance, providing the Menu's Header Text, Prompt Message
	 * and HashMap of choices
	 */
	private static Menu mainMenu = new Menu("MAIN MENU", genericPrompt,
			mainMenuChoices);

	// BOOK GENRE MENU SETUP

	/*
	 * The book genre menu prompts the user to select between a list of
	 * available genres (Fiction and Non-Fiction)
	 */

	// Creates a HashMap where each key is a menu choice and each value is a
	// lambda function that is executed when a specific choice is selected
	private static Map<String, Consumer<Void>> bookGenreMenuChoices = new LinkedHashMap<>();

	// Populate the HashMap of choices and associated functions
	static {
		bookGenreMenuChoices.put("Fiction", (Void) -> {
			return;
		});
		bookGenreMenuChoices.put("Non Fiction", (Void) -> {
			return;
		});
	}

	/*
	 * Create a Menu instance, providing the Menu's Header Text, Prompt Message
	 * and HashMap of choices
	 */
	private static Menu bookGenreMenu = new Menu("GENRE MENU",
			"Please choose the book's genre", bookGenreMenuChoices);

	// DATE SEARCH METHOD MENU SETUP

	/*
	 * The date search method menu, prompts the user to select a the desired
	 * method to be used to search the loans collection for expired loans.
	 */

	// Creates a HashMap where each key is a menu choice and each value is a
	// lambda function that is executed when a specific choice is selected
	private static Map<String, Consumer<Void>> dateSearchMethodMenuChoices = new LinkedHashMap<>();

	// Populate the HashMap of choices and associated functions
	static {
		dateSearchMethodMenuChoices.put("Today", (Void) -> {
			return;
		});
		dateSearchMethodMenuChoices.put("Custom Date", (Void) -> {
			return;
		});
	}

	/*
	 * Create a Menu instance, providing the Menu's Header Text, Prompt Message
	 * and HashMap of choices
	 */
	private static Menu dateSearchMethodMenu = new Menu("", genericPrompt,
			dateSearchMethodMenuChoices);

	/**
	 * A method that uses the addMember method of the Library class. The user
	 * is prompted for the member's details and adds the member to the library's
	 * members collection.
	 */
	private static void addMember() {
		// Print the menu header
		PrintUtil.menuHeader("ADD A MEMBER");

		// Member Data Prompter Setup

		// The array of prompts that will be displayed to the user
		// to request the required member details
		String[] memberDataPrompts = {"First Name: ", "Last Name: ",
				"Email Address: ", "Phone Number: "};

		// Create a prompter instance, passing in the array of prompts
		Prompter memberDataPrompter = new Prompter(memberDataPrompts);

		// Initiate the process of displaying prompts and collecting the
		// the user's input
		memberDataPrompter.issuePrompts();

		// Get the user's responses from the prompter
		List<String> memberInfo = memberDataPrompter.getResponses();

		// Extract and label the user's prompt responses
		String firstName = memberInfo.get(0);
		String lastName = memberInfo.get(1);
		String email = memberInfo.get(2);
		String phoneNumber = memberInfo.get(3);

		try {
			// Create and add the new member using the details
			// provided by the user
			Member newMember = new Member(firstName, lastName, email,
					phoneNumber);
			
			library.addMember(newMember);

			// Display confirmation to the user that the member has been added
			PrintUtil.successBanner("The member has been added");
			PrintUtil.infoBanner("MEMBERS DETAILS");
			newMember.displayDetails();

		} catch (Exception e) {
			// Handles cases where the provided email already exists
			PrintUtil.errorBanner(e.getMessage());

			// Displays the details of the member who has already
			// registered with the provided email address
			for (Member existingMember : library.getMembers()) {
				if (existingMember.getEmail().equalsIgnoreCase(email)) {
					existingMember.displayDetails();
					break;
				}
			}
		}
	}

	/**
	 * A method that requests the ID Number of the member to be removed and
	 * removes them if they are found
	 */
	private static void removeMember() {
		try {
			// Check that members exist, and abort with an error if there are no
			// members
			if (library.getMembers().isEmpty()) {
				throw new NoSuchElementException(
						"The members collection is empty");
			}

			// Display the menu context to the user and
			// prompt them for the ID number of the member to be removed (or 0
			// to go back to the
			// members menu)
			PrintUtil.menuHeader("REMOVE A MEMBER");
			PrintUtil.promptBanner(
					"Please input the ID number of the member you want to remove\nor input 0 to go back");

			int userInput = scanNextInt();

			// Go back to the members menu if 0 is selected by the user
			if (userInput == 0) {
				return;
			}

			// Prevent the user from entering invalid ID numbers
			if (userInput < 0) {
				throw new IllegalArgumentException(
						"All ID numbers are greater than 0, you entered "
								+ userInput);
			}

			// Rename the userInput for clarity
			int idNumber = userInput;

			// Attempt to find the member to be removed
			Member member = library.getMemberByID(idNumber);
			// Display the member's details to the user
			// and ask them to confirm that this is the correct member
			boolean shouldRemoveMember = isConfirmed(member);

			// If confirmed, remove the member
			if (shouldRemoveMember) {
				library.removeMemberByID(idNumber);
				PrintUtil.successBanner("The member has been removed");

				// Display confirmation that the member has been removed
				// and the member's details
				PrintUtil.infoBanner("REMOVED MEMBER");
				member.displayDetails();
			}

		} catch (Exception e) {
			// Displays any error messages to the user
			PrintUtil.errorBanner(e.getMessage());
		}
	}

	/**
	 * A routine that requests the ID Number of the member to be displayed and
	 * displays their details if they are found
	 */
	private static void displayMember() {
		try {
			// Check that members exist, and abort with an error if there are no
			// members
			if (library.getMembers().size() == 0) {
				throw new NoSuchElementException(
						"The members collection is empty");
			}

			// Display the menu context to the user and
			// prompt them for the ID number of the member to be displayed (or 0
			// to go back to the
			// members menu)
			PrintUtil.menuHeader("DISPLAY A MEMBER");
			PrintUtil.promptBanner(
					"Please input the member's ID number or 0 to go back");

			int userInput = scanNextInt();

			// Go back to the members menu if 0 is selected by the user
			if (userInput == 0) {
				return;
			}

			// Prevent the user from entering invalid ID numbers
			if (userInput < 0) {
				throw new IllegalArgumentException(
						"All ID numbers are greater than 0, you entered "
								+ userInput);
			}

			// Rename userInput for clarity
			int idNumber = userInput;

			// Check that the member exists before attempting to display their
			// details
			library.getMemberByID(idNumber);

			PrintUtil.infoBanner("MEMBER DETAILS");
			library.displayByID(library.getMembers(), idNumber);

		} catch (Exception e) {
			// Displays any error messages to the user
			PrintUtil.errorBanner(e.getMessage());
		}
	}

	/**
	 * A routine that displays the details of all members
	 */
	private static void displayAllMembers() {
		try {
			// Check that members exist, and abort with an error if there are no
			// members
			if (library.getMembers().size() == 0) {
				throw new NoSuchElementException(
						"The members collection is empty");
			}

			// Display each and every member's details to the user
			PrintUtil.infoBanner("ALL MEMBERS");
			library.displayAll(library.getMembers());
		} catch (Exception e) {
			// Displays any error messages to the user
			PrintUtil.errorBanner(e.getMessage());
		}
	}

	/**
	 * A routine that requests query string from the user to be used to search
	 * for members with last names that include the (non-empty) query string and
	 * displays their details
	 */
	private static void searchMembers() {
		try {
			// Check that members exist, and abort with an error if there are no
			// members
			if (library.getMembers().size() == 0) {
				throw new NoSuchElementException(
						"The members collection is empty");
			}

			// Prompt the user for, and capture the query string
			PrintUtil.menuHeader("SEARCH AND DISPLAY MEMBERS");
			PrintUtil.promptBanner("To search for and display members"
					+ "\nby surname search query"
					+ "\nplease enter a search term: ");
			String query = scanNextLine();

			// Display members where the last name contains the query string
			// (case insensitive)
			library.displayMembersByLastNameQuery(query);

		} catch (Exception e) {
			// Displays any error messages to the user
			PrintUtil.errorBanner(e.getMessage());
		}
	}

	/**
	 * A routine that requests details for the new book and adds the book to the
	 * library's members collection. The book's ISBN Number must be unique.
	 */
	private static void addBook() {
		// Display the menu context to the user and
		// prompt the user for the details of the book
		// to be added
		PrintUtil.menuHeader("ADD A BOOK");

		// The list of prompts that will be displayed to the user
		String[] bookInfoPrompts = {"Author: ", "Title: ", "ISBN Number: "};

		// Create a prompter, passing in the prompts
		Prompter bookInfoPrompt = new Prompter(bookInfoPrompts);

		// Initiate the process of issuing prompts and collecting the
		// the user's input
		bookInfoPrompt.issuePrompts();

		// Get the user's responses from the prompter
		List<String> bookInfo = bookInfoPrompt.getResponses();

		// Extract and label each response
		String author = bookInfo.get(0);
		String title = bookInfo.get(1);
		String isbnNumber = bookInfo.get(2);

		// A variable that will be assign a book if it already exists
		// (determined by ISBN number)
		Book existingBook = null;

		try {
			// Search the books collection and check that the
			// ISBN number exists or not
			for (Book book : library.getBooks()) {
				// If the ISBN number exists
				// capture the existing book object and terminate the process
				// with
				// an error
				if (book.getISBNNumber() == isbnNumber) {
					existingBook = book;
					throw new IllegalArgumentException(
							"A book with that ISBN number already exists");
				}
			}

		} catch (Exception e) {
			// Display the error message and the existing books details to the
			// user
			PrintUtil.errorBanner(e.getMessage());
			PrintUtil.infoBanner("EXISTING BOOK DETAILS");
			library.displayByID(library.getBooks(), existingBook.getIDNumber());
		}

		try {
			// Display the book genre menu to the user
			bookGenreMenu.run();

			// Get the chosen genre
			String genre = bookGenreMenu.getChosenKey();

			// Create and add the book
			Book newBook = new Book(author, title, genre, isbnNumber);
			library.addBook(newBook);

			// Display confirmation and book's details to the user
			PrintUtil.infoBanner("The following book has been added: ");
			library.getBookByID(newBook.getIDNumber()).displayDetails();
		} catch (Exception e) {
			// Displays any error messages to the user
			PrintUtil.errorBanner(e.getMessage());
		}
	}

	/**
	 * A routine that removes the book with the provided ID number, if the book
	 * is found
	 */
	private static void removeBook() {
		try {
			// Check that books exist, and abort with an error if there are no
			// books
			if (library.getBooks().size() == 0) {
				throw new NoSuchElementException(
						"The books collection is empty");
			}

			// Display the menu context to the user
			// and prompt the user for a book ID Number (or 0 to go back a step)
			PrintUtil.menuHeader("REMOVE A BOOK");
			PrintUtil.promptBanner(
					"Please input the ID number of the book or 0 to go back");

			// Read the users input
			int userInput = scanNextInt();

			// Prevent the user from entering invalid ID numbers
			if (userInput < 0) {
				throw new IllegalArgumentException(
						"All ID numbers are greater than 0, you entered "
								+ userInput);
			}

			// Go back if the user selected 0
			if (userInput == 0) {
				return;
			}

			// Rename user input for clarity
			int idNumber = userInput;

			// Find the book to be removed
			Book bookToRemove = library.getBookByID(idNumber);

			// Prompt the user to confirm the book
			boolean shouldRemoveBook = isConfirmed(bookToRemove);

			// If confirmed, remove the book and display confirmation
			// and the books details
			if (shouldRemoveBook) {
				library.removeBookByID(bookToRemove.getIDNumber());
				PrintUtil.successBanner("The book has been removed");
				PrintUtil.infoBanner("REMOVED BOOK");
				bookToRemove.displayDetails();
			}
		} catch (Exception e) {
			// Displays any error messages to the user
			PrintUtil.errorBanner(e.getMessage());
		}
	}

	/**
	 * A routine that displays to the user, details of the book with the
	 * provided ID number
	 */
	private static void displayBook() {
		try {
			// Check that books exist, and abort with an error if there are no
			// books
			if (library.getBooks().size() == 0) {
				throw new NoSuchElementException(
						"The books collection is empty");
			}

			// Display the menu context to the user
			// and prompt the user for a book ID Number (or 0 to go back a step)
			PrintUtil.menuHeader("DISPLAY A BOOK");
			PrintUtil.promptBanner(
					"Please input the book's ID number or 0 to go back");

			// Capture the user's input
			int userInput = scanNextInt();

			// If the user selected 0, go back
			if (userInput == 0) {
				return;
			}

			// Prevent the user from entering invalid ID numbers
			if (userInput < 0) {
				throw new IllegalArgumentException(
						"All ID numbers are greater than 0, you entered "
								+ userInput);
			}

			// rename user input for clarity
			int idNumber = userInput;

			// Display the book's details
			PrintUtil.infoBanner("BOOK DETAILS");
			library.getBookByID(idNumber).displayDetails();

		} catch (Exception e) {
			// Displays any error messages to the user
			PrintUtil.errorBanner(e.getMessage());
		}
	}

	/**
	 * A routine that prompts the user for a search query string, and displays
	 * any books where the title includes the query string
	 */
	private static void searchBooks() {
		try {
			// Check that books exist, and abort with an error if there are no
			// books
			if (library.getBooks().size() == 0) {
				throw new NoSuchElementException(
						"The books collection is empty");
			}

			// Display the menu context to the user
			// and prompt the user for a search term (or 0 to go back a step)
			PrintUtil.menuHeader("SEARCH AND DISPLAY BOOKS");
			PrintUtil.promptBanner("To search for and display books"
					+ "\nby title search query"
					+ "\nplease enter a search term: ");

			// Capture the user's input
			String query = scanNextLine();

			// Display all books that match the query
			library.displayBooksByTitleQuery(query);

		} catch (Exception e) {
			// Displays any error messages to the user
			PrintUtil.errorBanner(e.getMessage());
		}
	}

	/**
	 * A routine that displays the details of all books to the user
	 */
	private static void displayAllBooks() {
		// Check that books exist, and abort with an error if there are no
		// books
		try {
			if (library.getBooks().isEmpty()) {
				throw new NoSuchElementException(
						"The members collection is empty");
			}

			// Display menu context and all book details to the user
			PrintUtil.infoBanner("ALL BOOKS");
			library.displayAll(library.getBooks());
		} catch (Exception e) {
			// Displays any error messages to the user
			PrintUtil.errorBanner(e.getMessage());
		}
	}

	/**
	 * A routine that requests details for the new loan and adds the loan to the
	 * library's loan collection.
	 */
	private static void addLoan() {
		// Check that books exist, and abort with an error if there are no
		// books
		if (library.getBooks().isEmpty()) {
			PrintUtil.errorBanner("The books collection is empty");
			return;
		}

		// Check that members exist, and abort with an error if there are no
		// members
		if (library.getMembers().isEmpty()) {
			PrintUtil.errorBanner("The members collection is empty");
			return;
		}

		try {
			// Display menu context to the user and prompt the user for
			// a member ID number
			PrintUtil.menuHeader("ADD A LOAN");
			PrintUtil.promptBanner(
					"Please input the member's ID number or 0 to go back");

			// Capture the user's input
			int userInput = scanNextInt();

			// If the user selects 0, go back a step
			if (userInput == 0) {
				return;
			}

			// Prevent the user from entering invalid ID numbers
			if (userInput < 0) {
				throw new IllegalArgumentException(
						"All ID numbers are greater than 0, you entered "
								+ userInput);
			}

			// Rename user input for clarity
			int idNumber = userInput;

			Member member = library.getMemberByID(idNumber);
			boolean isConfirmedMember = isConfirmed(member);

			if (!isConfirmedMember) {
				return;
			}

			// Prompt the user for the book's ID number
			PrintUtil.promptBanner(
					"Please input the book's ID number or 0 to go back");

			userInput = scanNextInt();

			// If the user selected 0, go back a step
			if (userInput == 0) {
				return;
			}

			// Prevent the user from entering invalid ID numbers
			if (userInput < 0) {
				throw new IllegalArgumentException(
						"All ID numbers are greater than 0, you entered "
								+ userInput);
			}

			// Rename user input for clarity
			idNumber = userInput;

			// Attempt to get the book
			Book book = library.getBookByID(idNumber);

			// Request confirmation from the user
			boolean isConfirmedBook = isConfirmed(book);

			// If not confirmed go back
			if (!isConfirmedBook) {
				return;
			}

			// Create and add the loan
			library.addLoan(member, book);
			// Display confirmation to the user and display the loans details
			PrintUtil.infoBanner("The following loan has been added: ");
			library.getLoan(member, book).displayDetails();

		} catch (Exception e) {
			// Displays any error messages to the user
			PrintUtil.errorBanner(e.getMessage());
		}
	}

	/*
	 * A routine that prompts the user for the details of the loan to be removed
	 * and removes it if it is found
	 */
	private static void removeLoan() {
		try {
			// Check that loans exist, and abort with an error if there are no
			// loans
			if (library.getLoans().isEmpty()) {
				throw new NoSuchElementException(
						"The loans collection is empty");
			}

			// Display the menu context to the user and prompt the
			// user for the ID number of the loan's member
			PrintUtil.menuHeader("REMOVE A LOAN");
			PrintUtil.promptBanner(
					"Please enter the ID number of the member who borrowed the book\nor 0 to go back");

			// Capture the user's input
			int userInput = scanNextInt();

			// If the user selected 0, go back a step
			if (userInput == 0) {
				return;
			}

			// Prevent the user from entering invalid ID numbers
			if (userInput < 0) {
				throw new IllegalArgumentException(
						"All ID numbers are greater than 0, you entered "
								+ userInput);
			}

			// Rename user input for clarity
			int idNumber = userInput;

			// Attempt the get the user
			Member borrower = library.getMemberByID(idNumber);

			// Prompt the user for the book's ID number
			PrintUtil.promptBanner(
					"Please enter the ID number of the book that was borrowed \nor 0 to go back");

			// Capture the user's input
			userInput = scanNextInt();

			// Prevent the user from entering invalid ID numbers
			if (userInput < 0) {
				throw new IllegalArgumentException(
						"All ID numbers are greater than 0, you entered "
								+ userInput);
			}

			// Rename user input for clarity
			idNumber = userInput;

			// Attempt to get the book
			Book borrowedBook = library.getBookByID(idNumber);

			// Attempt to get the loan to be removed
			Loan loanToRemove = library.getLoan(borrower, borrowedBook);

			// Prompt the user for confirmation
			boolean shouldRemoveLoan = isConfirmed(loanToRemove);

			// If confirmed, remove the loan and display confirmation to the
			// user
			// and the details of the removed loan
			if (shouldRemoveLoan) {
				library.removeLoan(borrower, borrowedBook);
				PrintUtil.successBanner("The loan has been removed");
				PrintUtil.infoBanner("REMOVED LOAN");
				loanToRemove.displayDetails();
			}
		} catch (Exception e) {
			// Displays any error messages to the user
			PrintUtil.errorBanner(e.getMessage());
		}
	}

	/**
	 * A routine that displays the details of all loans to the user
	 */
	private static void displayAllLoans() {
		try {
			// Check that loans exist, and abort with an error if there are no
			// loans
			if (library.getLoans().size() == 0) {
				throw new NoSuchElementException(
						"The loans collection is empty");
			}

			// Display the details of all loans to the user
			PrintUtil.infoBanner("ALL LOANS");
			library.displayAll(library.getLoans());
		} catch (Exception e) {
			// Displays any error messages to the user
			PrintUtil.errorBanner(e.getMessage());
		}
	}

	/**
	 * A routine that prompts the user for the search criteria and displays the
	 * details of all matching loans to the user
	 */
	private static void displayExpiredLoans() {
		try {
			// Check that loans exist, and abort with an error if there are no
			// loans
			if (library.getLoans().size() == 0) {
				throw new NoSuchElementException(
						"The loans collection is empty");
			}

			// Display the menu context to the user
			PrintUtil.menuHeader("DISPLAY OVERDUE LOANS");

			// Display the search method menu to the user
			// and request their input
			dateSearchMethodMenu.run();

			// Determine the user's menu choice
			String choice = dateSearchMethodMenu.getChosenKey();

			// Initialise expiryDate to default of current date
			LocalDate expiryDate = LocalDate.now();

			// User chose to use a custom date
			// prompt the user for the custom date
			if (choice == "Custom Date") {
				PrintUtil.promptBanner(
						"Please input a valid date in the format: dd-MM-yyyy");

				// Capture the users input
				String dateStr = inputScanner.nextLine();

				try {
					// Attempt to parse the date
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
					expiryDate = LocalDate.parse(dateStr, formatter);
				} catch (Exception e) {
					// Display the parse failure error to the user and go back a
					// step
					PrintUtil.errorBanner(
							"The provided date: " + dateStr + "is not valid");
					return;
				}
			}

			// Get the list of loans that have expired
			// based on the expiration date
			List<Loan> expiredLoans = library.getExpiredLoans(expiryDate);

			// Display the expired loans to the user
			PrintUtil.infoBanner("EXPIRED LOANS");
			library.displayAll((ArrayList<Loan>) expiredLoans);

		} catch (Exception e) {
			// Displays any error messages to the user
			PrintUtil.errorBanner(e.getMessage());
		}
	}

	/**
	 * A helper function that handles capturing user input / of the integer type
	 */
	private static int scanNextInt() {
		int nextInt = -1;
		try {
			nextInt = Integer.parseInt(inputScanner.nextLine());
		} catch (NumberFormatException e) {
			PrintUtil.errorBanner("Please enter a valid number");
		}
		return nextInt;
	}

	/**
	 * A routine that handles the capturing of a single line of user input
	 */
	private static String scanNextLine() {
		String nextLine = inputScanner.nextLine();
		return nextLine;
	}

	/**
	 * 
	 * @param <T>
	 *            - Any type that implements the {@code Displayable} interface
	 * @see <a href="Displayable.html>Displayable Interface</a>
	 * @param object
	 *            - The object that is to be confirmed
	 * @param objectType - A string
	 * @return isConfirmed - Indicates that the correct object has 
	 *                       been confirmed by the user
	 */
	private static <T> boolean isConfirmed(T object) {
		// Determine the objects class name
		// so that it can be dynamically inserted into 
		// the confirmation prompt
		String className = object.getClass().getSimpleName();

		// Display the a objects details to the user
		PrintUtil.infoBanner(className.toUpperCase() + " DETAILS");
		((Displayable) object).displayDetails();

		// Request confirmation from the user
		String confirmMenuPrompt = "Is this the correct " + className + "?";
		Map<String, Consumer<Void>> confirmMenuChoices = new LinkedHashMap<>();
		
		// Empty lambdas as no actions are required 
		// when a choice is made
		confirmMenuChoices.put("Yes", (Void) -> {
		});
		confirmMenuChoices.put("No", (Void) -> {
		});
		
		Menu confirmMenu = new Menu("", confirmMenuPrompt, confirmMenuChoices);
		
		confirmMenu.run();
		
		// Get the user's choice
		String choice = confirmMenu.getChosenKey();

		// Return true if the use has confirmed the choice
		if (choice == "Yes") {
			return true;
		}
		
		return false;
	}

	/**
	 * A method that quits the program
	 */
	private static void quit() {
		PrintUtil.infoBanner("Thank you and Goodbye");
		inputScanner.close();
		System.exit(0);
	}

	/**
	 * The main method of the application
	 * 
	 * @param args - Not used
	 */
	public static void main(String[] args) {
		// Display a welcome message to the user
		PrintUtil.infoBanner(
				"WELCOME TO THE MIDSOMER MIDGET LIBRARY ADMIN SYSTEM");
		// An infinite loop that keeps the application running
		while (true) {
			try {
				// A switch statement that manages menu context switching
				// based on the currentMenu variable
				switch (currentMenu) {
					case "Main" :
						mainMenu.run();
						break;
					case "Members" :
						membersMenu.run();
						break;
					case "Books" :
						booksMenu.run();
						break;
					case "Loans" :
						loansMenu.run();
						break;
					default :
						mainMenu.run();
				}
			} catch (Exception e) {
				// A "catch all" to prevent the app from crashing
				// takes the user back to the main menu
				PrintUtil.errorBanner(e.getMessage());
				currentMenu = "Main";
			}
		}
	}
}
