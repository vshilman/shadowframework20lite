package interfaces;

public interface Observed {

	public void addObserver(Observer observer);

	public void delObserver(Observer observer);

	public void notifyObservers();

}
