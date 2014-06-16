package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import models.exceptions.PessoaInvalidaException;

@Entity
public class Admin extends Usuario {

	@NotNull
	private String senha;

	@OneToMany(mappedBy = "administrador")
	private List<Evento> eventos;

	public Admin(String nome, String email, String senha)
			throws PessoaInvalidaException {
		super(nome, email);
		setSenha(senha);
	}

	private void setSenha(String senha) throws PessoaInvalidaException {
		if (senha == null)
			throw new PessoaInvalidaException("Parametro nulo");
		this.senha = senha;
	}
	
	public List<Evento> getEventos() {
		return eventos;
	}

}
