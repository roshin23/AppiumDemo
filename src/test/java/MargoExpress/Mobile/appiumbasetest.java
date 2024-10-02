package MargoExpress.Mobile;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class appiumbasetest extends BaseTest {
	
	@Test
	public void WifiSettingName()
	{
		driver.findElement(AppiumBy.accessibilityId("Preference")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@content-desc='3. Preference dependencies']")).click();
		driver.findElement(By.id("android:id/checkbox")).click();		
		driver.findElement(By. xpath("(//android.widget.RelativeLayout)[2]")).click();
		
		String alertTitle = driver.findElement(By.id("android:id/alertTitle")).getText();
		Assert.assertEquals(alertTitle,"WiFi settings");
		
		
		driver.findElement(By.id("android:id/edit")).sendKeys("Roshin Wifi");
		driver.findElements(AppiumBy.className("android.widget.Button")).get(1).click();
	}
	
	public void longPress()
	{
		driver.findElement(AppiumBy.accessibilityId("Views")).click();;
		driver.findElement(AppiumBy.accessibilityId("Expandable Lists")).click();
		driver.findElement(AppiumBy.accessibilityId("1. Custom Adapter")).click();
		
		
	}

}
