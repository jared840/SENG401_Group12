package UnitTests;

import entities.*;

import java.util.List;

import org.junit.*;
import org.junit.Assert.*;

public class WarehouseTests {
    public Warehouse tester;        //Warehouse entity to test

    //Warehouse test deafult constructor
    public WarehouseTests(){
        //do nothing
    }

    /**
     * Set-up method for the test. Instantiates a Warehouse object for the below Unit Tests
     */
    @Before 
    public void setUp(){
        tester = new Warehouse(123,"111 Street Ave, Calgary, AB", "XYZ Warehouse");
    }

/**
 * Test for the setter and getter of Warehouse's Address
 */
    @Test 
    public void testAddress(){
        System.out.println("Testing Warehouse's Address Setters & Getters Functionality...");
        String newAddr = "123 XXX St., Calgary, AB";
        tester.setAddress(newAddr);

        Assert.assertEquals(newAddr, tester.getAddress());
    }

    /**
 * Test for the setter and getter of Warehouse's Name
 */
@Test 
public void testName(){
    System.out.println("Testing Warehouse's Name Setters & Getters Functionality...");
    String newName = "ABC Shopping";
    tester.setFirstName(newName);

    Assert.assertEquals(newName, tester.getFirstName());
}

   /**
 * Test for Warehouse's inventory list functionality
 */
@Test 
public void testInventory(){
    System.out.println("Testing Warehouse's Inventory Functionality...");
    Product p1 = new Product(11,12, "Playstation 5", "PS5 Gaming System", 400.50, "Gaming", 3);
    tester.addInventory(p1);

    Assert.assertTrue(tester.getInventory().contains(p1));
}

   /**
 * Test for Warehouse's inventory list removal functionality
 */
@Test 
public void testInventoryRemoval(){
    System.out.println("Testing Warehouse's Inventory Removal Functionality...");
    Product p1 = new Product(11, 12, "Playstation 5", "PS5 Gaming System", 400.50, "Gaming", 3);
    tester.addInventory(p1);

    tester.removeInventory(p1);

    Assert.assertFalse(tester.getInventory().contains(p1));
}

   /**
 * Test for Warehouse's order list functionality
 */
@Test 
public void testOrders(){
    System.out.println("Testing Warehouse's Order Functionality...");
    Order testOrder = new Order();
    testOrder.setO_Date(2022, 10, 8);
    testOrder.setO_Total(123.45);
    testOrder.setOrder_ID(14);
    testOrder.setShip_Address("999 City Ave, Vacnouver, BC");

    tester.addOrder(testOrder);

    Assert.assertTrue(tester.getOrders().contains(testOrder));
}

  /**
 * Test for Warehouse's order removal list functionality
 */
@Test 
public void testOrdersRemoval(){
    System.out.println("Testing Warehouse's Order Removal Functionality...");
    Order testOrder = new Order();
    testOrder.setO_Date(2022, 10, 8);
    testOrder.setO_Total(123.45);
    testOrder.setOrder_ID(14);
    testOrder.setShip_Address("999 City Ave, Vacnouver, BC");

    tester.addOrder(testOrder);

    tester.removeOrder(testOrder);

    Assert.assertFalse(tester.getOrders().contains(testOrder));
}


}
