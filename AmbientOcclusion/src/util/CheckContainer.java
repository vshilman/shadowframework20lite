package util;

import java.util.ArrayList;

import shadow.math.SFVertex3f;

public class CheckContainer{

	private ArrayList<Check> checkArray;
	
	
	public CheckContainer(){
		checkArray = new ArrayList<Check>();
	}

	
	public boolean isCalculate(SFVertex3f vertex){
		for(Check check : checkArray){
			if(compare(check.getVertex(), vertex)){
				return true;
			}
		}
		return false;
	}

	
	
	public float retrieveAO(SFVertex3f vertex){
		for(Check check : checkArray){
			if (compare(check.getVertex(), vertex)){
				return check.getAO();
			}
		}
		return -1;
	}

	
	
	public void storeCheck(SFVertex3f vertex, float ao){
		Check check = new Check();
		check.setVertex(vertex);
		check.setAO(ao);
		checkArray.add(check);
	}

	
	
	public int retrieveSize() {
		return checkArray.size();
	}

	
	
	public boolean compare(SFVertex3f v1, SFVertex3f v2){
		
		if(Math.abs(v1.getX()-v2.getX()) < 0.0001){
			
			if(Math.abs(v1.getY()-v2.getY()) < 0.0001){

				if(Math.abs(v1.getZ()-v2.getZ()) < 0.0001){
					return true;
				}
			}
		}
		return false;
	}

	
	
	
}
