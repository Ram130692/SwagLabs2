package org.testclass;

import java.io.IOException;

import org.baseclass.BaseClass;
import org.manager.PageManager;
import org.openqa.selenium.WebElement;
import org.page.LoginPage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class TC01_LoginTest extends BaseClass {
	public static PageManager pageManager;
	public static BaseClass baseClass;
	public static LoginPage loginPage;
	public static boolean isLoggedin = false;

	@BeforeClass
	public  void browserLaunch() throws IOException {
		pageManager = new PageManager();
		baseClass = pageManager.getBaseClass();
		baseClass.setup(baseClass.readExcel("sheet1", 1, 0), baseClass.readExcel("sheet1", 1, 1));
	}

	@Test(priority = 1)
	public void loginCredentials() throws IOException {
		try {

			loginPage = pageManager.getLoginPage();

			WebElement userName = loginPage.getUserName();
			baseClass.getSendKeys(userName, baseClass.readExcel("sheet1", 1, 2));

			WebElement password = loginPage.getPassword();
			baseClass.getSendKeys(password, baseClass.readExcel("sheet1", 1, 3));

			WebElement clickLogin = loginPage.clickLogin();
			baseClass.performClick(clickLogin);
			try {
				WebElement errorMsgEle = loginPage.getErrorMsgEle();
				if (errorMsgEle.isDisplayed()) {
					String actualText = baseClass.captureText(errorMsgEle);
					String expText = baseClass.readExcel("sheet1", 1, 4);
					if (actualText.contains(expText)) {
						System.out.println("Error Msg:" + actualText);
						isLoggedin = false;
					}
				}
			} catch (Exception e) {
				isLoggedin = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 2)
	public void result() {
		if (isLoggedin) {
			System.out.println("Test Passed:TC01_LoginTest");
		} else {
			System.out.println("Test Failed:TC01_LoginTest");
		}
	}

	@AfterClass
	public  void closeBrowser() throws InterruptedException {
		Thread.sleep(2000);
		driver.quit();
	}
}
