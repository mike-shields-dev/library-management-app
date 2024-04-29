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
	 * Constructor
	 *
	 * @param member
	 *            The member (borrower)
	 * @param book
	 *            The book that being borrowed
	 * @param issueDate
	 *            The loan's date of issue
	 * @param expiryDate
	 *            The loan's date of expiration
	 * @throws IllegalArgumentException
	 *             if any of the provided parameters are invalid
	 */
	public Loan(Member member, Book book, LocalDate issueDate,
			LocalDate expiryDate) {
		setMember(member);
		setBook(book);
		setIssueDate(issueDate);
		setExpiryDate(expiryDate);
	}

	/**
	 * Sets the member who is borrowing the book
	 *
	 * @param member
	 *            The member who is borrowing the book
	 * @throws IllegalArgumentException
	 *             if the member's ID number has not been set indicated by a
	 *             default ID Number of zero.
	 */
	private void setMember(Member member) {
		if (member == null) {
			throw new IllegalArgumentException(
					"Cannot create loan, member is null");
		}
		if (member.getIDNumber() == 0) {
			throw new IllegalArgumentException(
					"Cannot create loan, the member has not been issued with an ID number");
		}

		details.put("Member", member);
	}

	/**
	 * Returns the borrower of the book
	 *
	 * @return The member that borrowed the book
	 */
	public Member getMember() {
		return (Member) details.get("Member");
	}

	/**
	 *
	 * @param book
	 *            The book that is being borrowed
	 * @throws IllegalArgumentException
	 *             if the book's ID number has not been set indicated by a
	 *             default ID Number of zero.
	 */
	private void setBook(Book book) {
		if (book == null) {
			throw new IllegalArgumentException(
					"Cannot create loan, book is null");
		}
		if (book.getIDNumber() == 0) {
			throw new IllegalArgumentException(
					"Cannot create loan, the book has not been issued with an ID number");
		}

		details.put("Book", book);
	}

	/**
	 * Returns the book that was loaned
	 *
	 * @return The book that was loaned
	 */
	public Book getBook() {
		return (Book) details.get("Book");
	}

	/**
	 * Sets the loan's date of issue. The method is intentionally private, so
	 * that once a loan has been created its issue date is read only.
	 * 
	 * @param issueDate
	 *            The loan's date of issue
	 */
	private void setIssueDate(LocalDate issueDate) {
		if (issueDate == null) {
			throw new IllegalArgumentException(
					"Cannot create loan, cannot set issue date to null");
		}

		details.put("Issue Date", issueDate);
	}

	/**
	 * Returns the loan's date of issue
	 *
	 * @return The loan's date of issue
	 */
	public LocalDate getIssueDate() {
		return (LocalDate) details.get("Issue Date");
	}

	/**
	 * Sets the loan's expiration date. The method is intentionally private, so
	 * that once a loan has been created its expiration date is read only.
	 * 
	 * @param expiryDate
	 *            The expiration date
	 */
	private void setExpiryDate(LocalDate expiryDate) {
		if (expiryDate == null) {
			throw new IllegalArgumentException(
					"Cannot create loan, cannot set expiry date to null");
		}

		int comparedDates = getIssueDate().compareTo(expiryDate);

		if (comparedDates >= 0) {
			throw new IllegalArgumentException(
					"Cannot create loan, the issue date cannot be on or after the expiry date");
		}

		details.put("Expiry Date", expiryDate);
	}

	/**
	 * Returns the loan's expiration date
	 * 
	 * @return The loan's expiration date
	 */
	public LocalDate getExpiryDate() {
		return (LocalDate) details.get("Expiry Date");
	}

	/**
	 * Displays the details of the loan
	 */
	@Override
	public void displayDetails() {
		Member member = (Member) details.get("Member");
		Book book = (Book) details.get("Book");
		DateTimeFormatter formatter = DateTimeFormatter
				.ofPattern("d MMMM uuuu");

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
