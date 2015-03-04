package servlets.html;

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
@WebServlet("/HtmlSwitch")
public class HtmlSwitcher extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HtmlSwitcher() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession ses = request.getSession(false);

		if (ses==null) {
			response.sendRedirect("/ccom/html/login/login.html");
		}else {
			PrintWriter writer= response.getWriter();
			writer.write("<html>");
			writer.write("<head>");
			writer.write("<link rel=\"stylesheet\" href=\"./html/style/style.css\" type=\"text/css\">");
			writer.write("<title>Cloud Computing: Select a Game</title>");
			writer.write("</head>");
			writer.write("<body>");
			writer.write("<div id=\"games\">");
			writer.write("Scegli un gioco:");
			writer.write("<form name=\"choosingForm\" action=\"HtmlSwitch\" method=\"post\">");
			String[] games=Mediator.getMed().getGames();
			for (int i = 0; i < games.length; i++) {
				writer.write("<input class=\"gamebutton\" type=\"submit\" name=\"game\" value=\""+games[i]+"\">");
			}
			writer.write("</form>");
			writer.write("</div>");
			writer.write("</body>");
			writer.write("</html>");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession ses = request.getSession(false);

		if (ses==null) {
			response.sendRedirect("/ccom/html/login/login.html");
		}else {
			Mediator.getMed().changeGame((String)ses.getAttribute("id"), request.getParameter("game"));
			response.sendRedirect("/ccom/index.html");

		}
		
		
	}

}
