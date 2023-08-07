package servlets;

import java.io.IOException;
import jakarta.servlet.*;
import models.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.*;
import java.util.concurrent.TimeUnit;

import controller.DoctorController;
import controller.EspecialidadeController;

/**
 * Servlet implementation class MedicoServlet
 */
@WebServlet(urlPatterns = {"/insert_medico, /filter_docs", "/filterDoc"})
public class MedicoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public MedicoServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getServletPath();
		switch(action) {
			case "/insert_medico": insert_doc(request, response); break;
			case "/filterDoc": filter_docs(request,response);break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	protected void insert_doc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println(DoctorController.getNextID());
		
		System.out.println(request.getParameter("hiddeninput").split("|"));
				
		String[] a = request.getParameter("hiddeninput").split("|");
		
		int newDoctorId = DoctorController.getNextID();
		
		if(DoctorController.getAll().isEmpty()) {
			
			newDoctorId = 1;
			
		}
		
		Doctor d = new Doctor(request.getParameter("input_nome"), request.getParameter("input_crm"), request.getParameter("input_cpf"));
		
		if(DoctorController.insert(d)) {
			
			
			for(int i = 0; i < a.length; i++) {
				if(!a[i].equals("|")) {
					Especialidade e = new Especialidade(newDoctorId, Integer.parseInt(a[i]));
					EspecialidadeController.insert(e);
				}
			}
			
		}
		
		
		
	}
	
	protected void filter_docs(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String filterer =  request.getParameter("select_filter");
		
		response.sendRedirect("cad_medico.jsp?filterer="+filterer);
		
		
	}

	
}
