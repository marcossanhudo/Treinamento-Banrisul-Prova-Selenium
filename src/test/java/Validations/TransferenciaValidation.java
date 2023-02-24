package Validations;

import org.openqa.selenium.WebDriver;
import PageObjects.TransferenciaPage;
import Framework.Browser.Waits;
import PageObjects.GenericPage;
import org.junit.jupiter.api.Assertions;
import Framework.Report.Report;
import com.aventstack.extentreports.Status;
import Framework.Report.Screenshot;
import org.openqa.selenium.By;

public class TransferenciaValidation {

	private WebDriver driver;
	private TransferenciaPage transferenciaPage;
	private Waits wait;
	private GenericPage genericModal;
	
	public TransferenciaValidation(WebDriver driver) {
		this.driver = driver;
		transferenciaPage = new TransferenciaPage(this.driver);
		wait = new Waits(this.driver);
		genericModal = new GenericPage(this.driver);
	}
	
	public void validateTransferirAgoraButton() {
		wait.visibilityOfElement(By.xpath("//button[@type='submit']"));
		try {
			Assertions.assertTrue(transferenciaPage.getTransferirAgoraButton().isDisplayed());
			Report.log(Status.PASS, "A página \"TRANSFERÊNCIA\" foi acessada com sucesso.");
			Report.log(Status.INFO, "Foi encontrado o botão \"Transferir agora\".", Screenshot.captureFile(driver));
		} catch (Exception e) {
			Report.log(Status.FAIL, "Não foi encontrado o botão \"Transferir agora\". " + e.getMessage(), Screenshot.captureFile(driver));
		}
	}
	
	public void validateTransferenciaRealizadaModal() {
		wait.loadElement(genericModal.getModal());
		try {
			Assertions.assertEquals(
					genericModal.getModalTextParagraph().getText(),
					"Transferencia realizada com sucesso");
			Report.log(Status.PASS, "A transferência foi realizada com sucesso", Screenshot.captureFile(driver));
		} catch (Exception e) {
			Report.log(Status.FAIL, "A transferência não foi realizada. " + e.getMessage(), Screenshot.captureFile(driver));
		}
	}
	
}
