package main;

// this file contain core business logic for the code
// it using one recursive function to calculate the cell value
// using the DP storing the value at same time so no need to again calculate the same value for same cell
public class SpreadsheetImpl {

	SpreadsheetUtils utils = new SpreadsheetUtils();

	// this is recursive method which is called by the spreadsheet main class
	public double recurSpreadSheet(SpreadResult[][] spreadsheets, int row, int col) {

		SpreadResult cell = spreadsheets[row][col];

		// checking the cell if already we calculated the cell value
		if (cell.isVisited() && cell.getAns() != null) {
			return cell.getAns();
		}
		// this is the bool parameter which is telling about the cycle dependency
		else if (cell.isVisited() && cell.getAns() == null) {
			return Double.MIN_VALUE;
		}
		if (!cell.isVisited()) {
			cell.setVisited(true);
		}

		String[] split = cell.getInput().split(" ");
		//expression checking
		if (SpreadsheetHelper.isExpression(split)) {
			int expLenth = split.length;
			String[] vals = new String[expLenth];
			for (int i = 0; i < expLenth; i++) {
				String cellVal = split[i];
				if (!SpreadsheetHelper.isArithmeticOperator(cellVal)) {
					double calulatedVal = recurRefOrValue(spreadsheets, cellVal);
					if (Double.MIN_VALUE == calulatedVal) {
						return calulatedVal;
					}
					vals[i] = String.valueOf(calulatedVal);
				}
				else {
					vals[i] = cellVal;
				}
			}
			double result = utils.calculateExpression(vals);
			cell.setAns(result);
			return result;
		}
		// if expression is false it means its a value or some other ref
		else {
			String cellVal = split[0];
			double result = recurRefOrValue(spreadsheets, cellVal);
			if (Double.MIN_VALUE != result) {
				cell.setAns(result);
			}
			return result;
		}
	}

	// this method return cell value or again call to other cell reference
	private double recurRefOrValue(SpreadResult[][] spreadsheets, String cellVal) {
		if (SpreadsheetHelper.isReference(cellVal)) {
			String[] splits = SpreadsheetHelper.splitLetterDigits(cellVal);
			int irow = utils.calculateRowIndex(splits[0]) - 1;
			int icol = Integer.parseInt(splits[1]) - 1;
			return recurSpreadSheet(spreadsheets, irow, icol);
		}
		else {
			return Double.parseDouble(cellVal);
		}
	}
}
