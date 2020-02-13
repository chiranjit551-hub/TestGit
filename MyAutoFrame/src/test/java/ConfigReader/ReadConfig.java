package ConfigReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadConfig 
{
	public Properties prop;
	public FileInputStream fis;


	public void configLoader() 
	{
		try 
		    {
				
				fis = new FileInputStream(System.getProperty("user.dir")+"/ConfigFile/GlobalDataSet.properties");
				prop = new Properties();
				
				try 
				{
					prop.load(fis);
				} 
				catch (IOException e) 
				{
					System.out.println(e.getMessage() + "Not loaded the config file ");
				}
			 
			} 
	    catch (FileNotFoundException e) 
		{
			System.out.println(e.getMessage() + "File not able to load file not found");
		}
		
	}
	
	public String getURL() 
	{
		return prop.getProperty("TestSiteURL");
		
	}
	public String getBrowserName()
	{
		return prop.getProperty("BrowserName");
	}
	
	public long getImplicitWaitTime() 
	{
		String implicitlyWait = prop.getProperty("ImplicitWait");

		if(implicitlyWait != null) 
		{
			return Long.parseLong(implicitlyWait);
		}
			
		else
		{
			throw new RuntimeException("implicitlyWait not specified in the GlobalDataSet.properties file.");
		}
	}

}
