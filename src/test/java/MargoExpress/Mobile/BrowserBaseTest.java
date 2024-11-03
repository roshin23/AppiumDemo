package MargoExpress.Mobile;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
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

public class BrowserBaseTest {
	public AndroidDriver driver;
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
		
		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName("Pixel 8 Pro Emulator");
		options.setChromedriverExecutable(System.getProperty("user.dir")+"//src//test//java//resources//chromedriver.exe");
		options.setCapability("broswerName", "Chrome");
		
		driver = new AndroidDriver(new URI("http://127.0.0.1:4723").toURL(), options);		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	
	@AfterClass
	public void teardown()
	{
		driver.quit();
		service.stop();
	}
}
