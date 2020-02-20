package driverscript;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.xmlbeans.impl.tool.Extension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commenFunctionLibrary.FunctionalLibrary;
import utilities.ExcelUtilitiesHy;

public class DriverScript {
	static WebDriver driver ;
	static ExtentReports reports;
	static ExtentTest test;

	public static void main(String[] args) throws Throwable {
		ExcelUtilitiesHy exl=new ExcelUtilitiesHy();
		for (int i=1;i<=exl.rowCount("MasterTestCases");i++){
			String ModuleStatus="";
			if(exl.getData("MasterTestCases", i,2).equalsIgnoreCase("Y")){
				
			String	TCModule=exl.getData("MasterTestCases", i, 1);
			
			reports=new ExtentReports("D:\\DEC_seleniumOJT82\\StackAccounting_Hybrid\\Reports\\report"+TCModule+FunctionalLibrary.getDate()+"html");
			test=reports.startTest(TCModule);
			for(int j=1;j<=exl.rowCount(TCModule);j++){
				String Description=exl.getData(TCModule, j, 0);
				String Function_Name=exl.getData(TCModule, j, 1);
				String Locator_Type=exl.getData(TCModule, j, 2);
				String Locator_Value=exl.getData(TCModule, j, 3);
				String Test_Data=exl.getData(TCModule, j, 4);
				try{
				if(Function_Name.equalsIgnoreCase("startBrowser")){
					driver=FunctionalLibrary.startBrowser();
				test.log(LogStatus.INFO	, Description);
				}else if(Function_Name.equalsIgnoreCase("openApplication")){
					FunctionalLibrary.openApplication(driver);
					test.log(LogStatus.INFO,Description);
				}else if(Function_Name.equalsIgnoreCase("waitForElement")){
					FunctionalLibrary.waitForElement(driver, Locator_Type, Locator_Value, Test_Data);
					test.log(LogStatus.INFO,Description);
					
				}else if(Function_Name.equalsIgnoreCase("typeAction")){
					FunctionalLibrary.typeAction(driver, Locator_Type, Locator_Value, Test_Data);
					test.log(LogStatus.INFO,Description);
				}else if(Function_Name.equalsIgnoreCase("clickAction")){
					FunctionalLibrary.clickAction(driver, Locator_Type, Locator_Value);
					test.log(LogStatus.INFO,Description);
				}else if(Function_Name.equalsIgnoreCase("captureData")){
					FunctionalLibrary.captureData(driver, Locator_Type, Locator_Value);
					test.log(LogStatus.INFO,Description);
				}
				
				else if(Function_Name.equalsIgnoreCase("Closebrw")){
				FunctionalLibrary.Closebrw(driver);
				test.log(LogStatus.INFO,Description);
			}
			exl.setData(TCModule, j, 5, "Pass");
			ModuleStatus="Pass";
			test.log(LogStatus.INFO,Description);
			}catch(Exception e){
				exl.setData(TCModule, j, 5, "Fail");
				ModuleStatus="Fail";
				
				String reqDate=FunctionalLibrary.getDate();
				
				File srcfile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				
				FileUtils.copyFile(srcfile, new File("D:\\DEC_seleniumOJT82\\StackAccounting_Hybrid\\ScreenShots\\screen"+Description+reqDate+".png"));
				test.log(LogStatus.INFO,Description);
				test.log(LogStatus.INFO, test.addScreenCapture("D:\\DEC_seleniumOJT82\\StackAccounting_Hybrid\\ScreenShots\\screen"+Description+reqDate+".png"));
				break;
				
				
			}
			}	
			if(ModuleStatus.equalsIgnoreCase("Pass")){
				exl.setData("MasterTestCases", i, 3, "Pass");
			}else{
				exl.setData("MasterTestCases", i, 3, "Fail");
					
				}
			reports.endTest(test);
			reports.flush();
		
		}else{
			exl.setData("MasterTestCases", i, 3, "Not Executed");
		
			 }
		}
		

	}

}
