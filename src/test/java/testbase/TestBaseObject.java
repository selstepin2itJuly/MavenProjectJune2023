package testbase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class TestBaseObject {

	public WebDriver driver;
	public Properties prop = new Properties();
	public String readBrowser;
	public WebDriver getDriverInstance() throws IOException
	{
		String config = "./src/test/resources/config/config.properties";
		//Read config file
		FileInputStream inStream = new FileInputStream(new File(config));
		prop.load(inStream);
		readBrowser = prop.getProperty("browser");
		if(readBrowser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", prop.getProperty("chromedriver"));
			driver = new ChromeDriver();
		}else if(readBrowser.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.geckp.driver", prop.getProperty("geckodriver"));
			driver = new FirefoxDriver();
		}else if(readBrowser.equalsIgnoreCase("edge"))
		{
			System.setProperty("webdriver.edge.driver", prop.getProperty("edgedriver"));
			driver = new EdgeDriver();
		}else
		{
			Throwable thr = new Throwable();
			thr.initCause(null);
		}
		//driver.get("http://www.facebook.ca");
		driver.manage().window().maximize();
		enableImplicit(15);
		driver.get(prop.getProperty("url"));
		return driver;
	}
	
	public void enableImplicit(int a)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(a));	
	}
	public void disableImplicit(int a)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(a));	
	}
	public void attachScreenshot()
	{
		TakesScreenshot ts = (TakesScreenshot) driver;
		String src = ts.getScreenshotAs(OutputType.BASE64);
		String tag = "<img src=\"data:image/jpg;base64,"+src+"\" height=\"600\" width=\"800\" />";
		Reporter.log(tag);
	}
	
	public void WaitForElementClickable(WebElement ele)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.elementToBeClickable(ele));
	}
	
	public void WaitForElementVisible(WebElement ele)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	
	
}
