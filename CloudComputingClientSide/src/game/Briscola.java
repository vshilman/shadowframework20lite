package game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import mediator.Mediator;

public class Briscola implements IGame {

	private List<Integer> carteLibere;
	private List<Integer> possiblePlayers;
	private List<Integer> storedCards;
	private LinkedList<String> orderedPlayers;
	private Random r;
	private int numberOfPlayers;
	private HashMap<Integer, Integer> cardPoint= new HashMap<Integer, Integer>();
	private int briscola;
	private int myTurn=0;
	
	public Briscola(int seed, int numberOfPlayers, int myTurn, LinkedList<String> orderedPlayers) {
		r=new Random(seed);
		carteLibere= new ArrayList<Integer>();
		compilaCarte();
		possiblePlayers= new ArrayList<Integer>();
//		possiblePlayers.add(2);
		possiblePlayers.add(4);
		storedCards= new ArrayList<Integer>();
		this.numberOfPlayers=numberOfPlayers;
		this.myTurn=myTurn;
		this.orderedPlayers=orderedPlayers;
		//Mediator.getGMed.setBRISCOLA!!!
	}
	
	@Override
	public int firstCard() {
		int index=r.nextInt(carteLibere.size());
		int number=carteLibere.get(index);
		carteLibere.remove(index);
		briscola=number;
		return number;
	}
	
	@Override
	public LinkedList<String> getOrderedPlayers() {
		
		return orderedPlayers;
	}
	@Override
	public String storeCard(List<Integer> card) {
		String catcher="";
		int mine=card.get(myTurn);
		int higher=-1;
		for (int i = 0; i < card.size(); i++) {
			if (isSameSeme(briscola,card.get(i))) {
				if (higher==-1||cardPoint.get(card.get(higher))<cardPoint.get(card.get(i))) {
					higher=i;
				}
			}
		}
		if (higher==-1) {
			higher=0;
			for (int i = 0; i < card.size(); i++) {
				if (isSameSeme(higher,card.get(i))) {
					if (cardPoint.get(card.get(higher))<cardPoint.get(card.get(i))) {
						higher=i;
					}
				}
			}
		}
		if (card.get(higher)==mine) {
			storedCards.addAll(card);
			catcher=Mediator.getMed().getComputator().getNick() +" takes the hand";
		}else {
			catcher=orderedPlayers.get(higher)+" takes the hand";
		}
		
		orderPlayers(higher);
		for (int i = 0; i < orderedPlayers.size(); i++) {
			if (Mediator.getMed().getComputator().getNick().equals(orderedPlayers.get(i))) {
				myTurn=i+1;
			}
		}
		return catcher;
	}
	
	private void orderPlayers(int position){
		String tmp;
		for (int i = 0; i < position; i++) {
			tmp=orderedPlayers.poll();
			orderedPlayers.addLast(tmp);
		}
	}
	
	@Override
	public void setPlayer(int position, String name) {
		orderedPlayers.set(position, name);
		
		
	}
	
	private boolean isSameSeme(int toCompare, int card){
		if (toCompare<=10&&card<=10) {
			return true;
		}else if ((toCompare>10&&toCompare<=20)&&(card>10&&card<=20)) {
			return true;
		}else if ((toCompare>20&&toCompare<=30)&&(card>20&&card<=30)) {
			return true;
		}else if ((toCompare>30&&toCompare<=40)&&(card>30&&card<=40)) {
			return true;
		}
		return false;
	}
	@Override
	public List<Integer> getPossiblePlayers() {
		return possiblePlayers;
	}
	@Override
	public String checkWin() {
		int total=0;
		for (int i = 0; i < storedCards.size(); i++) {
			total=total+cardPoint.get(storedCards.get(i));
		}
		if (total>60) {
			return "You win";
		}else if (total==60) {
			return "Even";
		}
		
		return "You Lose";
	}
	
	@Override
	public int getMaxPlayers() {
		return numberOfPlayers;
	}
	
	@Override
	public int nextCard() {
		int number=0;
		try {
			if (myTurn==0) {
				int index=(r.nextInt(carteLibere.size()));
				System.out.println(index);
				number=carteLibere.get(index);
				carteLibere.remove(index);
				for (int i = 0; i < numberOfPlayers-1; i++) {
					index=(r.nextInt(carteLibere.size()));
					carteLibere.remove(index);
					System.out.println(index);
				}
			}else{
				for (int i = 0; i < myTurn; i++) {
					
					int index=(r.nextInt(carteLibere.size()));
					number=carteLibere.get(index);
					carteLibere.remove(index);
					
				}
				for (int i = 0; i < numberOfPlayers-(myTurn+1); i++) {
					int index=(r.nextInt(carteLibere.size()));
					carteLibere.remove(index);
				}
			
			}
		}catch (IllegalArgumentException e) {
				number=-1;
		}
		return number;
	}
	
	
	private void compilaCarte(){
		for (int i = 1; i < 41; i++) {
			carteLibere.add(i);
		}
		
		cardPoint.put(1, 11);
		cardPoint.put(2, 0);
		cardPoint.put(3, 10);
		cardPoint.put(4, 0);
		cardPoint.put(5, 0);
		cardPoint.put(6, 0);
		cardPoint.put(7, 0);
		cardPoint.put(8, 2);
		cardPoint.put(9, 3);
		cardPoint.put(10, 4);
		cardPoint.put(11, 11);
		cardPoint.put(12, 0);
		cardPoint.put(13, 10);
		cardPoint.put(14, 0);
		cardPoint.put(15, 0);
		cardPoint.put(16, 0);
		cardPoint.put(17, 0);
		cardPoint.put(18, 2);
		cardPoint.put(19, 3);
		cardPoint.put(20, 4);
		cardPoint.put(21, 11);
		cardPoint.put(22, 0);
		cardPoint.put(23, 10);
		cardPoint.put(24, 0);
		cardPoint.put(25, 0);
		cardPoint.put(26, 0);
		cardPoint.put(27, 0);
		cardPoint.put(28, 2);
		cardPoint.put(29, 3);
		cardPoint.put(30, 4);
		cardPoint.put(31, 11);
		cardPoint.put(32, 0);
		cardPoint.put(33, 10);
		cardPoint.put(34, 0);
		cardPoint.put(35, 0);
		cardPoint.put(36, 0);
		cardPoint.put(37, 0);
		cardPoint.put(38, 2);
		cardPoint.put(39, 3);
		cardPoint.put(40, 4);
		
		
	}
	
}
