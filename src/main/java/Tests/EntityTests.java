package Tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ SupplierTests.class, UserTests.class, WarehouseTests.class, WarehouseWorkerTests.class })
public class EntityTests {

}
