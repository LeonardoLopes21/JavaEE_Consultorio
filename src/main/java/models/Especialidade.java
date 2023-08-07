package models;

public class Especialidade {
	private int id;
	private int id_medico;
	private int id_exame;
	
	public Especialidade(int id, int id_medico, int id_exame) {
		this.id = id;
		this.id_medico = id_medico;
		this.id_exame = id_exame;
	}
	
	public Especialidade(int id_medico, int id_exame) {
		this.id_medico = id_medico;
		this.id_exame = id_exame;
	}
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId_medico() {
		return id_medico;
	}
	public void setId_medico(int id_medico) {
		this.id_medico = id_medico;
	}
	public int getId_exame() {
		return id_exame;
	}
	public void setId_exame(int id_exame) {
		this.id_exame = id_exame;
	}
	
	
}
