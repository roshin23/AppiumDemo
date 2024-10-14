package MargoExpress.Mobile;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BaseTest {
	public AndroidDriver driver;
	public AppiumDriverLocalService service;
	
	@BeforeClass
	public void ConfigureAppium() throws MalformedURLException, URISyntaxException
	{
		//AndroidDriver, Ios driver
		//Appium code --> Appium Server --> Mobile

//		service = new AppiumServiceBuilder().withAppiumJS(new File("C://Users//xps//AppData//Roaming//npm//node_modules//appium//build//lib//main.js"))
//				.withIPAddress("127.0.0.1").usingPort(4723).build();
		service = new AppiumServiceBuilder().withAppiumJS(new File("C://Users//roshin.surendran//AppData//Roaming//npm//node_modules//appium//build//lib/main.js"))
					.withIPAddress("").usingPort(4723).build();
		service.start();	
		
		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName("Pixel 8 Pro Emulator");
//		options.setApp("C://Users//roshin.surendran//eclipse-workspace//mobile//src//test//java//resources//app-release.apk");
//		options.setApp(System.getProperty("user.dir")+"//src//test//java//resources//ApiDemos-debug.apk");
		options.setApp(System.getProperty("user.dir")+"//src//test//java//resources//General-Store.apk");

		
		driver = new AndroidDriver(new URI("http://127.0.0.1:4723").toURL(), options);		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	
	public void longPressAction(WebElement ele)
	{
		((JavascriptExecutor) driver).executeScript("mobile: longClickGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) ele).getId(),"duration", 2000));
	}
	
	public void scrollToElement(WebElement ele) throws InterruptedException
	{
		//scroll using android ui automator, where to scrol; is know prior
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(ele);"));
		Thread.sleep(2000);
	}
	
	public void scrollTOEndAction()
	{
		boolean canScrollMore;
		do
		{
		canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
			    "left", 100, "top", 100, "width", 200, "height", 200,
			    "direction", "down",
			    "percent", 1.0
			));
	}
		while(canScrollMore);

	}
	
	public void SwipeAction(WebElement ele, String direction)
	{
		//Swipe
		((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
				"elementId", ((RemoteWebElement)ele).getId(),
			    "direction", direction,
			    "percent", 0.75
			));
	}
	
	public void dragAction(WebElement ele, int xcord, int ycord)
	{
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) ele).getId(),
			    "endX", 100,
			    "endY", 100
			));
	}
	
	@AfterClass
	public void teardown()
	{
		driver.quit();
		service.stop();
	}

}
