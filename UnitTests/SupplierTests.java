package UnitTests;


import entities.*;

import java.util.List;

import org.junit.*;
import org.junit.Assert.*;

public class SupplierTests{
    public Supplier tester;
    public List<Product> products;
    Product p1, p2, p3;
    /**
     * Test default constructor
     */
    public SupplierTests(){

    }

    /**
     * Setup method. Creates Supplier object to test.
     */
    @Before
    public void setup(){
        //create a supplier to test
        tester = new Supplier("J.R.", "Lundy", "Independent electronic supplier", "JRL_2k", "abcd1234");

        //create products for testing the product list later
        p1 = new Product("a1", "Playstation 5", "PS5 Gaming System", 400.50, "Gaming", 3);
        p2 = new Product("a2", "XBox One", "XBoc One Gaming System", 387.25, "Gaming", 10);
        p3 = new Product("a3", "Sony TV", "Sony Television", 505.35, "TVs", 2);
        
        



    }

    /**
     * Case: Testing the Supplier Constructor functionality
     * Expectation: PASS -> Expects correct constructor instantiation, and therefore the identical Supplier objects should assert true
     */
    @Test 
    public void SupplierConstructorTest() {
        System.out.println("Testing Supplier Constructor Functionality...");
        
        Supplier tester2 = new Supplier("J.R.", "Lundy", "Independent electronic supplier", "JRL_2k", "abcd1234");
        Assert.assertEquals(tester.getFirstName(), tester2.getFirstName());
        Assert.assertEquals(tester.getLastName(), tester2.getLastName());
        Assert.assertEquals(tester.getPassword(), tester2.getPassword());
        Assert.assertEquals(tester.getDescription(), tester2.getDescription());



    }

    /**
     * Case: Testing Supplier Constructor Functionality
     * Expectation: Two unequal Supplier objects created and not equal
     */
    @Test 
    public void SupplierConstructorNotEqual(){
        System.out.println("Testing Supplier Constructor Functionality...");

        Supplier tester2 = new Supplier("John", "Doe", "Unknown", "xxx", "pswd");
        Assert.assertNotEquals(tester.getFirstName(), tester2.getFirstName());
        Assert.assertNotEquals(tester.getLastName(), tester2.getLastName());
        Assert.assertNotEquals(tester.getPassword(), tester2.getPassword());
        Assert.assertNotEquals(tester.getDescription(), tester2.getDescription());
    }

    /**
     * Testing Supplier's setter and getter for First Name
     * Expects that the name is properly set and properly retrieved via the getter
     */
   @Test 
   public void testFirstName(){
    System.out.println("Testing Supplier First Name getter & setter Functionality...");

    tester.setFirstName("Joe");
    Assert.assertTrue(tester.getFirstName().equals("Joe"));
   }

    /**
     * Testing Supplier's setter and getter for Last Name
     * Expects that the name is properly set and properly retrieved via the getter
     */
   @Test 
   public void testLastName(){
    System.out.println("Testing Supplier Last Name getter & setter Functionality...");

    tester.setLastName("Johnson");
    Assert.assertTrue(tester.getLastName().equals("Johnson"));
   }   

   /**
     * Testing Supplier's setter and getter for Description
     * Expects that the description is properly set and properly retrieved via the getter
     */
    @Test 
    public void testDescription(){
        System.out.println("Testing Supplier Description getter & setter Functionality...");

        String desc="Here is a sample description";
     tester.setDescription(desc);
     Assert.assertTrue(tester.getDescription().equals(desc));
    }

    /**
     * Testing Supplier's setter and getter for Username
     * Expects that the username is properly set and properly retrieved via the getter
     */
    @Test 
    public void testUsername(){
        System.out.println("Testing Supplier Username getter & setter Functionality...");

        String user="Sample_User";
     tester.setUsername(user);
     Assert.assertTrue(tester.getUsername().equals(user));
    }

    /**
     * Testing Supplier's setter and getter for Password
     * Expects that the password is properly set and properly retrieved via the getter
     */
    @Test 
    public void testPassword(){
        System.out.println("Testing Supplier Password getter & setter Functionality...");

        String pass="12345";
     tester.setPassword(pass);
     Assert.assertTrue(tester.getPassword().equals(pass));
    }



    /**
     * Testing Supplier's adding method for Products
     * Expects that the products are added to the Supplier's list and the Supplier's list size matches the number of prodcuts
     */
    /*  //removed for now - JUnit order not guaranteed so cannot test size -> TODO: Add clear method in Supplier first
    @Test 
    public void testAddProductsSize(){
        System.out.println("Testing Supplier Product 'add products' Functionality...");

        
     tester.addProduct(p1);
     tester.addProduct(p2);
     tester.addProduct(p3);
     Assert.assertEquals(3, tester.getProducts().size());
     
    }
    */

     /**
     * Testing Supplier's addition method for Products
     * Expects to add a prodcut and ensures that the Product list contains that product
     */
    @Test 
    public void testAddProducts(){
        System.out.println("Testing Supplier Product 'add products' Functionality...");

        Product p4 = new Product("a4", "Beats Headphones", "Beats Audio Headphones and Charger", 200.00, "Audio", 100);
        tester.addProduct(p4);

     
     Assert.assertTrue(tester.getProducts().contains(p4));
    }

     /**
     * Testing Supplier's removal method for Products
     * Expects the clean removal of a products from a Supplier's product List. 
     * Note: adds the product, then removes that product ... finally checks that this element was removed
     */
    @Test 
    public void testRemoveProducts(){
        System.out.println("Testing Supplier Product removal Functionality...");

       Product p5 = new Product("a5", "HP Pavillion", "HP Pavillion Laptop", 150.50, "Computers&Laptops", 0);
        
       tester.addProduct(p5);
        tester.removeProduct(p5);
    
     Assert.assertFalse(tester.getProducts().contains(p5));
    }

    
    //Tear Down method -> turns all used objects to NULL
    @After
    public void tearDown(){
        tester=null;
        products=null;
        p1=null;
        p2=null;
        p3=null;
    }
}
