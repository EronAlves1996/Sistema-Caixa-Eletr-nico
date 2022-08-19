package sistema;

public interface ServicoRemoto {
	ContaCorrente recuperarConta(int conta, int senha);
	void persistirConta(ContaCorrente cc);
}
