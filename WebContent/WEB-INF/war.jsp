<%@page import="negocio.CtrlCombate"%>
<%@page import="entidades.Personaje"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="http://getbootstrap.com/favicon.ico">

    <title>Play JavaCraft!</title>

    <!-- Bootstrap core CSS -->
    <link href="style/bootstrap.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="style/start.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="style/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
<title>War!!</title>
</head>
<body>
	<h1>War!!</h1>
	
	
	<% 
	    CtrlCombate ctrl= ((CtrlCombate)session.getAttribute("CTRL"));
	
		Personaje p1= ((Personaje)session.getAttribute("P1"));
		Personaje p2= ((Personaje)session.getAttribute("P2"));
	%>
	
	<%if(p1.getVidaActual()<=0)
		{request.getSession().setAttribute("JGanador",p2);
		System.out.println("ganador j2");
		}

	 if(p2.getVidaActual()<=0)
		{request.getSession().setAttribute("JGanador",p1);
		 System.out.println("ganador j1");
		}
	 
	 if(p1.getVidaActual()<=0 || p2.getVidaActual()<=0)
			//response.sendRedirect("WEB-INF/war.jsp");
	    	//response.sendRedirect("WEB-INF/winner.jsp");

        	 request.getRequestDispatcher("winner.jsp").forward(request, response);
	 %>
	
	 
	<% ctrl.generarNuevoTurno(); %> 
	TURNO ACTUAL: 
	<%=ctrl.getJugadorTurnoActual().getNombre() %> <br/>
	<%=p1.getNombre()+" Energia: "+p1.getEnergiaActual()+" Vida: "+p1.getVidaActual()+ " Defensa: "+p1.getDefensa()+ " Evasion:"+p1.getEvasion() %> <br/>
	<%=p2.getNombre()+" Energia: "+p2.getEnergiaActual()+" Vida: "+p2.getVidaActual()+ " Defensa: "+p2.getDefensa()+ " Evasion:"+p2.getEvasion() %>
	
	
	
	
	    <div class="container">

      <form class="form-atack" name="ataque" action="atack" method="post">
        <input name="Puntos" id="inputPuntos" class="form-control" placeholder="puntos de ataque" required="" autofocus="" >
        <button class="btn btn-lg btn-primary btn-block" type="submit">atack</button>
      </form>
      
    </div> <!-- /container -->
    
    	<div class="container">

      <form class="form-defense" name="signin" action="defense" method="post">
        <button class="btn btn-lg btn-primary btn-block" type="submit">defense</button>
      </form>
      
    </div> <!-- /container -->
	
	
</body>
</html>