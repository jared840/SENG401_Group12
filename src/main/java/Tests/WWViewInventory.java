package Tests;

import static org.junit.Assert.*;

import java.util.List;

import org.htmlunit.WebAssert;
import org.htmlunit.WebClient;
import org.htmlunit.html.HtmlAnchor;
import org.htmlunit.html.HtmlForm;
import org.htmlunit.html.HtmlPage;
import org.htmlunit.html.HtmlPasswordInput;
import org.htmlunit.html.HtmlSelect;
import org.htmlunit.html.HtmlSubmitInput;
import org.htmlunit.html.HtmlTable;
import org.htmlunit.html.HtmlTableCell;
import org.htmlunit.html.HtmlTableRow;
import org.htmlunit.html.HtmlTextInput;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class WWViewInventory {

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
		
		HtmlAnchor anchor=page2.getAnchorByHref("findAllWarehouses");
		HtmlPage page3 = anchor.click();
		
		HtmlForm forms = page3.getForms().get(0);
		final HtmlSubmitInput button2 = forms.getInputByValue("SUBMIT");
		
		final HtmlSelect selector = forms.getSelectByName("WareSelected");
		selector.setSelectedAttribute("1: 639 Herzog River, Suite 042, 61707, Rogahnville, Missouri, United States", true);

		HtmlPage page4 = button2.click();
		
		
		HtmlTable table = (HtmlTable)page4.getByXPath("//table[@class='GeneratedTable']").get(0);
		
		//asserts inventory indeed shown:
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
		
		//testing present inventories:
		WebAssert.assertTextPresent(page4, "Rogahnville, Missouri");
		WebAssert.assertTextPresent(page4, "PS5");
		//testing known inventory row:
		HtmlTableRow row = table.getRow(2);
		HtmlTableCell cell = row.getCell(0);
		HtmlTableCell cell2 = row.getCell(1);
		HtmlTableCell cell3 = row.getCell(2);
		
		
		//System.out.println(cell.asNormalizedText());
		
		//asserts known inventory present:
		Assert.assertTrue(cell.asNormalizedText().equals("639 Herzog River, Suite 042, 61707, Rogahnville, Missouri, United States"));
		
		Assert.assertEquals("2", cell2.asNormalizedText());
		Assert.assertEquals("PS5", cell3.asNormalizedText());
		
	


		
		x.close();
		}catch(Exception e) {
			System.out.println("No bueno");
			System.out.println(e.getMessage());
		}
	}

}
