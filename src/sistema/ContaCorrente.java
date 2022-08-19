package sistema;

public class ContaCorrente {
	
	private int conta;
	private int senha;
	private float saldo;

	public ContaCorrente(int conta, int senha, float saldoInicial) {
		if(conta < 0) throw new CriacaoContaException("Conta não pode ser negativa!");
		else if(senha > 9999 || senha < 1000) throw new CriacaoContaException("Senha inválida!");
		else if(saldoInicial < 0) throw new CriacaoContaException("Saldo inicial inválido!");

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
		if(saldo < valorASerSacado) return false;
		saldo -= valorASerSacado;
		return true;
	}

}
