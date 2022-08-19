package sistema;

public interface Hardware {
	public String pegarNumeroContaCartao(String numeroCartao) throws HardwareException;
	public void entregarDinheiro() throws HardwareException;
	public void lerEnvelope() throws HardwareException;
}
