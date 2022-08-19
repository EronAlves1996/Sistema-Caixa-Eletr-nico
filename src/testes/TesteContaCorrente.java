package testes;

import sistema.ContaCorrente;
import sistema.CriacaoContaException;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TesteContaCorrente {
	
	private ContaCorrente cc;
	
	@Test
	public void whenGetContaThenRetornaUmInt() {
		cc = new ContaCorrente(12345, 1234, 1000.0f);
		assertEquals(12345, cc.getConta());
	}
	
	@Test
	public void whenGetSenhaThenRetornaInt() {
		cc = new ContaCorrente(12345, 4321, 1000.0f);
		assertEquals(4321, cc.getSenha());
	}
	
	@Test
	public void whenGetSaldoThenRetornaFloat() {
		cc = new ContaCorrente(12345, 4321, 1000.0f);
		assertEquals(1000.0f, cc.getSaldo());
	}
	
	@Test
	public void whenNumeroContaCorrenteEInvalidoThenJogaErro() {
		assertThrows(CriacaoContaException.class, ()->{
			new ContaCorrente(-1234, 4321, 1000.0f);
		});
	}
	
	@Test
	public void whenSenhaTemMaisDeQuatroDigitosThenJogaErro() {
		assertThrows(CriacaoContaException.class, ()->{
			new ContaCorrente(25467, 43212, 1000.0f);
		});
	}
	
	@Test
	public void whenSenhaENumeroNegativoThenJogaErro() {
		assertThrows(CriacaoContaException.class, ()->{
			new ContaCorrente(25467, -2, 1000.0f);
		});
	}
	
	@Test
	public void whenSenhaTemMenosDeQuatroDigitosThenJogaErro() {
		assertThrows(CriacaoContaException.class, ()->{
			new ContaCorrente(25467, 999, 1000.0f);
		});
	}
	
	@Test
	public void whenSaldoInicialNegativoThenJogaErro() {
		assertThrows(CriacaoContaException.class, ()->{
			new ContaCorrente(25467, 9874, -1.0f);
		});
	}
}
