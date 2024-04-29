package uk.co.michaelshields.assignment_1;

import java.util.NoSuchElementException;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import java.util.regex.Matcher;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Models a library system with functionality to manage collections of Books,
 * Members and Loans.
 * 
 * @see < href="Book.html">Book</a>
 * @see <a href="Member.html">Member</a>
 * @see <a href="Loan.html">Loan</a>
 * 
 * @author M. Shields
 * @version 1.0
 */
public class Library {
	/** A collection that stores the books that the library owns */
	private ArrayList<Book> books;
	/** A collection that keeps track of the the library's members */
	private ArrayList<Member> members;
	/**
	 * A collection that keeps track of which books are loaned and to which
	 * members
	 */
	private ArrayList<Loan> loans;
	/**
	 * An integer used to keep track of ID number sequencing 
	 * (see the Library's getNextIDNumber method)
	 */
	private int nextIDNumber;
	/** An integer that represents the duration of loans in days */
	private int loanDurationInDays;

	/**
	 * Constructor
	 * 
	 * @param - No parameters
	 */
	public Library() {
		// Assign an empty ArrayList to contain instances of the Book class
		books = new ArrayList<Book>();
		// Assign an empty ArrayList to contain instances of the Member class
		members = new ArrayList<Member>();
		// Assign an empty ArrayList to contain instances of the Loan class
		loans = new ArrayList<Loan>();
		// Set the starting point of the ID number sequencing
		// (initialised to 0, therefore the first ID Number issued will be 1
		// see the library's getNextIDNumber method)
		nextIDNumber = 0;
		// Set the loan duration to 21 days
		loanDurationInDays = 21;
	}

	/**
	 * Adds a member to the library's members collection<br>
	 * It also sets the member's ID number using the library's getNextIDNumber method<br>
	 * 
	 * @see <a href="Member.html">Member</a>
	 * 
	 * @param member - A member (Member)
	 * 
	 * @throws IllegalArgumentException - If null is provided or the provided member's 
	 *                                    email matches an existing member's email
	 */
	public void addMember(Member member) throws IllegalArgumentException {
		// Guard clause to prevent null values being added as members
		if (member == null) {
			throw new IllegalArgumentException("Cannot add null member");
		}
		// Guard clause to prevent members with duplicate email addresses being
		// added
		if (hasExistingEmail(member)) {
			throw new IllegalArgumentException(
					"A member with this email address is already registered!");
		}

		// Issues an ID Number to the member
		member.setIDNumber(getNextIDNumber());

		// Adds the member to the library's members collection
		getMembers().add(member);
	}

	/**
	 * Getter Method: Returns the members collection
	 * 
	 * @return members - The library's members (ArrayList<Member>)
	 */
	public ArrayList<Member> getMembers() {
		return members;
	}

	/**
	 * Getter Method: Returns the member with the provided ID number
	 * 
	 * @param idNumber - The ID number of the member (integer)
	 * 
	 * @return member  - The member of the library with the provided ID Number (Member)
	 * 
	 * @throws IllegalArgumentException - If the provided ID number is not valid
	 * 
	 * @throws NoSuchElementException - If the member is not found or the
	 *                                  members collection is empty
	 */
	public Member getMemberByID(int idNumber) throws IllegalArgumentException, NoSuchElementException {
		// Guard clause that prevents invalid ID numbers from being provided
		if (!isValidIDNumber(idNumber)) {
			throw new IllegalArgumentException("The ID number: " + idNumber + " is not valid");
		}
		
		// Guard clause to throw an error if the members collection
		// is empty, terminating the method
		if (getMembers().isEmpty()) {
			throw new NoSuchElementException(
					"The members collection is empty!");
		}

		// Search the members collection for the member
		// with the provided ID Number and return them
		for (Member member : getMembers()) {
			if (member.getIDNumber() == idNumber) {
				return member;
			}
		}

		throw new NoSuchElementException(
				"No member with that ID number was found!");
	}

