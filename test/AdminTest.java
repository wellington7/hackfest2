import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import models.Admin;
import models.exceptions.PessoaInvalidaException;

import org.junit.Test;

public class AdminTest {

	@Test
	public void deveCriarUmAdmin() {
		try {
			new Admin("João José da Silva", "joao_jose@mail.com", "test#123");
		} catch (PessoaInvalidaException e) {
			fail();
		}
	}

	@Test
	public void deveOcorrerException() {
		try {
			new Admin("João José da Silva", "joao_jose@mail.com", null);
			fail();
		} catch (PessoaInvalidaException e) {
			assertEquals("Parametro nulo", e.getMessage());
		}
	}
}
