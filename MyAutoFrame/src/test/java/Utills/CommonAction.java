package Utills;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Coordinates;
import org.openqa.selenium.interactions.Locatable;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import TestBases.TestCasesBase;

public class CommonAction extends TestCasesBase
{
	
	public static WebDriver driver;
	
	public CommonAction(WebDriver driver) {
		CommonAction.driver = driver;
	}

	
	public static void switchToNewWindow(WebDriver driver) {
		Set<String> s = driver.getWindowHandles();
		Iterator<String> itr = s.iterator();
		String w1 = (String) itr.next();
		String w2 = (String) itr.next();
		driver.switchTo().window(w2);
		}

		public static void switchToOldWindow(WebDriver driver) {
		Set<String> s = driver.getWindowHandles();
		Iterator<String> itr = s.iterator();
		String w1 = (String) itr.next();
		String w2 = (String) itr.next();
		driver.switchTo().window(w1);
		}

		public static void switchToParentWindow(WebDriver driver) {
		driver.switchTo().defaultContent();
		}

		
		public static void waitForElement(WebElement element,WebDriver driver) {

			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.elementToBeClickable(element));
			}

			public static void waitTillElementFound(WebElement ElementTobeFound,
			int seconds,WebDriver driver) {
			WebDriverWait wait = new WebDriverWait(driver, seconds);
			wait.until(ExpectedConditions.visibilityOf(ElementTobeFound));
			}

		

			public static void setWindowSize(int Dimension1, int dimension2,WebDriver driver) {
			driver.manage().window().setSize(new Dimension(Dimension1, dimension2));

			}

			 public void getScreenShot(String fileName, WebDriver driver) throws IOException{
			        DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy h-m-s");
			        Date date = new Date();
			        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			        FileUtils.copyFile(scrFile, new File("E:/AutoFrame/MyAutoFrame/ScreenShots"+fileName+"-"+dateFormat.format(date)+".png"));
			        
			    }
			public static void pressKeyDown(WebElement element,WebDriver driver) {
			element.sendKeys(Keys.DOWN);
			}

			public void pressKeyEnter(WebElement element,WebDriver driver) {
			element.sendKeys(Keys.ENTER);
			}

			public static void pressKeyUp(WebElement element,WebDriver driver) {
			element.sendKeys(Keys.UP);
			}

			public static void moveToTab(WebElement element,WebDriver driver) {
			element.sendKeys(Keys.chord(Keys.ALT, Keys.TAB));
			}
			
			public static void waitTillPageLoad(int i) {

				driver.manage().timeouts().pageLoadTimeout(i, TimeUnit.SECONDS);

				}

				public static void keyboardEvents(WebElement webelement, Keys key,
				String alphabet) {
				webelement.sendKeys(Keys.chord(key, alphabet));

				}

				public static void navigate_forward() {
				driver.navigate().forward();
				}

				public static void navigate_back() {
				driver.navigate().back();
				}

				public static void refresh() {
				driver.navigate().refresh();
				}

				public static void waitMyTime(int i) {
				driver.manage().timeouts().implicitlyWait(i, TimeUnit.SECONDS);

				}

				public static void clearTextField(WebElement element) {
				element.clear();

				}

				public static void clickWebelement(WebElement element,WebDriver driver) 
				{
				  try 
				  {
				     boolean elementIsClickable = element.isEnabled();
				     while (elementIsClickable) 
				     {
				       element.click();
				     }
				 } 
				
				 catch (Exception e)
				  {
				   System.out.println("Element is not enabled");
				   e.printStackTrace();
				  }
				
				}
				public static void clickMultipleElements(WebElement someElement,
				WebElement someOtherElement) 
				{
				Actions builder = new Actions(driver);
				builder.keyDown(Keys.CONTROL).click(someElement)
				.click(someOtherElement).keyUp(Keys.CONTROL).build().perform();
				}


				public static boolean checkAlert_Accept(WebDriver driver) 
				{
				   try 
				   {
				      Alert a = driver.switchTo().alert();
				      String str = a.getText();
				      System.out.println(str);

				      a.accept();
				      return true;

				    }
				   catch (Exception e) 
				    {

				      System.out.println("No Alert");
				      return false;

				    }
				}

				public static boolean checkAlert_Dismiss(WebDriver driver) {
				try
				{

					Alert a = driver.switchTo().alert();
					String str = a.getText();
					System.out.println(str);
					
					a.dismiss();
					return true;
				}
				catch (Exception e) 
				{


					System.out.println("No Alert");
					return false;
				}
				}

				public static void scrolltoElement(WebElement ScrolltoThisElement , WebDriver driver) 
				{
					Coordinates coordinate = ((Locatable) ScrolltoThisElement)
							.getCoordinates();
					coordinate.onPage();
					coordinate.inViewPort();
				}

				public static void checkbox_Checking(WebElement checkbox,WebDriver driver)
				{
					boolean checkstatus;
					checkstatus = checkbox.isSelected();
					if (checkstatus == true) 
					{
						System.out.println("CheckBox is already Checked");
					} 
					else 
					{
						checkbox.click();
						System.out.println("Checked the CheckBox");
					}
				}

				public static void radiobutton_Select(WebElement Radio,WebDriver driver) 
				{
				boolean checkstatus;
				checkstatus = Radio.isSelected();
				  if (checkstatus == true) 
				  {
					System.out.println("RadioButton Is alreadySelected");
				  } 
				 else 
				  {
				   Radio.click();
				   System.out.println("Selected the Radiobutton");
				   }
				}

				public static void checkbox_Unchecking(WebElement checkbox,WebDriver driver)
				{
				boolean checkstatus;
				checkstatus = checkbox.isSelected();
				if (checkstatus == true) 
				{
					checkbox.click();
					System.out.println("Checkbox is unchecked");
				}
				else 
				{
				  System.out.println("Checkbox is already unchecked");
				}
				}

				public static void radioButton_Deselect(WebElement Radio,WebDriver driver) {
				boolean checkstatus;
				checkstatus = Radio.isSelected();
				if (checkstatus == true) 
				{
				Radio.click();
				System.out.println("“Radio Button is deselected");
				} 
				else 
				{
				System.out.println("Radio Button was already Deselected");
				}
				}

				public static void dragAndDrop(WebElement fromWebElement,
				WebElement toWebElement,WebDriver driver)
				{
				Actions builder = new Actions(driver);
				builder.dragAndDrop(fromWebElement, toWebElement);
				}

				public static void dragAndDrop_Method2(WebElement fromWebElement,
				WebElement toWebElement,WebDriver driver) 
				{
				Actions builder = new Actions(driver);
				Action dragAndDrop = builder.clickAndHold(fromWebElement)
				.moveToElement(toWebElement).release(toWebElement).build();
				dragAndDrop.perform();
				}


				public static void hoverWebelement(WebElement HovertoWebElement,WebDriver driver)
				throws InterruptedException 
				{
				Actions builder = new Actions(driver);
				builder.moveToElement(HovertoWebElement).perform();
				Thread.sleep(2000);

				}

				public static void doubleClickWebelement(WebElement doubleclickonWebElement)
				throws InterruptedException {
				Actions builder = new Actions(driver);
				builder.doubleClick(doubleclickonWebElement).perform();
				Thread.sleep(2000);

				}

