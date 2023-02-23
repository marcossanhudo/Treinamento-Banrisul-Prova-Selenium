package TestCases;

import Framework.TestBase;
import Framework.Report.Report;
import Framework.Report.ReportType;
import Framework.Report.Screenshot;
import Tasks.*;
import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.Status;
import org.junit.jupiter.api.Test;

public class RealizarTransferenciaEntreContas extends TestBase {
	
	private WebDriver driver = super.getDriverFromDriverManager();
	
	private CadastroTask cadastroTask = new CadastroTask(this.driver);
	private LoginTask loginTask = new LoginTask(this.driver);
	private TransferenciaTask transferenciaTask = new TransferenciaTask(this.driver);
	
	@Test
	public void realizarTransferenciaEntreContas() throws InterruptedException {
		try {
			Report.createTest("Realizar transferência entre contas.", ReportType.GROUP);
			
			Report.createStep("Criar duas contas.");
			cadastroTask.cadastrarConta("user1");
			cadastroTask.cadastrarConta("user2");
			
			Report.createStep("Transferir dinheiro da primeira conta para a segunda.");
			loginTask.fazerLogin("user1");
			transferenciaTask.enviar("user1", "1000", "user2");
			
			Report.createStep("Verificar o recebimento do dinheiro da primeira conta na segunda.");
			loginTask.fazerLogin("user2");
			transferenciaTask.verificarRecebimento("user2", "1000", "user1");
			
		} catch (Exception e) {
			Report.log(Status.FAIL, "Teste 'Realizar transferência entre contas.' falhou. " + e.getMessage(), Screenshot.captureFile(driver));
		}
	}
}
