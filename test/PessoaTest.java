import static org.junit.Assert.*;
import models.Pessoa;
import models.exceptions.PessoaInvalidaException;

import org.junit.Test;

public class PessoaTest {

	@Test
	public void deveCriarUmaPessoa() {
		try {
			new Pessoa("João José da Silva", "joao_jose@mail.com");
		} catch (PessoaInvalidaException e) {
			fail();
		}
	}

	@Test
	public void deveOcorrerException() {
		try {
			new Pessoa(null, "joao_jose@mail.com");
			fail();
		} catch (PessoaInvalidaException e) {
			assertEquals("Parametro nulo", e.getMessage());
		}
		try {
			new Pessoa("João José da Silva", null);
			fail();
		} catch (PessoaInvalidaException e) {
			assertEquals("Parametro nulo", e.getMessage());
		}
		try {
			new Pessoa("João José da Silva", "joao_jose_mail.com");
			fail();
		} catch (PessoaInvalidaException e) {
			assertEquals("Email inválido", e.getMessage());
		}
	}
}
