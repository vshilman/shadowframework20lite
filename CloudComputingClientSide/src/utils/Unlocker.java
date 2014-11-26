package utils;

import mediator.Mediator;

public class Unlocker implements Runnable{

	private String dealer;
	private String me;
	
	public Unlocker() {
		updateNicks();
	}
	private void updateNicks(){
		dealer=Mediator.getCMed().getServiceMap().get("dealer").getNick();
		me=Mediator.getMed().getComputator().getNick();
	}
	public void run() {
		updateNicks();
		while (dealer.equals(me)) {
			
//			Mediator.getMed().getComputator().refresh();
			
			try {
				wait(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			updateNicks();
			
		}
		
	};
}
