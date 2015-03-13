package servlets.html;

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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			PrintWriter writer = response.getWriter();
			writer.write("<p>");
			writer.write("get non consentito");
			writer.write("</p>");
			writer.close();
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession ses = request.getSession(false);
		String action= request.getParameter("action");

		if (ses == null || !Mediator.getMed().isOnline((String)ses.getAttribute("id"))|| !Mediator.getMed().getOnline().get(ses.getAttribute("id")).getPlatform().equals("html")) {
			
			
			PrintWriter writer = response.getWriter();
			writer.write("<p>");
			writer.write("Sembri non essere loggato, effettua il login <a href=\"./html/login/login.html\">qui</a>.");
			writer.write("</p>");
			writer.close();
			
			
		} else if (action.equals("refresh")) {
			
			String nick=(String)ses.getAttribute("id");
			PrintWriter wr= response.getWriter();
			if (Mediator.getMed().isOnline(nick)) {
				Mediator.getMed().setWelcomeUser(nick);
				String name = request.getParameter("nickname");
//				PrintWriter writer = response.getWriter();
//				writer.write("<p>Hi: " + ses.getAttribute("id") + "</p>");
//				Set<String> usersOnline = Mediator.getMed().getOnline().keySet();
//				HashMap<String, List<String>> status = new HashMap<String, List<String>>();
//				status.putAll(Mediator.getMed().getOnline());
//				List<String> users = new ArrayList<String>();
//				for (Iterator iterator = usersOnline.iterator(); iterator.hasNext();) {
//					String string = (String) iterator.next();
//					users.add(string);
//				}
//				writer.write("<table>");
//				writer.write("<tr>");
//				writer.write("<th> Nickname </th>");
//				writer.write("<th> Available </th>");
//				writer.write("</tr>");
//				for (int i = 0; i < users.size(); i++) {
//					writer.write("<tr>");
//					writer.write("<td> " + users.get(i) + " </td>");
//					writer.write("<td>" + status.get(users.get(i)).get(0) + "</td>");
//					writer.write("</tr>");
//
//				}
//				writer.write("</table>");
//				// writer.write("<p><a href=\"./html/test/appletTest.html\"> Go to the Applet </a></p>");
//				writer.close();
				String xml= Mediator.getMed().getXmlOnlinePlayers();
				
//				for (int i = 0; i < xml.size(); i++) {
					wr.write(xml);
//					System.out.println(xml);
//					System.out.println(xml.get(i));
//				}
//				wr.write("done "+(int)(Math.random()*10000));
				wr.close();
			}else {
				wr.write(Mediator.getMed().codeMessage("error"));
				wr.close();
			}
			

		}else if (action.equals("getProfile")){
			PrintWriter wr= response.getWriter();
			wr.write(Mediator.getMed().getXmlUser((String)ses.getAttribute("id")));
			wr.close();
		}
	}
}
