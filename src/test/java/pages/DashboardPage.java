package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage {

	private WebDriver lpDr; 

	public DashboardPage(WebDriver dr)
	{
		this.lpDr=dr;
		PageFactory.initElements(lpDr, this);
	}
	
	@FindBy(tagName="h6")
	private WebElement dashboardText;
	
	public boolean isDashboadTextDisplayed()
	{
		boolean b =false;
		try {
			b = dashboardText.isDisplayed();
		}catch(Exception e)
		{
			e.getMessage();
		}
		return b;
	}
}
