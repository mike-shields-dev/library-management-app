package uk.co.michaelshields.assignment_1;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * A suite of JUnit tests for the PrintUtil class
 * 
 * @see <a target="_blank" href= "https://junit.org/junit5/">JUnit5</a>
 * @see <a target="_blank" href="PrintUtil.html">PrintUtil</a>
 * 
 * @author M. Shields
 * @version 1.0
 */
@DisplayName("PrintUtil Tests")
public class PrintUtilTest {
	ByteArrayOutputStream testStream;

	@BeforeEach
	void setUp() {
		// Create a test stream to accept a print stream
		testStream = new ByteArrayOutputStream();

		// Redirect the standard out to the test stream via a new print
		// stream
		System.setOut(new PrintStream(testStream));
	}

	@AfterEach
	void tearDown() {
		// Reinstate the standard output stream
		System.setOut(System.out);
	}

	/**
	 * Verifies that <code>menuHeader(String text)</code> prints the menu header
	 * correctly
	 */
	@Test
	@DisplayName("menuHeader() prints the menu's header")
	void menuHeader() {
		String text = "MENU HEADER";

		PrintUtil.menuHeader(text);

		String expectedOutput = "[36m===================================================================="
				+ "\r\n" + text + "\r\n"
				+ "===================================================================="
				+ "\r\n" + "[0m";

		assertEquals(expectedOutput, testStream.toString().trim(),
				"menuHeader() should print the expected output");
	}

	/**
	 * Verifies that <code>errorBanner(String text)</code> prints the error
	 * banner correctly
	 */
	@Test
	@DisplayName("errorBanner() prints the error banner")
	void errorBanner() {
		String errorMessage = "error message";

		PrintUtil.errorBanner(errorMessage);

		String expectedOutput = "[31m--------------------------------------------------------------------\r\n"
				+ "error message\r\n"
				+ "--------------------------------------------------------------------\r\n"
				+ "[0m";

		assertEquals(expectedOutput, testStream.toString().trim(),
				"errorBanner() should print the expected output");
	}

	/**
	 * Verifies that <code>successBanner(String text)</code> prints the success
	 * banner correctly
	 */
	@Test
	@DisplayName("successBanner() prints the success banner")
	void successBanner() {
		String successMessage = "success message";

		PrintUtil.successBanner(successMessage);

		String expectedOutput = "[32m--------------------------------------------------------------------\r\n"
				+ "success message\r\n"
				+ "--------------------------------------------------------------------\r\n"
				+ "[0m";

		assertEquals(expectedOutput, testStream.toString().trim(),
				"successBanner() should print the expected output");
	}

	/**
	 * Verifies that <code>promptLine(String text)</code> prints the prompt line
	 * correctly
	 */
	@Test
	@DisplayName("promptLine() prints the prompt line")
	void promptLine() {
		String prompt = "prompt";

		PrintUtil.promptLine(prompt);

		String expectedOutput = "[33mprompt\r\n" + "[0m";

		assertEquals(expectedOutput, testStream.toString().trim(),
				"promptLine() should print the expected output");
	}

	/**
	 * Verifies that <code>promptBanner(String text)</code> prints the prompt
	 * banner correctly
	 */
	@Test
	@DisplayName("promptBanner() prints the prompt banner")
	void promptBanner() {
		String prompt = "prompt";

		PrintUtil.promptBanner(prompt);

		String expectedOutput = "[33m--------------------------------------------------------------------\r\n"
				+ "prompt\r\n"
				+ "--------------------------------------------------------------------\r\n"
				+ "[0m";

		assertEquals(expectedOutput, testStream.toString().trim(),
				"promptLine() should print the expected output");
	}

	/**
	 * Verifies that <code>infoBanner(String text)</code> prints the info banner
	 * correctly
	 */
	@Test
	@DisplayName("infoBanner() prints the info banner")
	void infoBanner() {
		String info = "info";

		PrintUtil.infoBanner(info);

		String expectedOutput = "[35m--------------------------------------------------------------------\r\n"
				+ "info\r\n"
				+ "--------------------------------------------------------------------\r\n"
				+ "[0m";

		assertEquals(expectedOutput, testStream.toString().trim(),
				"infoBanner() should print the expected output");
	}

	/**
	 * Verifies that <code>printDetails()</code> prints the objects Map entries
	 * correctly
	 */
	@Test
	@DisplayName("printDetails() prints the Map details")
	void printDetails() {
		Map<String, Object> details = new LinkedHashMap<>();

		String key1 = "key1";
		int value1 = 1;

		String key2 = "key2";
		String value2 = "value2";

		String key3 = "key3";
		boolean value3 = false;

		details.put(key1, value1);
		details.put(key2, value2);
		details.put(key3, value3);

		String expectedOutput = key1 + ":           " + value1 + "\r\n" + key2
				+ ":           " + value2 + "\r\n" + key3 + ":           "
				+ value3;

		PrintUtil.printDetails(details);

		assertEquals(expectedOutput, testStream.toString().trim(),
				"infoBanner() should print the expected output");
	}

	/**
	 * Verifies that menu prints the expected output
	 */
	@Test
	@DisplayName("menu prints the expected output")
	void testMenu() {
		String[] menuChoices = {"Choice A", "Choice B", "Choice C"};

		PrintUtil.menu(menuChoices);

		String expectedOutput = "--------------------------------------------------------------------\r\n"
				+ "1: Choice A\r\n" + "2: Choice B\r\n" + "3: Choice C\r\n"
				+ "--------------------------------------------------------------------";

		assertEquals(expectedOutput, testStream.toString().trim(),
				"infoBanner() should print the expected output");
	}
}
