package servlets;

import java.io.IOException;
import models.*;
import java.util.ArrayList;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.sql.Timestamp;

import controller.AgendamentoController;
import controller.ExameController;
import controller.PatientController;

/**
 * Servlet implementation class AgendamentoServlet
 */
@WebServlet(urlPatterns = {"/regAgendamento", "/filterAg"})
public class AgendamentoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public AgendamentoServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getServletPath();
		
		switch(action) {
		
			case "/regAgendamento": regAgendamento(request, response); break;
			case "/filterAg": filterAg(request, response) ;break;
		
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	protected void regAgendamento(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String dtj = request.getParameter("input_datetime");
		
		Timestamp date_time = Timestamp.valueOf(LocalDateTime.parse(dtj));
		
		int patientId = Integer.parseInt(request.getParameter("selectP"));
		
		int exameId = Integer.parseInt(request.getParameter("selectE"));
		
		ArrayList<Agendamento> all_ag = AgendamentoController.getAll();
		
		Agendamento ag = new Agendamento(date_time, ExameController.getById(exameId), PatientController.getById(patientId));
		
		for(Agendamento a :all_ag) {
			if(a.getExame().getNome().equals(ag.getExame().getNome()) && a.getDatetimer().equals(ag.getDatetimer())) {
				response.sendRedirect("error_message.jsp?msg=errtimeex");
				return;
			}
			
			if((a.getDatetimer().toLocalDateTime().minusMinutes(14).isBefore(ag.getDatetimer().toLocalDateTime()) || a.getDatetimer().toLocalDateTime().minusMinutes(14).isEqual(ag.getDatetimer().toLocalDateTime())) && ag.getDatetimer().after(a.getDatetimer())) {
				response.sendRedirect("error_message.jsp?msg=errtimenotav");
				return;
			}
			
			if(a.getPaciente().getId() == ag.getPaciente().getId() && a.getDatetimer().equals(ag.getDatetimer())) {
				
				response.sendRedirect("error_message.jsp?msg=errtimepac");
				return;
				
			}
			
			
			
		}
		
		if(AgendamentoController.insert(ag)) {
			
			response.sendRedirect("cad_agendamento.jsp");
			
		}
		
		
	}
	
	protected void filterAg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		switch(request.getParameter("input_filter")) {
		
			case "all": response.sendRedirect("cad_agendamento.jsp"); break;
			case "all_done": response.sendRedirect("cad_agendamento.jsp?done=1"); break;
			case "by_date": response.sendRedirect("cad_agendamento.jsp?filterer=date&info_filter=" + request.getParameter("filter_text_input")); break;
			case "by_week": response.sendRedirect("cad_agendamento.jsp?filterer=byweeker");break;
			case "by_patient": response.sendRedirect("cad_agendamento.jsp?filterer=bypatienter&info_filter="+request.getParameter("filter_text_input"));break;
			case "by_patient_open":response.sendRedirect("cad_agendamento.jsp?filterer=bypatienterdoner&info_filter="+request.getParameter("filter_text_input"));break;
			case "by_patient_done": response.sendRedirect("cad_agendamento.jsp?filterer=bypatienterdone&info_filter="+request.getParameter("filter_text_input")); break;
			
		}
		
	}

}
