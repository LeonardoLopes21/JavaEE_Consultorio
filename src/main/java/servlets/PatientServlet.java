package servlets;

import java.io.IOException;
import jakarta.servlet.*;
import models.Patient;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.*;

import controller.PatientController;

@WebServlet(urlPatterns= {"/regPatient", "/filter"})
public class PatientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public PatientServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getServletPath();
		switch(action) {
		case "/regPatient": regPatient(request, response);break;
		case "/filter": filter(request, response);break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	protected void filter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Working");
		
		switch(request.getParameter("input-filter")) {
			case "month-birthday": response.sendRedirect("cad_pacientes.jsp?filter=month"); break;
			case "sex-filter": response.sendRedirect("cad_pacientes.jsp?filter=sex&sex=" + request.getParameter("filter_text_input")); break;
			case "week-birthday": response.sendRedirect("cad_pacientes.jsp?filter=week"); break;
			case "birthday": response.sendRedirect("cad_pacientes.jsp?filter=birthday");break;
			case "all": response.sendRedirect("cad_pacientes.jsp");
		}
		
//		return;
	}
	
	protected void regPatient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String name = request.getParameter("inputNome");
		String email = request.getParameter("inputEmail");
		String sexo = request.getParameter("inputSexOther");
		LocalDate data_nasci = LocalDate.parse(request.getParameter("inputNasci"));
		Patient newPatient = new Patient(name, email, sexo, data_nasci);
		
		System.out.println(newPatient.getSexo());
		
		try{
			
			PatientController.insert(newPatient);
			response.sendRedirect("cad_pacientes.jsp");
			
		} catch(Exception e) {
			
			System.out.println("Erro: " + e);
			
		}
		
	}

}
