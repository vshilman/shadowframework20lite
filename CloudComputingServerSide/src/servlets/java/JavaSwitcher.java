package servlets.java;

import generic.Mediator;

import java.beans.XMLEncoder;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.corba.se.impl.ior.WireObjectKeyTemplate;

/**
 * Servlet implementation class Switcher
 */
@WebServlet("/JavaSwitch")
public class JavaSwitcher extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JavaSwitcher() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//TODO
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nick=request.getParameter("nickname");
		String game=request.getParameter("game");
		PrintWriter wr= response.getWriter();
		if (Mediator.getMed().isOnline(nick)) {
			Mediator.getMed().changeGame(nick, game);
			wr.write("done");
			wr.close();
		}else {
			wr.write("error");
			wr.close();
		}
		
		
	}

}
