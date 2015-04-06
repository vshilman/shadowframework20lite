package tests;

import java.awt.Dimension;
import java.util.LinkedList;

import graphics.proxy.BriscolaPanel;

import javax.swing.JFrame;

import mediator.Mediator;

public class TestGamingTable {

	public static void main(String[] args) {
		JFrame frame= new JFrame();
		frame.setSize(new Dimension(1000, 700));

	//	frame.setIconImage(Toolkit.getDefaultToolkit().getImage("./images/libro.png"));
//		frame.setLayout(new BorderLayout());
//	-->	CalculatePanel cPanel=new CalculatePanel();
		LinkedList<String> nicks=new LinkedList<String>();
		nicks.add("Andrea");
		nicks.add("Giorgio");
		nicks.add("Sofia");
		nicks.add("Marco");
		BriscolaPanel panel= new BriscolaPanel();
		frame.getContentPane().add(panel);
		frame.setVisible(true);
		
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
