package Validations;

import org.openqa.selenium.WebDriver;
import PageObjects.GenericPage;
import Framework.Browser.Waits;
import org.junit.jupiter.api.Assertions;
import Framework.Report.Report;
import Framework.Report.Screenshot;

import com.aventstack.extentreports.Status;

public class GenericModalValidation {

	private WebDriver driver;
	private GenericPage genericModal;
	private Waits wait;
	
	public GenericModalValidation(WebDriver driver) {
		this.driver = driver;
		genericModal = new GenericPage(this.driver);
		wait = new Waits(this.driver);
	}
	
	public void validateModal() {
		wait.loadElement(genericModal.getModal());
		try {
			Assertions.assertTrue(genericModal.getModal().isDisplayed());
			Report.log(Status.PASS, "Foi encontrado o modal esperado.", Screenshot.captureFile(driver));
		} catch (Exception e) {
			Report.log(Status.FAIL, "Não foi encontrado o modal esperado. " + e.getMessage(), Screenshot.captureFile(driver));
		}
	}
	
}
