package MargoExpress.Mobile;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;

public class ScrollDemo extends BaseTest {
	
	@Test	
	public void ScrollDemoTest() throws InterruptedException
	{
		driver.findElement(AppiumBy.accessibilityId("Views")).click();
		
//		//scroll using android ui automator, where to scrol; is know prior
//		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"WebView\"));"));
//		Thread.sleep(2000);
		
		//scroll using appium gestures, scroll when you dont know where the element is located
		scrollTOEndAction();
		
}
}
	
	
