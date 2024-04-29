package uk.co.michaelshields.assignment_1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * A suite of JUnit tests for the Menu class
 * 
 * @see <a target="_blank" href= "https://junit.org/junit5/">JUnit5</a>
 * @see <a target="_blank" href="Menu.html">Menu</a>
 * 
 * @author M. Shields
 * @version 1.0
 */

public class MenuTest {

	/**
	 * Verifies that the constructor throws if choices is null
	 */
	@Test
	@DisplayName("Menu constructor should throw if choices is null")
	void testConstructorNullChoices() {

		assertThrows(IllegalArgumentException.class,
				() -> new Menu("MENU HEADER", "prompt message", null),
				"an error should have been thrown if choices is null");
	}

	/**
	 * Verifies that the constructor throws if choices size is less than 2
	 */
	@Test
	@DisplayName("Menu constructor should throw if choices size is less than 2")
	void testConstructorEmptyChoices() {

		Map<String, Consumer<Void>> choices = new LinkedHashMap<>();

		assertThrows(IllegalArgumentException.class,
				() -> new Menu("MENU HEADER", "prompt message", choices),
				"an error should have been thrown if choices less than 2");
	}

	/**
	 * Verifies that no menu header is displayed if headerText is null
	 */
	@Test
	@DisplayName("menu header should not be displayed if headerText is null")
	void testRunMenuHeaderTextNull() {
		// Create a test stream to accept a print stream
		ByteArrayOutputStream testStream = new ByteArrayOutputStream();

		// Redirect the standard out to the test stream via a new print
		// stream
		System.setOut(new PrintStream(testStream));

		// This provides the menu with a mock user input so the test doesn't
		// hang
		String simulatedInput = "1";
		ByteArrayInputStream mockInput = new ByteArrayInputStream(
				simulatedInput.getBytes());
		System.setIn(mockInput);

		Map<String, Consumer<Void>> choices = new LinkedHashMap<>();
		choices.put("ChoiceA", (Void) -> {
		});
		choices.put("ChoiceB", (Void) -> {
		});
		choices.put("ChoiceC", (Void) -> {
		});

		String expectedOutput = "[33m--------------------------------------------------------------------\r\n"
				+ "promptMessage\r\n"
				+ "--------------------------------------------------------------------\r\n"
				+ "[0m--------------------------------------------------------------------\r\n"
				+ "1: ChoiceA\r\n" + "2: ChoiceB\r\n" + "3: ChoiceC\r\n"
				+ "--------------------------------------------------------------------";

		Menu menu = new Menu(null, "promptMessage", choices);

		menu.run();

		assertEquals(expectedOutput, testStream.toString().trim(),
				"no menu header should be displayed if headerText is null");

		// Reinstate System.in and System.out
		System.setOut(System.out);
		System.setIn(System.in);
	}

	/**
	 * Verifies that no menu header is displayed if headerText is empty string
	 */
	@Test
	@DisplayName("menu header should not be displayed if headerText is empty string")
	void testRunMenuHeaderTextEmptyString() {
		// Create a test stream to accept a print stream
		ByteArrayOutputStream testStream = new ByteArrayOutputStream();

		// Redirect the standard out to the test stream via a new print
		// stream
		System.setOut(new PrintStream(testStream));

		// This provides the menu with a mock user input so the test doesn't
		// hang
		String simulatedInput = "1";
		ByteArrayInputStream mockInput = new ByteArrayInputStream(
				simulatedInput.getBytes());
		System.setIn(mockInput);;

		Map<String, Consumer<Void>> choices = new LinkedHashMap<>();
		choices.put("ChoiceA", (Void) -> {
		});
		choices.put("ChoiceB", (Void) -> {
		});
		choices.put("ChoiceC", (Void) -> {
		});

		String expectedOutput = "[33m--------------------------------------------------------------------\r\n"
				+ "promptMessage\r\n"
				+ "--------------------------------------------------------------------\r\n"
				+ "[0m--------------------------------------------------------------------\r\n"
				+ "1: ChoiceA\r\n" + "2: ChoiceB\r\n" + "3: ChoiceC\r\n"
				+ "--------------------------------------------------------------------";

		Menu menu = new Menu("", "promptMessage", choices);

		menu.run();

		assertEquals(expectedOutput, testStream.toString().trim(),
				"no menu header should be displayed if headerText is empty string");

		// Reinstate System.in and System.out
		System.setOut(System.out);
		System.setIn(System.in);
	}

