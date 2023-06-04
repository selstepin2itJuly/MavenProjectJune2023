package testcases;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages.AdminPage;
import pages.DashboardPage;
import pages.LoginPage;
import testbase.TestBase;
import testbase.TestBaseObject;
import utilities.TestUtility;

import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;

public class AdminTest {
	 private LoginPage lp;
	 private WebDriver dr;
	 private DashboardPage dp;
	 private AdminPage ap;
	 TestBaseObject tb;
  @Test(description="Verify Table Count defult", priority=0)
  public void verifyTheTableCountDefault_TC003() {
	  
	  ap.clickOnAdmin();
	  boolean act = ap.isAdminHeaderTextDisplayed();
	  Assert.assertTrue(act);
	  int actCount = ap.getTheUsernamCountInTable();
	  tb.attachScreenshot();
	  Reporter.log(actCount+"-->"+36);
	  Assert.assertEquals(actCount, 36);
	 
	  
  }
  
  @Test(description="Verify First Row Data", priority=1)
  public void verifyFirstRowData_TC004()
  {
	  ap.clickOnAdmin();
	  boolean act = ap.isAdminHeaderTextDisplayed();
	  Assert.assertTrue(act);
	  List<String> actList = ap.getTheRecordFirstRow();
	  List<String> exp = new ArrayList<String>();
	  exp.add("Admin");
	  exp.add("Admin12");
	  exp.add("Paul Collings");
	  exp.add("Enabled1");
	  SoftAssert sa = new SoftAssert();
	  for(int i = 0;i<exp.size();i++) {
		  sa.assertEquals(actList.get(i), exp.get(i));
		  Reporter.log(actList.get(i)+"---->"+exp.get(i));
	  }
	  tb.attachScreenshot();
	  sa.assertAll();
	  
	  //Assert.assertEquals(actList, exp);
  }
  @BeforeClass(alwaysRun=true)
  public void beforeClass() throws IOException {
	  //dr = TestBase.getDriverInstance();
	  tb = new TestBaseObject();
	  dr = tb.getDriverInstance();
	  lp = new LoginPage(dr);
	  dp = new DashboardPage(dr);
	  ap = new AdminPage(dr);
	  lp.enterUsername("Admin")
	  .enterPassword("admin123")
	  .clickOnLogin();
	  boolean act = dp.isDashboadTextDisplayed();
	  tb.attachScreenshot();
	  Assert.assertTrue(act);
  }

  @AfterClass(alwaysRun=true)
  public void afterClass() {
	  dr.quit();
  }

}
