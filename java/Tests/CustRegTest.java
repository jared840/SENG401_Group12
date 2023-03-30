package Tests;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Random;

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
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CustRegTest {

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
		HtmlAnchor anch = page.getAnchorByHref("CustomerReg.jsp");
		HtmlPage page2 = anch.click();
		
		final List<HtmlForm> form=page2.getForms();
		final HtmlForm login=form.get(0);

		final HtmlSubmitInput button = login.getInputByValue("Register");
		final HtmlTextInput textField = login.getInputByName("nameuser");
		final HtmlTextInput textField2 = login.getInputByName("usernameuser");

		final HtmlPasswordInput textField3 = login.getInputByName("passworduser");
		final HtmlTextInput textField4 = login.getInputByName("emailuser");
		final HtmlTextInput textField5 = login.getInputByName("addressuser");
		final HtmlTextInput textField6 = login.getInputByName("creditcarduser");

		Random rr = new Random();
		String rand = "Mr.";
		for(int i = 0; i<6; i++) {
			
		
		
		char ch = (char)(rr.nextInt(26)+'a');
		
		rand +=ch;
		}

		textField.type(rand);
		textField2.type(rand);

		textField3.type(rand);
		textField4.type(rand);
		textField5.type(rand);
		textField6.type(String.valueOf(rr.nextInt(10000)));


		
		

		final HtmlPage pagenext = button.click();
		
WebAssert.assertTextPresent(pagenext, "Warehouse Website");		


		
		x.close();
		//page2.get
		//Also assertEquals by URL: page2.getBaseURL()

		}catch(Exception e) {
			System.out.println("No bueno");
			System.out.println(e.getMessage());
		}
	}

}
