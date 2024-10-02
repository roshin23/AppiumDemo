package MargoExpress.Mobile;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;


import io.appium.java_client.AppiumBy;

public class DragDropDemo extends BaseTest {
	
	@Test	
	public void ScrollDemoTest() throws InterruptedException
	{
		driver.findElement(AppiumBy.accessibilityId("Views")).click();
		driver.findElement(AppiumBy.accessibilityId("Drag and Drop")).click();
		WebElement source =  driver.findElement(By.id("io.appium.android.apis:id/drag_dot_1"));
		
		dragAction(source, 619, 560);

		Thread.sleep(3000);
		String result = driver.findElement(AppiumBy.accessibilityId("io.appium.android.apis:id/drag_result_text")).getText();
		Assert.assertEquals(result, "Dropped!");
	}
}
	
	
