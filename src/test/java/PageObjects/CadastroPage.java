package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class CadastroPage {

	private WebDriver driver;
	private final String FORM_XPATH_PREFIX = "//div[@class='card__register']/form/div/";
	private GenericPage genericModal;
	
	public CadastroPage(WebDriver driver) {
		this.driver = driver;
		genericModal = new GenericPage(this.driver);
	}
	
	public WebElement getEmailInput() {
		return driver.findElement(By.xpath(FORM_XPATH_PREFIX + "input[@name='email']"));
	}
	
	public WebElement getNomeInput() {
		return driver.findElement(By.xpath(FORM_XPATH_PREFIX + "input[@name='name']"));
	}
	
	public WebElement getSenhaInput() {
		return driver.findElement(By.xpath(FORM_XPATH_PREFIX + "div/input[@name='password']"));
	}
	
	public WebElement getConfirmacaoSenhaInput() {
		return driver.findElement(By.xpath(FORM_XPATH_PREFIX + "div/input[@name='passwordConfirmation']"));
	}
	
	public WebElement getCriarContaComSaldoToggle() {
		return driver.findElement(By.id("toggleAddBalance"));
	}
	
	public WebElement getCadastrarButton() {
		return driver.findElement(By.xpath("//div[@class='card__register']/form/button[contains(text(), 'Cadastrar')]"));
	}
	
	public String extrairNumeroDaContaNoModal() {
		String textoDeModalText = genericModal.getModalTextParagraph().getText();
		String possivelNumero = textoDeModalText.split(" ")[2];
		
		Matcher matcher = Pattern.compile("[0-9]?[0-9]{2}-[0-9]").matcher(possivelNumero);
		if (matcher.find()) return possivelNumero;
		return null;
	}
}