//				public static String getToolTip(WebElement toolTipofWebElement)
//				throws InterruptedException {
//				String tooltip = toolTipofWebElement.getAttribute(“title”);
//				System.out.println(“Tool text : ” + tooltip);
//				return tooltip;
//				}
//
//				public static void selectElementByNameMethod(WebElement element, String Name) {
//				Select selectitem = new Select(element);
//				selectitem.selectByVisibleText(Name);
//				}
//
//				public static void selectElementByValueMethod(WebElement element,
//				String value) {
//				Select selectitem = new Select(element);
//				selectitem.selectByValue(value);
//				}
//
//				public static void selectElementByIndexMethod(WebElement element, int index) {
//				Select selectitem = new Select(element);
//				selectitem.selectByIndex(index);
//				}
//
//				public static void clickCheckboxFromList(String xpathOfElement,
//				String valueToSelect) {
//
//				List<WebElement> lst = driver.findElements(By.xpath(xpathOfElement));
//				for (int i = 0; i < lst.size(); i++) {
//				List<WebElement> dr = lst.get(i).findElements(By.tagName(“label”));
//				for (WebElement f : dr) {
//				System.out.println(“value in the list : ” + f.getText());
//				if (valueToSelect.equals(f.getText())) {
//				f.click();
//				break;
//				}
//				}
//				}
//				}
//
//				public static void downloadFile(String href, String fileName)
//				throws Exception {
//				URL url = null;
//				URLConnection con = null;
//				int i;
//				url = new URL(href);
//				con = url.openConnection();
//				File file = new File(“.//OutputData//” + fileName);
//				BufferedInputStream bis = new BufferedInputStream(con.getInputStream());
//				BufferedOutputStream bos = new BufferedOutputStream(
//				new FileOutputStream(file));
//				while ((i = bis.read()) != -1) {
//				bos.write(i);
//				}
//				bos.flush();
//				bis.close();
//				}
//
//				public static void navigateToEveryLinkInPage() throws InterruptedException {
//
//				List<WebElement> linksize = driver.findElements(By.tagName(“a”));
//				int linksCount = linksize.size();
//				System.out.println(“Total no of links Available: ” + linksCount);
//				String[] links = new String[linksCount];
//				System.out.println(“List of links Available: “);
//				// print all the links from webpage
//				for (int i = 0; i < linksCount; i++) {
//				links[i] = linksize.get(i).getAttribute(“href”);
//				System.out.println(linksize.get(i).getAttribute(“href”));
//				}
//				// navigate to each Link on the webpage
//				for (int i = 0; i < linksCount; i++) {
//				driver.navigate().to(links[i]);
//				Thread.sleep(3000);
//				System.out.println(driver.getTitle());
//				}
//				}
//
//	
	
	
	
	
	
	
}




