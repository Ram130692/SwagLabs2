package org.page;

import org.baseclass.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BaseClass{
	
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//input[@id='user-name']")
	WebElement txtUserName;
	public WebElement getUserName() {
		return txtUserName;
		
	}
	
	@FindBy(xpath="//input[@id='password']")
	WebElement txtPassword;
	public WebElement getPassword() {
		return txtPassword;
	}
	
	@FindBy(xpath="//input[@id='login-button']")
	WebElement loginBtn;
	public WebElement clickLogin() {
		return loginBtn;
	}
	
	@FindBy(xpath="//h3[@data-test='error']")
	WebElement errorMsg;
	public WebElement getErrorMsgEle() {
		return errorMsg;
		
	}
}





















