package TestCases;

import Framework.TestBase;
import Framework.Report.Report;
import Framework.Report.ReportType;
import Framework.Report.Screenshot;
import Tasks.*;
import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.Status;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class RealizarTransferenciaEntreContasTest extends TestBase {
	
	private WebDriver driver = super.getDriverFromDriverManager();
	
	private CadastroTask cadastroTask = new CadastroTask(this.driver);
	private LoginTask loginTask = new LoginTask(this.driver);
	private TransferenciaTask transferenciaTask = new TransferenciaTask(this.driver);
	private ExtratoTask extratoTask = new ExtratoTask(this.driver);
	
	@ParameterizedTest
	@CsvFileSource(resources = "/CSVs/Transferências.csv", numLinesToSkip = 1)
	public void realizarTransferenciaEntreContas(
			String idDoUsuarioEnviante, String valorDaTransferencia, String idDoUsuarioRecipiente, String descricao) {
		try {
		
			Report.createTest("Realizar transferência entre contas.", ReportType.GROUP);
			
			Report.createStep("Criar duas contas.");
			cadastroTask.cadastrarConta(idDoUsuarioEnviante);
			cadastroTask.cadastrarConta(idDoUsuarioRecipiente);
			
			Report.createStep("Verificar saldos iniciais.");
			loginTask.fazerLogin(idDoUsuarioEnviante);
			extratoTask.verificarSaldoInicialEFazerLogout(idDoUsuarioEnviante);
			loginTask.fazerLogin(idDoUsuarioRecipiente);
			extratoTask.verificarSaldoInicialEFazerLogout(idDoUsuarioRecipiente);

			Report.createStep("Transferir dinheiro da primeira conta para a segunda.");
			loginTask.fazerLogin(idDoUsuarioEnviante);
			transferenciaTask.enviar(idDoUsuarioEnviante, valorDaTransferencia, idDoUsuarioRecipiente, descricao);
			extratoTask.verificarSaldoPosterior(idDoUsuarioEnviante);
			extratoTask.verificarTransferenciaEFazerLogout(idDoUsuarioEnviante, valorDaTransferencia, descricao);
			
			Report.createStep("Verificar o recebimento do dinheiro da primeira conta na segunda.");
			loginTask.fazerLogin(idDoUsuarioRecipiente);
			extratoTask.verificarSaldoPosterior(idDoUsuarioRecipiente);
			extratoTask.verificarTransferenciaEFazerLogout(idDoUsuarioRecipiente, valorDaTransferencia, descricao);
			
		} catch (Exception e) {
			Report.log(Status.FAIL, "Teste \"Realizar transferência entre contas.\" falhou. " + e.getMessage(), Screenshot.captureFile(driver));
		}
	}
}
