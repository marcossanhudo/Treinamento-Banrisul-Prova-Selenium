package Validations;

import PageObjects.CadastroPage;
import PageObjects.GenericPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.aventstack.extentreports.Status;
import org.junit.jupiter.api.Assertions;
import Framework.Browser.Waits;
import Framework.Report.Report;
import Framework.Report.Screenshot;

public class CadastroValidation {

	private WebDriver driver;
	private CadastroPage cadastroPage;
	private Waits wait;
	private GenericPage genericPage;
	
	public CadastroValidation(WebDriver driver) {
		this.driver = driver;
		cadastroPage = new CadastroPage(this.driver);
		wait = new Waits(this.driver);
		genericPage = new GenericPage(this.driver);
	}
	
	public void validateCadastrarButton() {
		WebElement cadastrarButton = wait.loadElement(cadastroPage.getCadastrarButton());
		try {
			Assertions.assertTrue(cadastrarButton.isDisplayed());
			Report.log(Status.PASS, "Foi encontrado o botão para realizar cadastros.", Screenshot.captureFile(driver));
		} catch (Exception e) {
			Report.log(Status.FAIL, "Não foi encontrado o botão de cadastro. " + e.getMessage(), Screenshot.captureFile(driver));
		}
	}
	
	public void validateEmailInput() {
		wait.loadElement(cadastroPage.getEmailInput());
		try {
			Assertions.assertTrue(cadastroPage.getEmailInput().isDisplayed());
			Report.log(Status.PASS, "Foi encontrado, com sucesso, o formulário de cadastro.");
			Report.log(Status.INFO, "Foi encontrado, no campo de cadastro, o campo de entrada de email.", Screenshot.captureFile(driver));
		} catch (Exception e) {
			Report.log(Status.FAIL, "Não foi encontrado o campo de entrada de email para cadastro. " + e.getMessage(), Screenshot.captureFile(driver));
		}
	}
	
	public void validateCadastroComSucesso() {
		wait.loadElement(genericPage.getModal());
		try {
			Assertions.assertNotNull(cadastroPage.extrairNumeroDaContaNoModal());
			Report.log(Status.PASS, "Foi criada, com sucesso, a conta de número " + cadastroPage.extrairNumeroDaContaNoModal() + ".", Screenshot.captureFile(driver));
		} catch (Exception e) {
			Report.log(Status.FAIL, "Não houve sucesso em criar uma nova conta. " + e.getMessage(), Screenshot.captureFile(driver));
		}
	}
	
}
