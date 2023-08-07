package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.Exame;
import models.Patient;
import services.DB;

public class ExameController {
	
	public static boolean insert(Exame ex) {
		
		Connection conexao = DB.connect();
		
		try {
			String sql = "INSERT INTO exames (nome, dia_da_semana) VALUES (?,?)";
			
			PreparedStatement comando = conexao.prepareStatement(sql);
			comando.setString(1, ex.getNome());
			comando.setString(2, ex.getDias_da_semana());

			
			comando.execute();
			
			DB.disconnect(conexao);
			return true;
			
		} catch(SQLException e) {
			System.out.println("Erro ao cadastrar: " + e);
		}
		
		DB.disconnect(conexao);
		return false;
		
	}
	
public static ArrayList<Exame> getAll(){
		
		Connection conex = DB.connect();
		ArrayList<Exame> allEx = new ArrayList<Exame>();
		
		try {
			String sqlText = "SELECT * FROM exames";
			Statement statement = conex.createStatement();
			ResultSet res = statement.executeQuery(sqlText);
			while(res.next()) {
				
				Exame e = new Exame(res.getInt("id"), res.getString("nome"), res.getString("dia_da_semana"));
				allEx.add(e);
				
				
			}
		} catch (Exception e) {
			System.out.println("Erro: " + e);
		}
		
		DB.disconnect(conex);
		return allEx;
		
	}

public static Exame getById(int id) {
	
	ArrayList<Exame> allEx = ExameController.getAll();
	
	for(Exame e : allEx) {
		
		if(e.getId() == id) {
			
			return e;
			
		}
		
	}
	
	return new Exame();
	
	
	
}




}
