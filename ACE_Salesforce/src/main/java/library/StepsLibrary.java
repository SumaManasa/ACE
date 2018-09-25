package library;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.util.SystemOutLogger;
import org.omg.CORBA.TIMEOUT;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.sun.jna.platform.win32.Sspi.TimeStamp;

import Utils.Config;
import Utils.Wait;
import cucumber.runtime.Timeout;
import pageObject.Website.LoginPage;
import pageObject.Website.MemberDemographicsPage;
import selenium.SeleniumFunctions;

public class StepsLibrary extends SeleniumFunctions {

	SeleniumFunctions function;
	String path = System.getProperty("EvidencePath");
	LoginPage loginPage = getPageManager().getLoginPage();
	MemberDemographicsPage memberPage = getPageManager().getMemberDemoPage();
	Wait wait = new Wait();

	Actions action=new Actions(driver);	

	public void login(String userName, String pwd) {
		try {
			System.out.println(path + " evidencepath");
			String url = Config.getInstance().getApplicationUrl();
			System.out.println(url + " url");
			createDriver(url);
			System.out.println("Driver created and then failed");

			Assert.assertTrue(isElementPresent(loginPage.btn_login),
					"Error: Not able to navigate to Application Login Page");

			//Thread.sleep(3000);
			wait.waitForLoad(driver);
			loginPage.txt_userName.sendKeys(userName);
			loginPage.txt_pwd.sendKeys(pwd);
			loginPage.btn_login.click();

			Assert.assertTrue(isElementPresent(memberPage.lbl_projectName), "Error: User Not able to Login");
			takeScreenshot(path, "Passed_login");
			//	Thread.sleep(3000);
			//	Robot robot=new Robot();
			//	robot.keyPress(KeyEvent.VK_TAB);
			//	robot.keyPress(KeyEvent.VK_ENTER);


		} catch (Exception e) {
			e.printStackTrace();
			takeScreenshot(path, "Failed_login");
			throw new RuntimeException("Error : User Failed to login");
		}
	}

	public void verifyMemeberDemographicDetails(List<Map<String, String>> dataMap) throws InterruptedException {

		try {
			wait.waitForLoad(driver);
			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[starts-with(@id ,'vfFrameId')]")));
			action.moveToElement(memberPage.lnk_seeMore).click().perform();
			//memberPage.lnk_seeMore.click();

			wait.waitForLoad(driver);
			if((dataMap.get(0).get("Member Name").equals(memberPage.lbl_memberName.getText())) && 
					(dataMap.get(0).get("Member Id").equals(memberPage.lbl_memId.getText())) && 
					//	(dataMap.get(0).get("SSN").equals(memberPage.lbl_ssn.getText())) &&
					(dataMap.get(0).get("Cutomer Happines Score").equals(memberPage.lbl_hapinesScore.getText())))
				/*(dataMap.get(0).get("Gender").equals(memberPage.lbl_gender.getText())) && 
					(dataMap.get(0).get("Plan Name").equals(memberPage.lbl_planName.getText())) && 
					(dataMap.get(0).get("email").equals(memberPage.lbl_emailId.getText())) && 
					(dataMap.get(0).get("dob").equals(memberPage.lbl_dob.getText())) && 
					(dataMap.get(0).get("SSN").equals(memberPage.lbl_ssn.getText())) && 
					(dataMap.get(0).get("Phone Number").equals(memberPage.lbl_phoneNmb.getText())))*/
			{
				highlightElement(memberPage.lbl_memId);
				highlightElement(memberPage.lbl_memberName);
				highlightElement(memberPage.lbl_hapinesScore);
				//	highlightElement(memberPage.lbl_ssn);

				/*highlightElement(memberPage.lbl_planName);
				highlightElement(memberPage.lbl_gender);
				highlightElement(memberPage.lbl_emailId);
				highlightElement(memberPage.lbl_dob);
				highlightElement(memberPage.lbl_ssn);
				highlightElement(memberPage.lbl_phoneNmb);*/

				System.out.println("Passed : Member demographic details verified succesfully ");
				takeScreenshot(path, "Passed_MemberDetailsView");
			}
		}

		catch(Exception e){
			e.printStackTrace();
			takeScreenshot(path, "Failed_MemberDetails");
			throw new RuntimeException("Error : Failed to validate member demographic details");
		}

	}

