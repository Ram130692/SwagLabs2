package org.testclass;

import java.io.IOException;
import java.util.List;

import org.baseclass.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class swagTestClass extends BaseClass {
	public static void main(String[] args) throws IOException, InterruptedException {

		// launching the application- step 1 and 2
		BaseClass baseClass = new BaseClass();
		baseClass.setup(baseClass.readExcel("sheet1", 1, 0), baseClass.readExcel("sheet1", 1, 1));

		// Entering the login details - step 3
		WebElement userName = driver.findElement(By.xpath("//input[@id='user-name']"));
		baseClass.getSendKeys(userName, baseClass.readExcel("sheet1", 1, 2));
		WebElement password = driver.findElement(By.xpath("//input[@id='password']"));
		baseClass.getSendKeys(password, baseClass.readExcel("sheet1", 1, 3));
		// clicking Login button
		WebElement loginBtn = driver.findElement(By.xpath("//input[@id='login-button']"));
		baseClass.performClick(loginBtn);

		// Add the item in the cart - step 4

		WebElement bagItem = driver.findElement(By.xpath("(//button[@class='btn_primary btn_inventory'])[1]"));
		baseClass.performClick(bagItem);
		Thread.sleep(2000);

		WebElement addCartBtn = driver.findElement(By.xpath("(//button[@class='btn_primary btn_inventory'])[2]"));
		baseClass.performClick(addCartBtn);
		Thread.sleep(2000);

		// validate the cart - step 5
		WebElement cartBtn = driver.findElement(By.xpath("//div[@id='shopping_cart_container']"));
		baseClass.performClick(cartBtn);

		
		
		List<WebElement> allItemsInCart = driver.findElements(By.xpath("//div[@class='cart_item']"));
		// String expectedTextItem="Sauce Labs Bolt T-Shirt";
		String capturedTextItem = "";
		if (!allItemsInCart.isEmpty()) {
			List<WebElement> listItems = driver.findElements(By.xpath("//div[@class='inventory_item_name']"));
			for (WebElement itms : listItems) {

				capturedTextItem += baseClass.captureText(itms).concat("\n");

			}
			System.out.println("Your recentely added item is available in cart");
			System.out.println("Added item names is:" + capturedTextItem);

			int size2 = allItemsInCart.size();
			System.out.println("Total items in cart:" + size2 + " items");
			System.out.println("================================================");

			
			
			
			
			
			
			
			
			
			Thread.sleep(2000);
			// click on the check out button - step 6 and 7
			WebElement checkoutBtn = driver.findElement(By.xpath("//a[text()='CHECKOUT']"));
			if (checkoutBtn.isDisplayed()) {
				System.out.println("checkout button is appered");
				Thread.sleep(3000);
				baseClass.performClick(checkoutBtn);
			} else {
				System.out.println("checkout button is not visible...");
			}

			
			
			
			
			
			
			
			// fill the information for check out and continue step-8
			WebElement firstName = driver.findElement(By.xpath("//input[@id='first-name']"));
			baseClass.getSendKeys(firstName, baseClass.readExcel("sheet2", 2, 0));

			WebElement lastName = driver.findElement(By.xpath("//input[@id='last-name']"));
			baseClass.getSendKeys(lastName, baseClass.readExcel("sheet2", 2, 1));

			WebElement postalCode = driver.findElement(By.xpath("//input[@id='postal-code']"));
			baseClass.getSendKeys(postalCode, baseClass.readExcel("sheet2", 2, 1));

			WebElement continueBtn = driver.findElement(By.xpath("//input[@value='CONTINUE']"));
			baseClass.performClick(continueBtn);

			
			
			
			
			System.out.println("====================================");
			// validate checkout overview page step-9
			List<WebElement> checkoutItems = driver.findElements(By.xpath("//div[@class='cart_item']"));
			if (!checkoutItems.isEmpty()) {
				int size = checkoutItems.size();
				System.out.println("Total items to checkout :" + size + " item");
			} else {
				System.out.println("No items is available to checkout...");
			}

			// click finish step -10
			WebElement finishBtn = driver.findElement(By.xpath("//a[normalize-space()='FINISH']"));
			if (!checkoutItems.isEmpty()) {
				baseClass.performClick(finishBtn);
			}

			
			
			System.out.println("==========================");
			// validate order confirmation page step - 11
			WebElement confirmationMsg = driver.findElement(By.xpath("//h2[@class='complete-header']"));
			if (confirmationMsg.isDisplayed()) {
				String confirmationText = baseClass.captureText(confirmationMsg);
				System.out.println("Message Displayed");
				System.out.println("Displayed Message is:" + confirmationText);
			}
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			// back to Home step-12
			WebElement humbergBtn = driver.findElement(By.xpath("//button[text()='Open Menu']"));
			baseClass.performClick(humbergBtn);
			WebElement homePage = driver.findElement(By.xpath("//a[text()='All Items']"));
			baseClass.performClick(homePage);

			System.out.println("=========================");
			// logout step-13
			WebElement humbergBtn1 = driver.findElement(By.xpath("//button[text()='Open Menu']"));
			baseClass.performClick(humbergBtn1);
			Thread.sleep(2000);
			WebElement logoutBtn = driver.findElement(By.xpath("//a[text()='Logout']"));
			if (logoutBtn.isDisplayed()) {
				baseClass.performClick(logoutBtn);
				System.out.println("User logged out successfully...");
			}
		} else {
			System.out.println("Cart is Empty...you need to add the items");
		}

		Thread.sleep(2000);
		driver.quit();
	}
}
