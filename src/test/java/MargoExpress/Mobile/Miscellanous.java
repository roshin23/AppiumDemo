package MargoExpress.Mobile;

import org.openqa.selenium.By;
import org.openqa.selenium.DeviceRotation;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class Miscellanous extends BaseTest {
	
	@Test
	public void MiscellanousDemo()
	{
		driver.findElement(AppiumBy.accessibilityId("Preference")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@content-desc='3. Preference dependencies']")).click();
		driver.findElement(By.id("android:id/checkbox")).click();
		
		//switch view to landscape
		DeviceRotation landscape = new DeviceRotation(0,0,90);
		driver.rotate(landscape);
		
		driver.findElement(By. xpath("(//android.widget.RelativeLayout)[2]")).click();
		
		String alertTitle = driver.findElement(By.id("android:id/alertTitle")).getText();
		Assert.assertEquals(alertTitle,"WiFi settings");
		
		
	//Copy to clipboard
		driver.setClipboardText("Roshin Wifi");
		
		
		driver.findElement(By.id("android:id/edit")).sendKeys(driver.getClipboardText());
		driver.findElements(AppiumBy.className("android.widget.Button")).get(1).click();
		
		//keyevents
		driver.pressKey(new KeyEvent(AndroidKey.ENTER));
		driver.pressKey(new KeyEvent(AndroidKey.BACK));
		driver.pressKey(new KeyEvent(AndroidKey.HOME));


	}

}
