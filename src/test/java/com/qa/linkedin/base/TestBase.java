package com.qa.linkedin.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

private Logger log=Logger.getLogger(TestBase.class);
public static WebDriver driver=null;
public WebDriverWait wait=null;
public Properties prop=null;
	
	public String readPropertyValue(String key) throws IOException {
		log.info("create object for Properties");
		prop = new Properties();
		log.debug("read the properties file"); 
		try {
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\com\\qa\\linkedin\\config\\config.properties");
			log.info("load all the properties");
			prop.load(fis);
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		return prop.getProperty(key);
	}
	
	@BeforeSuite
	public void setup() throws IOException {
		log.info("get the browser value from properties file");
		String browserName=readPropertyValue("browser");
		if (browserName.equalsIgnoreCase("firefox")) {
			log.debug("set the WebdriverManager setup");
			WebDriverManager.firefoxdriver().setup();
			// interface refvar=new implmentingclass();
			driver = new FirefoxDriver();
			log.debug("Firefox browser is launched");
		} else if (browserName.equalsIgnoreCase("chrome")) {
			log.debug("set CHROME_DRIVER_SILENT_OUTPUT_PROPERTY to avoid TImeOut log in console");
			System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");

			log.debug("set webdrivermanager setup for chrome");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			log.debug("Chrome browser is launched");
		} else if (browserName.equalsIgnoreCase("edge")) {
			log.debug("set the edge browser related exe with webdrivermanager");
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			log.debug("Edge browser is launched");
		} else if (browserName.equalsIgnoreCase("ie")) {
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
			log.debug("Chrome browser is launched");
		}

		driver.manage().window().maximize();
		log.debug("maximized the window");
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		log.debug(" added implicit wait");
		log.debug("create Object for WebDriverWait class");
		wait = new WebDriverWait(driver, 30);
		driver.get(readPropertyValue("url"));
		log.debug("application url is launched");
	}
	
	@AfterSuite
	public void tearDown() {
		log.debug("started executing the browser tearDown()");
		if(driver!=null) {
			log.debug("close the browser");
			driver.close();
		}
	}
	
}
