package MargoExpress.Mobile;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class IOSScrollTest extends IOSBaseTest {
	
	@Test
	public void IOSScrollTestDemo() throws InterruptedException
	{
		WebElement ele =  driver.findElement(AppiumBy.accessibilityId("Web View"));
		//passing key and value pairs as parameters in a haspmap
		Map <String,Object> params = new HashMap<>();
		params.put("element", ((RemoteWebElement) ele).getId());
		params.put("direction", "down");
		
		//ios event touchAndHold
		driver.executeScript("mobile:scroll", params);
		driver.findElement(AppiumBy.accessibilityId("Web View")).click();
		Thread.sleep(2000);
		//to navigate to back page, need to click on the elament
		driver.findElement(By.xpath("//XCUIElemeentTypeButton[@name='UIkiCatalog']")).click();
		
		//Picker component/ drop-downs
		driver.findElement(AppiumBy.accessibilityId("Picker View")).click();
		driver.findElement(AppiumBy.accessibilityId("Red color component value")).sendKeys("80");
		driver.findElement(AppiumBy.accessibilityId("Green color component value")).sendKeys("220");
		driver.findElement(AppiumBy.iOSNsPredicateString("label == 'Blue color component value'")).sendKeys("105");
		String number = driver.findElement(AppiumBy.iOSNsPredicateString("label == 'Blue color component value'")).getText();
		Assert.assertEquals(number, "105");

		}
}
