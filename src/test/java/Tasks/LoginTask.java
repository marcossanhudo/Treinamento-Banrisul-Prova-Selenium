package Tasks;

import org.openqa.selenium.WebDriver;
import PageObjects.LoginPage;
import Validations.LoginValidation;
import Validations.HomeValidation;
import Framework.Utils.FileOperation;

public class LoginTask {

	private WebDriver driver;
	private LoginPage loginPage;
	private LoginValidation loginValidation;
	private HomeValidation homeValidation;
	
	public LoginTask(WebDriver driver) {
		this.driver = driver;
		loginPage = new LoginPage(this.driver);
		loginValidation = new LoginValidation(this.driver);
		homeValidation = new HomeValidation(this.driver);
	}
	
	public void fazerLogin(String idDoUsuario) {
		loginValidation.validateAcessarButton();
		
		loginPage.getEmailInput().sendKeys(FileOperation.getProperty("user", idDoUsuario + ".email"));
		loginPage.getSenhaInput().sendKeys(FileOperation.getProperty("user", idDoUsuario + ".senha"));
		loginPage.getAcessarButton().click();
		
		homeValidation.validateNumeroDaContaSpan(idDoUsuario);
	}
	
}
