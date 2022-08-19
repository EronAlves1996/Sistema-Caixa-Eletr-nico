package sistema;

public class CaixaEletronico {
	
	private Hardware hard;
	private ServicoRemoto serv;
	
	public CaixaEletronico(Hardware hard, ServicoRemoto serv) {
		this.hard = hard;
		this.serv = serv;
	}

	public String logar(String numeroCartao, int senha) {
		int numeroConta = Integer.parseInt(hard.pegarNumeroContaCartao(numeroCartao));
		ContaCorrente conta = serv.recuperarConta(numeroConta, senha);
		if(conta != null) 
			return "Usuário autenticado";
		return "Nada";
	}

}
