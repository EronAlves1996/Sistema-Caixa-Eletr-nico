package sistema;

public class CaixaEletronico {
	
	private Hardware hard;
	private ServicoRemoto serv;
	private ContaCorrente contaLogada;
	
	public CaixaEletronico(Hardware hard, ServicoRemoto serv) {
		this.hard = hard;
		this.serv = serv;
	}


	public String logar(String numeroCartao, int senha) {
		try {
			int numeroConta = Integer.parseInt(hard.pegarNumeroContaCartao(numeroCartao));
			contaLogada = serv.recuperarConta(numeroConta, senha);
		} catch (Exception e) {
			contaLogada = null;
			return "Não foi possível autenticar o usuário";
		} 
		
		return "Usuário autenticado";
	}

	public String sacar(float valorASerSacado) {
		if(contaLogada == null) return "Gentileza inserir seu cartão e digitar sua senha";
		boolean isOperationSuccedded = contaLogada.sacar(valorASerSacado);
		if(isOperationSuccedded) {
			try {
				hard.entregarDinheiro();
				serv.persistirConta(contaLogada);
				return "Retire seu dinheiro";
			} catch (HardwareException e) {
				contaLogada.depositar(valorASerSacado);
				return "Não foi possível realizar saque";
			}					
		}
		return "Saldo insuficiente";
	}

}
