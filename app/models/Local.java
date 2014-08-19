package models;

public class Local {

	private String nome;
	private int capacidade;
	private String rota;
	
	public Local(String nome, int capacidade, String rota){
		this.nome = nome;
		this.capacidade = capacidade;
		this.rota = rota;
	}
		
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}

	public String getRota() {
		return rota;
	}

	public void setRota(String rota) {
		this.rota = rota;
	}
	
}
