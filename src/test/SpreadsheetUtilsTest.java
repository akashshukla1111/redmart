package test;

import main.SpreadsheetUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SpreadsheetUtilsTest {

	private SpreadsheetUtils utils;

	@Before
	public void setup() {
		utils = new SpreadsheetUtils();
	}

	@Test
	public void calCulateRowIndexTest() throws Exception {
		int index = utils.calculateRowIndex("A");
		Assert.assertEquals(index, 1);
		int index1 = utils.calculateRowIndex("R");
		Assert.assertEquals(index1, 18);
		int index2 = utils.calculateRowIndex("BA");
		Assert.assertEquals(index2, 53);

	}

	@Test
	public void name() throws Exception {
		String[] ar = { "20.000", "3", "/", "2", "+" };
		double value = utils.calculateExpression(ar);
		Assert.assertEquals(value, 8.666666666666668);

	}

	@Test
	public void stringFormatter() throws Exception {
		Double d = 12.345299;
		d = Double.parseDouble(String.format("%.4f", d));
		//		Assert.assertThat(d,equalTo(12.3453));
	}
}