package uk.co.michaelshields.assignment_1;

/**
 * The IDNumber interface represents an object that has an ID Number that can be
 * updated or retrieved. Implementing classes should provide an implementation
 * for the {@code getIDNumber} and {@code setIDNumber(int idNumber)} methods
 * 
 * @author M. Shields
 * @version 1.0
 */
public interface IDNumber {
	/**
	 * Getter method:
	 * Returns the object's ID Number
	 * 
	 * @return idNumber - The object's ID Number (int)
	 */
	int getIDNumber();

	/**
	 * Setter Method:
	 * Sets the object's ID Number
	 * 
	 * @param idNumber - The object's ID Number (int)
	 */
	void setIDNumber(int idNumber);
}
