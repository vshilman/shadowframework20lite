package testPackage;

public class Expressions {
	
	public String a="Ciao";
	
	public static int methodTest(int a,int b,int c){
		
		for (int i=0; i < c; i++) {
			a+=c;
		}
		
		a = b + c;
		
		return a+3;
	}
	
	
	public static int methodTest1(int a,int b,int c){
		
		a = b * c;
		
		return a+b;
	}

	public static int methodTest2(int a,int b,int c){
		
		a = b + ( c * a );
		
		return a;
	}
}
