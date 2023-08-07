package models;

public class Doctor {
	
	private int id;
	
	private String nome;
	
	private String crm;
	
	private String cpf;
	
	

	public Doctor(int id, String nome, String crm, String cpf) {
		this.id = id;
		this.nome = nome;
		this.crm = crm;
		this.cpf = cpf;
	}
	
	public Doctor(String nome, String crm, String cpf) {
		this.nome = nome;
		this.crm = crm;
		this.cpf = cpf;
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

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	
	
}
