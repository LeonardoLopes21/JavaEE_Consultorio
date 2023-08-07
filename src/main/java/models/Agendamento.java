package models;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Agendamento {
	
	private int id;
	
	private Timestamp datetimer;

	private Exame exame;
	
	private Patient paciente;
	
	public Agendamento(Timestamp datetimer, Exame exame, Patient paciente) {
		
		this.datetimer = datetimer;
		this.exame = exame;
		this.paciente = paciente;
	
		
	}

	public Agendamento(int id, Timestamp datetimer, Exame exame, Patient paciente) {
		this.id = id;
		this.datetimer = datetimer;
		this.exame = exame;
		this.paciente = paciente;
		
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getDatetimer() {
		return datetimer;
	}

	public void setDatetimer(Timestamp datetimer) {
		this.datetimer = datetimer;
	}

	public Exame getExame() {
		return exame;
	}

	public void setExame(Exame exame) {
		this.exame = exame;
	}

	public Patient getPaciente() {
		return paciente;
	}

	public void setPaciente(Patient paciente) {
		this.paciente = paciente;
	}

	
	
	
	
}
