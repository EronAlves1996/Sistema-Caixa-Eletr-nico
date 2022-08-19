package sistema;

public class ContaCorrente {
	
	private int conta;
	private int senha;
	private float saldo;

	public ContaCorrente(int conta, int senha, float saldo) {
		this.conta = conta;
		this.senha = senha;
		this.saldo = saldo;
	}

	public int getConta() {
		return conta;
	}

	public int getSenha() {
		return senha;
	}

	public float getSaldo() {
		return saldo;
	}

}
