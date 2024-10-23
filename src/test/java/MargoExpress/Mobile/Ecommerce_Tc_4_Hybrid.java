package MargoExpress.Mobile;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import javax.lang.model.element.Element;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class Ecommerce_Tc_4_Hybrid extends BaseTest {
	
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
		driver.findElements(By.xpath("//android.widget.TextView[@text='ADD TO CART']")).get(0).click();
		driver.findElements(By.xpath("//android.widget.TextView[@text='ADD TO CART']")).get(0).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text=\"Total Purchase Amount:  \"]")));
		
		//Verify page is successfully navigated to cart
		WebElement CartpageTitleelem = driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title"));
		String actualCartPageTitle = CartpageTitleelem.getAttribute("text");
		Assert.assertEquals(actualCartPageTitle, "Cart");
	}

	@Test
	public void verifyProductsAddedinCart() throws InterruptedException
	{
		List<WebElement> productPrices = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice"));
		int productCount = productPrices.size();
		Double totalSum = 0.0;
		for (int i=0; i<productCount; i++)
		{
			String productAmount = productPrices.get(i).getText();
//			productAmount = productAmount.substring(1);
//			Double Price = Double.parseDouble(productAmount);
			Double formattedAmount = getFormattedAmount(productAmount);
			totalSum =totalSum + formattedAmount;
			System.out.println("TotalSumAmount = " + totalSum);
		}
       String displaySum = driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
       System.out.println("Display Amount = " + displaySum);
       Double displayFormattedSum = getFormattedAmount(displaySum);
       Assert.assertEquals(totalSum, displayFormattedSum);
       WebElement readTandcond = driver.findElement(By.id("com.androidsample.generalstore:id/termsButton"));
       longPressAction(readTandcond);
       driver.findElement(By.id("android:id/button1")).click();
       driver.findElement(AppiumBy.className("android.widget.CheckBox")).click();
       driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();
       Thread.sleep(8000);
       
       //Hybrid 
       Set<String> contexts = driver.getContextHandles();
       
//       for (int i=0;i<=contextnames.size();i++)
//       {
//    	   System.out.println(i);
//       }
       for (String contextName : contexts)
       {
    	   System.out.println(contextName);
       }
       //Switch to webview
       driver.context("WEBVIEW_com.androidsample.generalstore");
       driver.findElement(By.name("q")).sendKeys("Roshin");
       driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
       driver.pressKey(new KeyEvent(AndroidKey.BACK));
       driver.context("NATIVE_APP");
	}
}
