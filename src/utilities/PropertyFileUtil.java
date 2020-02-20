package utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertyFileUtil {
	
	public static String getValueForkey(String Key) throws Throwable{
		Properties p=new Properties();
		FileInputStream fis=new FileInputStream("D:\\DEC_seleniumOJT82\\StackAccounting_Hybrid\\PropertiesFile\\Environment.properties");
		p.load(fis);
		return p.getProperty(Key);
	}

}
