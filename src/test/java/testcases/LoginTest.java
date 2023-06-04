package testcases;

import org.testng.annotations.Test;

import pages.DashboardPage;
import pages.LoginPage;
import testbase.TestBase;
import testbase.TestBaseObject;
import utilities.TestUtility;

import org.testng.annotations.BeforeMethod;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class LoginTest {
	
  private LoginPage lp;
  private WebDriver dr;
  private DashboardPage dp;
  private TestBaseObject tb;
  @Test(description="Verify Successful Login", priority=0)
  public void loginSuccessFul_TC001() {
	  lp.enterUsername("Admin")
	  .enterPassword("admin123")
	  .clickOnLogin();
	  boolean act = dp.isDashboadTextDisplayed();
	  tb.attachScreenshot();
	  Assert.assertTrue(act);
  }
  
  @Test(description="Verify Unsuccessful Login", priority=-1)
  public void loginUnSuccessFul_TC002() {
	  lp.enterUsername("Admin")
	  .enterPassword("admin123")
	  .clickOnLogin();
	  boolean act = dp.isDashboadTextDisplayed();
	  tb.attachScreenshot();
	  Assert.assertFalse(act);
  }
  @BeforeMethod(alwaysRun=true)
  public void beforeMethod() throws IOException {
	  //dr = TestBase.getDriverInstance();
	  tb = new TestBaseObject();
	  dr = tb.getDriverInstance();
	  lp = new LoginPage(dr);
	  dp = new DashboardPage(dr);
  }

  @AfterMethod(alwaysRun=true)
  public void afterMethod() {
	  dr.quit();
  }

}
