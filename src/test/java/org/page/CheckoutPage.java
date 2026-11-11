package org.page;

import java.util.List;

import org.baseclass.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends BaseClass{
	
	public CheckoutPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//input[@id='first-name']")
	WebElement txtFirstName;
	public WebElement getFirstName() {
		return txtFirstName;
	}
	
	@FindBy(xpath="//input[@id='last-name']")
	WebElement txtLastName;
	public WebElement getLastName() {
		return txtLastName;
	}
	
	@FindBy(xpath="//input[@id='postal-code']")
	WebElement pinCode;
	public WebElement getPinCode() {
		return pinCode;
	}
	
	@FindBy(xpath="//input[@value='CONTINUE']")
	WebElement continueBtn;
	public WebElement clickContinueBtn() {
		return continueBtn;
	}
	
	@FindBy(xpath="//div[@class='cart_item']")
	List<WebElement> itemsToCheckout;
	public List<WebElement> getItemToCheckout(){
		return itemsToCheckout;
	}
	
	@FindBy(xpath="//a[normalize-space()='FINISH']")
	WebElement finishBtn;
	public WebElement clickFinishBtn() {
		return finishBtn;
	}
	
	@FindBy(xpath="//h2[@class='complete-header']")
	WebElement confirmationMsg;
	public WebElement getConfirmationMsg() {
		return confirmationMsg;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
