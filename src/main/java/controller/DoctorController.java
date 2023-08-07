package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.*;
import services.DB;

public class DoctorController {

public static boolean insert(Doctor doc) {
		
		Connection conexao = DB.connect();
		
		try {
			String sql = "INSERT INTO medico (nome, crm, cpf) VALUES (?,?,?)";
			
			PreparedStatement comando = conexao.prepareStatement(sql);
			comando.setString(1, doc.getNome());
			comando.setString(2, doc.getCrm());
			comando.setString(3, doc.getCpf());

			
			comando.execute();
			
			DB.disconnect(conexao);
			return true;
			
		} catch(SQLException e) {
			System.out.println("Erro ao cadastrar: " + e);
		}
		
		DB.disconnect(conexao);
		return false;
		
	}

	public static ArrayList<Doctor> getAll(){
		
		Connection conex = DB.connect();
		ArrayList<Doctor> allPatients = new ArrayList<Doctor>();
		
		try {
			String sqlText = "SELECT * FROM medico";
			Statement statement = conex.createStatement();
			ResultSet res = statement.executeQuery(sqlText);
			while(res.next()) {
				
				Doctor d = new Doctor(res.getInt("id"), res.getString("nome"), res.getString("crm"), res.getString("cpf"));
				allPatients.add(d);
				
				
			}
		} catch (Exception e) {
			System.out.println("Erro: " + e);
		}
		
		DB.disconnect(conex);
		return allPatients;
		
	}
	
	public static Doctor getById(int id) {
		
		ArrayList<Doctor> allDocs = DoctorController.getAll();
		
		for(Doctor d : allDocs) {
			if(d.getId() == id) {
				return d;
			}
		}
		
		return null;
		
	}


	public static int getLastID() {
		
		ArrayList<Doctor> allDocs = DoctorController.getAll();
		
		
		if(allDocs.isEmpty()) {
			return 1;
		}
		
		return allDocs.size() + 1;
		
		
	}
	
public static int getNextID() {
		
		ArrayList<Doctor> allDocs = DoctorController.getAll();
		
		
		if(allDocs.isEmpty()) {
			return 1;
		}
		
		return allDocs.size() + 1;
		
		
	}

public static ArrayList<Doctor> getAllByExame(int exame_id){
	
	ArrayList<Especialidade> allSpecs = EspecialidadeController.getAll();
	
	ArrayList<Doctor> matching_Docs = new ArrayList<Doctor>();
	
	for(Especialidade s : allSpecs) {
		
		if(s.getId_exame() == exame_id) {
			matching_Docs.add(DoctorController.getById(s.getId_medico()));
		}
		
	}
	
	return matching_Docs;
	
}
	
}
