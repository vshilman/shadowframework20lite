package mediator;

import utils.Computator;


public class Mediator {
	
	private static Mediator med= new Mediator();
	private static ConnectionMediator cMed;
	private static GraphicMediator gMed;
	private static Computator computator;
	
	public Mediator() {
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
	};
	public static GraphicMediator getGMed() {
		return gMed;
	};
	public Computator getComputator(){
		return computator;
	}
	
}
