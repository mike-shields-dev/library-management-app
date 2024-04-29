package uk.co.michaelshields.assignment_1;

import java.util.Map;
import java.util.Scanner;
import java.util.function.Consumer;

/**
 * A class that displays a menu of choices to the user, prompts the user to make
 * a choice and executes the lambda function associated with that choice.
 * 
 * @author M. Shields
 * @version 1.0
 */
public class Menu {
	/* The hash map containing a list of choices
	* The key is the choice listed in the menu and 
	* the value is the lambda function that will be
	* if the user selects that choice
	*/
	private Map<String, Consumer<Void>> choices;
	// The text that will be displayed in the Menu's header
	private String headerText;
	// The message that will be displayed as the Menu's prompt
	private String promptMessage;
	// A scanner to read the user's menu selection
	private Scanner inputScanner = new Scanner(System.in);
	// Stores the user's choice
	private int chosenIndex = -1;
	

	/**
	 * Constructor
	 * 
	 * @param headerText
	 *            The text to be displayed in the menu's header (not displayed
	 *            if value is empty string, whitespace or null)
	 * @param promptMessage
	 *            The prompt message to be displayed to the user (not displayed
	 *            if value is empty string, whitespace or null)
	 * @param choices
	 *            The list of choices presented to the user
	 * 
	 * @throws IllegalArgumentException
	 *             if choices is null or empty
	 */
	public Menu(String headerText, String promptMessage,
			Map<String, Consumer<Void>> choices) {
		// Guard clause that prevents Menus from being created if no choices are provided
		if (choices == null) {
			throw new IllegalArgumentException(
					"Cannot create menu, choices is null");
		}
		// Guard clause that prevents menus with less than 2 options from being created
		if (choices.size() < 2) {
			throw new IllegalArgumentException(
					"Cannot create menu, choices must be greater than 1");
		}

		if (headerText == null || headerText.isBlank()) {
			this.headerText = "";
		} else {
			this.headerText = headerText;
		}

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
		if (!headerText.isBlank()) {
			displayHeader(headerText);
		}

		if (!promptMessage.isBlank()) {
			displayPrompt(promptMessage);
		}

		displayChoices(choices);
		requestAndRunUsersChoice();
	}

	/**
	 * Prints a menu header containing the provided text
	 * 
	 * @param text
	 *            The text to be displayed in the menu header
	 * @see <a href="PrintUtil.html">PrintUtil.menuHeader()</a>
	 */
	public void displayHeader(String text) {
		PrintUtil.menuHeader(text);
	}

	/**
	 * Prints the prompt banner containing the provided text
	 * 
	 * @param text
	 *            The text to be displayed in the prompt banner
	 * @see <a href="PrintUtil.html">PrintUtil.promptBanner()</a>
	 */
	private void displayPrompt(String text) {
		PrintUtil.promptBanner(text);
	}

	/**
	 * Prints the menu's list of choices * @see
	 * <a href="PrintUtil.html">PrintUtil.menu()</a>
	 */
	private void displayChoices(Map<String, Consumer<Void>> choices) {
		String[] choiceKeys = choices.keySet().toArray(new String[0]);

		PrintUtil.menu(choiceKeys);
	}

	/**
	 * Requests a choice from the user and runs the associated function
	 */
	public void requestAndRunUsersChoice() {
		try {
			setChosenIndex(Integer.parseInt(inputScanner.nextLine()) - 1);

			if (getChosenIndex() < 0
					|| getChosenIndex() >= getChoices().size()) {
				resetChosenIndex();
				throw new IllegalArgumentException ("Please enter a number between 1 and "
						+ getChoices().size());
			}

			String chosenKey = getChosenKey();

			Consumer<Void> chosenFunction = choices.get(chosenKey);

			chosenFunction.accept(null);

		} catch (NumberFormatException e) {
			resetChosenIndex();
			throw new IllegalArgumentException("Please enter a valid number");
		}
	}

	/**
	 * Resets the menu's choice index
	 */
	private void resetChosenIndex() {
		setChosenIndex(-1);
	}

	/**
	 * Sets the menu's chosenIndex
	 * 
	 * @param index
	 */
	private void setChosenIndex(int index) {
		this.chosenIndex = index;
	}

	/**
	 * Returns the menu's chosenIndex
	 * 
	 * @return chosenIndex The index that the user chose
	 */
	public int getChosenIndex() {
		return chosenIndex;
	}

	/**
	 * Returns the key name of the choice that corresponds to the menu's
	 * chosenIndex
	 * 
	 * @return choiceKey The choices key name
	 */
	public String getChosenKey() {
		String choiceKey = (String) choices.keySet()
				.toArray()[getChosenIndex()];
		return choiceKey;
	}

	/**
	 * Returns the menu's choices
	 * 
	 * @return choices The menu's choices
	 */
	private Map<String, Consumer<Void>> getChoices() {
		return choices;
	}
}
