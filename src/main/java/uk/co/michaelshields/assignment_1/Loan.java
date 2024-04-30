package uk.co.michaelshields.assignment_1;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * A class that models a loan, including the member borrowing the book, the
 * book, and an issue and expiration date.
 *
 * @author M. Shields
 * @version 1.0
 * @see <a href="Member.html">Member</a>
 * @see <a href="Book.html">Book</a>
 */
public class Loan implements Displayable {
	private Map<String, Object> details = new LinkedHashMap<>();

	/**
	 * Constructor:
	 * Instantiates Loan objects 
	 * @param member - The member (borrower)
	 *
	 * @param book - The book that is being borrowed (Book)
	 * 
	 * @param issueDate - The loan's date of issue (LocalDate)
	 * 
	 * @param expiryDate - The loan's date of expiration (LocalDate)
	 * 
	 * @throws IllegalArgumentException - If any of the provided parameters are invalid
	 */
	public Loan(Member member, Book book, LocalDate issueDate,
			LocalDate expiryDate) throws IllegalArgumentException {
		setMember(member);
		setBook(book);
		setIssueDate(issueDate);
		setExpiryDate(expiryDate);
	}

	/**
	 * Mutator Method:
	 * Sets the member who is borrowing the book
	 *
	 * @param member - The member who is borrowing the book (Member)
	 * 
	 * @throws IllegalArgumentException - If the member's ID number has not 
	 *                                    been set indicated by a
	 *                                    default ID Number of zero.
	 */
	private void setMember(Member member) throws IllegalArgumentException {
		// Guard clause preventing null members
		if (member == null) {
			throw new IllegalArgumentException(
					"Cannot create loan, member is null");
		}
		// Guard clause preventing members with no set ID number
		if (member.getIDNumber() == 0) {
			throw new IllegalArgumentException(
					"Cannot create loan, the member has not been issued with an ID number");
		}

		// Add the member to the loan
		details.put("Member", member);
	}

	/**
	 * Accessor Method:
	 * Returns the borrower of the book
	 *
	 * @return member - The member that borrowed the book (Member)
	 */
	public Member getMember() {
		return (Member) details.get("Member");
	}

	/**
	 * Mutator Method:
	 * Set's the loan's book
	 * 
	 * @param book - The book that is being borrowed (Book)
	 * 
	 * @throws IllegalArgumentException - If the book's ID number has not 
	 *                                    been set indicated by a
	 *                                    default ID Number of zero.
	 */
	private void setBook(Book book) throws IllegalArgumentException {
		if (book == null) {
			throw new IllegalArgumentException(
					"Cannot create loan, book is null");
		}
		if (book.getIDNumber() == 0) {
			throw new IllegalArgumentException(
					"Cannot create loan, the book has not been issued with an ID number");
		}

		// Set the loan's book
		details.put("Book", book);
	}

	/**
	 * Accessor Method:
	 * Returns the book that was loaned
	 *
	 * @return book - The book that was loaned (Book)
	 */
	public Book getBook() {
		return (Book) details.get("Book");
	}

	/**
	 * Mutator Method:
	 * Sets the loan's date of issue. The method is intentionally private, so
	 * that once a loan has been created its issue date is read only.
	 * 
	 * @param issueDate - The loan's date of issue (LocalDate)
	 * 
	 * @throws IllegalArgumentException - If the issue date is null
	 */
	private void setIssueDate(LocalDate issueDate) {
		// Guard clause preventing null issueDate
		if (issueDate == null) {
			throw new IllegalArgumentException(
					"Cannot create loan, cannot set issue date to null");
		}

		// Set the loan's issue date
		details.put("Issue Date", issueDate);
	}

	/**
	 * Accessor Method:
	 * Returns the loan's date of issue
	 *
	 * @return issueDate - The loan's date of issue (LocalDate)
	 */
	public LocalDate getIssueDate() {
		return (LocalDate) details.get("Issue Date");
	}

	/**
	 * Sets the loan's expiration date. The method is intentionally private, so
	 * that once a loan has been created its expiration date is read only.
	 * 
	 * @throws IllegalArgumentException - If the expiration date is null
	 */
	private void setExpiryDate(LocalDate expiryDate) {
		// Guard clause preventing null expiryDate
		if (expiryDate == null) {
			throw new IllegalArgumentException(
					"Cannot create loan, cannot set expiry date to null");
		}

		// Compare the dates to make sure that the issueDate is before the expiryDate
		int comparedDates = getIssueDate().compareTo(expiryDate);

		// Throw an error if the issueDate is after the expiryDate
		if (comparedDates >= 0) {
			throw new IllegalArgumentException(
					"Cannot create loan, the issue date cannot be on or after the expiry date");
		}

		// Set the loans expiration date
		details.put("Expiry Date", expiryDate);
	}

	/**
	 * Accessor Method:
	 * Returns the loan's expiration date
	 * 
	 * @return expiryDate - The loan's expiration date (LocalDate)
	 */
	public LocalDate getExpiryDate() {
		return (LocalDate) details.get("Expiry Date");
	}

	/**
	 * Displays the details of the loan
	 */
	@Override
	public void displayDetails() {
		// Get the loans member
		Member member = (Member) details.get("Member");
		// Get the loans book
		Book book = (Book) details.get("Book");
		
		// Create a DateTimeFormatter and configure it 
		// to display dates in the required format
		DateTimeFormatter formatter = DateTimeFormatter
				.ofPattern("d MMMM uuuu");

		// Display the loans details
		System.out.println("Member:");
		member.displayDetails();
		System.out.println();
		System.out.println("Book:");
		book.displayDetails();
		System.out.println();
		System.out
				.println("Issued:         " + getIssueDate().format(formatter));
		System.out.println(
				"Expires:        " + getExpiryDate().format(formatter));
		System.out.println();
	}
}
