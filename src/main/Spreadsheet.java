package main;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Date;
/*

3 2
A1
4 5 *
A2
A1 B2 / 2 +
3
39 B1 B2 * /

		*/

public class Spreadsheet {

	public static final String OUTPUT = "output_";
	public static final String TXT = ".txt";
	SpreadsheetImpl impl = new SpreadsheetImpl();

	public static void main(String[] args) throws IOException {
		Spreadsheet spreadsheet = new Spreadsheet();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, Charset.defaultCharset()));
		spreadsheet.spreadsheet(reader);
	}

	public SpreadResult[][] spreadsheet(BufferedReader reader) throws IOException {

		String indexs = reader.readLine();
		String[] splitIndex = indexs.split(" ");
		int c = Integer.parseInt(splitIndex[0]);
		int r = Integer.parseInt(splitIndex[1]);
		SpreadResult[][] spreadsheets = new SpreadResult[r][c];

		for (int rw = 0; rw < r; rw++) {
			for (int cl = 0; cl < c; cl++) {
				spreadsheets[rw][cl] = new SpreadResult();
				spreadsheets[rw][cl].setInput(reader.readLine());
			}
		}
		BufferedWriter outputWriter = new BufferedWriter(new FileWriter(OUTPUT+new Date().getTime()+ TXT));
		outputWriter.write(indexs);
		outputWriter.newLine();
		for (int rw = 0; rw < r; rw++) {
			for (int cl = 0; cl < c; cl++) {
				double result = impl.recurSpreadSheet(spreadsheets, rw, cl);
				if (Double.MIN_VALUE == result) {
					outputWriter.write("cyclic");
					outputWriter.newLine();
				}
				else {
					outputWriter.write(String.format("%.5f", spreadsheets[rw][cl].getAns()));
					outputWriter.newLine();
				}
				System.out.println(spreadsheets[rw][cl]);
			}
		}
		outputWriter.flush();
		outputWriter.close();
		System.out.println("PLEASE CHECK THE output_*.txt FILE FOR RESULT");
		return spreadsheets;
	}
}

