package driverscript;

import org.openqa.selenium.WebDriver;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commenFunctionLibrary.FunctionalLibrary;

public class Dummyclass {

	public static void main(String[] args) throws Throwable {
		
		ExtentReports report=new ExtentReports("D:\\DEC_seleniumOJT82\\StackAccounting_Hybrid\\Reports\\Extentreports.html");
		
		ExtentTest write=report.startTest("Login Function");
		WebDriver driver1=FunctionalLibrary.startBrowser();
		
		
		
		FunctionalLibrary.openApplication(driver1);
		write.log(LogStatus.INFO, "waiting for Luanching the webapp");
		
		FunctionalLibrary.waitForElement(driver1, "id", " username", "10");
		FunctionalLibrary.typeAction(driver1, "id", "username", "admin");
		write.log(LogStatus.INFO, "waiting for Username");
		
		FunctionalLibrary.waitForElement(driver1, "id", "password", "10");
		FunctionalLibrary.typeAction(driver1, "name","password","master");
		write.log(LogStatus.INFO, "waiting for Password");
		
		FunctionalLibrary.waitForElement(driver1, "xpath", "//*[@id='btnsubmit']", "10");
		FunctionalLibrary.clickAction(driver1, "xpath", "//*[@id='btnsubmit']");
		write.log(LogStatus.INFO, "waiting for clicking Login button");
		
		FunctionalLibrary.waitForElement(driver1, "id", "logout", "10");
		FunctionalLibrary.clickAction(driver1, "xpath", "logout");
		write.log(LogStatus.INFO, "waiting for clicking Logout button");
		
		write.log(LogStatus.PASS, "Login successfully");
		report.endTest(write);
		report.flush();
		
	}

}
