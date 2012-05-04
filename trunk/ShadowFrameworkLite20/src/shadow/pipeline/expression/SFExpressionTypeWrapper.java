package shadow.pipeline.expression;

import shadow.math.SFValuenf;
import shadow.pipeline.parameters.SFParameteri;

public class SFExpressionTypeWrapper extends SFExpressionElement{

	public SFExpressionTypeWrapper(short type) {
		super("wr"+type);
		setType(type);
	}

	@Override
	public void addSubExpression(SFExpressionElement element)
			throws ArrayIndexOutOfBoundsException {
		list.add(element);
	}
	
	@Override
	public void evaluateType() {
		//nothing to do.
	}
	
	@Override
	public SFValuenf evaluate(SFValuesMap values) {
		SFValuenf subValue=getExpressionElement(0).evaluate(values);
		int dimension=SFParameteri.getTypeDimension(this.getType());

		SFValuenf result=new SFValuenf(dimension);
		
		if(subValue.get().length==1){
			for (int i = 0; i < result.getSize(); i++) {
				result.get()[i]=subValue.get()[0];
			}
		}else if(dimension>=subValue.getSize()){
			for (int i = 0; i < subValue.getSize(); i++) {
				result.get()[i]=subValue.get()[i];
			}
			if(subValue.getSize()<3){
				result.get()[2]=0;
			}
			if(subValue.getSize()<4){
				result.get()[3]=1;
			}
		}else{//dimension<subValue.getSize()
			for (int i = 0; i <dimension; i++) {
				result.get()[i]=subValue.get()[i];
			}
		}

		return result;
	}
}
