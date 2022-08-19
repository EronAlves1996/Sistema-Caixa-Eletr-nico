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


	public String sacar(float f) {
		boolean isOperationSuccedded = contaLogada.sacar(f);
		hard.entregarDinheiro();
		return "Retire seu dinheiro";		
	}

}
