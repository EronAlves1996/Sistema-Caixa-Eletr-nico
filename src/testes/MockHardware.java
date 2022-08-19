package testes;

import java.util.HashMap;
import java.util.Map;

import sistema.Hardware;
import sistema.HardwareException;

public class MockHardware implements Hardware {
	
	private Map<String, String> relacaoCartoesContas = new HashMap<>();
	private boolean isCorrupted = false;
	
	public MockHardware() {
		relacaoCartoesContas.put("3456 8756 9812 2351", "345612");
		relacaoCartoesContas.put("5457 8770 9157 6445", "956145");
		relacaoCartoesContas.put("5257 3920 3709 5395", "951153");
		relacaoCartoesContas.put("5453 5311 3171 5000", "262207");
	} 

	@Override
	public String pegarNumeroContaCartao(String numeroCartao) throws HardwareException, CartaoInvalidoException {
		if(isCorrupted) throw new HardwareException("Erro de leitura!");
		if(relacaoCartoesContas.containsKey(numeroCartao)) return relacaoCartoesContas.get(numeroCartao);
		throw new CartaoInvalidoException("Cartão inválido!");
	}

	@Override
	public void entregarDinheiro() throws HardwareException {
		if(isCorrupted) throw new HardwareException("Não foi possível entregar o dinheiro");
	}

	@Override
	public void lerEnvelope() throws HardwareException {
		if(isCorrupted) throw new HardwareException("Não foi possível pegar o envelope");
	}
	
	public void corromper() {
		isCorrupted = true;
	}

}
