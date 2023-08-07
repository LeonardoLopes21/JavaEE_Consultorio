<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
</head>
<%String errDirect = request.getParameter("msg");

String errMsg = "";

switch(errDirect){
	case "errtimeex":errMsg = "Não é possível marcar mais um exame do mesmo tipo na mesma data e hora ";break;
	case "errtimepac":errMsg = "O mesmo paciente não pode realizar mais de um exame no mesmo dia e horário";break;
	case "errtimenotav": errMsg = "Os horarios disponíveis são sempre com intervalo de 15 min (ex: 08:00, próximo 08:15)";break;
}

%>
<body style="background-image: linear-gradient(to right, #212529, black)">

<nav class="navbar navbar-expand-lg bg-dark">
  <div class="container-fluid">
    <a class="navbar-brand text-white" href="#">Navbar</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link text-white" aria-current="page" href="#">Home</a>
        </li>
        <li class="nav-item">
          <a class="nav-link text-white" href="#">Link</a>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle text-white" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            Dropdown
          </a>
          <ul class="dropdown-menu bg-dark">
            <li><a class="dropdown-item text-white" href="./cad_exames.jsp">Cadastro Exame</a></li>
            <li><a class="dropdown-item text-white" href="./cad_medico.jsp">Cadastro Médico</a></li>
            <li><a class="dropdown-item text-white" href="./cad_pacientes.jsp">Cadastro Pacientes</a></li>
            <li><a class="dropdown-item text-white" href="./cad_agendamento.jsp">Cadastro Agendamento</a></li>
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

<h1 class="text-white"style="margin-left: 150px;"> <%=errMsg %> </h1>

<img src="https://cdni.iconscout.com/illustration/premium/thumb/robot-with-sad-face-6460628-5334270.png" style="margin-left: 600px;">

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
</body>
</html>