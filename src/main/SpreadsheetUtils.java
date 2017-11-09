package main;

import java.util.Stack;

public class SpreadsheetUtils {

	public static final String MUL = "*";
	public static final String DEV = "/";
	public static final String PLUS = "+";
	public static final String NEG = "-";
	public static final int DIGIT = 64;
	private static String decimalPattern = "[0-9]*\\.?[0-9]*";
	private static int TWO =2;

	public double calculateExpression(String[] val) {
		int l = val.length;
		int i = 0;
		Stack<Double> stack = new Stack<>();
		while (i < l) {
			String vl = val[i];
			if (vl.matches(decimalPattern)) {
				stack.push(Double.parseDouble(vl));
			}
			else {
				if (stack.size() >= TWO) {
					Double st1 = stack.pop();
					Double st2 = stack.pop();
					if (vl.equals(MUL))
						stack.push(st2 * st1);
					else if (vl.equals(DEV))
						stack.push(st2 / st1);
					else if (vl.equals(PLUS))
						stack.push(st2 + st1);
					else if (vl.equals(NEG))
						stack.push(st2 - st1);
				}
			}
			i++;
		}
		return stack.pop();
	}

	public int calculateRowIndex(String val) {
		int sum = 0;
		int length = val.length();
		for (int i = 0; i < length; i++) {
			int index = val.charAt(i) - DIGIT;
			sum = 26 * sum + index;
		}
		return sum;
	}
}
