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
import org.htmlunit.html.HtmlTextInput;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestStock {

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

		textField.type("s");
		textField2.type("s");
		select.setSelectedAttribute("Supplier", true);

		final HtmlPage page2 = button.click();

		//WebAssert.assertTextPresent(page2, "Hello Supplier");
		//System.out.println(page2.getVisibleText());
		
		HtmlAnchor anchor=page2.getAnchorByHref("stockItemsServlet");
		HtmlPage page3 = anchor.click();
		
		
	
		
		
		
		
		//Now assert that stocking is available for products
		WebAssert.assertLinkPresentWithText(page3, "Stock Item");
		
		HtmlAnchor anchor2 = page3.getAnchorByText("Stock Item");
		HtmlPage page4 = anchor2.click();
		
		//System.out.println(page4.getVisibleText());
		
		HtmlForm stockForm = page4.getForms().get(0);
		HtmlSubmitInput submit = stockForm.getInputByValue("SUBMIT");
		HtmlTextInput stockno = stockForm.getInputByName("stockno");
		
		final HtmlSelect selectWare = stockForm.getSelectByName("WareSelect");

		
		select.setSelectedAttribute("1: 639 Herzog River, Suite 042, 61707, Rogahnville, Missouri, United States", true);
		stockno.type("100");
		
		HtmlPage page5 = submit.click();
		System.out.println(page5.getVisibleText());
		
		WebAssert.assertTextPresent(page5, "Stocked item successfully");
		
		

		
		x.close();
		}catch(Exception e) {
			System.out.println("No bueno");
			System.out.println(e.getMessage());
		}
	}

}
