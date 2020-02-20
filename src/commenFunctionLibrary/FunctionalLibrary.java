package commenFunctionLibrary;




import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.crypto.Data;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



import freemarker.template.SimpleDate;
import utilities.PropertyFileUtil;

public class FunctionalLibrary {
	static WebDriver driver;
	
	public static WebDriver startBrowser() throws Throwable{
		if(PropertyFileUtil.getValueForkey("Browser").equalsIgnoreCase("chrome")){
			System.setProperty("webdriver.chrome.driver", "D:\\DEC_seleniumOJT82\\StackAccounting_Hybrid\\Drivers\\chromedriver.exe");
		 driver=new ChromeDriver();
			
		}else if(PropertyFileUtil.getValueForkey("Browser").equalsIgnoreCase("firefox")){
			System.setProperty("webdriver.gecko.driver", "D:\\DEC_seleniumOJT82\\StackAccounting_Hybrid\\Drivers\\geckodriver.exe");
		 driver=new ChromeDriver();
		}
		else{
			System.setProperty("webdriver.ie.driver", "D:\\DEC_seleniumOJT82\\StackAccounting_Hybrid\\Drivers\\IEDriverServer.exe");
		 driver=new ChromeDriver();
		}
		return driver;
	}
	public static void openApplication(WebDriver driver) throws Throwable{
		driver.get(PropertyFileUtil.getValueForkey("Url"));
		driver.manage().window().maximize();
		
	}
	public static void waitForElement(WebDriver driver,String locatortype,String locatorValue,String waittime){
		WebDriverWait mywait=new WebDriverWait(driver, Integer.parseInt(waittime));
		if(locatortype.equalsIgnoreCase("id")){
			mywait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue)));
		} else
		if(locatortype.equalsIgnoreCase("name")){
			mywait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorValue)));
		}else
		if(locatortype.equalsIgnoreCase("xpath")){
			mywait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));
			}
		else {
			System.out.println("Unable to locate wait for element method with"+locatortype);
		}
	}
	public static void typeAction(WebDriver driver,String locatortype,String locatorValue,String teatdata){
		if(locatortype.equalsIgnoreCase("id")){
			driver.findElement(By.id(locatorValue)).clear();
			driver.findElement(By.id(locatorValue)).sendKeys(teatdata);
		}
		else if(locatortype.equalsIgnoreCase("name")){
		driver.findElement(By.name(locatorValue)).clear();
				driver.findElement(By.name(locatorValue)).sendKeys(teatdata);
		}else
		if(locatortype.equalsIgnoreCase("xpath")){
			driver.findElement(By.xpath(locatorValue)).clear();
			driver.findElement(By.xpath(locatorValue)).sendKeys(teatdata);
		}else{
			System.out.println("unable to locate for typeAction method with"+locatortype);
		}
	}
		public static void clickAction(WebDriver driver,String locatortype,String locatorValue){
			if(locatortype.equalsIgnoreCase("id")){
				driver.findElement(By.id(locatorValue)).click();
		}else
			if(locatortype.equalsIgnoreCase("name")){
				driver.findElement(By.name(locatorValue)).click();
			}else
			if(locatortype.equalsIgnoreCase("xpath")){
				driver.findElement(By.xpath(locatorValue)).click();
			}else{
				System.out.println("unable to locate for click action method with"+locatortype);
			}
		
	}
		public static void Closebrw(WebDriver driver){
			driver.close();
		}
		public static String getDate(){
			Date d=new Date();
			SimpleDateFormat rawdateformat=new SimpleDateFormat("YYYY_MM_DD_hh_mm_ss");
		String 	mdate=rawdateformat.format(d);
			
			return mdate;
			
		}
	
		public static void captureData(WebDriver driver,String locatortype,String locatorvalue) throws Exception{
			String supplierdata="";
			
			if(locatortype.equalsIgnoreCase("id")){
				supplierdata=driver.findElement(By.id(locatorvalue)).getAttribute("value");
				}
				else if(locatortype.equalsIgnoreCase("name")){
					supplierdata=driver.findElement(By.name(locatorvalue)).getAttribute("value");
				}else if(locatortype.equalsIgnoreCase("xpath")){
					supplierdata=driver.findElement(By.xpath(locatorvalue)).getAttribute("value");
				}
				else{
					System.out.println("Unable to located for capturedata method of"+locatortype);
				}
			FileWriter fw=new FileWriter("D:\\DEC_seleniumOJT82\\StackAccounting_Hybrid\\CaptureData\\Sampledata");
			BufferedWriter bw=new BufferedWriter(fw);
			bw.write(supplierdata);
			bw.flush();
		}
	public static void tableValidation(WebDriver driver,String column) throws Throwable{
			FileReader fr=new FileReader("D:\\DEC_seleniumOJT82\\StackAccounting_Hybrid\\CaptureData\\Sampledata");
			BufferedReader br=new BufferedReader(fr);
			String exp_data=br.readLine();
			if(driver.findElement(By.id(PropertyFileUtil.getValueForkey(""))).isDisplayed()){
				Thread.sleep(5000);
				driver.findElement(By.id(PropertyFileUtil.getValueForkey(""))).sendKeys(exp_data);
				driver.findElement(By.id(PropertyFileUtil.getValueForkey(""))).click();
			}
			
		}
		
	
}
