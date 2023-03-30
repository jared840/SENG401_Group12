package Tests;

import static org.junit.Assert.*;

import java.util.List;

import org.htmlunit.TextPage;
import org.htmlunit.UnexpectedPage;
import org.htmlunit.WebAssert;
import org.htmlunit.WebClient;
import org.htmlunit.WebWindow;
import org.htmlunit.WebWindowListener;
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

public class SupplierReportTest {

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
		
		HtmlAnchor anchor=page2.getAnchorByHref("CreateReport.jsp");
		HtmlPage page3 = anchor.click();
		
	
		
		//System.out.println(page4.getVisibleText());
		
		HtmlForm stockForm = page3.getForms().get(0);
		HtmlSubmitInput submit = stockForm.getInputByValue("Generate Supplier Report");
		HtmlTextInput Report_Title = stockForm.getInputByName("reportTitle");
		HtmlTextInput Report_Product = stockForm.getInputByName("reportProduct");
		
		final HtmlSelect selectWare = stockForm.getSelectByName("TransType");

		
		select.setSelectedAttribute("Created Product",true);
		Report_Title.type("My Report");
		Report_Product.type("iPad");
		
		WebWindow win = page3.getEnclosingWindow();
		
		submit.click();
		
		TextPage download =  (TextPage) win.getEnclosedPage();
		Assert.assertNotNull(download);
		
		//System.out.println(page5.getVisibleText());
		
		//x.addWebWindowListener(new WebWindowListener());
		
		//WebAssert.assertTextPresent(page5, "Stocked item successfully");
		
		

	
		x.close();
		}catch(Exception e) {
			System.out.println("No bueno");
			System.out.println(e.getMessage());
		}
	}

}
