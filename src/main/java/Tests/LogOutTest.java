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

import junit.framework.Assert;

public class LogOutTest {

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

		textField.type("ccc");
		textField2.type("ccc");
		select.setSelectedAttribute("Customer", true);

		final HtmlPage page2 = button.click();

		WebAssert.assertTextPresent(page2, "Welcome: ccc");
		WebAssert.assertTextPresent(page2, "Warehouse Website");

		//System.out.println(page2.getVisibleText());
		//HtmlAnchor anchor=page2.getAnchorByHref("allProductsController");
		//HtmlPage page3 = anchor.click();
		
		//WebAssert.assertTextNotPresent(page4, "oops...there seems to be no product matching what you're looking for");
		//System.out.println(page4.getVisibleText());
//System.out.println(page3.getVisibleText());
		//final List<HtmlForm> form2=page3.getForms();
		//final HtmlForm login2=form2.get(0);
		HtmlAnchor anchor2=page2.getAnchorByHref("SelectionPage.jsp");
		HtmlPage page4 = anchor2.click();
		
		
		//final HtmlPage page4 = button2.click();
		//String URL = (String)page4.getBaseURL();
		String url = page4.getBaseURI();
		Assert.assertEquals("http://localhost:8080/401Project/SelectionPage.jsp", url);

		x.close();

		//page2.get
		//Also assertEquals by URL: page2.getBaseURL()

		}catch(Exception e) {
			System.out.println("No bueno");
			System.out.println(e.getMessage());
		}
	}

}
