package shadow.math;

public class SFSegment<T extends SFValue> {

	private T p1;
	private T p2;
	
	public SFSegment(T p1, T p2) {
		super();
		this.p1 = p1;
		this.p2 = p2;
	}
	
	public float getLength(){
		return p1.distance(p2);
	}
	
	public float distance(T p3){
		
		/* wait ..
		 * 
		 * Q = P1+t(P2-P1)
		 * 
		 * (P2-P1)(P3-Q)=0
		 * 
		 * (P2-P1)(P3-P1-t(P2-P1))=0
		 * 
		 * t=(P3-P1)(P2-P1)/(P2-P1)*(P2-P1)
		 * 
		 */
		float distance12=p1.distance(p2);
		float tp3=p3.triangularDot(p2, p1);
		float tp1=p1.triangularDot(p2, p1);
		
		float t=(tp3-tp1)/(distance12*distance12);
		
		if(t<0){
			return p3.distance(p1);
		}else if(t>1){
			return p3.distance(p2);
		}
		
		SFVertex4f Q=new SFVertex4f();
		Q.addMult((1-t), p1);
		Q.addMult(t, p2);
		
		return p3.distance(Q);
	}
	
	public T getP1() {
		return p1;
	}
	
	public T getP2() {
		return p2;
	}
}
