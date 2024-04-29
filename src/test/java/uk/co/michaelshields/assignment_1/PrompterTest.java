package uk.co.michaelshields.assignment_1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * A suite of JUnit tests for the Prompter class.
 * 
 * @see <a target="_blank" href="https://junit.org/junit5/">JUnit5</a>
 * @see <a target="_blank" href="Prompter.html">Prompter</a>
 * 
 * @author M. Shields
 * @version 1
 */
public class PrompterTest {
	/**
	 * Verifies that the Prompter constructor throws if the list of prompts is
	 * empty
	 */
	@Test
	@DisplayName("Member constructor throws if list of prompts is empty")
	public void testConstructorEmptyPrompts() {
		String[] prompts = {};

		assertThrows(IllegalArgumentException.class,
				() -> new Prompter(prompts),
				"an error should have been thrown if prompts is empty");
	}
	
	/**
	 * Verifies that the Prompter constructor throws if the list of prompts is null
	 */
	@Test
	@DisplayName("Member constructor throws if list of prompts is null")
	public void testConstructorNullPrompts() {
		assertThrows(IllegalArgumentException.class,
				() -> new Prompter(null),
				"an error should have been thrown if prompts is null");
	}

	/**
	 * Verifies that the Prompter constructor instantiates a prompter
	 */
	@Test
	@DisplayName("Member constructor throws if list of prompts is empty")
	public void testConstructor() {
		String[] prompts = {"What is your name?", "How old are you?"};

		Prompter prompter = new Prompter(prompts);

		assertTrue(prompter instanceof Prompter,
				"a prompter instance should have been created");
	}

	/**
	 * Verifies that the prompter's responses collection is initially empty
	 */
	@Test
	@DisplayName("prompter's response collection is empty")
	public void testGetResponsesInitEmpty() {
		String[] prompts = {"What is your name?", "How old are you?"};

		Prompter prompter = new Prompter(prompts);

		assertTrue(prompter.getResponses().isEmpty(),
				"the prompter's should initially have no responses");
	}

	/**
	 * Verifies that issuePrompts issues the prompts and aggregates the users
	 * responses
	 */
	@Test
	@DisplayName("issuePrompts issues the prompts and aggregates the responses")
	public void testIssuePrompts() {
        String[] prompts = {"Enter name: ", "Enter age: "};
		
        String name = "Michael";
        String age = "25";
        
        // Create the user's input and feed into System.in
        String simulatedInput = name +"\n" + age + "\n";
        
        ByteArrayInputStream mockInput = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(mockInput);
        
        // Temporarily redirects the System.out to prevent output to the terminal
        System.setOut(new PrintStream(new ByteArrayOutputStream()));
        
        Prompter prompter = new Prompter(prompts);
        
        prompter.issuePrompts();
        
        List<String> responses = prompter.getResponses();
        assertEquals(Arrays.asList(name, age), responses);
        
        // Reinstates System.in and System.out streams
        System.setIn(System.in);
        System.setOut(System.out);
	}
}
