package main;

public class SpreadsheetImpl {

	SpreadsheetUtils utils = new SpreadsheetUtils();

	public double recurSpreadSheet(SpreadResult[][] spreadsheets, int row, int col) {

		SpreadResult cell = spreadsheets[row][col];

		if (cell.isVisited() && cell.getAns() != null) {
			return cell.getAns();
		}
		else if (cell.isVisited() && cell.getAns() == null) {
			return Double.MIN_VALUE;
		}
		if (!cell.isVisited()) {
			cell.setVisited(true);
		}

		String[] split = cell.getInput().split(" ");
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
		else {
			String cellVal = split[0];
			double result = recurRefOrValue(spreadsheets, cellVal);
			if (Double.MIN_VALUE != result) {
				cell.setAns(result);
			}
			return result;
		}
	}

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
