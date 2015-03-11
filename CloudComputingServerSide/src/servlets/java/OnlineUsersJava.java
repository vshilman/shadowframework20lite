package servlets.java;

import generic.Mediator;

import java.beans.XMLEncoder;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.swing.internal.plaf.metal.resources.metal;

/**
 * Servlet implementation class HomeJava
 */
@WebServlet("/HomeJava")
public class OnlineUsersJava extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OnlineUsersJava() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			PrintWriter wr= response.getWriter();
			wr.write(Mediator.getMed().getWelcomeUser());
			wr.flush();
			wr.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");
		String nick=request.getParameter("nickname");
		PrintWriter wr= response.getWriter();
		
		if (action.equals("getTablesMap")) {
			wr.write(Mediator.getMed().getXmlTableMap());
			wr.close();
		}else if (action.equals("getOnlineMap")) {
			wr.write(Mediator.getMed().getXmlOnlinePlayers());
			wr.close();
		}else if (action.equals("setMeAsWelcomer")) {
			if (Mediator.getMed().isOnline(nick)) {
				Mediator.getMed().setWelcomeUser(nick);
				wr.write(Mediator.getMed().codeMessage("done"));
				wr.close();
			}else {
				wr.write(Mediator.getMed().codeMessage("error"));
				wr.close();
			}
		}else {
			wr.write(Mediator.getMed().codeMessage("error"));
			wr.close();
		}
		
		
	}

}
