package testes;

import java.util.ArrayList;
import java.util.List;

import sistema.ContaCorrente;
import sistema.ServicoRemoto;

public class MockServicoRemoto implements ServicoRemoto {
	
	private List<ContaCorrente> contasRegistradas = new ArrayList<>();
	
	public MockServicoRemoto() {
		contasRegistradas.add(new ContaCorrente(345612, 1234, 1300.0f));
		contasRegistradas.add(new ContaCorrente(956145, 4321, 4000.0f));
		contasRegistradas.add(new ContaCorrente(951153, 9876, 500.0f));
		contasRegistradas.add(new ContaCorrente(262207, 4567, 100.0f));
	}

	@Override
	public ContaCorrente recuperarConta(int conta, int senha) throws InformacaoInvalidaException {
		for(ContaCorrente contaRegistrada : contasRegistradas) {
			if(contaRegistrada.getConta() == conta && contaRegistrada.getSenha() == senha) {
				ContaCorrente ccInformacoes = new ContaCorrente(contaRegistrada.getConta(), contaRegistrada.getSenha(), contaRegistrada.getSaldo());
				return ccInformacoes;
			}
		}
		throw new InformacaoInvalidaException("Dados incorretos!");
	}

	@Override
	public void persistirConta(ContaCorrente cc) {
		contasRegistradas.set(contasRegistradas.indexOf(cc), cc);
	}
	
	public ContaCorrente verifySpecificAccount(int index) {
		return contasRegistradas.get(index);
	}

}
