package MargoExpress.Mobile;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class Ecommerce_Tc_01 extends BaseTest {
	
	@Test
	public void FillForm() throws InterruptedException
	{
		driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry")).click();
		
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Bahamas\"));"));
		WebElement scrollctry = driver.findElement(By.xpath("//android.widget.TextView[@resource-id='android:id/text1' and @text='Bahamas']"));

		scrollctry.click();
		
		//Verify toast message
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
        WebElement toastMessage = driver.findElement(By.xpath("//android.widget.Toast"));
        String actualMessage = toastMessage.getAttribute("text");
        Assert.assertEquals(actualMessage, "Please enter your name");

		
		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Roshin");
		driver.hideKeyboard();
		driver.findElement(By.id("com.androidsample.generalstore:id/radioMale")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
	}
		

}
