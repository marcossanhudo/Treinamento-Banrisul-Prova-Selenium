package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

public class HomePage extends GenericPage {

	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	public WebElement getNumeroDaContaSpan() {
		return driver.findElement(By.xpath("//p[@id='textAccountNumber']/span"));
	}
	
	public WebElement getTransferenciaButton() {
		return driver.findElement(By.id("btn-TRANSFERÊNCIA"));
	}
	
	public WebElement getExtratoButton() {
		return driver.findElement(By.id("btn-EXTRATO"));
	}
	
	public WebElement getSairButton() {
		return driver.findElement(By.id("btnExit"));
	}
	
}
