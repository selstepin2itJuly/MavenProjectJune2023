package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testbase.TestBaseObject;

public class AdminPage extends TestBaseObject {

	private WebDriver lpDr; 

	public AdminPage(WebDriver dr)
	{
		this.lpDr=dr;
		PageFactory.initElements(lpDr, this);
	}
	
	@FindBy(xpath="//span[text()='Admin']")
	private WebElement admin; 
	
	@FindBy(xpath="//h6[text()='User Management']")
	private WebElement adminHeaderText;
	
	@FindBy(css=".oxd-table-card > div >div:nth-Child(2)>div")
	private List<WebElement> tableUsername;
	
	public void clickOnAdmin()
	{
		//WaitForElementVisible(admin);
		//admin.click();
		new Actions(lpDr).click(admin).perform();
	}
	
	public boolean isAdminHeaderTextDisplayed()
	{
		boolean b =false;
		try {
			b = adminHeaderText.isDisplayed();
		}catch(Exception e)
		{
			e.getMessage();
		}
		return b;
	}
	
	public int getTheUsernamCountInTable()
	{
		//WaitForElementVisible(tableUsername.get(0));
		return tableUsername.size();
	}
	
	public List<String> getTheRecordFirstRow()
	{
		List<String> temp = new ArrayList<String>();
		int col = lpDr.findElements(By.cssSelector(".oxd-table-card > div >div>div")).size();
		int i=tableUsername.size();
		for(int ii=1; ii<=i-1;ii++)
		{
			
			if(ii==1)
			{
				for(int j = 2;j<=col-1;j++) {
					if(j==6)
					{
						break;
					}
					String s =lpDr.findElement(By.cssSelector(".oxd-table-card:nth-child("+ii+") > div>div:nth-Child("+j+")>div")).getText();
					temp.add(s);
					
				}
				break;
			}
		}
		return temp;
	}
	
}
