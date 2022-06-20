package demo.SeleniumDemo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.By.ByClassName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import dev.failsafe.internal.util.Assert;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Demo {

	public static void main(String[] args) throws InterruptedException {
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		
//		Map<String, String> mobileEmulator = new HashMap<String, String>();
//		mobileEmulator.put("deviceName", "iPhone XR");
//		Map<String, Object> chromeOPtion = new HashMap<String, Object>();
//		chromeOPtion.put("mobileEmulator", mobileEmulator);
		//Open link
		driver.get("https://www.liputan6.com/");
		
		//Search
		Thread.sleep(1000);
		String searchKey = "indonesia";
		driver.findElement(By.cssSelector(".navbar--top--search__input")).sendKeys(searchKey);
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("span.navbar--top--search__icon")).click();
		
		//Search with filter
		Thread.sleep(1000);
		driver.findElement(By.cssSelector(".advanced-search__order-selection")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("option.advanced-search__order-selection__item:nth-child(3)")).click();
		driver.findElement(By.cssSelector(".advanced-search__selector")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("option.advanced-search__selector-item:nth-child(8)")).click();
		driver.findElement(By.name("from_date")).click();
		driver.findElement(By.cssSelector(".ui-datepicker-next")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector(".ui-datepicker-calendar > tbody:nth-child(2) > tr:nth-child(1) > td:nth-child(3) > a:nth-child(1)")).click();
		driver.findElement(By.name("to_date")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector(".ui-datepicker-calendar > tbody:nth-child(2) > tr:nth-child(1) > td:nth-child(4) > a:nth-child(1)")).click();
		driver.findElement(By.cssSelector(".advanced-search__submit-button")).click();
		Thread.sleep(4000);
		
		//Verify keyword search
		String getSearchKey = driver.findElement(By.cssSelector("#main > div.container-article > div > article > div > div > span > strong > span")).getText();
		org.junit.Assert.assertEquals(getSearchKey, searchKey);
		
		//Scroll down
		JavascriptExecutor scroll = (JavascriptExecutor) driver ;
		scroll.executeScript("window.scrollBy(0, 800);");
		Thread.sleep(5000);
		
		//Select article
		driver.findElement(By.cssSelector("#article_4859106 > aside > header > h4 > a")).click();
		Thread.sleep(10000);
//		
		//Get element on new window
		Set<String> windowHandles = driver.getWindowHandles();
		System.out.println(windowHandles);
		Iterator<String> iterator = windowHandles.iterator();
		String parentWindow = iterator.next();
		String childWindow = iterator.next();
		driver.switchTo().window(childWindow);
		Thread.sleep(2000);
		
		//Verify title of article
//		String title_article = driver.findElement(By.cssSelector("#main > div.container-article > div > article > div.read-page-upper > header > h1")).getText();
//		String expected_title = "Top 3: Penampakan Bendungan Terindah di Indonesia";
//		org.junit.Assert.assertEquals(title_article, expected_title);
		//System.out.println("Testing Success");
		WebElement m=driver.findElement(By.xpath("//*[contains(text(),'Indonesia')]"));
	      System.out.println("Element with contains(): " + m.getText());
	    //*[@id="main"]/div[3]/div/article/div[1]/header/h1
		

	}

}
