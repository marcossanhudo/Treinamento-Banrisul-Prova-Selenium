package Validations;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import PageObjects.HomePage;
import Framework.Browser.Waits;
import org.junit.jupiter.api.Assertions;
import Framework.Utils.FileOperation;
import Framework.Report.Report;
import com.aventstack.extentreports.Status;
import Framework.Report.Screenshot;

public class HomeValidation {

	private WebDriver driver;
	private HomePage homePage;
	private Waits wait;
	
	public HomeValidation(WebDriver driver) {
		this.driver = driver;
		homePage = new HomePage(this.driver);
		wait = new Waits(this.driver);
	}
	
	public void validateNumeroDaContaSpan(String idDoUsuario) {
		wait.visibilityOfElement(By.xpath("//p[@id='textAccountNumber']/span"));
		try {
			Assertions.assertEquals(
					FileOperation.getProperty("user", idDoUsuario + ".numeroDaConta"),
					homePage.getNumeroDaContaSpan().getText());
			Report.log(Status.PASS, "Foi encontrado o número " + homePage.getNumeroDaContaSpan().getText() + ", esperado da conta do usuário.", Screenshot.captureFile(driver));
		} catch (Exception e) {
			Report.log(Status.FAIL, "Não foi encontrado o número esperado de conta. " + e.getMessage(), Screenshot.captureFile(driver));
		}
	}
	
}
