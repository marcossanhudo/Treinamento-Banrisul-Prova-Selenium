package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

public class GenericPage {

	protected WebDriver driver;
	
	public GenericPage(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getModal() {
		return driver.findElement(By.className("styles__ContainerContent-sc-8zteav-1"));
	}
	
	public WebElement getModalTextParagraph() {
		return driver.findElement(By.id("modalText"));
	}
	
	public WebElement getFecharModalButton() {
		return driver.findElement(By.id("btnCloseModal"));
	}
	
	public WebElement getSairButton() {
		return driver.findElement(By.id("btnExit"));
	}
	
	public WebElement getVoltarLink() {
		return driver.findElement(By.id("btnBack"));
	}
	
}
