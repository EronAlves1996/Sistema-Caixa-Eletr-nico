package testes;

import java.util.HashMap;
import java.util.Map;

import sistema.Hardware;
import sistema.HardwareException;

public class MockHardware implements Hardware {
	
	private Map<String, String> relacaoCartoesContas = new HashMap<>();
	private boolean isCorrupted = false;

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