	/**
	 * Displays the details of an object in the provided collection
	 *  that has the provided ID number
	 * 
	 * @param collection - A collection of any type that implements the Displayable and IDNumber interfaces
	 * 
	 * @param idNumber - The ID Number of the object of which details should be displayed (int)
	 *            
	 * @throws IllegalArgumentException - If the provided collection is null or empty
	 *                                    or the provided ID number is not valid
	 * 
	 * @throws NoSuchElementException - If an object with the provided ID number is not found
	 * 
	 * @see <a href="Displayable.html">Displayable</a>
	 * @see <a href="IDNumber.html">IDNumber</a>
	 */
	public <T extends IDNumber & Displayable> void displayByID(
	        ArrayList<T> collection, int idNumber) throws IllegalArgumentException, NoSuchElementException {
	    
		// Guard clause that prevents invalid ID numbers from being provided
		if (!isValidIDNumber(idNumber)) {
			throw new IllegalArgumentException("The ID number: " + idNumber + " is not valid");
		}
		
		// Guard clause to prevent collection with null value being provided
		if(collection == null) {
			throw new IllegalArgumentException("The provided collection is null");
		}
		
		// Guard clause to prevent empty collection being provided
		if (collection.isEmpty()) {
	        throw new IllegalArgumentException("Cannot display details, the collection is empty");
	    }
	    
	    // Get the class name of the objects in the collection
		// so that it can be inserted into the Exception message below
	    String className = collection.get(0).getClass().getSimpleName();

	    // Iterate the collection, searching for the object with 
	    // the ID number and return it if found
	    for (T object : collection) {
	        if (object.getIDNumber() == idNumber) {
	            object.displayDetails();
	            return;
	        }
	    }

	    // Throws exception if no object was found
	    throw new NoSuchElementException("No " + className + "s found with ID number " + idNumber);
	}

	/**
	 * Displays the details of every item in the provided collection
	 * 
	 * @param collection - A collection of any type that implements the Displayable interface
	 * 
	 * @throws IllegalArgumentException - If the provided collection is null or empty
	 * 
	 * @see <a href="Displayable">Displayable</a>
	 */
	public <T extends Displayable> void displayAll(ArrayList<T> collection)
			throws IllegalArgumentException {
		
		// Guard clause to prevent collections with null value being provided
		if(collection == null) {
			throw new IllegalArgumentException(
					"The provided collection is null");
		}
		
		// Guard clause to prevent empty collection from being provided
		if (collection.isEmpty()) {
			throw new IllegalStateException(
					"Cannot display details, the collection is empty");
		}

		// Iterate the collection and display the
		// details of each object
		for (T object : collection) {
			object.displayDetails();
			System.out.println();
		}
	}

	/**
	 * Displays the details of members whose surname contains the search query
	 * (case insensitive)
	 * 
	 * @throws NoSuchElementException - If the members collection is empty
	 * 
	 * @throws IllegalArgumentException - If the query is null, empty string or whitespace
	 * 
	 * @param query - The search term (String)
	 */
	public void displayMembersByLastNameQuery(String query)
			throws IllegalArgumentException, NoSuchElementException {
		
		// Guard clause to prevent running the remaining logic on empty collections
		if (getMembers().isEmpty()) {
			throw new IllegalStateException("The members collection is empty");
		}
		
		// Guard clause to prevent running remaining logic with empty search query
		if (query == null || query.isBlank()) {
			throw new IllegalArgumentException("Cannot search for member's without a search term");
		}

		// Generate the Regex pattern to match member's last names against
		Pattern pattern = Pattern.compile(query, Pattern.CASE_INSENSITIVE);
		// If no matches are found this remains false
		// triggering the error below
		boolean foundMatch = false;

		// Iterate the members collection and display the
		// details of any member whose last name matches the
		// search query regex pattern
		for (Member member : getMembers()) {
			Matcher matcher = pattern.matcher(member.getLastName());
			if (matcher.find()) {
				// Set found match to true
				// preventing the NoSuchElementException from
				// being thrown
				foundMatch = true;
				// Display the members details
				member.displayDetails();
			}
		}

		// If no matches were found, throw an error
		if (!foundMatch) {
			throw new NoSuchElementException(
					"No members with last names matching the query were found!");
		}
	}

