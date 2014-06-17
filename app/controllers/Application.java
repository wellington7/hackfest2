package controllers;

import models.exceptions.EventoInvalidoException;
import models.exceptions.PessoaInvalidaException;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

public class Application extends Controller {
	
	@Transactional
    public static Result index() throws PessoaInvalidaException, EventoInvalidoException {
		
        return ok(index.render("Your new application is ready."));
    }

}