	public void clickManagerAlerttab(String alertName, String type, String desc, String pod, String priority) 
	{
		try {
			wait.waitForLoad(driver);
			//		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[starts-with(@id ,'vfFrameId')]")));
			wait.waitForLoad(driver);
			Thread.sleep(4000);
			memberPage.manager_alert.click();
			WebElement alert_div=driver.findElement(By.xpath("//div[@class='slds-utility-panel__body' and @style='height: 507px;']"));
			if(alert_div.isDisplayed())
			{
				takeScreenshot(path, "Alert Pop up is displaying");
				System.out.println("Alert Pop up is displaying");
			}
			else
			{
				System.out.println("Alert Pop up is not displaying");
			}

			memberPage.alret_name.sendKeys(alertName);

			Select alr_typ=new Select(memberPage.alert_type);
			alr_typ.selectByValue(type);

			memberPage.alert_desc.sendKeys(desc);

			Select alr_pod=new Select(memberPage.alert_pod);
			//List<WebElement> abc=alr_pod.getOptions();
			alr_pod.selectByVisibleText(pod);
			//alr_pod.selectByValue(pod);
			//	System.out.println("Selected");

			Select alr_prior=new Select(memberPage.alert_prio);
			alr_prior.selectByValue(priority);

			memberPage.alert_sdate_cal.click();
			memberPage.alert_start_date.click();
			memberPage.alert_edate_cal.click();
			memberPage.alert_next_month.click();
			memberPage.alert_end_date.click();

			Thread.sleep(4000);
			takeScreenshot(path, "Alert with all details entered");
			System.out.println("Alert created successfully");

			memberPage.save_btn.click();	
			Thread.sleep(3000);
			takeScreenshot(path, "Alert created with all details entered");


		} catch (Exception e) {
			e.printStackTrace();
			takeScreenshot(path, "Unable to create New Alert");
			//throw new RuntimeException("Error : Couldn't create a new Alert");
		}

	}

	public void alertcreated(String alertname) {
		try {

			memberPage.created_alert.click();
			//	List<WebElement> list1=driver.findElements(By.xpath("//*[contains(text(),'" + TestAlert123 + "')]"));
			//	Assert.assertTrue(list1.size() > 0, "Text not found!");
			//	Assert.IsTrue(driver.getPageSource().contains(arg1));
			boolean txt=driver.getPageSource().contains(alertname);
			if (txt==true) 
			{
				takeScreenshot(path, "Created Alert name");
				System.out.println("The created alert name is displaying under Created Alert tab section");
			} 
			else 
			{
				System.out.println("The created alert name is not displaying under Created Alert tab section");
			}

		} catch (Exception e) {
			e.printStackTrace();
			takeScreenshot(path, "Created Alert is not showing");
			//	throw new RuntimeException("Error : Couldn't create a new Alert");
		}

	}

	public void navigate_created_alert() {
		try {
			wait.waitForLoad(driver);
			wait.waitForLoad(driver);
			Thread.sleep(4000);
			memberPage.manager_alert.click();
			WebElement alert_div=driver.findElement(By.xpath("//div[@class='slds-utility-panel__body' and @style='height: 507px;']"));
			if(alert_div.isDisplayed())
			{
				takeScreenshot(path, "Alert Pop up is displaying");
				System.out.println("Alert Pop up is displaying");
			}
			else
			{
				System.out.println("Alert Pop up is not displaying");
			}
			memberPage.created_alert.click();
			Thread.sleep(3000);
			takeScreenshot(path, "Created Alert Section");
			String alrt_txt=memberPage.fst_alrt_nme.getText();
			System.out.println(alrt_txt);

			/*List<WebElement> before_dlt=driver.findElements(By.xpath("//strong[contains(text(),'Active Alert') or contains(text(),'Upcoming Alert')]//..//..//table//tr[not(contains(@class,'slds-text-title_caps'))]"));
			int bfr_count=before_dlt.size();
			System.out.println(bfr_count);
			Actions act=new Actions(driver);
			act.moveToElement(memberPage.fst_dlt_icn);
			memberPage.fst_dlt_icn.click();

			takeScreenshot(path, "'"+alrt_txt+"' "+"Alert deleted");*/

			/*List<WebElement> after_dlt=driver.findElements(By.xpath("//strong[contains(text(),'Active Alert') or contains(text(),'Upcoming Alert')]//..//..//table//tr[not(contains(@class,'slds-text-title_caps'))]"));
			int aftr_count=after_dlt.size();
			System.out.println(aftr_count);

			if(aftr_count==bfr_count-1)
			{
				System.out.println("The alert "+alrt_txt+"deleted");
				System.out.println("Previous alert count was"+bfr_count);
				System.out.println("Current alert count is"+aftr_count);
			}
			else
			{
				System.out.println("Alert is not deleted");
			}*/
		}
		catch (Exception e) {
			e.printStackTrace();
			takeScreenshot(path, "Alert is not deleted");
		}
	}	

