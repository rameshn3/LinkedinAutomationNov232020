package com.qa.linkedin.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class LinkedinLoggedinPage extends BasePageWeb{


	private Logger log = Logger.getLogger(LinkedinLoggedinPage.class);
	
	public LinkedinLoggedinPage() {
		
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="div[class*='profile-rail-card']")
	private WebElement profileRailCard;
	
	@FindBy(xpath="//img[@class='global-nav__me-photo ember-view']")
	private WebElement profileImageIcon;
	
	@FindBy(xpath = "//a[contains(@data-control-name,'nav.settings_signout')]")
	private WebElement signOutLink;
	
	@FindBy(xpath="//input[contains(@class,'search-global-typeahead__input')]")
	private WebElement searchEditbox;
	
	public void verifyProfileRailCard() {
		log.debug("wait for the profileRailCard ");
		wait.until(ExpectedConditions.visibilityOf(profileRailCard));
		Assert.assertTrue(profileRailCard.isDisplayed(), "profileRailCard is not present");
	}
	
	public void doLogout() throws InterruptedException {
		log.debug("perform the logout operation from application");
		click(profileImageIcon);
		click(signOutLink);
	}
	
	public SearchResultsPage doPeopleSearch(String keyword) throws InterruptedException {
		log.debug("perform the type action");
		sendKey(searchEditbox,keyword);
		Thread.sleep(2000);
		searchEditbox.sendKeys(Keys.ENTER);
		return new SearchResultsPage();
	}
	
}
