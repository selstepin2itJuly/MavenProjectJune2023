package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testbase.TestBaseObject;

public class LoginPage extends TestBaseObject {
	
	private WebDriver lpDr; 

	public LoginPage(WebDriver dr)
	{
		this.lpDr=dr;
		PageFactory.initElements(lpDr, this);
	}
	
	@FindBy(name="username")
	private WebElement username;
	
	@FindBy(css="[type='password']")
	private WebElement password;
	
	@FindBy(xpath="//button[@type='submit']")
	private WebElement loginButton;
	
	public LoginPage enterUsername(String user)
	{
		//WaitForElementVisible(username);
		username.sendKeys(user);
		return this;
	}
	
	public LoginPage enterPassword(String pass)
	{
		password.sendKeys(pass);
		return this;
	}
	public LoginPage clickOnLogin()
	{
		loginButton.click();
		return this;
	}
	

}
