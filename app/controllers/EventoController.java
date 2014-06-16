package controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import play.db.jpa.Transactional;
import play.mvc.Result;
import play.mvc.Controller;
import models.Admin;
import models.Evento;
import models.Tema;
import models.dao.GenericDAO;
import models.dao.GenericDAOImpl;
import models.exceptions.EventoInvalidoException;
import models.exceptions.PessoaInvalidaException;
import akka.actor.FSM.Event;

import com.fasterxml.jackson.databind.ObjectMapper;

public class EventoController extends Controller {

	private static GenericDAO dao = new GenericDAOImpl();
	
	@Transactional
	public static Result eventosPorTema(int id) throws PessoaInvalidaException, EventoInvalidoException{
		Admin admin = new Admin("João", "joao@mail.com", "123");
		dao.persist(admin);
		dao.merge(admin);
		dao.flush();
		
		List<Evento> list = new ArrayList<>();
		
		List<Tema> temas = new ArrayList<>();
		
		temas.add(Tema.ANDROID);
		temas.add(Tema.PROGRAMACAO);
		temas.add(Tema.JAVA);
		
		list.add(new Evento("Mobile", "Nenhuma", new Date(), admin, temas));
		
		temas = new ArrayList<>();
		
		temas.add(Tema.ARDUINO);
		temas.add(Tema.ELETRONICA);
		temas.add(Tema.PROGRAMACAO);
		
		list.add(new Evento("Eletrica", "Nenhuma", new Date(), admin, temas));
		
		List<Evento> novo = new ArrayList<>();
		
		for (Evento ev : list) {
			if (ev.getTemas().contains(Tema.values()[(int) id])){
				novo.add(ev);
			}
		}
		
		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		try{
			json = mapper.writeValueAsString(novo);
		}catch (Exception _){
			
		}
		
		return ok(json);
	}
}
