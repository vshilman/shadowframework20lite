package tests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import utils.Incapsulator;

public class TestIncapsulator {

	private HashMap<String, List<String>>mappa= new HashMap<String, List<String>>();
	
	public TestIncapsulator() {
		List<String> lista;
		String testo;
		for (int i = 0; i < 100; i++) {
			lista= new ArrayList<String>();
			for (int j = 0; j < 3; j++) {
				lista.add(""+(i+j));
			}
			mappa.put(""+i, lista);

		}
		Incapsulator cap= new Incapsulator();
		lista= new ArrayList<String>();
		testo=cap.convert(mappa);
//		for (int i = 0; i < lista.size(); i++) {
			System.out.println(testo);
//		}
	}
	
	
}
