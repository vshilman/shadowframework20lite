package shadow.pipeline.data;

import shadow.math.SFValuenf;
import shadow.pipeline.expression.SFExpressionElement;
import shadow.pipeline.expression.SFExpressionException;
import shadow.pipeline.expression.SFValuesMap;
import shadow.system.data.SFDataObject;
import shadow.system.data.SFInputStream;
import shadow.system.data.SFOutputStream;

public class SFDataExpressionElement extends SFExpressionElement implements SFDataObject{

	public SFDataExpressionElement() {
		super("");
	}
	
	public SFDataExpressionElement(String element,short type) {
		super(element);
		setType(type);
	}
	
	@Override
	public SFDataExpressionElement clone() {
		return new SFDataExpressionElement();
	}
	
	@Override
	public void readFromStream(SFInputStream stream) {
		setElement(stream.readString());
		setType(stream.readShort());
	}
	
	@Override
	public void writeOnStream(SFOutputStream stream) {
		stream.writeString(getElement());
		stream.writeShort(getType());
	}
	
	@Override
	public SFValuenf evaluate(SFValuesMap values) {
		return null;
	}
	
	@Override
	public void addSubExpression(SFExpressionElement element)
			throws ArrayIndexOutOfBoundsException {
		//Nothing to do
	}
	
	@Override
	public void evaluateType() throws SFExpressionException {
		//Nothing to do
	}
	
}
