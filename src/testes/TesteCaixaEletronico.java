package testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sistema.CaixaEletronico;

class TesteCaixaEletronico {
	
	private CaixaEletronico cx;
	private MockHardware mh;
	private MockServicoRemoto msr;
	
	@BeforeEach
	public void criaCaixaEletronico() {
		mh = new MockHardware();
		msr = new MockServicoRemoto();
		cx = new CaixaEletronico(mh, msr);
	}

	@Test
	public void whenLogaComNumeroDoCartaoESenhaThenLogaComSucesso() {
		assertEquals("Usuário autenticado", cx.logar("3456 8756 9812 2351", 1234));
	}
	
	@Test
	public void whenLogaComSenhaIncorretaThenLogaSemSucesso() {
		assertEquals("Não foi possível autenticar o usuário", cx.logar("3456 8756 9812 2351", 1235));
	}
	
	@Test
	public void whenLogaComCartaoIncorretoThenLogaSemSucesso() {
		assertEquals("Não foi possível autenticar o usuário", cx.logar("3456 8756 9812 2352", 1234));
	}
	
	@Test
	public void whenLogaComDadosCorretosThenPoremAMaquinaEstaAvariada() {
		mh.corromper();
		assertEquals("Não foi possível autenticar o usuário", cx.logar("3456 8756 9812 2351", 1234));
	}
	
	@Test
	public void whenLogaESacaOValorNaContaThenSacaComSucesso() {
		cx.logar("3456 8756 9812 2351", 1234);
		assertEquals("Retire seu dinheiro", cx.sacar(200.0f));
		assertEquals(1100.0f, msr.verifySpecificAccount(0).getSaldo());
	}
	
	@Test
	public void whenTentaSacarSemTerLogado() {
		assertEquals("Gentileza inserir seu cartão e digitar sua senha", cx.sacar(200.0f));
	}
	
	@Test
	public void whenTentaSacarUmValorMaiorDoQueOQueHaNaConta() {
		cx.logar("5457 8770 9157 6445", 4321);
		assertEquals("Saldo insuficiente", cx.sacar(4100.0f));
		assertEquals(4000.0f, msr.verifySpecificAccount(1).getSaldo());
	}
	
	@Test
	public void whenTentaSacarPoremAMaquinaEstaAvariada() {
		cx.logar("5457 8770 9157 6445", 4321);
		mh.corromper();
		assertEquals("Não foi possível realizar saque", cx.sacar(100.0f));
		assertEquals(4000.0f, msr.verifySpecificAccount(1).getSaldo());
	}
	

	@Test
	public void whenTentaDepositarSemTerLogado() {
		assertEquals("Gentileza inserir seu cartão e digitar sua senha", cx.depositar(200.0f));
	}
	
	 @Test
	public void whenLogaEDepositaOValorNaContaThenDepositaComSucesso() {
		cx.logar("3456 8756 9812 2351", 1234);
		assertEquals("Depósito recebido com sucesso", cx.depositar(200.0f));
		assertEquals(1500.0f, msr.verifySpecificAccount(0).getSaldo());
	}
		
	
	@Test
	public void whenTentaDepositarPoremAMaquinaEstaAvariada() {
		cx.logar("5457 8770 9157 6445", 4321);
		mh.corromper();
		assertEquals("Não foi possível realizar o depósito", cx.depositar(100.0f));
		assertEquals(4000.0f, msr.verifySpecificAccount(1).getSaldo());
	}
	

}
