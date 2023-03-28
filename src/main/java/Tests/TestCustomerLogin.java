package Tests;

import static org.junit.Assert.*;

import java.util.List;

import org.htmlunit.WebAssert;
import org.htmlunit.WebClient;
import org.htmlunit.html.HtmlForm;
import org.htmlunit.html.HtmlPage;
import org.htmlunit.html.HtmlPasswordInput;
import org.htmlunit.html.HtmlSelect;
import org.htmlunit.html.HtmlSubmitInput;
import org.htmlunit.html.HtmlTextInput;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestCustomerLogin {

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

		System.out.println(page2.getVisibleText());
		//page2.get
		x.close();
		//Also assertEquals by URL: page2.getBaseURL()

		}catch(Exception e) {
			System.out.println("No bueno");
			System.out.println(e.getMessage());
		}
	}

}
