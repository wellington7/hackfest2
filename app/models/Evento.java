package models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import models.exceptions.EventoInvalidoException;
import play.data.validation.Constraints;

@Table(name = "evento")
public class Evento {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private long id;

	@Column(name = "descricao")
	@Constraints.Required
	private String titulo;

	@Column(name = "descricao")
	@Constraints.Required
	private String descricao;

	@Column(name = "descricao")
	@Constraints.Required
	private Date data;

	@OneToMany(mappedBy = "pessoa")
	private List<Pessoa> pessoas;

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

	private long getId() {
		return id;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
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
}