	/**
	 * Verifies that no menu header is displayed if headerText is empty string
	 */
	@Test
	@DisplayName("menu header should not be displayed if headerText is empty string")
	void testRunMenuHeaderTextWhitespace() {
		// Create a test stream to accept a print stream
		ByteArrayOutputStream testStream = new ByteArrayOutputStream();

		// Redirect the standard out to the test stream via a new print
		// stream
		System.setOut(new PrintStream(testStream));

		// This provides the menu with a mock user input so the test doesn't
		// hang
		String simulatedInput = "1";
		ByteArrayInputStream mockInput = new ByteArrayInputStream(
				simulatedInput.getBytes());
		System.setIn(mockInput);

		Map<String, Consumer<Void>> choices = new LinkedHashMap<>();
		choices.put("ChoiceA", (Void) -> {
		});
		choices.put("ChoiceB", (Void) -> {
		});
		choices.put("ChoiceC", (Void) -> {
		});

		String expectedOutput = "[33m--------------------------------------------------------------------\r\n"
				+ "promptMessage\r\n"
				+ "--------------------------------------------------------------------\r\n"
				+ "[0m--------------------------------------------------------------------\r\n"
				+ "1: ChoiceA\r\n" + "2: ChoiceB\r\n" + "3: ChoiceC\r\n"
				+ "--------------------------------------------------------------------";

		Menu menu = new Menu(" ", "promptMessage", choices);

		menu.run();

		assertEquals(expectedOutput, testStream.toString().trim(),
				"no menu header should be displayed if headerText is whitespace");

		// Reinstate System.in and System.out
		System.setOut(System.out);
		System.setIn(System.in);
	}

	/**
	 * Verifies that menu header is displayed
	 */
	@Test
	@DisplayName("menu header should be displayed")
	void testRunMenuHeaderText() {
		// Create a test stream to accept a print stream
		ByteArrayOutputStream testStream = new ByteArrayOutputStream();

		// Redirect the standard out to the test stream via a new print
		// stream
		System.setOut(new PrintStream(testStream));

		// This provides the menu with a mock user input so the test doesn't
		// hang
		String simulatedInput = "1";
		ByteArrayInputStream mockInput = new ByteArrayInputStream(
				simulatedInput.getBytes());
		System.setIn(mockInput);

		Map<String, Consumer<Void>> choices = new LinkedHashMap<>();
		choices.put("ChoiceA", (Void) -> {
		});
		choices.put("ChoiceB", (Void) -> {
		});
		choices.put("ChoiceC", (Void) -> {
		});

		String expectedOutput = "[36m====================================================================\r\n"
				+ "MENU HEADER\r\n"
				+ "====================================================================\r\n"
				+ "[0m[33m--------------------------------------------------------------------\r\n"
				+ "promptMessage\r\n"
				+ "--------------------------------------------------------------------\r\n"
				+ "[0m--------------------------------------------------------------------\r\n"
				+ "1: ChoiceA\r\n" + "2: ChoiceB\r\n" + "3: ChoiceC\r\n"
				+ "--------------------------------------------------------------------";

		Menu menu = new Menu("MENU HEADER", "promptMessage", choices);

		menu.run();

		assertEquals(expectedOutput, testStream.toString().trim(),
				"menu header should be displayed");

		// Reinstate System.in and System.out
		System.setOut(System.out);
		System.setIn(System.in);
	}

