package Utils;

import org.openqa.selenium.WebDriver;

import pageObject.Website.LoginPage;
import pageObject.Website.MemberDemographicsPage;



public class PageObjectManager {
	
	WebDriver driver;
	public PageObjectManager(WebDriver driver) {
		this.driver = driver;
	}

	private LoginPage loginPage;
	private MemberDemographicsPage memberDemoPage;


	public LoginPage getLoginPage() {
		return (loginPage == null) ? loginPage = new LoginPage(driver) : loginPage;
	}

	public MemberDemographicsPage getMemberDemoPage() {
		return (memberDemoPage == null) ? memberDemoPage = new MemberDemographicsPage(driver) : memberDemoPage;
	}
}