package org.page;

import org.baseclass.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogoutPage extends BaseClass{
	
	public LogoutPage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//button[text()='Open Menu']")
	WebElement humberBtn;
	public WebElement clickHumbergBtn() {
		return humberBtn;
	}
	
	@FindBy(xpath="//a[text()='All Items']")
	WebElement allItems;
	public WebElement clickAllItems() {
		return allItems;
	}
	

	@FindBy(xpath="//a[text()='Logout']")
	WebElement logoutBtn;
	public WebElement clickLogoutBtn() {
		return logoutBtn;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
