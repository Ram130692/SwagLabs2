package org.page;

import java.util.List;

import org.baseclass.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddItemToCartPage extends BaseClass{
	
	public AddItemToCartPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="(//button[@class='btn_primary btn_inventory'])[3]")
	WebElement itemToCart;
	public WebElement getAddToCartItem() {
		return itemToCart;
	}
	
	@FindBy(xpath="//div[@id='shopping_cart_container']")
	WebElement cartBtn;
	public WebElement getCartBtn() {
		return cartBtn;
	}
	
	@FindBy(xpath="//div[@class='cart_item']")
	List<WebElement> itemsInCart;
	public List<WebElement> getItemsInCart() {
		return itemsInCart;
	}
	
	@FindBy(xpath="//div[@class='inventory_item_name']")
	List<WebElement> listItem;
	public List<WebElement> getListItems() {
		return listItem;
		
	}
	
	@FindBy(xpath="//a[text()='CHECKOUT']")
	WebElement checkoutBtn;
	public WebElement getCheckoutBtnEle() {
		return checkoutBtn;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
