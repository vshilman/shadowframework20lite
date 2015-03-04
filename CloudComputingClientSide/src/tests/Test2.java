package tests;

import graphics.proxy.buttonFactory.Factory;
import graphics.proxy.buttonFactory.IFactory;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import mediator.Mediator;

public class Test2 {

	public static void main(String[] args) {
		
			JFrame frame= new JFrame();
			frame.setVisible(true);
		
		//	frame.setIconImage(Toolkit.getDefaultToolkit().getImage("./images/libro.png"));
//			frame.setLayout(new BorderLayout());
//		-->	CalculatePanel cPanel=new CalculatePanel();
			JPanel mainPanel= new JPanel();
			mainPanel.setLayout(new GridLayout(2,1));
			IFactory fa= new Factory();
			mainPanel.add(fa.createGameButton("Ciao").getButton());
			mainPanel.add(fa.createGameButton("O").getButton());
			
			frame.getContentPane().add(mainPanel);
			frame.setMinimumSize(new Dimension(800, 500));
			frame.setLocationRelativeTo(null);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
