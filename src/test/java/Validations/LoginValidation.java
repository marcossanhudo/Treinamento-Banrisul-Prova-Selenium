package Validations;

import Framework.Browser.Waits;
import Framework.Report.Report;
import Framework.Report.Screenshot;
import PageObjects.LoginPage;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.Status;

public class LoginValidation {

	private WebDriver driver;
	private LoginPage loginPage;
	private Waits wait;
	
	public LoginValidation(WebDriver driver) {
		this.driver = driver;
		loginPage = new LoginPage(this.driver);
		wait = new Waits(this.driver);
	}
	
	public void validateAcessarButton() {
		try {
			wait.loadElement(loginPage.getAcessarButton());
			Assertions.assertTrue(loginPage.getAcessarButton().isDisplayed());
		} catch (Exception e) {
			Report.log(Status.FAIL, "Não foi possível encontrar o botão 'Acessar'. " + e.getMessage(), Screenshot.captureFile(driver));
		}
	}
	
}
