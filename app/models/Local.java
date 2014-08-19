package models;

import models.exceptions.EventoInvalidoException;
import models.exceptions.LocalInvalidoException;

public class Local {

	private String nome;
	private int capacidade;
	private String rota;
	
	public Local(){
		
	}
	
	public Local(String nome, int capacidade, String rota) throws LocalInvalidoException{
		setNome(nome);
		setCapacidade(capacidade);
		setRota(rota);
	}
		
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) throws LocalInvalidoException {
		if (nome == null)
			throw new LocalInvalidoException("O parâmetro nome está nulo");
		if (nome.length() > 40)
			throw new LocalInvalidoException("O parâmetro nome está longo");
		this.nome = nome;
	}

	public int getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(int capacidade) throws LocalInvalidoException{
		if (capacidade == 0)
			throw new LocalInvalidoException("O parâmetro capacidade está zerado");
		if (capacidade > 9999999)
			throw new LocalInvalidoException("Capacidade do local super estimada");
		this.capacidade = capacidade;
	}

	public String getRota() {
		return rota;
	}

	public void setRota(String rota) throws LocalInvalidoException{
		if (rota == null)
			throw new LocalInvalidoException("O parâmetro rota está nulo");
		if (rota.length() > 450)
			throw new LocalInvalidoException("O parâmetro rota está muito longo");
		this.rota = rota;
	}
	
}