	/**
	 * Removes the member with the provided ID number from the library
	 * 
	 * @param idNumber - The ID number of the member (int)
	 * 
	 * @throws IllegalArgumentException - If the provided ID number is invalid
	 * 
	 * @throws IllegalStateException - If the member has books that need returning 
	 * 
	 * @throws NoSuchElementException - If no member is found
	 */
	public void removeMemberByID(int idNumber) throws NoSuchElementException, IllegalStateException {
		// A Guard clause that throws an error if the members collection is
		// empty preventing the remaining logic from running
		if (members.isEmpty()) {
			throw new IllegalStateException("The members collection is empty");
		}

		// Guard clause that prevents invalid ID numbers from being provided
		if (!isValidIDNumber(idNumber)) {
			throw new IllegalArgumentException("The ID number: " + idNumber + " is not valid");
		}
		
		// Guard clause to check if the member has books to return
		if(hasBooksToReturn(idNumber)) {
			throw new IllegalStateException("Cannot remove member, they have books to return");
		}

		// Create an iterator for the members collection
		Iterator<Member> membersIterator = getMembers().iterator();

		// Iterate the members collection
		while (membersIterator.hasNext()) {
			Member member = membersIterator.next();
			// Remove the member if they have the provided
			// ID number
			if (member.getIDNumber() == idNumber) {
				getMembers().remove(member);
				return;
			}
		}

		// Throw an error if a member with the provided ID was not found
		throw new NoSuchElementException(
				"A member with that ID number was not found");
	}
	
	/**
	 * A utility method that checks whether the member has books to return
	 * 
	 * @param idNumber - The ID number of the member (int)
	 * 
	 * @return hasBooksToReturn - Indicates whether they have books to return (boolean)
	 */
	private boolean hasBooksToReturn(int idNumber) {
		// Guard clause to return false if the loans collection is empty
		// preventing the remaining logic from being executed
		if(getLoans().isEmpty()) {
			return false;
		}
		
		// Iterate the loans collection and 
		// return true if a loan associated with the 
		// provided member ID number is found
		for(Loan loan : getLoans()) {
			if(loan.getMember().getIDNumber() == idNumber) {
				return true;
			}
		}
		
		// Return false if no loans were found
		return false;
	}

	/**
	 * Adds a book to the library's books collection<br>
	 * It also sets the member's ID number using the library's getNextIDNumber
	 * method
	 * 
	 * @param book - The book to be added (Book)
	 * 
	 * @throws IllegalArgumentException - If the provided book is null 
	 * 
	 * @throws IllegalStateException - If the book's ISBN number matches 
	 *                                 an existing book's ISBN number
	 */
	public void addBook(Book book) throws IllegalArgumentException, IllegalStateException {
		// A Guard clause that prevents null values being added to the library's
		// books collection
		if (book == null) {
			throw new IllegalArgumentException("Cannot add book, book is null");
		}

		// A guard clause that prevents books with duplicate ISBN numbers from
		// being added to the library's books collection
		if (hasExistingISBNNumber(book)) {
			throw new IllegalStateException(
					"A book with this ISBN number already exists!");
		}

		// Assigns a sequential ID number to the book
		book.setIDNumber(getNextIDNumber());

		// Adds the book to the library's book collection
		getBooks().add(book);
	}

	/**
	 * Returns the books collection
	 * 
	 * @return books - The library's books collection (<ArrayList<Book>)
	 */
	public ArrayList<Book> getBooks() {
		return books;
	}

	/**
	 * Getter Method:
	 * Returns the book with the provided ID number
	 * 
	 * @param idNumber - The ID number of the book
	 * 
	 * @return book - The book with the provided ID number
	 * 
	 * @throws NoSuchElementException - If no book is found
	 * 
	 * @throws IllegalArgumentException If the provided ID number is invalid
	 */
	public Book getBookByID(int idNumber) throws NoSuchElementException {
		// Guard clause that prevents invalid ID numbers from being provided
		if (!isValidIDNumber(idNumber)) {
			throw new IllegalArgumentException("The ID number: " + idNumber + " is not valid");
		}
		
		// A Guard clause that throws an error if the books collection is empty
		// prevents the remaining logic from executing
		if (getBooks().isEmpty()) {
			throw new NoSuchElementException("The books collection is empty");
		}

		// Iterate  the books collection
		for (Book book : getBooks()) {
			// Return the book with the provided ID number
			if (book.getIDNumber() == idNumber) {
				return book;
			}
		}

		// An error is thrown if no book was found
		throw new NoSuchElementException(
				"No book with that ID number was found!");
	}

	/**
	 * Displays the details of the book with the provided ID number
	 * 
	 * @param idNumber - The ID number of the book (int)
	 * 
	 * @throws NoSuchElementException - If no book is found
	 */
	public void displayBookByID(int idNumber) {
		getBookByID(idNumber).displayDetails();
	}

