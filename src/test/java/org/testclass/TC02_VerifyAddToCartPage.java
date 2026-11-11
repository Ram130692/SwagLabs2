package org.testclass;

import java.io.IOException;
import java.util.List;

import org.baseclass.BaseClass;
import org.manager.PageManager;
import org.openqa.selenium.WebElement;
import org.page.AddItemToCartPage;
import org.page.LoginPage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class TC02_VerifyAddToCartPage extends BaseClass {
	public static PageManager pageManager;
	public static BaseClass baseClass;
	public static LoginPage loginPage;
	public static AddItemToCartPage itemToCart;
	public static boolean isLoggedin = false;
	public static boolean isItemAdded = false;

	@BeforeClass
	public  void browserLaunch() throws IOException {
		pageManager = new PageManager();
		baseClass = pageManager.getBaseClass();
		baseClass.setup(baseClass.readExcel("sheet1", 1, 0), baseClass.readExcel("sheet1", 1, 1));
	}

	@Test(priority = 3)
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

	@Test(priority = 4)
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

		} else {
			System.out.println("Login Failed...Enter valid details");
		}
	}

	@Test(priority = 5)
	public void result() {
		if (isItemAdded) {
			System.out.println("Test Passed:TC02_VerifyAddToCartPage");
		} else {
			System.out.println("Test Skipped:TC02_VerifyAddToCartPage");
		}
	}

	@AfterClass
	public  void closeBrowser() throws InterruptedException {
		Thread.sleep(2000);
		driver.quit();
	}

}
