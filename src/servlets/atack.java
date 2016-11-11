package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.CtrlCombate;
import utils.ApplicationException;

/**
 * Servlet implementation class atack
 */
@WebServlet("/atack")
public class atack extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public atack() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		CtrlCombate ctrl=(CtrlCombate)request.getSession().getAttribute("CTRL");
		int puntosAtaque=Integer.parseInt(request.getParameter("Puntos"));
				
		try {
		    ctrl.ataque(puntosAtaque);
			} catch (ApplicationException e) {
			e.printStackTrace();
			}finally{
		request.getRequestDispatcher("WEB-INF/war.jsp").forward(request, response);
			}
		
	}

}
