package Validations;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.Status;

import Framework.Browser.Waits;
import Framework.Report.Report;
import Framework.Report.Screenshot;
import Framework.Utils.FileOperation;
import PageObjects.ExtratoPage;

public class ExtratoValidation {

	private WebDriver driver;
	private ExtratoPage extratoPage;
	private Waits wait;
	
	public ExtratoValidation(WebDriver driver) {
		this.driver = driver;
		extratoPage = new ExtratoPage(this.driver);
		wait = new Waits(this.driver);
	}
	
	public void validateExtratoAtual(String idDoUsuario) {
		wait.visibilityOfElement(By.id("textBalanceAvailable"));
		try {
			Assertions.assertEquals(
					extratoPage.getBalance(),
					Double.parseDouble(FileOperation.getProperty("user", idDoUsuario + ".saldo")));
			Report.log(Status.PASS, "Saldo esperado para o usuário " + idDoUsuario + ": " + FileOperation.getProperty("user", idDoUsuario + ".saldo") + ".", Screenshot.captureFile(driver));
		} catch (Exception e) {
			Report.log(Status.FAIL, "O saldo do usuário " + idDoUsuario + " não foi verificado. " + e.getMessage(), Screenshot.captureFile(driver));
		}
	}
	
}
