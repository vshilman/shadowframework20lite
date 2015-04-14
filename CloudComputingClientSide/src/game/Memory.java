package game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

import mediator.Mediator;

public class Memory implements IGame{

		private List<Integer> carteLibere;
		private List<Integer> possiblePlayers;
		private List<Integer> storedCards;
		private Random r;
		private int numberOfCards;
		private HashMap<Integer, Integer> cardPoint= new HashMap<Integer, Integer>();
		private int myTurn=0;
		private LinkedList<String> orderedPlayers;
		
		public Memory(int seed, int numberOfCards, int myTurn, LinkedList<String> orderedPlayers) {
			r=new Random(seed);
			carteLibere= new ArrayList<Integer>();
			compilaCarte();
			possiblePlayers= new ArrayList<Integer>();
			possiblePlayers.add(2);
			storedCards= new ArrayList<Integer>();
			this.numberOfCards=numberOfCards;
			this.myTurn=myTurn;
			this.orderedPlayers=orderedPlayers;
		}
		
		
		@Override
		public int firstCard() {
			return -1;
		}
		@Override
		public String storeCard(List<Integer> card) {
			if (card.get(0)==card.get(1)) {
				return "Good!";
			}
			orderPlayers();
			return "no";
			
		}
		
		private void orderPlayers(){
			String tmp=orderedPlayers.peekFirst();
			orderedPlayers.addLast(tmp);
		}
		
		@Override
		public LinkedList<String> getOrderedPlayers() {
			
			return orderedPlayers;
		}
		@Override
		public void setPlayer(int position, String name) {
			orderedPlayers.set(position, name);
		}
		
		@Override
		public List<Integer> getPossiblePlayers() {
			return possiblePlayers;
		}
		@Override
		public String checkWin() {
			if (storedCards.size()>numberOfCards/2) {
				return "You Win";
			}else if (storedCards.size()==numberOfCards/2) {
				return "Even";
			}
			return "You Lose";
		}
		
		@Override
		public int getMaxPlayers() {
			return 2;
		}
		
		@Override
		public int nextCard() {
			int number=0;
			int index=r.nextInt(carteLibere.size());
			number=carteLibere.get(index);
			carteLibere.remove(index);
			return number;
		}
		
		
		private void compilaCarte(){
			for (int i = 0; i < (numberOfCards/2); i++) {
				carteLibere.add(i);
			}
			for (int i = 0; i < (numberOfCards/2); i++) {
				carteLibere.add(i);
			}
		}
		

}
