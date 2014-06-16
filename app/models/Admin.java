package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import models.exceptions.PessoaInvalidaException;


@Entity
public class Admin extends Pessoa {

	@NotNull
	private String senha;

	@OneToMany(mappedBy = "administrador")
	private List<Evento> eventos;

	public List<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}

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

}
