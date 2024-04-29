package uk.co.michaelshields.assignment_1;

import java.util.Map;

/**
 * A class that implements a suite of printing utility functions for displaying
 * information is various formats and colours.
 * 
 * @author M. Shields
 * @version 1.0
 */

public class PrintUtil {
	/**
	 * A map of colours where the key is the colour name and the value is the
	 * corresponding ANSI escape code for setting the print colour used in the
	 * terminal
	 * 
	 * @see <a href=
	 *      "https://www.lihaoyi.com/post/BuildyourownCommandLinewithANSIescapecodes.html#8-colors">here</a>
	 *      for info
	 */
	private final static String RESET = "\u001B[0m";
	private final static String PURPLE = "\u001B[35m";
	private final static String RED = "\u001B[31m";
	private final static String YELLOW = "\u001B[33m";
	private final static String GREEN = "\u001B[32m";
	private final static String CYAN = "\u001B[36m";

	/**
	 * A utility method to print menu headers in a standard format and
	 * consistent colour
	 * 
	 * @param text
	 *            The text to be displayed in the menu header
	 */
	public static void menuHeader(String text) {
		System.out.print(CYAN);
		System.out.println(
				"====================================================================");
		System.out.println(text);
		System.out.println(
				"====================================================================");
		System.out.print(RESET);
	}

	/**
	 * A utility method to print error banners in a standard format and
	 * consistent colour
	 * 
	 * @param text
	 *            The text to be displayed in the error banner
	 */
	public static void errorBanner(String text) {
		System.out.print(RED);
		System.out.println(
				"--------------------------------------------------------------------");
		System.out.println(text);
		System.out.println(
				"--------------------------------------------------------------------");
		System.out.print(RESET);
	}

	/**
	 * A utility method to print info banners in a standard format and
	 * consistent colour
	 * 
	 * @param text
	 *            The text to be displayed in the info banner
	 */
	public static void infoBanner(String text) {
		System.out.print(PURPLE);
		System.out.println(
				"--------------------------------------------------------------------");
		System.out.println(text);
		System.out.println(
				"--------------------------------------------------------------------");
		System.out.print(RESET);
	}

	/**
	 * A utility method to print success banners in a standard format and
	 * consistent colour
	 * 
	 * @param text
	 *            The text to be displayed in the success banner
	 */
	public static void successBanner(String text) {
		System.out.print(GREEN);
		System.out.println(
				"--------------------------------------------------------------------");
		System.out.println(text);
		System.out.println(
				"--------------------------------------------------------------------");
		System.out.print(RESET);
	}

	/**
	 * A utility method to print prompt banners in a standard format and
	 * consistent colour
	 * 
	 * @param text
	 *            The text to be displayed in the prompt banner
	 */
	public static void promptBanner(String text) {
		System.out.print(YELLOW);
		System.out.println(
				"--------------------------------------------------------------------");
		System.out.println(text);
		System.out.println(
				"--------------------------------------------------------------------");
		System.out.print(RESET);
	}

	/**
	 * A utility method to print prompt lines in a standard format and
	 * consistent colour
	 * 
	 * @param text
	 *            The text to be displayed in the prompt line
	 */
	public static void promptLine(String text) {
		System.out.print(YELLOW);
		System.out.println(text);
		System.out.print(RESET);
	}

	/**
	 * A utility method to print the details of types that implement the
	 * Displayable interface i.e. Members, Books and Loans
	 * 
	 * @see <a href="Displayable.html">Displayable</a>
	 * @param details
	 *            The object's details
	 */
	public static void printDetails(Map<String, Object> details) {
		int columnWidth = 15;

		for (Map.Entry<String, Object> entry : details.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();
			System.out.println(
					key + ":" + " ".repeat(columnWidth - key.length()) + value);
		}
	}

	/**
	 * A printing utility to display an array of strings as a numbered list
	 * starting from 1
	 * 
	 * @param choices
	 *            The array of strings
	 */
	public static void menu(String[] choices) {
		System.out.println(
				"--------------------------------------------------------------------");
		for (int i = 0; i < choices.length; i++) {
			System.out.println((i + 1) + ": " + choices[i]);
		}
		System.out.println(
				"--------------------------------------------------------------------");
	}
}
