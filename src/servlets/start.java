package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Personaje;
import negocio.CtrlCombate;
import negocio.CtrlPersonajes;
import utils.ApplicationException;

/**
 * Servlet implementation class start
 */
@WebServlet("/start")
public class start extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public start() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CtrlPersonajes ctrlP = new CtrlPersonajes();
		//prueba
		CtrlCombate ctrlC = new CtrlCombate();
		
		Personaje p1= new Personaje();
		p1.setNombre(request.getParameter("Personaje1"));
		
		Personaje p2= new Personaje();
		p2.setNombre(request.getParameter("Personaje2"));
		
		try {
			p1=ctrlP.buscar(p1);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			p2=ctrlP.buscar(p2);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//response.getWriter().append("P1: ").append(p1.getNombre()+" "+p1.getApellido());
		//response.getWriter().append("P2: ").append(p2.getNombre()+" "+p2.getApellido());
		request.getSession().setAttribute("P1", p1);
		request.getSession().setAttribute("P2", p2);
		
		ctrlC.setJugador1(p1);
		ctrlC.setJugador2(p2);
		ctrlC.generarPrimerTurnoAleatorio();
		
		request.getSession().setAttribute("CTRL", ctrlC);
		
		//response.sendRedirect("WEB-INF/war.jsp");
		request.getRequestDispatcher("WEB-INF/war.jsp").forward(request, response);
		
	}

}
