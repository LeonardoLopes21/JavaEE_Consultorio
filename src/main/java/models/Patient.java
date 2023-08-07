package models;

import java.time.LocalDate;

public class Patient {
	private int id;
	private String nome;
	private String email;
	private String sexo;
	private LocalDate data_nascimento;
	
	
	
	public Patient() {

	}

	public Patient(int id, String nome, String email, String sexo, LocalDate data_nascimento) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.sexo = sexo;
		this.data_nascimento = data_nascimento;
	}
	
	public Patient(String nome, String email, String sexo, LocalDate data_nascimento) {
		this.nome = nome;
		this.email = email;
		this.sexo = sexo;
		this.data_nascimento = data_nascimento;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public LocalDate getData_nascimento() {
		return data_nascimento;
	}

	public void setData_nascimento(LocalDate data_nascimento) {
		this.data_nascimento = data_nascimento;
	}
	
	
	
	
	
	
	

}
