package test;

import main.SpreadResult;
import main.Spreadsheet;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.StringReader;

public class SpreadsheetTest {

	private Spreadsheet spreadsheet;
	private StringReader stringReader;
	private StringReader stringReaderCycle;

	@Before
	public void setup() {
		spreadsheet = new Spreadsheet();
		stringReader = new StringReader(
				"3 2\n" + "A2\n" + "4 5 *\n" + "A1\n" + "A1 B2 / 2 +\n" + "3\n" + "39 B1 B2 * /");
		stringReaderCycle = new StringReader(
				"3 2\n" + "A3\n" + "4 5 *\n" + "A1\n" + "A1 B2 / 2 +\n" + "3\n" + "39 B1 B2 * /");
	}

	@Test
	public void spreadsheetTest() throws Exception {

		BufferedReader reader = new BufferedReader(stringReader);
		String[][] actual = { { "20.00000", "20.00000", "20.00000" }, { "8.66667", "3.00000", "1.50000" } };
		SpreadResult[][] expected = this.spreadsheet.spreadsheet(reader);
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 3; j++) {
				Assert.assertEquals(String.format("%.5f", expected[i][j].getAns()), actual[i][j]);
			}
		}
	}

	@Test
	public void spreadsheetCyclicTest() throws Exception {
		BufferedReader reader = new BufferedReader(stringReaderCycle);
		String[][] actual = { { "null", "20.00000", "null" }, { "null", "3.00000", "null" } };
		SpreadResult[][] expected = this.spreadsheet.spreadsheet(reader);
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 3; j++) {
				Assert.assertEquals(String.format("%.5f", expected[i][j].getAns()), actual[i][j]);
			}
		}
	}
}