package Demo;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;				// A PERFECT WAY TO ARRANGE FUNCTION AND CALLING A FUNCTION
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class SearchFunctionality {									// class

	Actions action;													// will be used repeatedly so put here.
	
	public SearchFunctionality(WebDriver driver) {					// constructor
		action = new Actions(driver);
	}
	
	public void pressEnter() {
		action.sendKeys(Keys.ENTER).build().perform();
	}
	
	public void scrollDown(WebDriver driver) {						// to scroll down we need webDriver ref
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("window.scrollBy(0,2000)","");
	}
	
	public void performMouseHover(WebElement element) {// to perform mouse hover activity we need webElement ref
		action.moveToElement(element).build().perform();
	}
	
	public void clickUsingJavaScriptExecutor(WebElement element, WebDriver driver) { // to click we need webdriver
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click()", element);
	}
	/*public void proceedToChkout(WebElement element, WebDriver driver) {
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click()", element);
	  }*/
	
	
	
	public static void main(String[] args) {
		
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		SearchFunctionality objSearch = new SearchFunctionality(driver);			// object creation
		
		driver.get("http://automationpractice.com/index.php");
		driver.manage().window().maximize();
		
		WebElement inputSearch = driver.findElement(By.xpath("//*[@id=\"search_query_top\"]"));
		inputSearch.sendKeys("Printed");
		
		objSearch.pressEnter();
		objSearch.scrollDown(driver);
		
		String image = "2";
		WebElement searchImage = driver.findElement(By.xpath("//*[@id='center_column']/ul/li["+image+"]/div/div[1]/div/a[1]/img"));
		objSearch.performMouseHover(searchImage);

		WebElement btnAddToCart = driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[1]/div/div[2]/div[2]/a[1]/span"));
		objSearch.clickUsingJavaScriptExecutor(btnAddToCart, driver);
		
		/*WebElement proceedToCheckout = driver.findElement(By.xpath("//*[@id='layer_cart']/div[1]/div[2]/div[4]/a/span"));
		objSearch.clickUsingJavaScriptExecutor(proceedToCheckout, driver);		*/
	}
}