	public void deleted_alert() {
		try
		{			
			List<WebElement> bfre_alert_ActiveAlert=driver.findElements(By.xpath("//div/strong[contains(text(),'Active Alerts')]/parent::div/following-sibling::table/tbody/tr"));
			int bfre_rowcount=bfre_alert_ActiveAlert.size();	
			System.out.println(bfre_rowcount);
			Thread.sleep(4000);
			WebElement activeAlert=driver.findElement(By.xpath("//div/strong[contains(text(),'Active Alerts')]/parent::div/following-sibling::table/tbody/tr[1]/td[9]/div/span"));			
			Actions actions=new Actions(driver);
			//actions.moveToElement(activeAlert).build().perform();
			WebDriverWait wait=new WebDriverWait(driver, 2);
			wait.until(ExpectedConditions.elementToBeClickable(activeAlert));
			actions.doubleClick(activeAlert).build().perform();			
			//((JavascriptExecutor) driver).executeScript("arguments[0].click();", activeAlert);
			Thread.sleep(4000);
			
			List<WebElement> aftr_alert_ActiveAlert=driver.findElements(By.xpath("//div/strong[contains(text(),'Active Alerts')]/parent::div/following-sibling::table/tbody/tr"));
			int aftr_rowcount=aftr_alert_ActiveAlert.size();
            System.out.println(aftr_rowcount);
            
			if(bfre_rowcount!=aftr_rowcount) {
				System.out.println("Alert deleted successfully");
				takeScreenshot(path, "Alert deleted");
			}
			else {
				System.out.println("Alert still exists");
			}

		}
		catch (Exception e) {
			e.printStackTrace();
			takeScreenshot(path, "Alert is not deleted");
		}

	}

	public void changealertstatus(String sts) {
		try {
			wait.waitForLoad(driver);
			wait.waitForLoad(driver);
			Thread.sleep(4000);
			memberPage.manager_alert.click();
			WebElement alert_div=driver.findElement(By.xpath("//div[@class='slds-utility-panel__body' and @style='height: 507px;']"));
			if(alert_div.isDisplayed())
			{
				takeScreenshot(path, "Alert Pop up is displaying");
				System.out.println("Alert Pop up is displaying");
			}
			else
			{
				System.out.println("Alert Pop up is not displaying");
			}
			memberPage.created_alert.click();
			Thread.sleep(4000);
			//	takeScreenshot(path, "Created Alert Section");
			//action.sendKeys(Keys.PAGE_DOWN);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", memberPage.dactv_alrt_name);
			memberPage.dactv_alrt_name.click();
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", memberPage.minimize);
			memberPage.minimize.click();

			Thread.sleep(5000);
			wait.waitForLoad(driver);
			/*Robot robot=new Robot();
			for(int i=0;i<9; i++)
			{
				robot.keyPress(KeyEvent.VK_DOWN);
				robot.keyRelease(KeyEvent.VK_DOWN);
			}*/


			//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", we);
			WebElement alertName= memberPage.alrt_edt_icn;
			alertName.sendKeys(Keys.PAGE_DOWN);
			WebElement we = memberPage.sts_edt_icn;
			//	

			//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", we);
			//action.moveToElement(memberPage.sts_edt_icn).build().perform();
			WebElement weamscn = memberPage.sts_edt_icn;
			if (memberPage.sts_edt_icn.isDisplayed()) {
				System.out.println("Status Edit icon is present ");
			} else {
				System.out.println("Status Edit icon is not present ");
			}

			memberPage.sts_edt_icn.click();
			Thread.sleep(2000);

			//	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", memberPage.drpdwn_sts);
			memberPage.drpdwn_sts.click();
			//	WebElement wl=driver.findElement(By.xpath("//span[text()='Status']//..//..//div//a"));
			//	wl.click();
			System.out.println("clicked");
			//Select slct_dr=new Select(memberPage.drpdwn_sts);
			//slct_dr.selectByValue(sts);
			//	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", memberPage.active_sts);
			memberPage.active_sts.click();
			Thread.sleep(2000);

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", memberPage.save_btn_sts);
			memberPage.save_btn_sts.click();	
			Thread.sleep(2000);

		}
		catch (Exception e) 
		{
			e.printStackTrace();
			System.out.println("Unable to change the status of an Alert");
		}

	}

