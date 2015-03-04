package mediator;

import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import graphics.FileMenu;
import graphics.GameMenu;
import graphics.proxy.ChooseGamePanel;
import graphics.proxy.IProxyGraphic;
import graphics.proxy.LoggingPanel;
import graphics.proxy.ProxyGraphic;
import graphics.proxy.RoomsPanel;
import graphics.proxy.buttonFactory.Factory;
import graphics.proxy.buttonFactory.IButton;
import graphics.proxy.buttonFactory.IFactory;

public class GraphicMediator {

	private static JFrame frame;
	private static FileMenu fileMenu;
	private static GameMenu gameMenu;
	private static ProxyGraphic proxyPanel;
	private static IProxyGraphic logPanel;
	private static IProxyGraphic roomsPanel;
	private static IProxyGraphic choosePanel;
	private static IFactory buttonFactory;

	public GraphicMediator() {

	}
	
	static{
		buttonFactory= new Factory();
		frame=new JFrame();
		fileMenu=new FileMenu(frame, Mediator.getCMed().getConnection());
		logPanel= new LoggingPanel();
		roomsPanel= new RoomsPanel(null);
		gameMenu= new GameMenu();
		proxyPanel= new ProxyGraphic(logPanel);
		choosePanel= new ChooseGamePanel();

	}

	
	public JFrame getFrame() {
		return frame;
	}
	public FileMenu getFileMenu(){
		return fileMenu;
	}
	public GameMenu getGameMenu(){
		return gameMenu;
	}
	public JPanel getMainPanel() {
		return proxyPanel.setUpPanel();
	}
	
	public void refreshPanel(){
		proxyPanel.refreshPanel();
	}
	public void generateDialog(String message){
		
		JDialog dialog=new JDialog(frame);
		dialog.setSize(new Dimension(300,100));
		dialog.setLocationRelativeTo(frame);
		dialog.add(new JLabel(message));
		dialog.setVisible(true);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
	}
	
	public void setLoginPanel(){
		proxyPanel.setPanel(logPanel);
		refresh();

	}
	public void setRoomsPanel(String gameName){
		((RoomsPanel) roomsPanel).setGame(gameName);
		proxyPanel.setPanel(roomsPanel);
		Mediator.getCMed().getConnection().setGame(gameName);
		refresh();
	}
	public void setChoosePanel() {
		proxyPanel.setPanel(choosePanel);
		refresh();
	}

	private static void refresh() {
		frame.getContentPane().removeAll();
		frame.getContentPane().repaint();
		frame.getContentPane().add(proxyPanel.setUpPanel());
		frame.getContentPane().validate();
	}
	
	public IButton generateButton(String game){
		return buttonFactory.createGameButton(game);
	}
}
