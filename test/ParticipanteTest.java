import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import models.Participante;
import models.exceptions.PessoaInvalidaException;

import org.junit.Test;

public class ParticipanteTest {

	@Test
	public void deveCriarUmaPessoa() {
		try {
			new Participante("João José da Silva", "joao_jose@mail.com");
		} catch (PessoaInvalidaException e) {
			fail();
		}
	}

	@Test
	public void deveOcorrerException() {
		try {
			new Participante(null, "joao_jose@mail.com");
			fail();
		} catch (PessoaInvalidaException e) {
			assertEquals("Parametro nulo", e.getMessage());
		}
		try {
			new Participante("João José da Silva", null);
			fail();
		} catch (PessoaInvalidaException e) {
			assertEquals("Parametro nulo", e.getMessage());
		}
		try {
			new Participante("João José da Silva", "joao_jose_mail.com");
			fail();
		} catch (PessoaInvalidaException e) {
			assertEquals("Email inválido", e.getMessage());
		}
	}
}
