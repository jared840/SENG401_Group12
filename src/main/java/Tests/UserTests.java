package Tests;


import entities.*;

import java.util.Date;
import java.util.List;

import org.junit.*;
import org.junit.Assert.*;

//Note: need to add an order constructor other than the default constructor before adding order testing functionalities

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
        //sample user to test:
        tester=new User(0, "A B", "888 Road", "A@email.com", "pswd", 10);
        
        //sample order object to test:
        Date d=new Date(1,2,2023);
        testOrder=new Order(0,d,100,"x");
        testOrder.setO_Date(1, 12, 2022);
        testOrder.setO_Total(100);
        testOrder.setOrder_ID(0);
        testOrder.setShip_Address("101 University Road, Calgary, AB");

        //sample product to test:
        testProduct=new Product(345, 14, "Joe", "Computer System", 1284.56, "Laptops", 100);
    }

/**
 * Test case: testing the functionality of adding a product to a user's cart
 * Thus, tests both the add to cart functionality, as well as the getter for the user's cart!
 * Assert: Asserts that the sample test product is in fact contained within the user's cart
 */
    @Test 
    public void addToCartTest(){
        System.out.println("Testing User add to cart functionality ....");
        tester.addProductToCart(testProduct);

        Assert.assertTrue(tester.getCartProducts().contains(testProduct));
    }

    /**
     * Test case: testing ability to remove items from a user's cart
     * Adds product to cart, then removes the product. Asserts that the user's cart does NOT contain the prodcut that was removed
     */
    @Test 
    public void removeFromCartTest(){
        System.out.println("Tersting cart removal for user...");
        testProduct=new Product(1000, 678, "Hard Drive", "Computer external memory ", 250.50, "Hard Drives & Memory", 13);
        tester.addProductToCart(testProduct);

        tester.removeProductFromCart(testProduct);
        Assert.assertFalse(tester.getCartProducts().contains(testProduct));

    }

    /**
     * Test case: testing order functionality of a user
     * Tests ability to add an otder, as well as the getter for orders
     */
    @Test 
    public void orderTests(){
        System.out.println("Testing user's order functionality...");
        tester.addOrder(testOrder);

        Assert.assertTrue(tester.getOrders().contains(testOrder));
        
        
    }


    //Setter & Getter Tests:
    //---------------------------------------------------

    /**
     * Test case: testing the ID of a user
     * Idea is to retrieve the current ID, increment it by arbitrary value (10) to ensure ID is changed, then assert that the ID is changed to that value
     */
    @Test 
    public void testID(){
        System.out.println("Testing users' ID functionality...");
        int prev_ID = tester.getUser_ID();
        tester.setUser_ID(prev_ID+10);

        Assert.assertEquals(prev_ID+10, tester.getUser_ID());

    }

/**
 * Test case: testing the ability to change the name of a user
 */
    @Test 
    public void testName(){
        System.out.println("Testing user name functionality...");
        tester.setName("ASDFG");

        Assert.assertEquals(tester.getName(), "ASDFG");
    }

    /**
     * Test case: Ensure exception thrown when setting name to blank string
     */
    /* 
    @Test(expected=Exception.class)
    public void testNameException(){
        System.out.println("Testing user name exception handling...");
        
        tester.setName("");
        
        
        
    }*/

    /**
     * Test case: testing address of user
     */
    @Test 
    public void testAddress(){
        System.out.println("Testing user address functionality...");
        tester.setUserAddress("111 Road");

        Assert.assertEquals(tester.getUserAddress(), "111 Road");
    }
    



    @After
    public void tearDown(){
        tester=null;
    }
}