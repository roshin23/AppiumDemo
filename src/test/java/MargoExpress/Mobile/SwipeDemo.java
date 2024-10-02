package MargoExpress.Mobile;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;


import io.appium.java_client.AppiumBy;

public class SwipeDemo extends BaseTest {
	
	@Test	
	public void DragDropTest() throws InterruptedException
	{
		driver.findElement(AppiumBy.accessibilityId("Views")).click();
		driver.findElement(AppiumBy.accessibilityId("Gallery")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@text='1. Photos']")).click();
		WebElement firstImage = driver.findElement(By.xpath("(//android.widget.ImageView)[1]"));
//		WebElement firstImage = driver.findElements(By.xpath("//android.widget.ImageView")).get(1);

		String isImgFocused = firstImage.getAttribute("focusable");
		Assert.assertEquals(isImgFocused, "true");
		
		//Swipe
		SwipeAction(firstImage, "left");
			
		Assert.assertEquals(isImgFocused, "false");

		
	}
}
	
	
