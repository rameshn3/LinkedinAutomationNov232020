package com.qa.linkedin.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class LinkedinHomePage extends BasePageWeb{


	private Logger log = Logger.getLogger(LinkedinHomePage.class);
	
	public LinkedinHomePage() {
		
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//*[@class='nav__logo-link']")
	private WebElement linkedinLogo;
	
	@FindBy(linkText="Sign in")
	private WebElement signInLink;
	
	@FindBy(css="h1.header__content__heading")
	private WebElement welcomeBackheaderText;
	
	@FindBy(id="username")
	private WebElement emailEditbox;
	
	@FindBy(name="session_password")
	private WebElement passwordEditbox;
	
	@FindBy(xpath="//button[@type='submit' and @aria-label='Sign in']")
	private WebElement signInBtn;
	
	String homePageTitle="LinkedIn: Log In or Sign Up";
	String loginPageTitle="LinkedIn Login, Sign in | LinkedIn";
	public void verifyLinkedinHomePageTitle() {
		log.debug("wait for the Linkedin home page title");
		wait.until(ExpectedConditions.titleContains(homePageTitle));
		Assert.assertEquals(driver.getTitle(), homePageTitle);
	}
	
	public void verifyLinkedinLogo() {
		log.debug("wait for the Linkedin logo");
		wait.until(ExpectedConditions.visibilityOf(linkedinLogo));
		Assert.assertTrue(linkedinLogo.isDisplayed(), "Linkedin logo is not present");
	}
	
	public void clickSignInLink() throws InterruptedException {
		log.debug("click on sign in link element ");
		click(signInLink);
	}
	
	public void verifyLinkedinLoginPageTitle() {
		log.debug("wait for the Linkedin login page title");
		wait.until(ExpectedConditions.titleContains(loginPageTitle));
		Assert.assertEquals(driver.getTitle(), loginPageTitle);
	}
	
	public void verifyWelcomeBackHeaderText() {
		log.debug("wait for the welcomebackHeadertext ");
		wait.until(ExpectedConditions.visibilityOf(welcomeBackheaderText));
		Assert.assertTrue(welcomeBackheaderText.isDisplayed(), "Linkedin logo is not present");
	}
	
	public void clickSignInBtn() throws InterruptedException {
		log.debug("click on sign in Button element ");
		click(signInBtn);
	}
	
	public LinkedinLoggedinPage doLogin(String uname,String pwd) throws InterruptedException {
		log.debug("started executing the doLogin() ");
		sendKey(emailEditbox, uname);
		sendKey(passwordEditbox, pwd);
		clickSignInBtn();
		return new LinkedinLoggedinPage();
	}
	
}
