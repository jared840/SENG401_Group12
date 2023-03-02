package UnitTests;


import entities.*;

import java.util.List;

import org.junit.*;
import org.junit.Assert.*;

public class UserTests {
    public User tester;
    public Order testOrder;
    public Product testProduct;

    /**
     * Default constructor
     */
    public UserTests(){
        //do nothing yet
    }

    @Before
    public void startUp(){
        tester=new User(0, "A B", "888 Road", "A@email.com", "pswd", 10);
        testOrder=new Order();
        testProduct=new Product(345, 14, "Joe", "Computer System", 1284.56, "Laptops", 100);
    }

/**
 * Test case: testing the functionality of adding a product to a user's cart
 */
    @Test 
    public void addToCartTest(){
        tester.addProductToCart(testProduct);

        //Assert.assertEquals
    }



    @After
    public void tearDown(){
        tester=null;
    }
}
