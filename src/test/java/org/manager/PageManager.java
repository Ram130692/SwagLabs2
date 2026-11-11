package org.manager;

import org.baseclass.BaseClass;
import org.page.AddItemToCartPage;
import org.page.CheckoutPage;
import org.page.LoginPage;
import org.page.LogoutPage;


public class PageManager extends BaseClass{
	
	private BaseClass baseClass;
	private LoginPage loginPage;
	private AddItemToCartPage itemToCart;
	private CheckoutPage checkoutPage;
	private LogoutPage logoutPage;
	
	
	public BaseClass getBaseClass() {
		return(baseClass==null)?baseClass=new BaseClass():baseClass;
	}
	
	public LoginPage getLoginPage() {
		return(loginPage==null)?loginPage=new LoginPage():loginPage;
	}
	
	public AddItemToCartPage getItemToCart() {
		return(itemToCart==null)?itemToCart=new AddItemToCartPage():itemToCart;	
	}
	
	public CheckoutPage getCheckoutPage() {
		return(checkoutPage==null)?checkoutPage=new CheckoutPage():checkoutPage;	
	}
	

	public LogoutPage getLogoutPage() {
		return(logoutPage==null)?logoutPage=new LogoutPage():logoutPage;	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
