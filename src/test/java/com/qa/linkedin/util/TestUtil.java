package com.qa.linkedin.util;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.qa.linkedin.base.TestBase;

public class TestUtil extends TestBase{

	private static Logger log = Logger.getLogger(TestUtil.class);
	/**
	 * it captures the screenshot and stores in given path
	 * @param methodName
	 * @return
	 * @throws IOException
	 */
	public static String captureScreenshot(String methodName) throws IOException {
			
			String fileName=getScreenshotName(methodName);
		    String directory="target/surefire-reports/failedTestScreenshots/";
			//String directory=System.getProperty("user.dir")+"/target/surefire-reports/failedTestScreenshots/";
			new File(directory).mkdirs();
			String path=directory + fileName;
			try {
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile,	new File(path));
			log.debug("********************************************************************************");
			log.debug("Screenshot stored at path: "+path);
			log.debug("********************************************************************************");
			}catch(Exception e) {
				e.printStackTrace();
			}
			return path;
		}

		public static String getScreenshotName(String methodName) {
			Date d = new Date();
			String fileName = methodName+"-"+ d.toString().replace(":", "_").replace(" ", "_") + ".png";
			return fileName;
		}

	

}
