import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import models.Evento;
import models.exceptions.EventoInvalidoException;

import org.junit.Test;

@Entity
@Table(name = "Evento")
public class EventoTest {
	
	@Test
	public void deveCriarUmEvento() {
		try {
			new Evento("Python na cabeça",
					"Vamos programar em Python!", new Date());
		} catch (EventoInvalidoException _) {
			fail();
		}
	}
	
	@Test
	public void deveDarException() {
		try {
			new Evento(null,
					"Vamos programar em Python!", new Date());
			fail();
		} catch (EventoInvalidoException e) {
			assertEquals("Parametro nulo", e.getMessage());
		}
		try {
			new Evento("Python na cabeça",
					null, new Date());
			fail();
		} catch (EventoInvalidoException e) {
			assertEquals("Parametro nulo", e.getMessage());
		}
		try {
			new Evento("Python na cabeça",
					"Vamos programar em Python!", null);
			fail();
		} catch (EventoInvalidoException e) {
			assertEquals("Parametro nulo", e.getMessage());
		}
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_WEEK, -1);

			new Evento("Python na cabeça",
					"Vamos programar em Python!", calendar.getTime());
			fail();
		} catch (EventoInvalidoException e) {
			assertEquals("Data inválida", e.getMessage());
		}
	}
}
