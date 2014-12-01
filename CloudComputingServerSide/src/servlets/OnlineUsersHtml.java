package servlets;

import generic.Mediator;
import generic.UserState;

import java.beans.XMLEncoder;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class HomeHtml
 */
@WebServlet("/HomeHtml")
public class OnlineUsersHtml extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String frase;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OnlineUsersHtml() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession ses = request.getSession(false);
		if (ses == null || !Mediator.getMed().getOnline().containsKey(ses.getAttribute("id"))|| !Mediator.getMed().getOnline().get(ses.getAttribute("id")).get(1).equals("html")) {
			PrintWriter writer = response.getWriter();
			writer.write("<p>");
			writer.write("You seem to not be logged, please log in <a href=\"./html/login/login.html\">here</a>.");
			writer.write("</p>");
			writer.close();
			
			
		} else {
			List<String> welcomer=Mediator.getMed().getWelcomeUser();
			XMLEncoder encode= new XMLEncoder(response.getOutputStream());
			encode.writeObject(welcomer);
			encode.flush();
			encode.close();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession ses = request.getSession(false);
		if (ses == null || !Mediator.getMed().getOnline().containsKey(ses.getAttribute("id"))|| !Mediator.getMed().getOnline().get(ses.getAttribute("id")).get(1).equals("html")) {
			
			PrintWriter writer = response.getWriter();
			writer.write("<p>");
			writer.write("You seem to not be logged, please log in <a href=\"./html/login/login.html\">here</a>.");
			writer.write("</p>");
			writer.close();
			
			
		} else {
			String nick=(String)ses.getAttribute("id");
			PrintWriter wr= response.getWriter();
			if (Mediator.getMed().getOnline().containsKey(nick)) {
				Mediator.getMed().setWelcomeUser(nick);
				wr.write("done");
				wr.close();
			}else {
				wr.write("error");
				wr.close();
			}
//			String name = request.getParameter("nickname");
//			PrintWriter writer = response.getWriter();
//			writer.write("<p>Hi: " + ses.getAttribute("id") + "</p>");
//			Set<String> usersOnline = Mediator.getMed().getOnline().keySet();
//			HashMap<String, List<String>> status = new HashMap<String, List<String>>();
//			status.putAll(Mediator.getMed().getOnline());
//			List<String> users = new ArrayList<String>();
//			for (Iterator iterator = usersOnline.iterator(); iterator.hasNext();) {
//				String string = (String) iterator.next();
//				users.add(string);
//			}
//			writer.write("<table>");
//			writer.write("<tr>");
//			writer.write("<th> Nickname </th>");
//			writer.write("<th> Available </th>");
//			writer.write("</tr>");
//			for (int i = 0; i < users.size(); i++) {
//				writer.write("<tr>");
//				writer.write("<td> " + users.get(i) + " </td>");
//				writer.write("<td>" + status.get(users.get(i)).get(0) + "</td>");
//				writer.write("</tr>");
//
//			}
//			writer.write("</table>");
//			// writer.write("<p><a href=\"./html/test/appletTest.html\"> Go to the Applet </a></p>");
//			writer.close();

		}
	}
}
