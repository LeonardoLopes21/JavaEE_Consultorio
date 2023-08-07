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
<title>Registo Médico</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
</head>

<% 

ArrayList<Exame> allExames = ExameController.getAll();

ArrayList<Doctor> allDocs = DoctorController.getAll();


String filterer = request.getParameter("filterer");

if(filterer != null && !filterer.equals("all")){
	
	allDocs = DoctorController.getAllByExame(Integer.parseInt(filterer));
	
}

String examesText = "";

String docText = "";

String examesOptions = "";


for(Exame e : allExames){
	examesText += "<div class=\"form-check\"> <input class=\"form-check-input exams\" type=\"checkbox\" value=\""+ e.getId() + "\" id=\"flexCheckDefault\"> <label class=\"form-check-label text-white\" for=\"flexCheckDefault\">" + e.getNome() + "</label> </div>";
}

for(Doctor doc : allDocs){
	docText += "<tr> <th scope=\"row\">" + doc.getId() +  "</th> <td>" + doc.getNome() +  "</td> <td>"+doc.getCrm()+"</td> <td>"+doc.getCpf()+ "</td> </tr>";
}

for(Exame e : allExames){
	
	examesOptions += "<option value=\"" + e.getId() + "\">"+ e.getNome() + "</option>";
	
}


%>
<script src="scripts/cad_medico.js"></script>
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

<br/>

<p class="d-inline-flex gap-1">
  <button class="btn btn-light" type="button" data-bs-toggle="collapse" data-bs-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample">
    Filtrar Dados
  </button>
</p>
<div class="collapse" id="collapseExample">
  <div class="card card-body bg-dark text-white">
  <h3>Filtro</h3>
  
  <form action="filterDoc">
  <div class="input-group select-group">
  
  <select class="form-control bg-dark text-white input-group-addon" onChange="filter_swapper()" name="select_filter" id="select_filter" style=" width: 30px!important;">
  			<option hidden selected> Opções </option>
  			<option value="all"> Todos </option>
  			<%=examesOptions %>
        </select>
    
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
      <th scope="col">CRM</th>
      <th scope="col">CPF</th>
    </tr>
  </thead>
  <tbody>
    <%=docText %>
  </tbody>
</table>

<br>


<form action="insert_medico" onsubmit="checkIfClicked()">
  <div class="mb-3">
    <label for="input_nome" class="form-label text-white">Nome</label>
    <input type="text" class="form-control bg-dark text-white" id="input_nome" name="input_nome" aria-describedby="emailHelp">
  </div>
  <div class="mb-3">
    <label for="input_crm" class="form-label text-white">CRM</label>
    <input type="number" class="form-control bg-dark text-white" id="input_crm" name="input_crm">
  </div>
   <div class="mb-3">
    <label for="input_crm" class="form-label text-white">CPF</label>
    <input type="text" class="form-control bg-dark text-white" id="input_cpf" name="input_cpf">
  </div>
  
  <%=examesText %>

<br/>

 <input type="text" hidden id="hiddeninput" name="hiddeninput">
 
  <button type="submit" class="btn btn-primary">Submit</button>
</form>

</div>



<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
</body>
</html>