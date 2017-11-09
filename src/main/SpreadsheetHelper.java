package main;

import java.util.Objects;

import static main.SpreadsheetUtils.*;

public class SpreadsheetHelper {

	public static final char A = 'A';
	public static final char Z = 'Z';
	private static String  REGEX = "(?<=\\D)(?=\\d+\\b)";

	public static boolean isArithmeticOperator(String cellVal) {
		return cellVal.equals(MUL) || cellVal.equals(DEV) || cellVal.equals(PLUS) || cellVal.equals(NEG);
	}

	public static String[] splitLetterDigits(String cellVal) {

		return cellVal.split(REGEX);
	}

	public static boolean isExpression(String[] value) {
		return Objects.nonNull(value) && (value.length > 1);
	}

	public static boolean isReference(String val) {
		char isAlph = val.charAt(0);
		return isAlph >= A && isAlph <= Z;
	}
}
