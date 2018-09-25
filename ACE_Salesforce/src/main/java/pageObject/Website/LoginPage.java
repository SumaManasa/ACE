package pageObject.Website;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver driver ;
	
	public LoginPage(WebDriver driver) {
	this.driver=driver;
	PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@id='username' and @class='input r4 wide mb16 mt8 username' and @type='email']")
	public WebElement txt_userName; 

	@FindBy(xpath="//input[@class='input r4 wide mb16 mt8 password' and @type='password' and @name='pw']")
	public WebElement txt_pwd; 

	@FindBy(xpath="//input[@id='Login' and @type='submit']")
	public WebElement btn_login;  
	
}
