package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import models.Patient;
import services.DB;



public class PatientController {
	
	public static ArrayList<Patient> getIfBirthdayMonth() {
		
		ArrayList<Patient> allPatients = PatientController.getAll();
		
		ArrayList<Patient> patientsBirthdayMonth = new ArrayList<Patient>();
		
		for(Patient p : allPatients) {
			if(p.getData_nascimento().getMonthValue() == LocalDate.now().getMonthValue()) {
				patientsBirthdayMonth.add(p);
			}
		}
		
		return patientsBirthdayMonth;
		
	}
	
public static ArrayList<Patient> getIfGivenSex(String sex) {
		
		ArrayList<Patient> allPatients = PatientController.getAll();
		
		ArrayList<Patient> patientsBySex = new ArrayList<Patient>();
		
		for(Patient p : allPatients) {
			if(p.getSexo().toUpperCase().equals(sex.toUpperCase())) {
				patientsBySex.add(p);
			}
		}
		
		return patientsBySex;
		
	}

public static ArrayList<Patient> getIfBirthdayWeek() {
	
	ArrayList<Patient> allPatients = PatientController.getAll();
	
	ArrayList<Patient> patientsByWeek = new ArrayList<Patient>();
	
	Calendar calendar = Calendar.getInstance();
	
	int week = calendar.get(Calendar.WEEK_OF_YEAR);
	
	int year = calendar.get(Calendar.YEAR);
	
	for(Patient p : allPatients) {
		
		Calendar target = Calendar.getInstance();
		
		target.setTime(Date.valueOf(p.getData_nascimento()));
		
		int targetWeek = target.get(Calendar.WEEK_OF_YEAR);
		
		if(targetWeek == week) {
			
			patientsByWeek.add(p);
			
		}
		
	}
	
	return patientsByWeek;
	
}

public static ArrayList<Patient> getIfBirthday() {
	
	ArrayList<Patient> allPatients = PatientController.getAll();
	
	ArrayList<Patient> patientsBirthday = new ArrayList<Patient>();
	
	for(Patient p : allPatients) {
		
		if(p.getData_nascimento().getMonth() == LocalDate.now().getMonth() && p.getData_nascimento().getDayOfMonth() == LocalDate.now().getDayOfMonth()) {
			
			patientsBirthday.add(p);
			
		}
		
	}
	
	return patientsBirthday;
	
}


	
	public static boolean insert(Patient p) {
		Connection conexao = DB.connect();
		
		try {
			String sql = "INSERT INTO paciente (nome, email, sexo, data_de_nascimento) VALUES (?,?,?,?)";
			
			PreparedStatement comando = conexao.prepareStatement(sql);
			comando.setString(1, p.getNome());
			comando.setString(2, p.getEmail());
			comando.setString(3, p.getSexo());
			comando.setDate(4, java.sql.Date.valueOf(p.getData_nascimento()));
			
			comando.execute();
			
			DB.disconnect(conexao);
			return true;
			
		} catch(SQLException e) {
			System.out.println("Erro ao cadastrar: " + e);
		}
		
		DB.disconnect(conexao);
		return false;
	}
	
	public static ArrayList<Patient> getAll(){
		
		Connection conex = DB.connect();
		ArrayList<Patient> allPatients = new ArrayList<Patient>();
		
		try {
			String sqlText = "SELECT * FROM paciente";
			Statement statement = conex.createStatement();
			ResultSet res = statement.executeQuery(sqlText);
			while(res.next()) {
				
				Patient p = new Patient(res.getInt("id"), res.getString("nome"), res.getString("email"), res.getString("sexo") ,res.getDate("data_de_nascimento").toLocalDate());
				allPatients.add(p);
				
				
			}
		} catch (Exception e) {
			System.out.println("Erro: " + e);
		}
		
		DB.disconnect(conex);
		return allPatients;
		
	}
	
	public static Patient getById(int id){
		
		ArrayList<Patient> allPats = PatientController.getAll();
		
		for(Patient p : allPats) {
			if(p.getId() == id) {
				return p;
			}
		}
		
		return new Patient();
		
	}
	

}
