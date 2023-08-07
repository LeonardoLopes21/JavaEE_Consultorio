<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="services.*" %>
<%@ page import="models.*" %>
<%@ page import="controller.*" %>
<%@ page import="java.time.*" %>
<%@ page import="java.util.*" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
<title>CADASTRO EXAME</title>
</head>
<style>

.dropdown-item:focus{
	background-color: grey!important;
	
} .dropdown-item:hover {
	background-color: grey!important;
	
}

</style>

<%
ArrayList<Exame> allExames = ExameController.getAll();

String tableText = "";


for(Exame e : allExames){
	
	tableText +=  "<tr> <th scope=\"row\">"+ e.getId() +"</th> <td>" + e.getNome()+"</td> <td>" + e.getDias_da_semana() +"</td> </tr>";

	
}
	%>

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

<br/>

<table class="table table-dark">
  <thead>
    <tr>
      <th scope="col">ID</th>
      <th scope="col">NOME</th>
      <th scope="col">DIAS DA SEMANA</th>
    </tr>
  </thead>
  <tbody>
  <%=tableText %>
  </tbody>
</table>

<form action="insertEx" onsubmit="checkIfClicked()">
  <div class="mb-3">
    <label for="inputNomeEx" class="form-label text-white">Nome Exame</label>
    <input type="text" class="form-control bg-dark text-white" name="inputNomeEx" id="inputNomeEx">
  </div>
  <p class="text-white"> Dias disponíveis: </p>
  <div class="mb-3 form-check">
    <input type="checkbox" value="Segunda," class="form-check-input daysofweek" id="seg">
    <label class="form-check-label text-white" for="exampleCheck1">Segunda</label>
  </div>
    <div class="mb-3 form-check">
    <input type="checkbox" value="Terça," class="form-check-input daysofweek" id="ter">
    <label class="form-check-label text-white" for="exampleCheck1">Terça</label>
  </div>
    <div class="mb-3 form-check">
    <input type="checkbox" value="Quarta," class="form-check-input daysofweek" id="qua">
    <label class="form-check-label text-white" for="exampleCheck1">Quarta</label>
  </div>
    <div class="mb-3 form-check">
    <input type="checkbox" value="Quinta," class="form-check-input daysofweek" id="qui">
    <label class="form-check-label text-white" for="qui">Quinta</label>
  </div>
    <div class="mb-3 form-check">
    <input type="checkbox" value="Sexta," class="form-check-input daysofweek" id="sex">
    <label class="form-check-label text-white" for="sex">Sexta</label>
  </div>
    <div class="mb-3 form-check">
    <input type="checkbox" value="Sabado," class="form-check-input daysofweek" id="sab">
    <label class="form-check-label text-white" for="exampleCheck1">Sábado</label>
  </div>
    <div class="mb-3 form-check">
    <input type="checkbox" value="Domingo" class="form-check-input daysofweek" id="dom">
    <label class="form-check-label text-white" for="exampleCheck1">Domingo</label>
  </div>
  <input type="text" hidden name="weekdays" id="hidden_input_for_weekdays" name="hidden_input_for_weekdays">
  <button type="submit" class="btn btn-primary">Submit</button>
</form>


</div>



<script src="./scripts/cad_exames.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
</body>
</html>