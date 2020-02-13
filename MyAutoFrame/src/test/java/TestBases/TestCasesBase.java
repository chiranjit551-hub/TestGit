package TestBases;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ConfigReader.ReadConfig;
import ObjectRepoReader.ObjectMap;

public class TestCasesBase 
{
	
	public static WebDriver driver;
	public static ReadConfig RC = new ReadConfig();
	
	static ObjectMap map = new ObjectMap();
	
	@BeforeSuite
	public void setUp() {
		RC.configLoader();
	}
	

	@AfterSuite
	public void tearDown() 
	{
		if(driver !=null) 
		{
			driver.quit();	
		}
	}
	
	public static void openBrowser() 
	{
		String browserValue = RC.getBrowserName();
		System.out.println(browserValue);
		
		if(browserValue.equalsIgnoreCase("Chrome")) 
		{
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir") + "/Drivers/chromedriver.exe");
		    driver = new ChromeDriver();
		    driver.manage().window().maximize();
		}
		
		else 
		{
			System.out.println("Not getting Browser Value");
		}
			
	}
	
	public static void openApplication() 
	{
		driver.get(RC.getURL());
		driver.manage().timeouts().implicitlyWait(RC.getImplicitWaitTime(),TimeUnit.SECONDS);
	}
	
	
	public static WebElement getWebElement(String WebElementName) 
	{
		return driver.findElement(map.getObjectLocator(WebElementName));
	}
}
