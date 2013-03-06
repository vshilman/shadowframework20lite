package CoordinatesTrasformations;

public class Angle2Rad {
	
	public static float angle2rad(float alfa){
		float rad=(alfa*(float)(Math.PI))/180;
		return rad;
	}
	
	public static float cos(float alfa){
		float angle= angle2rad(alfa);
		float cos=(float)(Math.cos(angle));
		return cos;
	}
	
	public static float sin(float alfa){
		float angle= angle2rad(alfa);
		float sin=(float)(Math.sin(angle));
		return sin;
	}
}