	/**
	 * Removes the book with the provided ID number
	 * 
	 * @param idNumber - The ID number of the book to be removed (int)
	 * 
	 * @throws NoSuchElementException - If the book cannot be found
	 * 
	 * @throws IllegalArgumentException - If the book is on loan
	 */
	public void removeBookByID(int idNumber) {
		// Guard clause preventing the book from being removed
		// if it is out on loan
		if (isBookOnLoan(idNumber)) {
	        throw new IllegalStateException("Cannot remove book, the book is on loan");
	    }

		// Create an iterator for the books collection
	    Iterator<Book> booksIterator = getBooks().iterator();

	    // Iterate the books collection
	    while (booksIterator.hasNext()) {
	        Book book = booksIterator.next();
	        // Remove the book with the provided ID number
	        if (book.getIDNumber() == idNumber) {
	            booksIterator.remove();
	            return;
	        }
	    }

	    // throw exception if the book is not found
	    throw new NoSuchElementException("Cannot remove book, book not found");
	}

	/** A utility method that checks whether the book with the 
	* provided ID number is on loan
	* 
	* @param idNumber - The ID number of the book to be checked (int)
	* 
	* @return isBookOnLoan - Indicates whether the book is on loan (boolean)
	*/
	private boolean isBookOnLoan(int idNumber) {
	    // Guard clause that returns false if the books 
		// collection is empty 
		if (getBooks().isEmpty()) {
			return false;
		}
		
		// Iterate the books collection
		for (Loan loan : getLoans()) {
	        Book book = loan.getBook();
	        // Return true if the book with the provided ID number
	        // is found
	        if (book.getIDNumber() == idNumber) {
	            return true;
	        }
	    }
		
		// Return false if the book is not on loan
	    return false;
	}

	/**
	 * Displays the details of books where their title contains the search query
	 * (case insensitive)
	 * 
	 * @param query - The search term (String)
	 */
	public void displayBooksByTitleQuery(String query) {
		// Guard clause that throws error if the books collection is empty
		
		// Guard clause to prevent running the remaining logic on empty collections
		if (getBooks().isEmpty()) {
			throw new IllegalStateException("The members collection is empty");
		}
		
		// Guard clause preventing null being provided as query
		if (query == null) {
			throw new IllegalArgumentException("Cannot search books with null query");
		}
		
		// Guard clause preventing blank queries being provided
		if (query.isBlank()) {
			throw new IllegalArgumentException("Cannot search books with blank query");
		}
		
		// Create the regex pattern using the query
		Pattern pattern = Pattern.compile(query, Pattern.CASE_INSENSITIVE);

		// Create an iterator for the books collection
		Iterator<Book> booksIterator = getBooks().iterator();

		// Iterate the books collection 
		while (booksIterator.hasNext()) {
			// Check that the books title matches the query
			Book book = booksIterator.next();
			Matcher matcher = pattern.matcher(book.getTitle());
			Boolean isMatch = matcher.find();

			// Display the books details if it matches the query
			if (isMatch) {
				book.displayDetails();
				return;
			}
		}

		// Throw an error if no books were found
		throw new NoSuchElementException(
				"No books with titles matching the query were found!");
	}

	/**
	 * Adds a loan to the lib's loans collection
	 * 
	 * @param member - The member borrowing the book (Member)
	 *
	 * @param book - The book that is being borrowed (Book)
	 * 
	 * @throws IllegalArgumentException - If the book or member is null
	 * 
	 * @throws NoSuchElementException - If member or book do not exist on loan
	 */
	public void addLoan(Member member, Book book) throws NoSuchElementException, IllegalArgumentException {
		
		// Guard clause to prevent null books being added to the books collection
		if (book == null) {
			throw new IllegalArgumentException("Cannot create loan, book is null");
		}
		
		// Guard clause to prevent null members being added to the books collection
		if (member == null) {
			throw new IllegalArgumentException("Cannot create loan, member is null");
		}
		
		// Guard clause to prevent loan being created with unknown member
		if (!getMembers().contains(member)) {
			throw new NoSuchElementException(
					"Cannot add loan, non-existent member");
		}

		// Guard clause to prevent loan being created with unknown book
		if (!getBooks().contains(book)) {
			throw new NoSuchElementException(
					"Cannot add loan, non-existent book");
		}

		// Create an iterator for the loans collection
		Iterator<Loan> loansIterator = getLoans().iterator();

		// Iterate the loans collection
		// and check that the book is not already 
		// on loan
		while (loansIterator.hasNext()) {
			Loan loan = loansIterator.next();
			Book loanedBook = loan.getBook();
			
			if (book == loanedBook) {
				throw new IllegalStateException(
						"Cannot add loan, this book is already on loan");
			}
		}

		// Create the issue date and expiration date for the loan
		LocalDate issueDate = LocalDate.now();
		LocalDate expiryDate = LocalDate.now().plusDays(loanDurationInDays);

		// Create the loan object 
		Loan loan = new Loan(member, book, issueDate, expiryDate);

		// Add the loan to the loans collection
		getLoans().add(loan);
	}

