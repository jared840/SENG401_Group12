package Tests;

import static org.junit.Assert.*;

import java.util.List;

import org.htmlunit.TextPage;
import org.htmlunit.WebClient;
import org.htmlunit.WebWindow;
import org.htmlunit.html.HtmlAnchor;
import org.htmlunit.html.HtmlForm;
import org.htmlunit.html.HtmlPage;
import org.htmlunit.html.HtmlPasswordInput;
import org.htmlunit.html.HtmlSelect;
import org.htmlunit.html.HtmlSubmitInput;
import org.htmlunit.html.HtmlTable;
import org.htmlunit.html.HtmlTableBody;
import org.htmlunit.html.HtmlTableCell;
import org.htmlunit.html.HtmlTableRow;
import org.htmlunit.html.HtmlTextInput;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestWWViewOrders {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		final WebClient x = new WebClient();
		try {
		final HtmlPage page = x.getPage("http://localhost:8080/401Project/SelectionPage.jsp");
		final List<HtmlForm> form=page.getForms();
		final HtmlForm login=form.get(0);

		final HtmlSubmitInput button = login.getInputByValue("Login");
		final HtmlTextInput textField = login.getInputByName("userin");
		final HtmlPasswordInput textField2 = login.getInputByName("pswd_in");
		//final HtmlTextInput textField3 = login.getInputByName("UserType");
		final HtmlSelect select = login.getSelectByName("UserType");

		textField.type("ww1");
		textField2.type("ww1");
		select.setSelectedAttribute("WarehouseWorker", true);

		final HtmlPage page2 = button.click();

		//WebAssert.assertTextPresent(page2, "Hello Supplier");
		//System.out.println(page2.getVisibleText());
		
		HtmlAnchor anchor=page2.getAnchorByHref("GenericViewOrder");
		HtmlPage page3 = anchor.click();
		
	
		HtmlTable table = (HtmlTable)page3.getByXPath("//table[@class='GeneratedTable']").get(0);
		
		//asserts orders are indeed shown:
		Assert.assertTrue(table.getRowCount()>0);
		
		/*for(HtmlTableBody body : table.getBodies()) {
			List<HtmlTableRow> rows = body.getRows();
			System.out.println(rows.get)
		}*/
		
		for(final HtmlTableRow row : table.getRows()) {
			System.out.println("Found row");
			for(final HtmlTableCell cell : row.getCells()) {
				System.out.println(" Found cell: "+cell.asNormalizedText());
				
			}
		}
		//testing known order #2 is found and correct:
		HtmlTableRow row = table.getRow(3);
		HtmlTableCell cell = row.getCell(4);
		HtmlTableCell cell2 = row.getCell(5);
		//System.out.println(cell.asNormalizedText());
		
		//asserts this known, shipped order is marked shipped:
		Assert.assertTrue(cell2.asNormalizedText().equals("ORDER SHIPPED"));
		//assert other info for this known order:
		Assert.assertEquals("10", row.getCell(0).asNormalizedText());
		Assert.assertEquals("123 Drive", row.getCell(3).asNormalizedText());
		
	


		
		x.close();
		}catch(Exception e) {
			System.out.println("No bueno");
			System.out.println(e.getMessage());
		}
	}

}
