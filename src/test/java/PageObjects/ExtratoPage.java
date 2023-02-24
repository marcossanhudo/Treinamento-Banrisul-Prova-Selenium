package PageObjects;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Framework.Utils.NumberFormatter;

public class ExtratoPage extends GenericPage {

	private final String PRIMEIRA_TRANSACAO_XPATH = "//div[@class='bank-statement__ContainerTransaction-sc-7n8vh8-12 fCYQeb']/"
			+ "div[2]/"
			+ "div[@class='bank-statement__ContainerDescAndValue-sc-7n8vh8-15 rYqba']/";
	
	public ExtratoPage(WebDriver driver) {
		super(driver);
	}
	
	public WebElement getSaldoParagraph() {
		return driver.findElement(By.id("textBalanceAvailable"));
	}
	
	public WebElement getValorDaPrimeiraTransacaoParagraph() {
		return driver.findElement(By.xpath(PRIMEIRA_TRANSACAO_XPATH + "p[@id='textTransferValue']"));
	}
	
	public WebElement getDescricaoDaPrimeiraTransacaoParagraph() {
		return driver.findElement(By.xpath(PRIMEIRA_TRANSACAO_XPATH + "p[@id='textDescription']"));
	}
	
	public WebElement getPrimeiraTransacaoDiv() {
		return driver.findElement(By.xpath(PRIMEIRA_TRANSACAO_XPATH));
	}
	
	public double getSaldo() {
		return NumberFormatter.brazilianToJavaDouble(getSaldoParagraph().getText());
	}
	
}
