package MargoExpress.Mobile;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MobileBroswerTest extends BrowserBaseTest{
	
	
	@Test
	
	public void browserTest() 
	{
//		driver.get("https://google.com");
//		driver.findElement(By.name("q")).sendKeys("Roshin Surendran");
//		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
		driver.get("https://rahulshettyacademy.com/angular/Appdemo");
		driver.findElement(By.xpath("//span[@class='navbar-toggler-icon']")).click();
		driver.findElement(By.cssSelector("a[routerlink*='products']")).click();
		//scroll on Browser
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,1000)", "");
		String text = driver.findElement(By.cssSelector("a[href*='products/3']")).getText();
		Assert.assertEquals(text, "DevOps");
	}
	
	
	
	
	
	
	
	
	
	
	
}