package testes;

import sistema.ContaCorrente;
import sistema.CriacaoContaException;
import sistema.CaixaEletronico;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TesteContaCorrente {
	
	private ContaCorrente cc;
	private CaixaEletronico cx;
	private MockHardware mh;
	private MockServicoRemoto msr;
	
	@BeforeEach
	public void criaNovaContaCorrente() {
		cc = new ContaCorrente(12345, 1234, 1000.0f);
	}
	
	@BeforeEach
	public void criaCaixaEletronico() {
		mh = new MockHardware();
		msr = new MockServicoRemoto();
		cx = new CaixaEletronico(mh, msr);
	}
	
	@Test
	public void whenGetContaThenRetornaUmInt() {
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
	
	@Test
	public void whenSacarValorQueTemNaContaThenRetornaTrue() {
		cc = new ContaCorrente(12345, 1234, 1000.0f);
		boolean result = cc.sacar(500.0f);
		assertTrue(result);
		assertEquals(500.0f, cc.getSaldo());
	}
	
	@Test
	public void whenSacarValorTotalQueTemNaContaThenSaldo0() {
		boolean result = cc.sacar(1000.0f);
		assertTrue(result);
		assertEquals(0.0f, cc.getSaldo());
	}
	
	@Test
	public void whenSacarValorAMaisDoQueTemNaContaThenRetornaFalseENaoFazNada() {
		boolean result = cc.sacar(1200.0f);
		assertFalse(result);
		assertEquals(1000.0f, cc.getSaldo());
	}
	
	@Test
	public void whenSacarValorNegativoThenRetornaFalseENaoFazNada() {
		boolean result = cc.sacar(-1200.0f);
		assertFalse(result);
		assertEquals(1000.0f, cc.getSaldo());
	}
	
	@Test
	public void whenDeposita500ThenRetornaTrue() {
		boolean result = cc.depositar(500.0f);
		assertTrue(result);
		assertEquals(1500.0f, cc.getSaldo());
	}
	
	@Test
	public void whenDeposita0ThenRetornaFalse() {
		boolean result = cc.depositar(0.0f);
		assertFalse(result);
		assertEquals(1000.0f, cc.getSaldo());
	}
	
	@Test
	public void whenDepositaValorNegativoThenRetornaFalseEFazNada() {
		boolean result = cc.depositar(-50.0f);
		assertFalse(result);
		assertEquals(1000.0f, cc.getSaldo());
	}
	
	@Test
	public void whenLogaComNumeroDoCartaoESenhaThenLogaComSucesso() {
		assertEquals("Usuário autenticado", cx.logar("3456 8756 9812 2351", 1234));
	}
	
	
	
}
