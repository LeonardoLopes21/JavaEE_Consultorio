package servlets;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import jakarta.servlet.*;
import models.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.*;

import controller.ExameController;
/**
 * Servlet implementation class ExameServlet
 */
@WebServlet(urlPatterns = {"/insertEx"})
public class ExameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ExameServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getServletPath();
		
		switch(action) {
			case "/insertEx": insertExame(request, response); break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	protected void insertExame(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Working!");
		
		request.setCharacterEncoding("UTF-8");
		
		System.out.println(request.getParameter("weekdays"));
		System.out.println(request.getParameter("inputNomeEx"));
		
		Exame ex = new Exame(request.getParameter("inputNomeEx"), request.getParameter("weekdays"));
				
		try{
			ExameController.insert(ex);
		} catch(Exception e) {
			System.out.println("Erro: " + e);
		}
		
		response.sendRedirect("cad_exames.jsp");
		
	}

}
