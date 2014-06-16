package models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import models.exceptions.EventoInvalidoException;
import play.data.validation.Constraints;

@Entity
public class Evento {

	@Id
	@GeneratedValue
	private long id;

	@Constraints.Required
	private String titulo;

	@Constraints.Required
	private String descricao;

	@Temporal(value = TemporalType.DATE)
	@Constraints.Required
	private Date data;

	@OneToMany(mappedBy = "evento")
	private List<Participante> participantes;

	@ManyToOne
	private Admin administrador;

	public Evento(String titulo, String descricao, Date data)
			throws EventoInvalidoException {
		setTitulo(titulo);
		setDescricao(descricao);
		setData(data);
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public Date getData() {
		return data;
	}

	public long getId() {
		return id;
	}

	private void setTitulo(String titulo) throws EventoInvalidoException {
		if (titulo == null)
			throw new EventoInvalidoException("Parametro nulo");
		this.titulo = titulo;
	}

	private void setDescricao(String descricao) throws EventoInvalidoException {
		if (descricao == null)
			throw new EventoInvalidoException("Parametro nulo");
		this.descricao = descricao;
	}

	private void setData(Date data) throws EventoInvalidoException {
		if (data == null)
			throw new EventoInvalidoException("Parametro nulo");
		if (data.compareTo(new Date()) < 0)
			throw new EventoInvalidoException("Data inválida");
		this.data = data;
	}

	public List<Participante> getParticipantes() {
		return participantes;
	}
	
	public Admin getAdmin() {
		return administrador;
	}
}
