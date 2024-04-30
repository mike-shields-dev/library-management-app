package uk.co.michaelshields.assignment_1;

/**
 * An interface that represents an object that has an ID Number that can be
 * accessed or mutated. Implementing classes should provide an implementation
 * for the getIDNumber and setIDNumber(int idNumber) methods
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
