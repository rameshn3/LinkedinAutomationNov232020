package com.qa.linkedin.testcase;

import org.testng.annotations.Test;

import com.qa.linkedin.base.TestBase;
import com.qa.linkedin.pages.LinkedinHomePage;
import com.qa.linkedin.pages.LinkedinLoggedinPage;
import com.qa.linkedin.pages.SearchResultsPage;
import com.qa.linkedin.util.ExcelUtils;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.AfterClass;

public class SearchDDTest extends TestBase{
  private Logger log = Logger.getLogger(SearchDDTest.class);
  private LinkedinHomePage lHomePg;
  private LinkedinLoggedinPage loggedPg;
  private SearchResultsPage srPg;
  private static String fpath=System.getProperty("user.dir")+"\\src\\test\\java\\com\\qa\\linkedin\\data\\searchData.xlsx";
  @BeforeTest
  public void objectSetup() {
	log.debug("Initlize the objects"); 
	lHomePg = new LinkedinHomePage();
	loggedPg = new LinkedinLoggedinPage();
	srPg = new SearchResultsPage();
	
	  
  }
@BeforeClass
public void LoginSetup() throws InterruptedException, IOException {
	log.debug("Login related actions performed here");
	lHomePg.verifyLinkedinHomePageTitle();
	lHomePg.verifyLinkedinLogo();
	lHomePg.clickSignInLink();
	lHomePg.verifyLinkedinLoginPageTitle();
	lHomePg.verifyWelcomeBackHeaderText();
	loggedPg = lHomePg.doLogin(readPropertyValue("uname"), readPropertyValue("pwd"));
	
}
  
  
  @Test(dataProvider="getData")
  public void searchTest(String keyword) throws InterruptedException {
	log.debug("started executing the searchTest() for :"+keyword);
	loggedPg.verifyProfileRailCard();
	srPg=loggedPg.doPeopleSearch(keyword);
	Thread.sleep(2000);
	long count=srPg.getResultsCount();
	log.debug("results count for"+ keyword+" is :"+count);
	srPg.clickHomeTab();
  }
  
  @DataProvider
  public Object[][] getData() throws InvalidFormatException, IOException{
	  Object[][] data=new ExcelUtils().getTestData(fpath, "Sheet1");
	  return data;
  }
  
  
  
  @AfterClass
  public void afterClass() throws InterruptedException {
	  log.debug("perform the logout from application");
	  loggedPg.doLogout(); 
  }

}
