package servlets;

import generic.Mediator;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Login")
public class Login extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		HttpSession ses = req.getSession(false);
		String nickname = req.getParameter("nickname");
		String password = req.getParameter("password");
		String platform = req.getParameter("platform");
		String action= req.getParameter("action");
		String address = req.getRemoteAddr();

		
		if (action.equals("login")) {
			
			if (platform.equals("java")) {
	//			System.out.println("Someone is trying to connect to me -- JAVA");
				if (nickname.isEmpty()||password.isEmpty()) {
					PrintWriter wr= resp.getWriter();
					wr.write("Username or Password empty");
					wr.close();
				}else{
	//				System.out.println(" ci sono"+ nickname+password);
						if (isAutenticated(nickname, password)) {
	//						System.out.println("nick and pass ok");
		
							if (!Mediator.getMed().getOnline().containsKey(nickname)) {
	//							System.out.println("i'm not online");
		
								Mediator.getMed().addOnline(nickname, address, 0, "yes", platform);
								PrintWriter w= resp.getWriter();
								w.write("Success!");
								w.close();
							}else{
	//							System.out.println("I'm Online!");
								
								Mediator.getMed().removeOnline(nickname);
								PrintWriter w= resp.getWriter();
								w.write("Disconnected!");
								w.close();
							}
						}else {
	//						System.out.println("wrong user or pass");
		
							PrintWriter w= resp.getWriter();
							w.write("Wrong Username or Password!");
							w.close();
						}
						
				}
				
			}else{
				if (ses == null) {
					if (req.getParameterMap().containsKey("nickname")&& req.getParameterMap().containsKey("password")) {
						boolean rights = isAutenticated(nickname, password);			
//						System.out.println("Someone is trying to connect to me");
						
						if (rights) {
//							System.out.println("User and pass ok");
	
							if (!Mediator.getMed().getOnline().containsKey(nickname)) {
//								System.out.println("I'm not online");
	
								ses = req.getSession(true);
								ses.setAttribute("id", nickname);
								Mediator.getMed().addOnline(nickname, address, 0 , "yes", platform);
								
								
//								System.out.println("connected others");
//								System.out.println(nickname+" "+address+" "+platform);
								resp.sendRedirect("./index.html");
	
							}else {
//								System.out.println("I'm already online");
								
								Mediator.getMed().removeOnline(nickname);
								resp.sendRedirect("./html/login/login.html");
							}
	
						}else {
//							System.out.println("user or password incorrect");
							resp.sendRedirect("./html/login/login.html");
						}
					} else {
//						System.out.println("not enough parameters");
						resp.sendRedirect("./html/login/login.html");
					}
	
				} else {
//					System.out.println("relogged, so disconnected");
					Mediator.getMed().removeOnline(nickname);
					ses.invalidate();
					resp.sendRedirect("./html/login/login.html");
	
				}
	
			}
		}else if (action.equals("logout")) {
//			System.out.println("entro e nick= "+ses.getAttribute("id"));
			if (platform.equals("java")) {
				Mediator.getMed().removeOnline((String)ses.getAttribute("id"));
				PrintWriter w= resp.getWriter();
				w.write("Disconnected!");
				w.close();
				
			}else if (platform.equals("html")) {
//				System.out.println((String)ses.getAttribute("id"));
				Mediator.getMed().removeOnline((String)ses.getAttribute("id"));
				ses.invalidate();
				resp.sendRedirect("./html/login/login.html");
			}
		}	
		

	}

	private boolean isAutenticated(String nick, String pass) {
//		System.out.println("entro");
		if (!nick.isEmpty()) {
//			System.out.println("nick non nullo");
			if (Mediator.getMed().getUsersMap().containsKey(nick)) {
//				System.out.println("nick ok");
				if (pass.equals(Mediator.getMed().getUsersMap().get(nick).getPass())) {
//					System.out.println("pass ok");
					return true;
				}
			}
		}
		return false;

	}
}
