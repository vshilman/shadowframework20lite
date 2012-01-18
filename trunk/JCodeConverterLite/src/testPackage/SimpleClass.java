package testPackage;

public class SimpleClass{

	private int a;

	public int getA(){
		return a;
	}

	public void setA(int a) {
		this.a=a;
		
		if(a==1){
			a+=1;
		}
	}
	
	
}
