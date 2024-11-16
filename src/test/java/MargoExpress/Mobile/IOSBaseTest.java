package MargoExpress.Mobile;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class IOSBaseTest {
	public IOSDriver driver;
	public AppiumDriverLocalService service;
	
	@BeforeClass
	public void ConfigureAppium() throws MalformedURLException, URISyntaxException
	{
		//AndroidDriver, Ios driver
		//Appium code --> Appium Server --> Mobile

		service = new AppiumServiceBuilder().withAppiumJS(new File("C://Users//xps//AppData//Roaming//npm//node_modules//appium//build//lib//main.js"))
				.withIPAddress("127.0.0.1").usingPort(4723).build();
//		service = new AppiumServiceBuilder().withAppiumJS(new File("C://Users//roshin.surendran//AppData//Roaming//npm//node_modules//appium//build//lib/main.js"))
//					.withIPAddress("").usingPort(4723).build();
		service.start();
		
		XCUITestOptions options = new XCUITestOptions();
		options.setDeviceName("iPhone 13 Pro");
		options.setApp(System.getProperty("user.dir")+"//src//test//java//resources//TestApp 3.apk");
		options.setPlatformVersion("15.5");
		//Appium-->Webdrive agent --> IOS app
		options.setWdaLaunchTimeout(Duration.ofSeconds(20));

		
		driver = new IOSDriver(new URI("http://127.0.0.1:4723").toURL(), options);		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	
	public void longpress()
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
	
	@AfterClass
	public void teardown()
	{
		driver.quit();
		service.stop();
	}
}