	public void verifyalertstatus(String sts) {

		String chng_sts_txt=memberPage.changed_sts.getText();
		if(chng_sts_txt.equalsIgnoreCase(sts))
		{
			System.out.println("Status of an alert changed to "+sts+ "successfully");
		}
		else
		{
			System.out.println("Status of an alert has not changed to "+sts);

		}
	}

	public void verifySystemDate() throws ParseException {

		try {
			DateFormat dtf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			String current_dt=dtf.format(date);
			Date system_dt=(Date)dtf.parse(current_dt);

			String start_dt = memberPage.strt_DT_ActiveAlert.getText().toString().trim();
			Date strt_date = (Date)dtf.parse(start_dt);

			String end_dt=memberPage.end_DT_ActiveAlert.getText().toString().trim();
			Date end_date=(Date)dtf.parse(end_dt);

			if((system_dt.after(strt_date) || system_dt.equals(strt_date)) && ((system_dt.before(end_date) || system_dt.equals(end_date))))
			{
				System.out.println("Current date "+system_dt + "is in between start date " +strt_date +"and end date"+end_date+"PASS");
			}else {
				System.out.println("Current date "+system_dt + "is not in between start date " +strt_date +"and end date"+end_date+"FAIL");
			}
		}
		catch(Exception e)		
		{
			e.printStackTrace();
			System.out.println("Unable to retrive the system date");
		}
	}


	public void verifyAlertTableDetails()
	{

		try {
			Thread.sleep(4000);
			memberPage.manager_alert.click();
			Thread.sleep(2000);
			if(memberPage.created_alert.isDisplayed() && memberPage.create_alert.isDisplayed())
			{
				System.out.println("Create Alert and Created Alert tabs are displayed");
			}
			else {
				System.out.println("Create Alert and Created Alert tabs are not displayed");
			}
			Thread.sleep(2000);
			memberPage.created_alert.click();
			Thread.sleep(2000);

			if(memberPage.active_alert.isDisplayed() && memberPage.upcoming_alert.isDisplayed() && memberPage.deactivated_alert.isDisplayed())
			{
				System.out.println("Active Alert, Upcoming alert and Deactive alert sections are displayed");
			}else {
				System.out.println("Active Alert, Upcoming alert and Deactive alert sections are not displayed");	
			}
			String headerNames= "POD,PRIORITY,SUBJECT,CREATED BY,ACKNOWLODGE,TYPE,START DATE,END DATE,ACTION";				
			boolean flag=false;
			List<String> activeAlerts=verifyAlertTbleHeaders("Active Alerts");
			for(String alertHeader: activeAlerts)
			{			
				if(headerNames.contains(alertHeader))
				{
					flag=true;
					System.out.println("Active Alert Table contains expected header values" +alertHeader);
				}
			}
			if(!flag)
			{
				System.out.println("Active Alert Table doesnot contains expected header values");
			}
			Thread.sleep(2000);
			List<String> upcomingAlerts=verifyAlertTbleHeaders("Upcoming Alerts");
			for(String alertHeader: upcomingAlerts)
			{
				if(headerNames.contains(alertHeader))
				{
					flag=true;
					System.out.println("Upcoming Alert Table contains expected header values" +alertHeader);
				}
			}
			if(!flag)
			{
				System.out.println("Upcoming Alert Table doesnot contains expected header values");
			}
			Thread.sleep(2000);
			List<String> deactivatedAlerts=verifyAlertTbleHeaders("Deactivated Alerts");
			for(String alertHeader: deactivatedAlerts)
			{
				if(headerNames.contains(alertHeader))
				{
					flag=true;
					System.out.println("Deactivated Alert Table contains expected header values" +alertHeader);
				}
			}
			if(!flag)
			{
				System.out.println("Deactivated Alert Table doesnot contains expected header values");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Unable to retrive the alert table details");
		}


	}

	public List<String> verifyAlertTbleHeaders(String alertTxt) {

		List<String> headerValue=new ArrayList<>();
		try {
			List<WebElement> dtable=driver.findElements(By.xpath("//div/strong[contains(text(),'"+alertTxt+"')]/parent::div/following-sibling::table/thead/tr/th"));
			int count=dtable.size();			
			for(int i=1;i<=count;i++)
			{
				WebElement alert_HeaderValues=driver.findElement(By.xpath("//div/strong[contains(text(),'"+alertTxt+"')]/parent::div/following-sibling::table/thead/tr/th["+i+"]/div/span"));
				String alertHeaderValues=alert_HeaderValues.getText().toString().trim();
				System.out.println(alertHeaderValues);
				headerValue.add(alertHeaderValues);

			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Unable to retrive header values from alert tables");
		}
		return headerValue;

	}

}


