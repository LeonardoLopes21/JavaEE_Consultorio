<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@ page import="services.*" %>
<%@ page import="models.*" %>
<%@ page import="controller.*" %>
<%@ page import="java.time.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<%

String filterer = request.getParameter("filterer");

String isDone = request.getParameter("done");

String input_data = request.getParameter("info_filter") ;

String patientsText = "";

String examesText = "";

ArrayList<Patient> allPatients = PatientController.getAll();

ArrayList<Exame> allExames = ExameController.getAll();

ArrayList<Agendamento> allAgendamentos = AgendamentoController.getAll();

String tableText = "";

if(isDone != null){
	
	allAgendamentos = AgendamentoController.getAllIfDone();
	
}

if(filterer != null){
	switch(filterer){
		case "date": allAgendamentos = AgendamentoController.getIfDateMatch(input_data); break;
		case "byweeker": allAgendamentos = AgendamentoController.getAllIfThisWeek();break;
		case "bypatienter": allAgendamentos = AgendamentoController.getIfPatient(input_data);break;
		case "bypatienterdoner": allAgendamentos = AgendamentoController.getPatientIfNotDone(input_data);break;
		case "bypatienterdone": allAgendamentos = AgendamentoController.getPatientIfDone(input_data);break;
	}
}

for(Patient p : allPatients){
	
	patientsText += "<option value=\""+ p.getId() + "\">" + p.getNome() + "</option>";
	
}

for(Exame e : allExames){
	examesText += "<option value=\""+ e.getId() + "\">" + e.getNome() + "</option>";
}

for(Agendamento ag : allAgendamentos){
	tableText += "<tr> <th scope=\"row\">" + ag.getId() +  "</th> <td>" + ag.getDatetimer().getDate() + "/" + (ag.getDatetimer().getMonth() + 1) + "/" + (ag.getDatetimer().getYear() + 1900)+ "</td> <td>"+ ag.getDatetimer().getHours() + ":" + ag.getDatetimer().getMinutes() +"</td> <td>"+ ag.getExame().getNome() +"</td> <td> " + ag.getPaciente().getNome() + " </td> </tr>";
}



%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
</head>
<style>

.dropdown-item:focus{
	background-color: grey!important;
	
} .dropdown-item:hover {
	background-color: grey!important;
	
}

</style>
<body style="background-image: linear-gradient(to right, #212529, black)">
<nav class="navbar navbar-expand-lg bg-dark">
  <div class="container-fluid">
    <a class="navbar-brand text-white" href="./index.html">Home</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle text-white" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            Registros
          </a>
          <ul class="dropdown-menu bg-dark">
            <li><a class="dropdown-item text-white" href="./cad_exames.jsp">Exame</a></li>
            <li><a class="dropdown-item text-white" href="./cad_medico.jsp">Médico</a></li>
            <li><a class="dropdown-item text-white" href="./cad_pacientes.jsp">Pacientes</a></li>
            <li><a class="dropdown-item text-white" href="./cad_agendamento.jsp">Agendamento</a></li>
          </ul>
        </li>

      </ul>
      <form class="d-flex" role="search">
        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
        <button class="btn btn-outline-success" type="submit">Search</button>
      </form>
    </div>
  </div>
</nav>

<div class="container">

<p class="d-inline-flex gap-1">
  <button class="btn btn-light" type="button" data-bs-toggle="collapse" data-bs-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample">
    Filtrar Dados
  </button>
</p>
<div class="collapse" id="collapseExample">
  <div class="card card-body bg-dark text-white">
  <h3>Filtro</h3>
  <form action="filterAg">
  <div class="input-group select-group">
  
  <select class="form-control bg-dark text-white input-group-addon" onChange="filter_swapper()" name="input_filter" id="input_filter" style=" width: 30px!important;">
  			<option hidden selected> Opções </option>
  			<option value="all">Todos &nbsp</option>
            <option value="all_done">Todos Realizados </option>
            <option value="by_date">Por dia</option>
            <option value="by_week">Por semana &nbsp</option>
            <option value="by_patient">Por Paciente &nbsp</option>
            <option value="by_patient_open">Por Paciente em Aberto &nbsp</option>
            <option value="by_patient_done">Por paciente já realizado &nbsp</option>
        </select>
    <input type="text" class="form-control bg-dark text-white" id="filter_text_input" name = "filter_text_input" style=" width: 870px;">
    <button type="submit" class="btn btn-light">Filtrar <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
  <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z"/>
</svg> </button>
        

</div>
 </form>
  </div>
</div>

<table class="table table-dark">
  <thead>
    <tr>
      <th scope="col">ID</th>
      <th scope="col">DIA</th>
      <th scope="col">HORA</th>
      <th scope="col">EXAME</th>
      <th scope="col">PACIENTE</th>
    </tr>
  </thead>
  <tbody>
  <%=tableText %>
  </tbody>
</table>


<form action="regAgendamento">

<div class="mb-3">
    <label for="input_datetime" class="form-label text-white">Dia e Hora</label>
    <input type="datetime-local" class="form-control" id="input_datetime" name="input_datetime" aria-describedby="emailHelp">
  </div>

<label for="selectP" class="form-label text-white">Pacientes</label>
<select class="form-select bg-dark text-white" aria-label="Default select example" id="selectP" name="selectP">
  <option selected hidden>Opções</option>
  <%=patientsText %>
</select>

<br/>

<label for="selectE" class="form-label text-white">Exames</label>
<select class="form-select bg-dark text-white" aria-label="Default select example" id="selectE" name="selectE">
  <option selected hidden>Opções</option>
  <%=examesText %>
</select>

<br/>

<button class="btn btn-light">Sumbit</button>

</form>



</div>
<script src="scripts/cad_agendamento.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
</body>
</html>