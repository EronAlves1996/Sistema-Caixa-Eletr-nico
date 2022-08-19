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
}
