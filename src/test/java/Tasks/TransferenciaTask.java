package Tasks;

import PageObjects.TransferenciaPage;
import org.openqa.selenium.WebDriver;
import Framework.Utils.FileOperation;
import Validations.TransferenciaValidation;
import PageObjects.HomePage;
import Validations.HomeValidation;
import PageObjects.ExtratoPage;
import PageObjects.GenericPage;
import Validations.ExtratoValidation;
import Validations.GenericModalValidation;

public class TransferenciaTask {

	private WebDriver driver;
	private TransferenciaPage transferenciaPage;
	private TransferenciaValidation transferenciaValidation;
	private HomePage homePage;
	private HomeValidation homeValidation;
	private GenericModalValidation genericPageModalValidation;
	private ExtratoPage extratoPage;
	private ExtratoValidation extratoValidation;
	
	public TransferenciaTask(WebDriver driver) {
		this.driver = driver;
		transferenciaPage = new TransferenciaPage(this.driver);
		transferenciaValidation = new TransferenciaValidation(this.driver);
		homePage = new HomePage(this.driver);
		homeValidation = new HomeValidation(this.driver);
		genericPageModalValidation = new GenericModalValidation(this.driver);
		extratoPage = new ExtratoPage(this.driver);
		extratoValidation = new ExtratoValidation(this.driver);
	}
	
	public void enviar(String idDoUsuarioEnviante, String valor, String idDoUsuarioRecipiente) {
		homeValidation.validateNumeroDaContaSpan(idDoUsuarioEnviante);
		homePage.getTransferenciaButton().click();
		transferenciaValidation.validateTransferirAgoraButton();
		
		String[] contaDoUsuarioRecipiente = FileOperation.getProperty("user", idDoUsuarioRecipiente + ".numeroDaConta").split("-");
		transferenciaPage.getNumeroDaContaInput().sendKeys(contaDoUsuarioRecipiente[0]);
		transferenciaPage.getDigitoInput().sendKeys(contaDoUsuarioRecipiente[1]);
		transferenciaPage.getValorDaTransferenciaInput().sendKeys(valor);
		transferenciaPage.getDescricaoInput().sendKeys("Teste de transferência");
		
		transferenciaPage.getTransferirAgoraButton().click();
		transferenciaValidation.validateTransferenciaRealizadaModal();
		transferenciaPage.getFecharModalButton().click();
		
		transferenciaPage.getVoltarLink().click();
		homePage.getExtratoButton().click();
		extratoValidation.validateExtratoAtual(idDoUsuarioEnviante);
		extratoPage.getSairButton().click();
	}
	
	public void verificarRecebimento(String idDoUsuarioRecipiente, String valor, String idDoUsuarioEnviante) {
		homeValidation.validateNumeroDaContaSpan(idDoUsuarioRecipiente);
		homePage.getExtratoButton().click();
		extratoValidation.validateExtratoAtual(idDoUsuarioRecipiente);
		extratoPage.getSairButton().click();
	}
	
}
