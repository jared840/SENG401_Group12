package Tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ LoginWWTest.class, TestCustomerLogin.class, TestIncorrectLogin.class, TestLogin.class })
public class LoginTestSuite {

}
