package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import models.exceptions.PessoaInvalidaException;

import org.hibernate.validator.constraints.Email;

@Entity
@Table(name = "pessoa")
public class Pessoa {
	
	private static final String EMAIL_PATTERN = 
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	@Column(name = "nome")
	@Email
	@NotNull
	private String nome;

	@Column(name = "email")
	@Email
	@NotNull
	private String email;

	public Pessoa(String nome, String email) throws PessoaInvalidaException {
		setNome(nome);
		setEmail(email);
	}

	public String getNome() {
		return nome;
	}

	private void setNome(String nome) throws PessoaInvalidaException {
		if (nome == null)
			throw new PessoaInvalidaException("Parametro nulo");
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	private void setEmail(String email) throws PessoaInvalidaException {
		if (email == null)
			throw new PessoaInvalidaException("Parametro nulo");
		if (!email.matches(EMAIL_PATTERN))
			throw new PessoaInvalidaException("Email inválido");
		this.email = email;
	}

}
