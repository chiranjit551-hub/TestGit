package TestPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import TestBases.TestCasesBase;

public class BankHomePage 
{
	
	//	WebElement signInLink;
	public WebDriver driver;
	
	public BankHomePage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	 
	
	public  String getTittle(WebDriver driver) {
		
		return driver.getTitle();
	}
    
	public void clickOnLink() {
		
		WebElement signInLink = TestCasesBase.getWebElement("signIn");
    	signInLink.click();

						
	}
	public void fillForm(String emailid){
		WebElement email=TestCasesBase.getWebElement("Email");
		email.sendKeys(emailid);
	}
	
}
