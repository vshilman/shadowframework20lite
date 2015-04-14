package mediator;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import graphics.FileMenu;
import graphics.GameMenu;
import graphics.proxy.BriscolaPanel;
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
	private static String panelSetted;

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
		panelSetted="login";
		choosePanel= new ChooseGamePanel();

	}

	
	public JFrame getFrame() {
		return frame;
	}
	public JPanel getActualPanel() {
		return proxyPanel.getActualPanel();
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
	public boolean isThisPanelSetted(String panelName){
		if (panelSetted.equals(panelName)) {
			return true;
		}
		return false;
	}
	public void refreshPanel(){
		proxyPanel.refreshPanel();
	}
	public void generateDialog(String message){
		
		final JDialog dialog=new JDialog(frame);
		dialog.setSize(new Dimension(300,100));
		dialog.setLocationRelativeTo(frame);
		dialog.add(new JLabel(message));
		dialog.setVisible(true);
		dialog.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				if (arg0.getKeyChar()=='\n') {
					dialog.dispose();
				}
				
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
	}
	
	public void setLoginPanel(){
		proxyPanel.setPanel(logPanel);
		refresh();
		panelSetted="login";
	}
	public void setRoomsPanel(String gameName){
			((RoomsPanel) roomsPanel).setGame(gameName);
			roomsPanel.setUpPanel();
			proxyPanel.setPanel(roomsPanel);
			Mediator.getCMed().getConnection().setGame(gameName);
			Mediator.getMed().getComputator().setUserGame(gameName);
			refresh();
			panelSetted="rooms";
	}
	public void setChoosePanel() {
		if (Mediator.getMed().getComputator().amILogged()) {
	
			proxyPanel.setPanel(choosePanel);
			refresh();
			panelSetted="choose";
		}
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
	
	public void setGamePanel(String game){
		if (game.equals("briscola")) {
			proxyPanel.setPanel(new BriscolaPanel());
			refresh();
//			frame.getContentPane().removeAll();
//			frame.getContentPane().repaint();
//			frame.getContentPane().add(new BriscolaPanel());
//			frame.getContentPane().validate();
		}else if (game.equals("memory")) {
			frame.getContentPane().removeAll();
			frame.getContentPane().repaint();
			frame.getContentPane().add(new JPanel());
			frame.getContentPane().validate();
		}
		panelSetted="game";
	}
}
