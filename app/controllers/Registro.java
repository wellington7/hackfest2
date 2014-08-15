package controllers;

import static play.data.Form.form;
import models.Usuario;
import models.dao.GenericDAO;
import models.dao.GenericDAOImpl;
import play.data.DynamicForm;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.registro;

public class Registro extends Controller {
	
	private static GenericDAO dao = new GenericDAOImpl();
	static Form<Usuario> registroForm = form(Usuario.class).bindFromRequest();

	@Transactional
    public static Result show() {
        return ok(registro.render(registroForm));
    }
    
	@Transactional
	public static Result registrar() {
		
		//Pegar a partir daqui Nome Email e Senha
		DynamicForm uForm = Form.form().bindFromRequest();
		
		//Usuario u = registroForm.bindFromRequest().get();
		
		String nome = uForm.get("nome");
		String email = uForm.get("email");
		String senha = uForm.get("pass"); 
		
		Usuario u = new Usuario (email, senha, nome);
		
    	
		/*if (/*registroForm.hasErrors() || validate(u.getEmail())) {
			flash("fail", "Email já está em uso");
            return badRequest(registro.render(registroForm));
        } else {*/
        	dao.persist(u);
            return redirect(
                routes.Login.show()
            );
        //}
    }
	
	private static boolean validate(String email) {
		return true;
	}

}
