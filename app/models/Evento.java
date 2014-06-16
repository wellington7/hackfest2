package models;

import java.util.Date;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

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
	@NotNull
	private Admin administrador;

	@ElementCollection
	@Enumerated(value = EnumType.ORDINAL)
	@NotNull
	private List<Tema> temas;

	public Evento(String titulo, String descricao, Date data,
			Admin administrador, List<Tema> temas)
			throws EventoInvalidoException {
		setTitulo(titulo);
		setDescricao(descricao);
		setData(data);
		setTemas(temas);
		this.administrador = administrador;
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

	private void setTemas(List<Tema> temas) throws EventoInvalidoException {
		if (temas == null)
			throw new EventoInvalidoException("Parametro nulo");
		this.temas = temas;
	}

	public List<Tema> getTemas() {
		return temas;
	}
}
