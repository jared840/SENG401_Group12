package Tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import database.DBController;
import junit.framework.Assert;

public class VerifyES {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	//Verifies that event sourcing occurred
	@Test
	public void test() {
		try {
			DBController db = new DBController("jdbc:mysql://localhost:3306/SENG401Project", "root", "password");
			int es_no = db.getEventSourceNumebr();
			Assert.assertTrue(es_no>0);
		}catch(Exception e) {
			fail();
		}
	}

}
