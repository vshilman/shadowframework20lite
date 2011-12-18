package testPackage;

public class Expressions {
	
	public static int methodTest(int a,int b,int c){
		
		a = b + c;
		
		return 0;
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
