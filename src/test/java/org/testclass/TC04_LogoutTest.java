package org.testclass;

import java.io.IOException;
import java.util.List;

import org.baseclass.BaseClass;
import org.manager.PageManager;
import org.openqa.selenium.WebElement;
import org.page.AddItemToCartPage;
import org.page.CheckoutPage;
import org.page.LoginPage;
import org.page.LogoutPage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class TC04_LogoutTest extends BaseClass {

	public static PageManager pageManager;
	public static BaseClass baseClass;
	public static LoginPage loginPage;
	public static AddItemToCartPage itemToCart;
	public static CheckoutPage checkoutPage;
	public static LogoutPage logoutPage;
	public static boolean isLoggedin = false;
	public static boolean isItemAdded = false;
	public static boolean isinCheckout = false;
	public static boolean isinLogout = false;

	@BeforeClass
	public  void browserLaunch() throws IOException {
		pageManager = new PageManager();
		baseClass = pageManager.getBaseClass();
		baseClass.setup(baseClass.readExcel("sheet1", 1, 0), baseClass.readExcel("sheet1", 1, 1));
	}

	@Test(priority = 9)
	public void EnterLoginCredentials() throws IOException {
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
				System.out.println("Successfully Entered Login Credentials");
				isLoggedin = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 10)
	public void adddItemToCart() throws InterruptedException {

		if (isLoggedin) {
			itemToCart = pageManager.getItemToCart();

			WebElement addToCartItem = itemToCart.getAddToCartItem();
			baseClass.performClick(addToCartItem);

			WebElement cartBtn = itemToCart.getCartBtn();
			baseClass.performClick(cartBtn);

			List<WebElement> allItemsInCart = itemToCart.getItemsInCart();
			String capturedTextItem = "";
			if (!allItemsInCart.isEmpty()) {
				List<WebElement> listItm = itemToCart.getListItems();
				for (WebElement itms : listItm) {
					capturedTextItem += baseClass.captureText(itms).concat("\n");
				}
				System.out.println("Your recentely added item is available in cart");
				System.out.println("Added item is:" + capturedTextItem);
				int size = allItemsInCart.size();
				System.out.println("Total items in cart: " + size + " item in cart");

				WebElement checkoutBtnEle = itemToCart.getCheckoutBtnEle();
				if (checkoutBtnEle.isDisplayed()) {
					System.out.println("checkout button is appered");
					Thread.sleep(1000);
					baseClass.performClick(checkoutBtnEle);
					isItemAdded = true;

				} else {
					System.out.println("checkout button is not visible...");
				}

			} else {
				System.out.println("Cart is Empty..need to add items");
			}
		}
	}

	@Test(priority = 11)
	public void checkoutPage() throws IOException {
		if (isItemAdded) {
			checkoutPage = pageManager.getCheckoutPage();
			WebElement firstName = checkoutPage.getFirstName();
			baseClass.getSendKeys(firstName, baseClass.readExcel("sheet2", 2, 0));

			WebElement lastName = checkoutPage.getLastName();
			baseClass.getSendKeys(lastName, baseClass.readExcel("sheet2", 2, 1));

			WebElement pinCode = checkoutPage.getPinCode();
			baseClass.getSendKeys(pinCode, baseClass.readExcel("sheet2", 2, 1));

			WebElement clickContinueBtn = checkoutPage.clickContinueBtn();
			baseClass.performClick(clickContinueBtn);

			List<WebElement> itemToCheckout = checkoutPage.getItemToCheckout();
			if (!itemToCheckout.isEmpty()) {
				int size = itemToCheckout.size();
				System.out.println("Total items to checkout :" + size + " item");
			} else {
				System.out.println("No items is available to checkout...");
			}

			WebElement clickFinishBtn = checkoutPage.clickFinishBtn();
			if (!itemToCheckout.isEmpty()) {
				baseClass.performClick(clickFinishBtn);
			}

			WebElement confirmationMsg = checkoutPage.getConfirmationMsg();
			if (confirmationMsg.isDisplayed()) {
				String confirmationText = baseClass.captureText(confirmationMsg);
				System.out.println("Message Displayed");
				System.out.println("Displayed Message is:" + confirmationText);
				isinCheckout = true;
			}
		}
	}

	@Test(priority = 12)
	public  void logoutPageTest() throws InterruptedException {

		if (isinCheckout) {

			logoutPage = pageManager.getLogoutPage();
			WebElement clickHumbergBtn = logoutPage.clickHumbergBtn();
			baseClass.performClick(clickHumbergBtn);

			WebElement clickAllItems = logoutPage.clickAllItems();
			baseClass.performClick(clickAllItems);

			WebElement clickHumbergBtn2 = logoutPage.clickHumbergBtn();
			baseClass.performClick(clickHumbergBtn2);
			Thread.sleep(2000);

			WebElement clickLogoutBtn = logoutPage.clickLogoutBtn();
			if (clickLogoutBtn.isDisplayed()) {
				baseClass.performClick(clickLogoutBtn);
				System.out.println("User logged out successfully...");
				isinLogout = true;
			} else {
				System.out.println("Logout Button is not displayed...");
			}

		} else {
			System.out.println("Login Failed");
		}

		if (isinLogout) {
			System.out.println("Test Passed:TC04_LogoutTest");
		} else {
			System.out.println("Test Skipped:TC04_LogoutTest");
		}
	}
	
	@AfterClass
	public void closeBrowser() throws InterruptedException {
		Thread.sleep(2000);
		driver.quit();
	}

}
