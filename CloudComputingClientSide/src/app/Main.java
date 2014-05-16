package app;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import mediator.Mediator;

public class Main{

	private static JFrame frame= Mediator.getMed().getFrame();
	
		public static void main(String[] args) {
			frame.setVisible(true);
			
		//	frame.setIconImage(Toolkit.getDefaultToolkit().getImage("./images/libro.png"));
//			frame.setLayout(new BorderLayout());
//		-->	CalculatePanel cPanel=new CalculatePanel();
			JPanel mainPanel= Mediator.getMed().getMainPanel();
			JMenuBar menuBar= new JMenuBar();
			menuBar.add(Mediator.getMed().getFileMenu());
			menuBar.add(Mediator.getMed().getGameMenu());
			frame.setJMenuBar(menuBar);	
			frame.getContentPane().add(mainPanel);
			frame.setMinimumSize(new Dimension(800, 500));
			frame.setLocationRelativeTo(null);
			frame.setDefaultCloseOperation(exit());


		}
	
		private static int exit(){
			//connection.getConnection();
			return JFrame.EXIT_ON_CLOSE;
		}

	
}
