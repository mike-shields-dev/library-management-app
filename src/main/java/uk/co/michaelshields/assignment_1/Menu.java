package uk.co.michaelshields.assignment_1;

import java.util.Map;
import java.util.Scanner;
import java.util.function.Consumer;

/**
 * A class that displays a menu of choices to the user, prompts the user to make
 * a choice and executes the provided lambda function associated with that choice.
 * 
 * @author M. Shields
 * @version 1.0
 */
public class Menu {
	/* The hash map containing a list of choices
	* The key is the choice listed in the menu and 
	* the value is the lambda function that will be executed
	* if the user selects that choice
	*/
	private Map<String, Consumer<Void>> choices;
	/** The text that will be displayed in the Menu's header **/
	private String headerText;
	/** The message that will be displayed as the Menu's prompt **/
	private String promptMessage;
	/** A scanner to read the user's menu selection **/
	private Scanner inputScanner = new Scanner(System.in);
	/** Stores the user's choice **/
	private int chosenIndex = -1;
	

	/**
	 * Constructor:
	 * Instantiates Menu objects
	 * 
	 * @param headerText - The text to be displayed in the menu's header (String)
	 *           
	 * @param promptMessage - The prompt message to be displayed to the user (String)
	 * 
	 * @param choices - The list of choices and presented to the user 
	 *                  and associated lambdas (May<String, Consumer<Void>>);
	 * 
	 * @throws IllegalArgumentException - If choices is null or has a size less than 2 
	 *                                    since a menu with less that 2 choices is redundant
	 */
	public Menu(String headerText, String promptMessage,
			Map<String, Consumer<Void>> choices) {
		// Guard clause that prevents Menus from being created if choices is null
		if (choices == null) {
			throw new IllegalArgumentException(
					"Cannot create menu, choices is null");
		}
		// Guard clause that prevents menus with less than 2 options from being created
		if (choices.size() < 2) {
			throw new IllegalArgumentException(
					"Cannot create menu, choices must be greater than 1");
		}

		// If no header text is provided, set it to an empty string
		if (headerText == null || headerText.isBlank()) {
			this.headerText = "";
		} else {
			this.headerText = headerText;
		}

		// If no prompt message is provided set it to an empty string
		if (promptMessage == null || promptMessage.isBlank()) {
			this.promptMessage = "";
		} else {
			this.promptMessage = promptMessage;
		}

		this.choices = choices;
	}

	/**
	 * Displays the menu, requests the user's choice and executes the
	 * method/function associated with the user's choice
	 */
	public void run() {
		// Display the header if header text if there is some
		if (!headerText.isBlank()) {
			displayHeader(headerText);
		}

		// Display the prompt message if there is one
		if (!promptMessage.isBlank()) {
			displayPrompt(promptMessage);
		}

		// Display the choice list
		displayChoices(choices);
		
		// Read the users input and run the lambda 
		// associated with their choice
		readAndRunUsersChoice();
	}

	/**
	 * Prints a menu header containing the provided text 
	 * using the PrintUtil's menuHeader method
	 * 
	 * @param text - The text to be displayed in the menu header (String)
	 * 
	 * @see <a href="PrintUtil.html">PrintUtil</a>
	 */
	public void displayHeader(String text) {
		PrintUtil.menuHeader(text);
	}

	/**
	 * Prints the prompt banner containing the provided text
	 * using the PrintUtil's promptBanner method
	 * 
	 * @param text - The text to be displayed in the prompt banner (String)
	 * 
	 * @see <a href="PrintUtil.html">PrintUtil.promptBanner()</a>
	 */
	private void displayPrompt(String text) {
		PrintUtil.promptBanner(text);
	}

	/**
	 * Prints the menu's list of choices 
	 * using the PrintUtil's menu method
	 * 
	 * @see <a href="PrintUtil.html">PrintUtil</a>
	 */
	private void displayChoices(Map<String, Consumer<Void>> choices) {
		// Extract the choices key set to an array
		String[] choiceKeys = choices.keySet().toArray(new String[0]);

		PrintUtil.menu(choiceKeys);
	}

	/**
	 * Requests a choice from the user and runs the associated function
	 */
	public void readAndRunUsersChoice() {
		try {
			// Set the chosen index according to the user's integer input
			setChosenIndex(Integer.parseInt(inputScanner.nextLine()) - 1);

			// Guard clause that check's that the user choice is valid
			if (getChosenIndex() < 0
					|| getChosenIndex() >= getChoices().size()) {
				
				// Reset the chosen index to its default value
				resetChosenIndex();
				
				// Throw an error explaining that the user's choice is not valid
				throw new IllegalArgumentException ("Please enter a number between 1 and "
						+ getChoices().size());
			}

			// Get the key associated with the user's choice
			String chosenKey = getChosenKey();

			// Get the lambda associated with the user's choice
			Consumer<Void> chosenFunction = choices.get(chosenKey);

			// Invoke the lambda
			chosenFunction.accept(null);

		} catch (NumberFormatException e) {
			// Throw an error if the user tried to enter a choice that was not 
			// an integer
			// Reset the menu's chosen index back to the default
			resetChosenIndex();
			throw new IllegalArgumentException("Please enter a valid number");
		}
	}

	/**
	 * Mutator Method: 
	 * Resets the menu's choice index
	 */
	private void resetChosenIndex() {
		setChosenIndex(-1);
	}

	/**
	 * Sets the menu's chosenIndex
	 * 
	 * @param index - The index of the choice (int)
	 */
	private void setChosenIndex(int index) {
		this.chosenIndex = index;
	}

	/**
	 * Returns the menu's chosenIndex
	 * 
	 * @return chosenIndex -The index that the user chose (int)
	 */
	public int getChosenIndex() {
		return chosenIndex;
	}

	/**
	 * Accessor Method:
	 * 
	 * Returns the key of the choice that corresponds to the menu's
	 * chosenIndex
	 * 
	 * @return choiceKey - The choices key name (String)
	 */
	public String getChosenKey() {
		String choiceKey = (String) choices.keySet()
				.toArray()[getChosenIndex()];
		return choiceKey;
	}

	/**
	 * Accessor Method:
	 * 
	 * Returns the menu's choices
	 * 
	 * @return choices - The menu's choices (Map<String, Consumer<Void>>)
	 */
	private Map<String, Consumer<Void>> getChoices() {
		return choices;
	}
}
