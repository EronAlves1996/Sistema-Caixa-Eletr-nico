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
	}
	

}
