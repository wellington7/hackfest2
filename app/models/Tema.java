package models;

public enum Tema {

	ARDUINO(0, "Arduino"), ANDROID(1, "Android"), PROGRAMACAO(2, "Programação"),
	MARATONA(3, "Maratona"), DESAFIOS(4, "Desafios"), JAVA(5, "JAVA"),
	PYTHON(6, "Python"), ELETRONICA(7, "Eletrônica"), PROJETOS(8, "Projetos"),
	WEB(9, "Web"), ASPNET(10, "Aspx.net"), JOGOS(11, "Jogos");

	private final Integer tipo;

	private final String descricao;

	private Tema(Integer tipo, String descricao) {
		this.tipo = tipo;
		this.descricao = descricao;
	}

	public Integer getTipo() {
		return tipo;
	}

	public String getDescricao() {
		return descricao;
	}

	@Override
	public String toString() {
		return descricao;
	}
}
