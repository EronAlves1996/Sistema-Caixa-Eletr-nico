package sistema;

public class ContaCorrente {
	
	private int conta;
	private int senha;
	private float saldo;

	public ContaCorrente(int conta, int senha, float saldoInicial) {
		if(isNegative(conta)) throw new CriacaoContaException("Conta não pode ser negativa!");
		else if(isSenhaInvalid(senha)) throw new CriacaoContaException("Senha inválida!");
		else if(isNegative(saldoInicial)) throw new CriacaoContaException("Saldo inicial inválido!");

		this.conta = conta;
		this.senha = senha;
		this.saldo = saldoInicial;
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

	public boolean sacar(float valorASerSacado) {
		if(saldo < valorASerSacado || isNegative(valorASerSacado)) return false;
		saldo -= valorASerSacado;
		return true;
	}
	
	private boolean isNegative(float value) {
		return value < 0;
	}
	
	private boolean isNegative(int value) {
		return value < 0;
	}
	
	private boolean isSenhaInvalid(int senha) {
		return senha > 9999 || senha < 1000;
	}

}
