package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

public class TransferenciaPage extends GenericPage {

	public TransferenciaPage(WebDriver driver) {
		super(driver);
	}
	
	public WebElement getNumeroDaContaInput() {
		return driver.findElement(By.name("accountNumber"));
	}
	
	public WebElement getDigitoInput() {
		return driver.findElement(By.name("digit"));
	}
	
	public WebElement getValorDaTransferenciaInput() {
		return driver.findElement(By.name("transferValue"));
	}
	
	public WebElement getDescricaoInput() {
		return driver.findElement(By.name("description"));
	}
	
	public WebElement getTransferirAgoraButton() {
		return driver.findElement(By.xpath("//button[contains(text(), 'Transferir agora')]"));
	}
	
}
