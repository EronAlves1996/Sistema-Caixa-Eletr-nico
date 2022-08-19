package sistema;

public class ContaCorrente {
	
	private int conta;
	private int senha;

	public ContaCorrente(int conta, int senha, float saldo) {
		this.conta = conta;
		this.senha = senha;
	}

	public int getConta() {
		return conta;
	}

	public int getSenha() {
		return senha;
	}

}
