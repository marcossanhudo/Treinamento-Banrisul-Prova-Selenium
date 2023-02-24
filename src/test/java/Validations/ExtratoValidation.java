package Validations;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.Status;

import Framework.Browser.Waits;
import Framework.Report.Report;
import Framework.Report.Screenshot;
import Framework.Utils.FileOperation;
import Framework.Utils.NumberFormatter;
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
	
	public void validateSaldoParagraph() {
		try {
			wait.visibilityOfElement(By.id("textBalanceAvailable"));
			Assertions.assertTrue(extratoPage.getSaldoParagraph().isDisplayed());
			Report.log(Status.PASS, "A página \"EXTRATO\" foi acessada com sucesso.");
			Report.log(Status.INFO, "Foi encontrado o parágrafo que indica o saldo.", Screenshot.captureFile(driver));
		} catch (Exception e) {
			Report.log(Status.FAIL, "Não foi encontrado o parágrafo que indica o saldo. " + e.getMessage(), Screenshot.captureFile(driver));
		}
	}
	
	public void validateExtratoAtual(String idDoUsuario) {
		try {
			wait.visibilityOfElement(By.id("textBalanceAvailable"));
			Assertions.assertEquals(
					extratoPage.getSaldo(),
					Double.parseDouble(FileOperation.getProperty("user", idDoUsuario + ".saldo")));
			Report.log(Status.PASS, "Foi verificado o saldo esperado para o usuário " + idDoUsuario + ": " + FileOperation.getProperty("user", idDoUsuario + ".saldo") + ".", Screenshot.captureFile(driver));
		} catch (Exception e) {
			Report.log(Status.FAIL, "Não foi verificado o saldo do usuário " + idDoUsuario + ". " + e.getMessage(), Screenshot.captureFile(driver));
		}
	}
	
	public void validateValorDaPrimeiraTransacao(double valor) {
		try {
			String paragraph = wait.loadElement(extratoPage.getValorDaPrimeiraTransacaoParagraph()).getText();
			if (paragraph.substring(0, 1).equals("-"))
				paragraph = paragraph.substring(1);
			Assertions.assertEquals(
					valor,
					NumberFormatter.brazilianToJavaDouble(paragraph));
			Report.log(Status.PASS, "Foi encontrado o valor esperado para a primeira transação: " + extratoPage.getValorDaPrimeiraTransacaoParagraph().getText() + ".", Screenshot.captureFile(driver));
		} catch (Exception e) {
			Report.log(Status.FAIL, "Não foi encontrado o valor esperado para a primeira transação: " + extratoPage.getValorDaPrimeiraTransacaoParagraph().getText() + ". " + e.getMessage(), Screenshot.captureFile(driver));
		}
	}
	
	public void validateDescricaoDaPrimeiraTransacao(String descricao) {
		try {
			wait.loadElement(extratoPage.getDescricaoDaPrimeiraTransacaoParagraph());
			Assertions.assertEquals(
					descricao,
					extratoPage.getDescricaoDaPrimeiraTransacaoParagraph().getText());
			Report.log(Status.PASS, "Foi encontrada a descrição esperada para a primeira transação: \"" + extratoPage.getDescricaoDaPrimeiraTransacaoParagraph().getText() + "\".", Screenshot.captureFile(driver));
		} catch (Exception e) {
			Report.log(Status.FAIL, "Não foi encontrada a descrição esperada para a primeira transação: \"" + extratoPage.getDescricaoDaPrimeiraTransacaoParagraph().getText() + "\". " + e.getMessage(), Screenshot.captureFile(driver));
		}
	}
	
}
