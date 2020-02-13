package ObjectRepoReader;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.By;

public class ObjectMap 
{
	 Properties prop;
	 FileInputStream fis;
	 
	public ObjectMap() 
	{	
		prop = new Properties();
		try 
		{
			fis = new FileInputStream(System.getProperty("user.dir")+"/ConfigFile/Locator.properties");
			prop.load(fis);
			fis.close();
		} 
		catch (IOException e) 
		{
			System.out.println(e.getMessage());
		}	
	
	}
  
	
	public By getObjectLocator(String WebElementName)
				{
				// Retrieve value from properties file using key
				String locatorTypeAndValue = prop.getProperty(WebElementName);

				// extract the locator type and value from the object
				String[] locatorTypeAndValueArray = locatorTypeAndValue.split(":");
				String locatorType = locatorTypeAndValueArray[0];
				String locatorValue = locatorTypeAndValueArray[1];

				switch (locatorType.toUpperCase()) {
				case "ID":
					return By.id(locatorValue);
				case "NAME":
					return By.name(locatorValue);
				case "TAGNAME":
					return By.tagName(locatorValue);
				case "LINKTEXT":
					return By.linkText(locatorValue);
				case "PARTIALLINKTEXT":
					return By.partialLinkText(locatorValue);
				case "XPATH":
					return By.xpath(locatorValue);
				case "CSS":
					return By.cssSelector(locatorValue);
				case "CLASSNAME":
					return By.className(locatorValue);
				default:
					return null;
				}
		}
}
