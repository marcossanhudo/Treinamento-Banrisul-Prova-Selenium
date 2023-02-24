package Tasks;

import PageObjects.CadastroPage;
import PageObjects.GenericPage;
import PageObjects.LoginPage;
import Validations.CadastroValidation;
import Validations.LoginValidation;
import Framework.Utils.FileOperation;
import org.openqa.selenium.WebDriver;

public class CadastroTask {

	private WebDriver driver;
	private CadastroPage cadastroPage;
	private LoginPage loginPage;
	private CadastroValidation cadastroValidation;
	private LoginValidation loginValidation;
	private GenericPage genericPage;
	
	public CadastroTask(WebDriver driver) {
		this.driver = driver;
		cadastroPage = new CadastroPage(this.driver);
		loginPage = new LoginPage(this.driver);
		cadastroValidation = new CadastroValidation(this.driver);
		loginValidation = new LoginValidation(this.driver);
		genericPage = new GenericPage(this.driver);
	}
	
	public void cadastrarConta(String idDoUsuario) {
		loginValidation.validateAcessarButton();
		loginPage.getRegistrarButton().click();
		
		cadastroValidation.validateEmailInput();
		removerEntradasDoFormulario();
		
		cadastroPage.getEmailInput().sendKeys(FileOperation.getProperty("user", idDoUsuario + ".email"));
		cadastroPage.getNomeInput().sendKeys(FileOperation.getProperty("user", idDoUsuario + ".nome"));
		cadastroPage.getSenhaInput().sendKeys(FileOperation.getProperty("user", idDoUsuario + ".senha"));
		cadastroPage.getConfirmacaoSenhaInput().sendKeys(cadastroPage.getSenhaInput().getAttribute("value"));
		cadastroPage.getCriarContaComSaldoToggle().click();
		cadastroValidation.validateFormularioPreenchido(idDoUsuario);
		
		cadastroValidation.validateCadastrarButton();
		cadastroPage.getCadastrarButton().click();
		
		cadastroValidation.validateCadastroComSucesso();
		FileOperation.setProperty("user", idDoUsuario + ".numeroDaConta", cadastroPage.extrairNumeroDaContaNoModal());
		genericPage.getFecharModalButton().click();
	}
	
	public void removerEntradasDoFormulario() {
		cadastroPage.getEmailInput().clear();
		cadastroPage.getNomeInput().clear();
		cadastroPage.getSenhaInput().clear();
		cadastroPage.getConfirmacaoSenhaInput().clear();
	}
	
}
