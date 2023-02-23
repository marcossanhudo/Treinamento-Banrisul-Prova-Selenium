package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

public class LoginPage {

	private WebDriver driver;
	private final String FORM_XPATH_PREFIX = "//div[@class='card__login']/form/div/";
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebElement getEmailInput() {
		return driver.findElement(By.xpath(FORM_XPATH_PREFIX + "input[@name='email']"));
	}
	
	public WebElement getSenhaInput() {
		return driver.findElement(By.xpath(FORM_XPATH_PREFIX + "div/input[@name='password']"));
	}
	
	public WebElement getAcessarButton() {
		return driver.findElement(By.xpath("//div[@class='login__buttons']/button[contains(text(), 'Acessar')]"));
	}
	
	public WebElement getRegistrarButton() {
		return driver.findElement(By.xpath("//div[@class='login__buttons']/button[contains(text(), 'Registrar')]"));
	}
	
}
