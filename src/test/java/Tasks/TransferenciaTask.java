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
import Validations.GenericValidation;

public class TransferenciaTask {

	private WebDriver driver;
	private TransferenciaPage transferenciaPage;
	private TransferenciaValidation transferenciaValidation;
	private HomePage homePage;
	private HomeValidation homeValidation;
	private ExtratoPage extratoPage;
	private ExtratoValidation extratoValidation;
	
	public TransferenciaTask(WebDriver driver) {
		this.driver = driver;
		transferenciaPage = new TransferenciaPage(this.driver);
		transferenciaValidation = new TransferenciaValidation(this.driver);
		homePage = new HomePage(this.driver);
		homeValidation = new HomeValidation(this.driver);
		extratoPage = new ExtratoPage(this.driver);
		extratoValidation = new ExtratoValidation(this.driver);
	}
	
	public void enviar(String idDoUsuarioEnviante, String valor, String idDoUsuarioRecipiente, String descricao) {
		homePage.getTransferenciaButton().click();
		transferenciaValidation.validateTransferirAgoraButton();
		
		preencherDetalhes(idDoUsuarioRecipiente, valor, descricao);
		transferirValor(idDoUsuarioEnviante, valor, idDoUsuarioRecipiente);
		transferenciaValidation.validateTransferenciaRealizadaModal();
		transferenciaPage.getFecharModalButton().click();
		
		transferenciaPage.getVoltarLink().click();
		homeValidation.validateNumeroDaContaSpan(idDoUsuarioEnviante);
	}
	
	public void preencherDetalhes(String idDoUsuarioRecipiente, String valor, String descricao) {
		String[] contaDoUsuarioRecipiente = FileOperation.getProperty("user", idDoUsuarioRecipiente + ".numeroDaConta").split("-");
		transferenciaPage.getNumeroDaContaInput().sendKeys(contaDoUsuarioRecipiente[0]);
		transferenciaPage.getDigitoInput().sendKeys(contaDoUsuarioRecipiente[1]);
		transferenciaPage.getValorDaTransferenciaInput().sendKeys(valor);
		transferenciaPage.getDescricaoInput().sendKeys(descricao);
	}
	
	public void transferirValor(String idDoUsuarioEnviante, String valor, String idDoUsuarioRecipiente) {
		transferenciaPage.getTransferirAgoraButton().click();
		Double valorEmDouble = Double.parseDouble(valor);
		
		Double saldoAtualDoUsuario = Double.parseDouble(FileOperation.getProperty("user", idDoUsuarioEnviante + ".saldo"));
		FileOperation.setProperty("user", idDoUsuarioEnviante + ".saldo", "" + (saldoAtualDoUsuario - valorEmDouble));
		
		saldoAtualDoUsuario = Double.parseDouble(FileOperation.getProperty("user", idDoUsuarioRecipiente + ".saldo"));
		FileOperation.setProperty("user", idDoUsuarioRecipiente + ".saldo", "" + (saldoAtualDoUsuario + valorEmDouble));
		
	}
	
}
