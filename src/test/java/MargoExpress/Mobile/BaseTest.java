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

public class BaseTest {
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
	
	public void scrollToElement(String ele) throws InterruptedException
	{
		//scroll using android ui automator, where to scrol; is know prior
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(ele);"));
		Thread.sleep(2000);
	}
	
	public void scrollToProdListAndAddToCart(String productName, List<WebElement> prodNameElem, String addToCartElem) {
	    // Advanced for loop
	    for (WebElement element : prodNameElem) {
	        String productNameText = element.getText();
	        if (productNameText.equalsIgnoreCase(productName)) {
	            try {
	                List<WebElement> addToCartBtn = driver.findElements(By.xpath(addToCartElem));
	                if (!addToCartBtn.isEmpty()) {
	                    addToCartBtn.get(0).click(); // Click the first matching button
	                    System.out.println("Product added to cart: " + productName);
	                } else {
	                    System.out.println("Add to Cart button not found for product: " + productName);
	                }
	            } catch (Exception e) {
	                System.out.println("An error occurred while adding the product to the cart: " + e.getMessage());
	            }
	            break; // Exit the loop after adding the product to the cart
	        }
	    }
	}
	
//	public void scrollToProdListAndAddtocard(String productName, List<WebElement>  ProdNameElem, String  AddtoCartElem)
//	{
//		//advanced for loop
//		for (WebElement element : ProdNameElem)
//		{
//			String ProductName = element.getAttribute("text");
//			if (ProductName.equalsIgnoreCase(productName))
//				{
//				List<WebElement> AddtoCartbtn = driver.findElements(By.xpath(AddtoCartElem));
//					AddtoCartbtn.get().click();
//				}
//		}
		//traditional for loop
//		int i=0;
//		for (i=0;i<=productsEle.size();i++)
//		{
//			String productName = driver.findElements(By.xpath("//android.widget.TextView")).get(i).getAttribute("text");
//					
//					if (productName.equalsIgnoreCase("Jordan 6 Rings"))
//					{
//						driver.findElements(By.xpath("(//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productAddCart\"])")).get(i).click();
//					}
//		}
	
	
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
    public int sum = 0;

    public void calculateSum(List<WebElement> cartitemspriceele) {
        for (WebElement item : cartitemspriceele) {
            String itemAmount = item.getText();
            itemAmount = itemAmount.substring(1); // Remove the currency symbol
            Double price = Double.parseDouble(itemAmount);
            sum += price;
            System.out.println(sum);
        }
    }
    
    public double getFormattedAmount(String Amount)
    {
    	double Price = Double.parseDouble(Amount.substring(1));
    	return Price;
    }
}