	/**
	 * Verifies that no prompt banner is displayed if promptMessage is null
	 */
	@Test
	@DisplayName("menu prompt banner should not be displayed if promptMessage is null")
	void testRunMenuPromptMessageNull() {
		// Create a test stream to accept a print stream
		ByteArrayOutputStream testStream = new ByteArrayOutputStream();

		// Redirect the standard out to the test stream via a new print
		// stream
		System.setOut(new PrintStream(testStream));

		// This provides the menu with a mock user input so the test doesn't
		// hang
		String simulatedInput = "1";
		ByteArrayInputStream mockInput = new ByteArrayInputStream(
				simulatedInput.getBytes());
		System.setIn(mockInput);

		Map<String, Consumer<Void>> choices = new LinkedHashMap<>();
		choices.put("ChoiceA", (Void) -> {
		});
		choices.put("ChoiceB", (Void) -> {
		});
		choices.put("ChoiceC", (Void) -> {
		});

		String expectedOutput = "[36m====================================================================\r\n"
				+ "MENU HEADER\r\n"
				+ "====================================================================\r\n"
				+ "[0m--------------------------------------------------------------------\r\n"
				+ "1: ChoiceA\r\n" + "2: ChoiceB\r\n" + "3: ChoiceC\r\n"
				+ "--------------------------------------------------------------------";

		Menu menu = new Menu("MENU HEADER", null, choices);

		menu.run();

		assertEquals(expectedOutput, testStream.toString().trim(),
				"no menu prompt banner should be displayed if promptMessage is null");

		// Reinstate System.in and System.out
		System.setOut(System.out);
		System.setIn(System.in);
	}

	/**
	 * Verifies that no prompt banner is displayed if promptMessage is empty
	 * string
	 */
	@Test
	@DisplayName("menu header should not be displayed if promptMessage is empty string")
	void testRunMenuPromptMessageEmptyString() {
		// Create a test stream to accept a print stream
		ByteArrayOutputStream testStream = new ByteArrayOutputStream();

		// Redirect the standard out to the test stream via a new print
		// stream
		System.setOut(new PrintStream(testStream));

		// This provides the menu with a mock user input so the test doesn't
		// hang
		String simulatedInput = "1";
		ByteArrayInputStream mockInput = new ByteArrayInputStream(
				simulatedInput.getBytes());
		System.setIn(mockInput);

		Map<String, Consumer<Void>> choices = new LinkedHashMap<>();
		choices.put("ChoiceA", (Void) -> {
		});
		choices.put("ChoiceB", (Void) -> {
		});
		choices.put("ChoiceC", (Void) -> {
		});

		String expectedOutput = "[36m====================================================================\r\n"
				+ "MENU HEADER\r\n"
				+ "====================================================================\r\n"
				+ "[0m--------------------------------------------------------------------\r\n"
				+ "1: ChoiceA\r\n" + "2: ChoiceB\r\n" + "3: ChoiceC\r\n"
				+ "--------------------------------------------------------------------";

		Menu menu = new Menu("MENU HEADER", "", choices);

		menu.run();

		assertEquals(expectedOutput, testStream.toString().trim(),
				"menu prompt banner should not be displayed if promptMessage is empty string");

		// Reinstate System.in and System.out
		System.setOut(System.out);
		System.setIn(System.in);
	}

	/**
	 * Verifies that no prompt banner is displayed if promptMessage is
	 * whitespace
	 */
	@Test
	@DisplayName("menu prompt banner should not be displayed if promptMessage is whitespace")
	void testRunMenuPromptMessageWhitespace() {
		// Create a test stream to accept a print stream
		ByteArrayOutputStream testStream = new ByteArrayOutputStream();

		// Redirect the standard out to the test stream via a new print
		// stream
		System.setOut(new PrintStream(testStream));

		// This provides the menu with a mock user input so the test doesn't
		// hang
		String simulatedInput = "1";
		ByteArrayInputStream mockInput = new ByteArrayInputStream(
				simulatedInput.getBytes());
		System.setIn(mockInput);

		Map<String, Consumer<Void>> choices = new LinkedHashMap<>();
		choices.put("ChoiceA", (Void) -> {
		});
		choices.put("ChoiceB", (Void) -> {
		});
		choices.put("ChoiceC", (Void) -> {
		});

		String expectedOutput = "[36m====================================================================\r\n"
				+ "MENU HEADER\r\n"
				+ "====================================================================\r\n"
				+ "[0m--------------------------------------------------------------------\r\n"
				+ "1: ChoiceA\r\n" + "2: ChoiceB\r\n" + "3: ChoiceC\r\n"
				+ "--------------------------------------------------------------------";

		Menu menu = new Menu("MENU HEADER", " ", choices);

		menu.run();

		assertEquals(expectedOutput, testStream.toString().trim(),
				"menu prompt banner should not be displayed if promptMessage is whitespace");

		// Reinstate System.in and System.out
		System.setOut(System.out);
		System.setIn(System.in);
	}

