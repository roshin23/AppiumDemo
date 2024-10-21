package MargoExpress.Mobile;

import java.time.Duration;
import java.util.List;

import javax.lang.model.element.Element;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class Ecommerce_Tc_2 extends BaseTest {
	
	@Test
	public void FillForm() throws InterruptedException
	{
		driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry")).click();
		
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Bahamas\"));"));
		WebElement scrollctry = driver.findElement(By.xpath("//android.widget.TextView[@resource-id='android:id/text1' and @text='Bahamas']"));

		scrollctry.click();

		
		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Roshin");
		driver.hideKeyboard();
		driver.findElement(By.id("com.androidsample.generalstore:id/radioMale")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		
		//add synchornization
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement pageTitleelem = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.androidsample.generalstore:id/toolbar_title")));
		
//		WebElement pageTitleelem =  driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title"));
		String actualPagetitle = pageTitleelem.getAttribute("text");
		Assert.assertEquals(actualPagetitle, "Products");
	}
	
	@Test
	public void selectProduct()
	{
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Jordan 6 Rings\"));"));
		List<WebElement> productsEle =  driver.findElements(By.xpath("//android.widget.TextView"));

		scrollToProdListAndAddToCart("Jordan 6 Rings", productsEle, "(//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productAddCart\"])");
//		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"LeBron Soldier 12 \"));"));
		scrollToProdListAndAddToCart("LeBron Soldier 12 ", productsEle, "(//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productAddCart\"])");
		//goto cart page
		driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement CartpageTitleelem = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.androidsample.generalstore:id/toolbar_title")));
		
		//Verify page is successfully navigated to cart
		String actualCartPageTitle = CartpageTitleelem.getAttribute("text");
		Assert.assertEquals(actualCartPageTitle, "Cart");
	}

	@Test
	public void verifyProductsAddedinCart()
	{
		WebElement JordanShoesele = driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productName\" and @text=\"Jordan 6 Rings\"]"));
		WebElement LebronShoesele = driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productName\" and @text=\"LeBron Soldier 12 \"]"));

        boolean isJordanElementPresent = JordanShoesele.isDisplayed();
        Assert.assertTrue(isJordanElementPresent, "Element is displayed");
        boolean isLebronElementPresent = LebronShoesele.isDisplayed();
        Assert.assertTrue(isLebronElementPresent, "Element is displayed");
       
        
        
        List<WebElement> cartitemspriceele = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice"));
//        Double sum = 0.0;
//        for (int i=0; i<=cartitemspriceele.size(); i++)
//        {
//        	String itemAmount = cartitemspriceele.get(i).getText();
//        	itemAmount =itemAmount.substring(1);
//        	Double price = Double.parseDouble(itemAmount);
//        	sum = sum+price;
//        	System.out.println(sum);
//        }
        
//        Double sum = 0.0;
//        for (WebElement item : cartitemspriceele) {
//            String itemAmount = item.getText();
//            itemAmount = itemAmount.substring(1); // Remove the currency symbol
//            Double price = Double.parseDouble(itemAmount);
//            sum += price;
//            System.out.println(sum);
//        }
        calculateSum(cartitemspriceele);
	}
	
	@Test
    // Method to get the stored sum
    public int getSum() {
        return sum;
    }
    
	@Test
	public void verifyTotalSum()
	{
    String ActualAmount = driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
    ;
    Double ActualAmountvalue = Double.parseDouble(ActualAmount.substring(1));
    Assert.assertEquals(ActualAmountvalue, sum);
	}
}
