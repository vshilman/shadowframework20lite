package mediator;

import java.util.ArrayList;
import java.util.List;

import utils.Computator;


public class Mediator {
	
	private static Mediator med= new Mediator();
	private static ConnectionMediator cMed;
	private static GraphicMediator gMed;
	private static Computator computator;
	private static List<String> availableGames;
	
	public Mediator() {
		availableGames= new ArrayList<String>();
		availableGames.add("Briscola");
		availableGames.add("Memory");
	}

	static {
		computator=new Computator();
		cMed=new ConnectionMediator();
		gMed=new GraphicMediator();
	}
	
	public static Mediator getMed() {
		return med;
	}
	public static ConnectionMediator getCMed() {
		return cMed;
	}
	public static GraphicMediator getGMed() {
		return gMed;
	}
	public Computator getComputator(){
		return computator;
	}
	public List<String> getAvailableGames() {
		return availableGames;
	}
	
}
