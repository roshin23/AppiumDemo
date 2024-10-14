package MargoExpress.Mobile;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class Ecommerce_Tc_2 extends BaseTest {
	
	@Test
	public void FillForm() throws InterruptedException
	{
		driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry")).click();
		
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Bahamas\"));"));
		WebElement scrollctry = driver.findElement(By.xpath("//android.widget.TextView[@resource-id='android:id/text1' and @text='Bahamas']"));

		scrollctry.click();

		
		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Roshin");
		driver.hideKeyboard();
		driver.findElement(By.id("com.androidsample.generalstore:id/radioMale")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		
		//add synchornization
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement pageTitleelem = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.androidsample.generalstore:id/toolbar_title")));
		
//		WebElement pageTitleelem =  driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title"));
		String actualPagetitle = pageTitleelem.getAttribute("text");
		Assert.assertEquals(actualPagetitle, "Products");
		
	}
	
	@Test
	public void selectProduct()
	{
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Jordan 6 Rings\"));"));
		List<WebElement> productsEle =  driver.findElements(By.xpath("//android.widget.TextView"));
		for (WebElement element : productsEle)
		{
			String ProductName = element.getAttribute("text");
			if ProductName == ("Jordan 6 Rings")
				{
					driver.findElement(By.xpath("(//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productAddCart\"])")).get.click();	
				}
		}
		
	}
}
