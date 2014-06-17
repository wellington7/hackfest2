import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import models.Evento;
import models.Participante;
import models.Tema;
import models.exceptions.EventoInvalidoException;
import models.exceptions.PessoaInvalidaException;

import org.junit.Before;
import org.junit.Test;

public class ParticipanteTest {

	private Evento evento;
	private List<Tema> temas;
	
	@Before
	public void setUp(){
		temas = new ArrayList<>();
		temas.add(Tema.ANDROID);
		try {
			evento = new Evento("Python na cabeça", "Vamos programar em Python!", new Date(), temas);
		} catch (EventoInvalidoException e) {
			fail();
		}
	}
	
	@Test
	public void deveCriarUmParticipante() {
		try {
			new Participante("João José da Silva", "joao_jose@mail.com", evento);
		} catch (PessoaInvalidaException e) {
			fail();
		}
	}

	@Test
	public void deveOcorrerException() {
		try {
			new Participante(null, "joao_jose@mail.com", evento);
			fail();
		} catch (PessoaInvalidaException e) {
			assertEquals("Parametro nulo", e.getMessage());
		}
		try {
			new Participante("João José da Silva", null, evento);
			fail();
		} catch (PessoaInvalidaException e) {
			assertEquals("Parametro nulo", e.getMessage());
		}
		try {
			new Participante("João José da Silva", null, null);
			fail();
		} catch (PessoaInvalidaException e) {
			assertEquals("Parametro nulo", e.getMessage());
		}
		try {
			new Participante("João José da Silva", "joao_jose_mail.com", evento);
			fail();
		} catch (PessoaInvalidaException e) {
			assertEquals("Email inválido", e.getMessage());
		}
	}
}
