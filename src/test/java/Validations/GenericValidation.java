package Validations;

import org.openqa.selenium.WebDriver;
import PageObjects.GenericPage;
import Framework.Browser.Waits;
import org.junit.jupiter.api.Assertions;
import Framework.Report.Report;
import Framework.Report.Screenshot;

import com.aventstack.extentreports.Status;

public class GenericValidation {

	private WebDriver driver;
	private GenericPage genericPage;
	private Waits wait;
	
	public GenericValidation(WebDriver driver) {
		this.driver = driver;
		genericPage = new GenericPage(this.driver);
		wait = new Waits(this.driver);
	}
	
	public void validateModal() {
		try {
			wait.loadElement(genericPage.getModal());
			Assertions.assertTrue(genericPage.getModal().isDisplayed());
			Report.log(Status.PASS, "Foi encontrado o modal esperado.", Screenshot.captureFile(driver));
		} catch (Exception e) {
			Report.log(Status.FAIL, "Não foi encontrado o modal esperado. " + e.getMessage(), Screenshot.captureFile(driver));
		}
	}
	
}
