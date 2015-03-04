package graphics.proxy.buttonFactory;


public class Factory implements IFactory {

	
	@Override
	public IButton createGameButton(String name) {
		
		return new GameButton(name);
	}
}
