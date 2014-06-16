package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import models.exceptions.PessoaInvalidaException;

@Entity
public class Participante extends Usuario {

	@ManyToOne
	private Evento evento;

	public Participante(String nome, String email)
			throws PessoaInvalidaException {
		super(nome, email);
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}
}
