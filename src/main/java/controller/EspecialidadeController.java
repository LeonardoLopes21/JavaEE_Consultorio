package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.Doctor;
import models.*;
import models.Exame;
import services.DB;

public class EspecialidadeController {
	
public static boolean insert(Especialidade e) {
		
		Connection conexao = DB.connect();
		
		try {
			String sql = "INSERT INTO especialidades (id_medico, id_exame) VALUES (?,?)";
			
			PreparedStatement comando = conexao.prepareStatement(sql);
			comando.setInt(1, e.getId_medico());
			comando.setInt(2, e.getId_exame());

			
			comando.execute();
			
			DB.disconnect(conexao);
			return true;
			
		} catch(SQLException err) {
			System.out.println("Erro ao cadastrar: " + err);
		}
		
		DB.disconnect(conexao);
		return false;
		
	}

public static ArrayList<Especialidade> getAll(){
	
	Connection conex = DB.connect();
	ArrayList<Especialidade> allPatients = new ArrayList<Especialidade>();
	
	try {
		String sqlText = "SELECT * FROM especialidades";
		Statement statement = conex.createStatement();
		ResultSet res = statement.executeQuery(sqlText);
		while(res.next()) {
			
			Especialidade e = new Especialidade(res.getInt("id"), res.getInt("id_medico"), res.getInt("id_exame"));
			allPatients.add(e);
			
			
		}
	} catch (Exception e) {
		System.out.println("Erro: " + e);
	}
	
	DB.disconnect(conex);
	return allPatients;
	
}
	
	
	
}
