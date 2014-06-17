package controllers;

import static play.data.Form.form;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import models.Evento;
import models.EventoComparator;
import models.Tema;
import models.dao.GenericDAO;
import models.dao.GenericDAOImpl;
import models.exceptions.EventoInvalidoException;
import models.exceptions.PessoaInvalidaException;

import org.apache.commons.lang.ArrayUtils;

import play.data.Form;
import play.data.validation.ValidationError;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import com.fasterxml.jackson.databind.ObjectMapper;

public class EventoController extends Controller {

	private static GenericDAO dao = new GenericDAOImpl();
	final static Form<Evento> metaForm = form(Evento.class);

	@Transactional
	public static Result eventosPorTema(int id) throws PessoaInvalidaException, EventoInvalidoException{
	
		List<Evento> todosEventos = dao.findAllByClassName("Evento");
		
		List<Evento> eventosRequeridos = new ArrayList<>();
		
		for (Evento ev : todosEventos) {
			if (ev.getTemas().contains(Tema.values()[(int) id])){
				eventosRequeridos.add(ev);
			}
		}
		
		Collections.sort(eventosRequeridos, new EventoComparator());
		
		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		
		try {
			json = mapper.writeValueAsString(eventosRequeridos);
		} catch (Exception _) {
			return badRequest();
		}
		
		return ok(json);
	}
	
	@Transactional
	public static Result novo() throws PessoaInvalidaException, EventoInvalidoException{
		Form<Evento> eventoForm = metaForm.bindFromRequest();

		if (eventoForm.hasErrors()) {

			String errorMsg = "";
			java.util.Map<String, List<play.data.validation.ValidationError>> errorsAll = eventoForm
					.errors();
			for (String field : errorsAll.keySet()) {
				errorMsg += field + " ";
				for (ValidationError error : errorsAll.get(field)) {
					errorMsg += error.message() + ", ";
				}
			}

			System.err.println("Erro no formulário: " + errorMsg);

			return badRequest();
		} else {
			Evento novoEvento = eventoForm.get();
			dao.persist(novoEvento);
			dao.merge(novoEvento);
			dao.flush();
			return redirect(controllers.routes.Application.index());
		}
	}
}
