package PageObjects;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ExtratoPage extends GenericPage {

	public ExtratoPage(WebDriver driver) {
		super(driver);
	}
	
	public WebElement getSaldoParagraph() {
		return driver.findElement(By.id("textBalanceAvailable"));
	}
	
	public double getBalance() {
		String saldo = getSaldoParagraph().getText().substring("R$ ".length());
		Matcher matcher = Pattern.compile("[0-9]+,[0-9]{2}").matcher(saldo);
		if (matcher.find()) {
			saldo.replace(',', '.');
			return Double.parseDouble(saldo);
		}
		return 0;
	}
	
}
