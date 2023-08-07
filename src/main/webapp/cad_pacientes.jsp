<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="services.*" %>
<%@ page import="models.*" %>
<%@ page import="controller.*" %>
<%@ page import="java.time.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastro de Pacientes</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
<link href="./style/regpaciente.css" rel="stylesheet">
</head>
<body style="background-image: linear-gradient(to right, #212529, black)">

<style>

.dropdown-item:focus{
	background-color: grey!important;
	
} .dropdown-item:hover {
	background-color: grey!important;
	
}

</style>
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

<br/>

<div class="container">

<% 
String filterDecider = request.getParameter("filter");
ArrayList<Patient> allPat = PatientController.getAll();
String tableText = "";



if(filterDecider != null){
	switch(filterDecider){
		case "month": allPat = PatientController.getIfBirthdayMonth(); break;
		case "sex": allPat = PatientController.getIfGivenSex(request.getParameter("sex")); break;
		case "week": allPat = PatientController.getIfBirthdayWeek(); break;
		case "birthday": allPat = PatientController.getIfBirthday(); break;
	}
}

for(Patient p : allPat){
	
	
	tableText +=  "<tr> <th scope=\"row\">"+ p.getId() +"</th> <td>"+ p.getNome()+"</td> <td>" + p.getEmail() +"</td> <td>"+ p.getSexo().substring(0, 1).toUpperCase() + p.getSexo().substring(1) +"</td> <td>"+ p.getData_nascimento() +"</td> </tr>";
	
}%>

<p class="d-inline-flex gap-1">
  <button class="btn btn-light" type="button" data-bs-toggle="collapse" data-bs-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample">
    Filtrar Dados
  </button>
</p>
<div class="collapse" id="collapseExample">
  <div class="card card-body bg-dark text-white">
  <h3>Filtro</h3>
  <form action="filter">
  <div class="input-group select-group">
  
  <select class="form-control bg-dark text-white input-group-addon" onChange="filter_swapper()" name="input-filter" id="select_filter" style=" width: 30px!important;">
  			<option hidden selected> Opções </option>
  			<option value="all">Todos &nbsp</option>
            <option value="month-birthday">Aniversariantes do mês &nbsp </option>
            <option value="sex-filter">Por sexo</option>
            <option value="week-birthday">Aniversariantes da semana &nbsp</option>
            <option value="birthday">Aniversariantes &nbsp</option>
        </select>
    <input type="text" class="form-control bg-dark text-white" id="filter_text_input" name = "filter_text_input" style=" width: 900px;">
    <button type="submit" class="btn btn-light">Filtrar <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
  <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z"/>
</svg> </button>
        

</div>
 </form>
  </div>
</div>

<br/>

<table class="table table-dark">
  <thead>
    <tr>
      <th scope="col">ID</th>
      <th scope="col">NOME</th>
      <th scope="col">EMAIL</th>
      <th scope="col">SEXO</th>
      <th scope="col">DATA DE NASCIMENTO</th>
    </tr>
  </thead>
  <tbody>
  <%=tableText %>
  </tbody>
</table>

<form action="regPatient">

<div class="mb-3">
    <label for="inputNome" class="form-label text-white">Nome</label>
    <input type="text" name="inputNome" class="form-control bg-dark text-white" id="inputNome">
  </div>
  <div class="mb-3">
    <label for="inputEmail" class="form-label text-white">Email</label>
    <input type="email" class="form-control bg-dark text-white" name="inputEmail" id="inputEmail">
  </div>
<div class="mb-3">
    <label for="inputNasci" class="form-label text-white">Data de nascimento</label>
    <input type="date" name="inputNasci" class="form-control bg-dark text-white" id="inputNasci">
  </div>
<label for="selectSex" class="text-white">Sexo</label>
 <select id="selectSex" onChange="gen_swapper()" class="form-select bg-dark text-white" aria-label="Default select example">
  <option hidden selected>Opções </option>
  <option value="masculino">Masculino</option>
  <option value="feminino">Feminino</option>
  <option value="outro">Outro</option>
</select>
  <div class="mb-3" id="optionalInput" style="display: none">
    <input type="text" class="form-control bg-dark text-white" name="inputSexOther" id="inputSexOther">
  </div>



<br/>

<br/>
  <button type="submit" class="btn btn-light ">Submit</button>
  
  
</form>


</div>



<script src="./scripts/cad_patient.js"> </script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>


</body>
</html>