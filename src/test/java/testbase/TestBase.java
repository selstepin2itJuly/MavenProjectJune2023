package testbase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestBase {

	public static WebDriver driver;
	public static Properties prop = new Properties();
	public static String readBrowser;
	public static WebDriver getDriverInstance() throws IOException
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
	
	public static void enableImplicit(int a)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(a));	
	}
	public static void disableImplicit(int a)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(a));	
	}
	
	
}