	/**
	 * Verifies that a prompt banner is displayed
	 */
	@Test
	@DisplayName("menu prompt banner should be displayed")
	void testRunMenuPrompt() {
		// Create a test stream to accept a print stream
		ByteArrayOutputStream testStream = new ByteArrayOutputStream();

		// Redirect the standard out to the test stream via a new print
		// stream
		System.setOut(new PrintStream(testStream));

		// This provides the menu with a mock user input so the test doesn't
		// hang
		String simulatedInput = "1";
		ByteArrayInputStream mockInput = new ByteArrayInputStream(
				simulatedInput.getBytes());
		System.setIn(mockInput);

		Map<String, Consumer<Void>> choices = new LinkedHashMap<>();
		choices.put("ChoiceA", (Void) -> {
		});
		choices.put("ChoiceB", (Void) -> {
		});
		choices.put("ChoiceC", (Void) -> {
		});

		String expectedOutput = "[36m====================================================================\r\n"
				+ "MENU HEADER\r\n"
				+ "====================================================================\r\n"
				+ "[0m[33m--------------------------------------------------------------------\r\n"
				+ "prompt message\r\n"
				+ "--------------------------------------------------------------------\r\n"
				+ "[0m--------------------------------------------------------------------\r\n"
				+ "1: ChoiceA\r\n" + "2: ChoiceB\r\n" + "3: ChoiceC\r\n"
				+ "--------------------------------------------------------------------";

		Menu menu = new Menu("MENU HEADER", "prompt message", choices);

		menu.run();

		assertEquals(expectedOutput, testStream.toString().trim(),
				"a menu prompt banner should be displayed");

		// Reinstate System.in and System.out
		System.setOut(System.out);
		System.setIn(System.in);
	}

	/**
	 * Verifies that an error banner is printed if the user's input is not a
	 * number
	 * 
	 */
	@Test
	@DisplayName("an error banner is printed if the user's choice is not a number")
	void testUsersChoiceNotNumber() {
		// Create a test stream to accept a print stream
		ByteArrayOutputStream testStream = new ByteArrayOutputStream();

		// Redirect the standard out to the test stream via a new print
		// stream
		System.setOut(new PrintStream(testStream));

		// This provides the menu with a mock user input so the test doesn't
		// hang
		String simulatedInput = "x";
		ByteArrayInputStream mockInput = new ByteArrayInputStream(
				simulatedInput.getBytes());
		System.setIn(mockInput);

		Map<String, Consumer<Void>> choices = new LinkedHashMap<>();
		choices.put("ChoiceA", (Void) -> {
		});
		choices.put("ChoiceB", (Void) -> {
		});
		choices.put("ChoiceC", (Void) -> {
		});

		Menu menu = new Menu("MENU HEADER", "prompt message", choices);

		assertThrows(IllegalArgumentException.class, () -> {
			menu.run();
		}, "an error should be thrown if the user's choice is not a number");
	}

	/**
	 * Verifies that an error banner is printed if the user's choice is outside
	 * the range of choices
	 * 
	 */
	@Test
	@DisplayName("an error banner is printed if the user's choice is outside the range of choices")
	void testUsersChoiceOutOfBounds() {
		// Create a test stream to accept a print stream
		ByteArrayOutputStream testStream = new ByteArrayOutputStream();

		// Redirect the standard out to the test stream via a new print
		// stream
		System.setOut(new PrintStream(testStream));

		// This provides the menu with a mock user input so the test doesn't
		// hang
		String simulatedInput = "4";
		ByteArrayInputStream mockInput = new ByteArrayInputStream(
				simulatedInput.getBytes());
		System.setIn(mockInput);

		Map<String, Consumer<Void>> choices = new LinkedHashMap<>();
		choices.put("ChoiceA", (Void) -> {
		});
		choices.put("ChoiceB", (Void) -> {
		});
		choices.put("ChoiceC", (Void) -> {
		});

		Menu menu = new Menu("MENU HEADER", "prompt message", choices);


		assertThrows(IllegalArgumentException.class, () -> {
			menu.run();
		}, "an error should be thrown if the user's choice is outside the range of choices");
	}
}
