package testes;

import sistema.ContaCorrente;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TesteContaCorrente {
	
	@Test
	public void whenGetContaThenRetornaUmInt() {
		ContaCorrente cc = new ContaCorrente(12345, 1234, 1000.0f);
		assertEquals(12345, cc.getConta());
	}
	
	@Test
	public void whenGetSenhaThenRetornaInt() {
		ContaCorrente cc = new ContaCorrente(12345, 4321, 1000.0f);
		assertEquals(4321, cc.getSenha());
	}
	
	@Test
	public void whenGetSaldoThenRetornaFloat() {
		ContaCorrente cc = new ContaCorrente(12345, 4321, 1000.0f);
		assertEquals(1000.0f, cc.getSaldo());
	}
}
