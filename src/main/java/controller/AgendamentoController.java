package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Timestamp;
import java.time.*;
import models.Agendamento;
import models.Doctor;
import services.DB;

public class AgendamentoController {
	
public static boolean insert(Agendamento ag) {
		
		Connection conexao = DB.connect();
		
		try {
			String sql = "INSERT INTO agendamento_de_exame (dia_hora, id_exame, id_paciente) VALUES (?,?,?)";
			
			PreparedStatement comando = conexao.prepareStatement(sql);
			comando.setTimestamp(1, ag.getDatetimer());
			comando.setInt(2, ag.getExame().getId());
			comando.setInt(3, ag.getPaciente().getId());

			
			comando.execute();
			
			DB.disconnect(conexao);
			return true;
			
		} catch(SQLException e) {
			System.out.println("Erro ao cadastrar: " + e);
		}
		
		DB.disconnect(conexao);
		return false;
		
	}


public static ArrayList<Agendamento> getAll(){
	
	Connection conex = DB.connect();
	ArrayList<Agendamento> allPatients = new ArrayList<Agendamento>();
	
	try {
		String sqlText = "SELECT * FROM agendamento_de_exame";
		Statement statement = conex.createStatement();
		ResultSet res = statement.executeQuery(sqlText);
		while(res.next()) {
			
			Agendamento ag = new Agendamento(res.getInt("id"), res.getTimestamp("dia_hora"), ExameController.getById(res.getInt("id_exame")), PatientController.getById(res.getInt("id_paciente")));
			allPatients.add(ag);
			
			
		}
	} catch (Exception e) {
		System.out.println("Erro: " + e);
	}
	
	DB.disconnect(conex);
	return allPatients;
	
}

public static ArrayList<Agendamento> getAllIfDone(){
	
	ArrayList<Agendamento> all_ag = AgendamentoController.getAll();
	
	ArrayList<Agendamento> all_ag_done = new ArrayList<Agendamento>();

	for(Agendamento ag : all_ag) {
		
		if(LocalDateTime.now().isAfter(ag.getDatetimer().toLocalDateTime())) {
			
			all_ag_done.add(ag);
			
		}
		
	}
	
	return all_ag_done;
	
}

public static ArrayList<Agendamento> getAllIfNotDone(){
	
	ArrayList<Agendamento> all_ag = AgendamentoController.getAll();
	
	ArrayList<Agendamento> all_ag_done = new ArrayList<Agendamento>();

	for(Agendamento ag : all_ag) {
		
		if(!LocalDateTime.now().isAfter(ag.getDatetimer().toLocalDateTime())) {
			
			all_ag_done.add(ag);
			
		}
		
	}
	
	return all_ag_done;
	
}

public static ArrayList<Agendamento> getPatientIfDone(String patient){
	
	ArrayList<Agendamento> all_ag = AgendamentoController.getAll();
	
	ArrayList<Agendamento> all_ag_done = new ArrayList<Agendamento>();

	for(Agendamento ag : all_ag) {
		
		if(LocalDateTime.now().isAfter(ag.getDatetimer().toLocalDateTime()) && ag.getPaciente().getNome().equals(patient)) {
			
			all_ag_done.add(ag);
			
		}
		
	}
	
	return all_ag_done;
	
}

public static ArrayList<Agendamento> getPatientIfNotDone(String nomeP){
	
	ArrayList<Agendamento> all_ag = AgendamentoController.getAll();
	
	ArrayList<Agendamento> all_ag_done = new ArrayList<Agendamento>();

	for(Agendamento ag : all_ag) {
		
		if(!LocalDateTime.now().isAfter(ag.getDatetimer().toLocalDateTime()) && ag.getPaciente().getNome().equals(nomeP)) {
			
			all_ag_done.add(ag);
			
		}
		
	}
	
	return all_ag_done;
	
}

public static ArrayList<Agendamento> getIfDateMatch(String date){
	
	ArrayList<Agendamento> all_ag = AgendamentoController.getAll();
	
	ArrayList<Agendamento> all_ag_done = new ArrayList<Agendamento>();

	for(Agendamento ag : all_ag) {
		
		if(ag.getDatetimer().toLocalDateTime().toLocalDate().equals(LocalDate.parse(date))) {
			
			all_ag_done.add(ag);
			
		}
		
	}
	
	return all_ag_done;
	
}

public static ArrayList<Agendamento> getIfPatient(String patient){
	
	ArrayList<Agendamento> all_ag = AgendamentoController.getAll();
	
	ArrayList<Agendamento> all_ag_done = new ArrayList<Agendamento>();

	for(Agendamento ag : all_ag) {
		
		if(ag.getPaciente().getNome().equals(patient)) {
			
			all_ag_done.add(ag);
			
		}
		
	}
	
	return all_ag_done;
	
}

public static ArrayList<Agendamento> getAllIfThisWeek(){
	
	ArrayList<Agendamento> all_ag = AgendamentoController.getAll();
	
	ArrayList<Agendamento> all_ag_done = new ArrayList<Agendamento>();

	Calendar calendar = Calendar.getInstance();
	
	int week = calendar.get(Calendar.WEEK_OF_YEAR);
	
	int year = calendar.get(Calendar.YEAR);
	
	
	for(Agendamento ag : all_ag) {
		
		Calendar target = Calendar.getInstance();
		
		target.setTime(Date.valueOf(ag.getDatetimer().toLocalDateTime().toLocalDate()));
		
		int targetWeek = target.get(Calendar.WEEK_OF_YEAR);
		
		if(targetWeek == week) {
			
			all_ag_done.add(ag);
			
			
		}
		
	}
	
	return all_ag_done;
	
}

//by_week
	

}
