package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import models.exceptions.PessoaInvalidaException;

@Entity
@Table(name = "admin")
public class Admin extends Pessoa {

	@Id
	@GeneratedValue
	private long id;

	@NotNull
	private String senha;

	@OneToMany(mappedBy = "evento")
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

}
