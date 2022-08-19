package sistema;

public class CaixaEletronico {
	
	private Hardware hard;
	private ServicoRemoto serv;
	
	public CaixaEletronico(Hardware hard, ServicoRemoto serv) {
		this.hard = hard;
		this.serv = serv;
	}


	public String logar(String numeroCartao, int senha) {
		try {
			int numeroConta = Integer.parseInt(hard.pegarNumeroContaCartao(numeroCartao));
			ContaCorrente conta = serv.recuperarConta(numeroConta, senha);
		} catch (Exception e) {
			return "Não foi possível autenticar o usuário";
		} 
		
		return "Usuário autenticado";
	}

}
