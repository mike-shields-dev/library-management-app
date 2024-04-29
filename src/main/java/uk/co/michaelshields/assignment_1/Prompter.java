package uk.co.michaelshields.assignment_1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * A Prompter, it prompts the user for information and scans and collects their input allowing the information to be accessed
 */
public class Prompter {
	private Scanner inputScanner = new Scanner(System.in);
	private String YELLOW = "\u001B[33m";
	private String RESET = "\u001B[0m";
	private String[] prompts;
	private List<String> responses = new ArrayList<>();

	/**
	 * Constructor
	 * 
	 * @param prompts
	 *            The list of prompts that will be issued to the user
	 * @throws IllegalArgumentException if the prompts list is null or empty
	 */
	public Prompter(String[] prompts) {
		if(prompts == null) {
			throw new IllegalArgumentException("Cannot create prompter with null prompts");
		}
		
		if(prompts.length == 0) {
			throw new IllegalArgumentException("Cannot create prompter without prompts");
		}
		
		this.prompts = prompts;
	}

	/**
	 * Iterates through the prompts, prompting a response from the user and
	 * adding their response to the responses collection
	 */
	public void issuePrompts() {
		for (String prompt : prompts) {
			System.out.print(YELLOW);
			System.out.print(prompt);
			System.out.print(RESET);
			String response = inputScanner.nextLine().trim();
			responses.add(response);
		}
	}

	/**
	 * Returns the user's responses
	 * 
	 * @return The list of responses that the user provided
	 */
	public List<String> getResponses() {
		return responses;
	}
}