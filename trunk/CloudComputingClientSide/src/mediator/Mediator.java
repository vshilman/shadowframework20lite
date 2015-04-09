package mediator;

import java.util.ArrayList;
import java.util.List;

import utils.Computator;
import utils.DeIncapsulator;
import utils.Incapsulator;


public class Mediator {
	
	private static Mediator med= new Mediator();
	private static ConnectionMediator cMed;
	private static GraphicMediator gMed;
	private static Computator computator;
	private static List<String> availableGames;
	private static DeIncapsulator decoder;
	private static Incapsulator coder;
	private static PlayingMediator pMed;
	
	public Mediator() {
		availableGames= new ArrayList<String>();
		availableGames.add("briscola");
		availableGames.add("memory");
	}

	static {
		computator=new Computator();
		cMed=new ConnectionMediator();
		gMed=new GraphicMediator();
		pMed=new PlayingMediator();
		decoder=new DeIncapsulator();
		coder= new Incapsulator();
	}
	
	public static PlayingMediator getPMed() {
		return pMed;
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
	public DeIncapsulator getDecoder() {
		return decoder;
	}
	public Incapsulator getCoder() {
		return coder;
	}
	
}
