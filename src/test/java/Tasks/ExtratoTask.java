package Tasks;

import org.openqa.selenium.WebDriver;

import Framework.Utils.FileOperation;
import Framework.Utils.NumberFormatter;
import PageObjects.ExtratoPage;
import PageObjects.HomePage;
import Validations.ExtratoValidation;
import Validations.HomeValidation;

public class ExtratoTask {

	private WebDriver driver;
	private ExtratoPage extratoPage;
	private ExtratoValidation extratoValidation;
	private HomePage homePage;
	private HomeValidation homeValidation;
	
	public ExtratoTask(WebDriver driver) {
		this.driver = driver;
		extratoPage = new ExtratoPage(this.driver);
		extratoValidation = new ExtratoValidation(this.driver);
		homePage = new HomePage(this.driver);
		homeValidation = new HomeValidation(this.driver);
	}
	
	public void verificarSaldoInicialEFazerLogout(String idDoUsuario) {
		homeValidation.validateNumeroDaContaSpan(idDoUsuario);
		homePage.getExtratoButton().click();
		extratoValidation.validateSaldoParagraph();
		FileOperation.setProperty("user", idDoUsuario + ".saldo", Double.toString(extratoPage.getSaldo()));
		extratoPage.getSairButton().click();
	}
	
	public void verificarSaldoPosteriorEVoltar(String idDoUsuario) {
		homeValidation.validateNumeroDaContaSpan(idDoUsuario);
		homePage.getExtratoButton().click();
		extratoValidation.validateExtratoAtual(idDoUsuario);
		extratoPage.getVoltarLink().click();
	}
	
	public void verificarTransferenciaEFazerLogout(String idDoUsuario, String valor, String descricao) {
		homeValidation.validateNumeroDaContaSpan(idDoUsuario);
		homePage.getExtratoButton().click();
		extratoValidation.validateValorDaPrimeiraTransacao(NumberFormatter.brazilianToJavaDouble(valor));
		extratoValidation.validateDescricaoDaPrimeiraTransacao(descricao);
		extratoPage.getSairButton().click();
	}
	
}