	/**
	 * Getter method:
	 * Returns a loan with the provided member and book
	 * 
	 * @param member - The member who borrowed the book (Member)
	 * 
	 * @param book - The book that the member borrowed (Book)
	 * 
	 * @return loan - The loan with the provided member and book (Loan)
	 * 
	 * @throws IllegalArgumentException - If the member or book is null
	 * 
	 * @throws NoSuchElementException - If no loan was found 
	 */
	public Loan getLoan(Member member, Book book)
			throws NoSuchElementException {
		// Guard clause that prevents null book being provided
		if (book == null) {
			throw new IllegalArgumentException("Cannot get loan, book is null");
		}
		
		// Guard clause that prevents null member being provided
		if (member == null) {
			throw new IllegalArgumentException("Cannot get loan, member is null");
		}
 		
		// Guard clause that throws error if the loans collection is empty
		if (getLoans().isEmpty()) {
			throw new IllegalStateException("Loans collection is empty");
		}

		// Create an iterator for the loans collection
		Iterator<Loan> loansIterator = getLoans().iterator();

		// Iterate the loans collection
		while (loansIterator.hasNext()) {
			Loan loan = loansIterator.next();
			Member borrower = loan.getMember();
			Book loanedBook = loan.getBook();

			// If the provided member and book are associated with 
			// the current loan, return it
			if (member == borrower && book == loanedBook) {
				return loan;
			}
		}

		// Throw error if the no loan was found
		throw new NoSuchElementException("Loan not found");
	}

	/**
	 * Removes a loan with the provided member and book
	 * 
	 * @param member - The member who borrowed the book (Member)
	 * 
	 * @param book - The book that was borrowed (Book)
	 * 
	 * @throws IllegalArgumentException - If member of book is null
	 * 
	 * @throws NoSuchElementException - If loan is not found
	 * 
	 * @throws IllegalStateException - If the loans collection is empty
	 */
	public void removeLoan(Member member, Book book)
			throws IllegalArgumentException, NoSuchElementException {
		// Guard clause preventing null members being provided
		
		if (member == null ) {
			throw new IllegalArgumentException("Cannot remove loan, member is null");
		}
		
		// Guard clause preventing null books being provided
		if (book == null ) {
			throw new IllegalArgumentException("Cannot remove loan, book is null");
		}
		
		// Guard clause that throws error if loans collection is empty
		if (getLoans().isEmpty()) {
			throw new IllegalStateException(
					"Cannot remove loan, the loans collection is empty");
		}

		// Create an iterator for the loans collection
		Iterator<Loan> loansIterator = getLoans().iterator();

		// Iterate the loans collection
		while (loansIterator.hasNext()) {
			Loan loan = loansIterator.next();
			Member borrower = loan.getMember();
			Book loanedBook = loan.getBook();
			// If the loan is associated with the provided member 
			// and book, remove it and return
			if (member == borrower && book == loanedBook) {
				loans.remove(loan);
				return;
			}
		}

		// Throw error if no loan was found
		throw new NoSuchElementException(
				"Could not remove loan, loan not found!");
	}

	/**
	 * Returns the loans collection
	 * 
	 * @return loans - The whole loans collection
	 */
	public ArrayList<Loan> getLoans() {
		return loans;
	}

