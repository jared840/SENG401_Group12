package Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.htmlunit.CollectingAlertHandler;
import org.htmlunit.WebAssert;
import org.htmlunit.WebClient;
import org.htmlunit.WebResponse;
import org.htmlunit.html.DomElement;
import org.htmlunit.html.HtmlAnchor;
import org.htmlunit.html.HtmlButton;
import org.htmlunit.html.HtmlDivision;
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

public class CustAddCartTest {

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

		textField.type("testcustomer");
		textField2.type("testcustomer");
		select.setSelectedAttribute("Customer", true);

		final HtmlPage page2 = button.click();

		WebAssert.assertTextPresent(page2, "Welcome: testcustomer");
		WebAssert.assertTextPresent(page2, "Warehouse Website");

		//System.out.println(page2.getVisibleText());
		HtmlAnchor anchor=page2.getAnchorByHref("allProductsController");
		HtmlPage page3 = anchor.click();
		
		//WebAssert.assertTextNotPresent(page4, "oops...there seems to be no product matching what you're looking for");
		//System.out.println(page4.getVisibleText());
//System.out.println(page3.getVisibleText());
	//	WebAssert.assertTextPresent(page3, "View More");
	//WebAssert.assertTextPresent(page3,"PS5 Controller");
		
		System.out.println(page3.getUrl());
		String content = x.getPage(page3.getBaseURL()).getWebResponse().getContentAsString();
		
		HtmlForm fff = page3.getForms().get(0);
		/*final HtmlSubmitInput bu = fff.getInputByValue("View Details");
		final HtmlPage page4 = bu.click();
		
		System.out.println(page4.getVisibleText());*/
		//System.out.println(fff.asNormalizedText());
		
		//System.out.println(page3.getForms().size());
		
		//HtmlAnchor anch  = page3.getForms().get(2).getInp
		HtmlButton submission  = (HtmlButton) page3.getElementsByTagName("button").get(2);
		HtmlPage page4 = submission.click();
		
		System.out.println(page4.getVisibleText());
		
		//asserts ability to add to cart:
		WebAssert.assertTextPresent(page4, "Add to cart");
		
		HtmlDivision addcart = (HtmlDivision) page4.getElementById("addToCart");
	//System.out.println(addcart.asNormalizedText());
	//HtmlButton addto = (HtmlButton)addcart;
		addcart.click();
		
		//assert successful: 
		WebResponse response = page4.getWebResponse();
		Assert.assertEquals(200, response.getStatusCode());
		
		//assert that window alert appeared:
		
		/*final List alerts = new ArrayList();
		x.setAlertHandler(new CollectingAlertHandler(alerts));
		
		Assert.assertTrue(alerts.size()>0);
		*/
		//x.getWebWindows().get(0).getHistory().back();
		HtmlPage backwards = x.getPage("http://localhost:8080/401Project/credentialServlet?userin=testcustomer&pswd_in=testcustomer&UserType=Customer");
		System.out.println(backwards.getVisibleText());
		
		HtmlAnchor ann = backwards.getAnchorByHref("CartController");
		HtmlPage page6 = ann.click();
			System.out.println(page6.getVisibleText());
			
			//assert that GTA V is now present in the cart:
			WebAssert.assertTextPresent(page6, "GTA V");
			
			//assert cancel order is available:
			WebAssert.assertTextPresent(page6, "Remove all");
			
			HtmlAnchor place_order = page6.getAnchorByHref("ProcessOrderController");
			HtmlPage fin = place_order.click();
			
			System.out.println(fin.getVisibleText());
			
			//assert the order screen is now presented'
			
			//Assert.assertEquals("http://localhost:8080/401Project/ProcessOrderController", fin.getUrl());
		Assert.assertTrue(fin.getBaseURI().equals("http://localhost:8080/401Project/ProcessOrderController"));
		//asserts payment available:
			WebAssert.assertTextPresent(fin, "Payment Method");
		
			
			HtmlButton submissions  = (HtmlButton) fin.getElementsByTagName("button").get(0);
			
			HtmlTextInput card = (HtmlTextInput)fin.getElementById("num");
			card.type("1294823");
			HtmlPage ppp = submissions.click();
			
			//asserts order processed & completed:
			WebResponse response2 = ppp.getWebResponse();
			Assert.assertEquals(200, response.getStatusCode());
			//System.out.println(ppp.getBaseURI());
			
			//assert order complete:
			//WebAssert.assertTextPresent(ppp, "Thanks you for your order");
			//Assert.assertEquals(ppp.getBaseURI(), "http://localhost:8080/401Project/OrderComplete");
			
			//System.out.println(submissions.asNormalizedText());

	//List<HtmlAnchor> xxx = page4.getAnchors();
	//System.out.println(xxx.get(0).getHrefLangAttribute());
	/*HtmlAnchor anch = page4.getAnchorByHref("CartController");
	HtmlPage next_page = anch.click();
	System.out.println(next_page.getVisibleText());*/
	//System.out.println(page6.getVisibleText());
	
	//x.getWebWindows().get(0).getHistory().back();
		x.close();

		//page2.get
		//Also assertEquals by URL: page2.getBaseURL()

		}catch(Exception e) {
			System.out.println("No bueno");
			System.out.println(e.getMessage());
		}
	}

}
