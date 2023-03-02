package UnitTests;



import entities.*;

import java.util.List;

import org.junit.*;
import org.junit.Assert.*;

public class WarehouseWorkerTests {
    public WarehouseWorkers tester;
    public WarehouseWorkers tester2;
    public WarehouseWorkers tester3;
    public WarehouseWorkerTests()
    {
        //do nothing
    }

    @Before
    public void setup()
    {
        tester=new WarehouseWorkers(23, "J" , "user", "password");
        tester2=new WarehouseWorkers(1,"k", "uname","pword");
        tester3=new WarehouseWorkers(1,"i","nameuser", "pass");
    }

    @Test
    public void getIDTest()
    {
        int val=tester.getEmployee_ID();
        Assert.assertEquals(val,23);
    }

    @Test
    public void differentWarehouseWorkersID()
    {
        Assert.assertNotEquals(tester.getEmployee_ID(),tester2.getEmployee_ID());

    }

    @Test
    public void sameIDs()
    {
        Assert.assertEquals(tester2.getEmployee_ID(),tester3.getEmployee_ID());
    }

    @After
    public void tearDown1()
    {
        tester=null;
        tester2=null;
        tester3=null;
    }
}