	/**
	 * Getter Method:
	 * Returns a collection of loans that have expiration dates exceeding the date
	 * provided
	 * 
	 * @param expiryDate - The cutoff date (LocalDate)
	 * 
	 * @throws IllegalArgumentException - If the expiryDate is null
	 * 
	 * @throws IllegalStateException - If the loans collection is empty
	 * 
	 * @throws NoSuchElementException - If the no expired loans were found
	 * 
	 * @return loans - A collection of expired loans (List<Loan>)
	 */
	public List<Loan> getExpiredLoans(LocalDate expiryDate)
			throws IllegalArgumentException, NoSuchElementException {

		// Guard clause preventing null expiryDate being provided
		if (expiryDate == null) {
			throw new IllegalArgumentException("Cannot get expired loans, expiryDate is null");
		}
		
		// Guard clause that throws and error if the loans collection is empty
		if (getLoans().isEmpty()) {
			throw new IllegalStateException("The loans collection is empty");
		}

		// Create a List of expired loans by streaming 
		// the ArrayList of loans through a filter
		// that only allows expired loans through
		List<Loan> expiredLoans = getLoans().stream()
				.filter(loan -> expiryDate.isAfter(loan.getExpiryDate()))
				.collect(Collectors.toList());

		// Throw an error if no loans expired loans were found
		if (expiredLoans.isEmpty()) {
			throw new NoSuchElementException("No expired loans found!");
		}

		// Return expired loans if any were found
		return expiredLoans;
	}

	/**
	 * Displays all loans that have an expiration date that exceed the date of
	 * expiration
	 * 
	 * @param expiryDate - The date of expiration (LocalDate)
	 * 
	 * @throws IllegalArgumentException - If the expiryDate is null
	 * 
	 * @throws IllegalStateException - If the loans collection is empty
	 */
	public void displayExpiredLoans(LocalDate expiryDate) throws IllegalArgumentException, IllegalStateException {
		// Guard clause preventing null expiryDate being provided
		if (expiryDate == null) {
			throw new IllegalArgumentException(
					"Cannot display loans, expiry date cannot be null");
		}

		// Guard clause that throws error if the loans collection is empty
		if (getLoans().isEmpty()) {
			throw new IllegalStateException(
					"Cannot display loans, loans collection is empty");
		}
		
		// Get the expired loans and display their details
		for (Loan expiredLoan : getExpiredLoans(expiryDate)) {
			expiredLoan.displayDetails();
		}
	}

	/**
	 * Getter Method:
	 * Returns whether the provided member's email address is a duplicate or
	 * unique
	 * 
	 * @param member - The member whose email will be checked (Member)
	 * 
	 * @return hasExistingEmail - Indicates whether the member's email 
	 * 							  is assigned to an existing member (boolean)
	 */
	private boolean hasExistingEmail(Member member) {
		// Iterate the members collection 
		for (Member existingMember : getMembers()) {
			boolean isExistingEmail = existingMember.getEmail()
					.equalsIgnoreCase(member.getEmail());

			// Return true if the member's email is already allocated
			if (isExistingEmail) {
				return true;
			}
		}

		// Return false if the email is new
		return false;
	}

	/**
	 * Utility method that checks whether the provided book has 
	 * an ISBN number that matches an existing book's ISBN number
	 * 
	 * @param book - The book whose ISBN number will be checked (Book)
	 * @return hasExistingISBNNumber - Indicates whether the book's 
	 * 								   ISBN number is already
	 *                                 assigned to an existing book
	 */
	private boolean hasExistingISBNNumber(Book book) {
		// Iterate the books collection 
		for (Book existingBook : getBooks()) {
			// Return true if the ISBN number is already allocated
			if (existingBook.getISBNNumber().equals(book.getISBNNumber())) {
				return true;
			}
		}

		// Return false if the ISBN number is new
		return false;
	}
	
	/**
	 * Getter Method: 
	 * Returns the duration in days of loans
	 * 
	 * @return loanDurationInDays - The duration of book loans (int)
	 * 
	 */
	
	public int getLoanDurationInDays() {
		return loanDurationInDays;
	}

	/**
	 * Get the next ID number (previously issued ID number by +1)
	 * 
	 * @return nextIdNumber The next ID number (int)
	 */
	public int getNextIDNumber() {
		// Increment the previous ID number
		nextIDNumber++;
	
		return nextIDNumber;
	}
	
	/**
	 * Utility method that checks whether ID numbers are valid
	 * @param idNumber - The ID number to be validated
	 * @return isValidIDNumber - Indicates whether the ID number is valid (boolean)
	 */
	private boolean isValidIDNumber(int idNumber) {
		if(idNumber >= 0) {
			return true;
		}
		
		return false;
	}
}
