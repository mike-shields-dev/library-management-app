package uk.co.michaelshields.assignment_1;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.validator.routines.ISBNValidator;

/**
 * A class that models a book and it's details:
 * <ul>
 * <li>Author</li>
 * <li>Title</li>
 * <li>Genre</li>
 * <li>ISBN Number</li>
 * <li>ID Number</li>
 * </ul>
 * 
 * 
 * This class utilises the <a href=
 * "https://commons.apache.org/proper/commons-validator/apidocs/org/apache/commons/validator/routines/ISBNValidator.html">ISBNValidator</a>
 * class of the <b>org.apache.commons.validator.routines</b> package
 * 
 * @see <a href=
 *      "https://commons.apache.org/proper/commons-validator/apidocs/org/apache/commons/validator/routines/package-summary.html">org.apache.commons.validator.routines</a>
 * @author M. Shields
 * @version 1.0
 */
public class Book implements Displayable, IDNumber {
	private Map<String, Object> details = new LinkedHashMap<>();

	/**
	 * Constructor:
	 * 
	 * Instantiates books with a default ID number of zero.
	 * 
	 * @param author
	 *            The book's author (String)
	 * @param title
	 *            The book's title (String)
	 * @param genre
	 *            The book's genre (String)
	 * @param isbnNumber
	 *            The book's ISBN number (int)
	 * @throws IllegalArgumentException
	 *             - If any parameters are invalid, in accordance with the Book's
	 *             setter methods
	 */
	public Book(String author, String title, String genre, String isbnNumber) throws IllegalArgumentException {
		setIDNumber(0);
		setAuthor(author);
		setTitle(title);
		setISBNNumber(isbnNumber);
		setGenre(genre);
	}

	/**
	 * Constructor: 
	 * 
	 * Instantiates books with the provided ID Number.
	 * 
	 * @param author
	 *            The book's author (String)
	 * @param title
	 *            The book's title (String)
	 * @param genre
	 *            The book's genre (String)
	 * @param isbnNumber
	 *            The book's ISBN number (String)
	 * @param idNumber
	 *            The book's idNumber (int)
	 * @throws IllegalArgumentException
	 *             -If any parameters are invalid, in accordance with the Book's
	 *             setter methods
	 */
	public Book(String author, String title, String genre, String isbnNumber,
			int idNumber) throws IllegalArgumentException {
		setIDNumber(idNumber);
		setAuthor(author);
		setTitle(title);
		setISBNNumber(isbnNumber);
		setGenre(genre);
	}

	/**
	 * Setter Method: Sets the book's ISBN number
	 * 
	 * @param isbnNumber - The book's ISBN number (String)
	 * @throws IllegalArgumentException -
	 *             If the parameter is null, empty string, whitespace or fails
	 *             ISBN number validation
	 * @see <a href=
	 *      "https://commons.apache.org/proper/commons-validator/apidocs/org/apache/commons/validator/routines/ISBNValidator.html">ISBNValidator</a>
	 * 
	 */
	public void setISBNNumber(String isbnNumber) throws IllegalArgumentException {
		// Guard clause to check for null, empty string or whitespace
		if (isbnNumber == null || isbnNumber.isBlank()) {
			throw new IllegalArgumentException(
					"The book must have an ISBN number");
		}

		// Create an instance of the ISBNValidator class
		ISBNValidator validator = ISBNValidator.getInstance();
		
		// Guard clause to check that ISBN's format is valid 
		if (!validator.isValid(isbnNumber)) {
			throw new IllegalArgumentException(
					"The provided ISBN number is not valid");
		}
		
		// Adds the book
		// trims any surplus whitespace
		details.put("ISBN Number", isbnNumber.trim());
	}

	/**
	 * Getter Method: Returns the book's ISBN number
	 * 
	 * @return isbnNUmber - The book's ISBN number (String)
	 */
	public String getISBNNumber() {
		return (String) details.get("ISBN Number");
	}

	/**
	 * Setter Method: Sets the book's author
	 * 
	 * @param author - The book's author (String)
	 * @throws IllegalArgumentException - If author is null, empty string or whitespace
	 */
	public void setAuthor(String author) throws IllegalArgumentException {
		// Guard clause to check for null, empty string or whitespace
		if (author == null || author.isBlank()) {
			throw new IllegalArgumentException(
					"The book's author must not be blank");
		}

		// Sets the book's author
		// trims any surplus whitespace
		details.put("Author", author.trim());
	}

	/**
	 * Getter Method: Returns the book's author
	 * 
	 * @return author - The book's author (String)
	 */
	public String getAuthor() {
		return (String) details.get("Author");
	}

	/**
	 * Sets the book's title
	 * 
	 * @param title - The book's title (String)
	 * @throws IllegalArgumentException -
	 *             If title is null, empty string or whitespace
	 */
	public void setTitle(String title) throws IllegalArgumentException {
		// Guard clause to check for null, empty string or whitespace
		if (title == null || title.isBlank()) {
			throw new IllegalArgumentException(
					"The book's title must not be blank");
		}

		// Sets the book's title
		// trims any surplus whitespace
		details.put("Title", title.trim());
	}

	/**
	 * Getter Method: Returns the book's title
	 * 
	 * @return title - The book's title (String)
	 */
	public String getTitle() {
		return (String) details.get("Title");
	}

	/**
	 * Setter Method: Sets the book's ID number
	 * (implementation of the IDNumber interface's setIDNumber method)
	 * 
	 * @see <a href="IDNumber.html">IDNumber</a>
	 * 
	 * @param idNumber - The book's ID number (int)
	 * @throws IllegalArgumentException
	 *             if the idNumber is a negative integer
	 */
	@Override
	public void setIDNumber(int idNumber) throws IllegalArgumentException {
		// Guard clause that checks that the provided ID Number is not negative
		if (idNumber < 0) {
			throw new IllegalArgumentException(
					"The book's ID number must be a non-negative integer");
		}
		
		// Sets the book's ID Number
		details.put("ID Number", idNumber);
	}

	/**
	 * Getter Method: Returns the book's ID number
	 * (implementation of the IDNumber interface's setIDNumber method)
	 * 
	 * @return idNumber - The book's ID number (int)
	 */
	@Override
	public int getIDNumber() {
		return (int) details.get("ID Number");
	}

	/**
	 * Getter Method: Returns the books genre
	 * 
	 * @return genre - The book's genre (String)
	 */
	public String getGenre() {
		return (String) details.get("Genre");
	}

	/**
	 * Sets the book's genre
	 * 
	 * @param genre - The book's genre (String)
	 * @throws IllegalArgumentException -
	 *             If genre is null, empty string or whitespace
	 */
	public void setGenre(String genre) throws IllegalArgumentException {
		// Guard clause to check for null, empty string or whitespace
		if (genre == null || genre.isBlank()) {
			throw new IllegalArgumentException(
					"The book's genre must not be blank");
		}

		// Sets the books genre
		details.put("Genre", genre);
	}

	/**
	 * Prints the book's details using the {@code PrintUtil}'s
	 * {@code printDetails} method
	 * (implementation of the Displayable interface's displayDetails method)
	 * 
	 * @see <a href="PrintUtil.html">PrintUtil</a>
	 * @see <a href="Displayable.html">Displayable</a>
	 */
	@Override
	public void displayDetails() {
		PrintUtil.printDetails(details);
	}
}
