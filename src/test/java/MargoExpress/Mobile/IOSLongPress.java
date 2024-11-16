package MargoExpress.Mobile;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class IOSLongPress extends IOSBaseTest {
	
	@Test
	public void IOSLongPressTest()
	{
		driver.findElement(AppiumBy.accessibilityId("Steppers")).click();
		WebElement ele =  driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`label == 'Increment'1][3]"));
		//passing key and value pairs as parameters in a haspmap
		Map <String,Object> params = new HashMap<>();
		params.put("element", ((RemoteWebElement) ele).getId());
		params.put("duration", 5);
		
		//ios event touchAndHold
		driver.executeScript("mobile:touchAndHold", params);
	}
}
