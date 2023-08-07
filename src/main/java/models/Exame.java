package models;

public class Exame {
	
	private int id;
	private String nome;
	private String dias_da_semana;
	
	public Exame() {
		
	}

	public Exame(int id, String nome, String dias_da_semana) {
		this.id = id;
		this.nome = nome;
		this.dias_da_semana = dias_da_semana;
	}

	public Exame(String nome, String dias_da_semana) {
		this.nome = nome;
		this.dias_da_semana = dias_da_semana;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDias_da_semana() {
		return dias_da_semana;
	}

	public void setDias_da_semana(String dias_da_semana) {
		this.dias_da_semana = dias_da_semana;
	}
	
	
	
	

}
