package shadow.pipeline.data;

import java.util.LinkedList;

import shadow.pipeline.expression.SFExpressionElement;
import shadow.pipeline.expression.SFExpressionTypeWrapper;
import shadow.pipeline.parameters.SFParameteri;
import shadow.system.data.SFDataObject;
import shadow.system.data.SFInputStream;
import shadow.system.data.SFOutputStream;

public class SFDataExpressionTypeWrapper extends SFExpressionTypeWrapper implements SFDataObject{

	public SFDataExpressionTypeWrapper() {
		super(SFParameteri.GLOBAL_FLOAT);
	}
	
	public SFDataExpressionTypeWrapper(short type) {
		super(type);
	}

	@Override
	public void addSubExpression(SFExpressionElement element)
			throws ArrayIndexOutOfBoundsException {
		super.addSubExpression(element);
	}
	
	@Override
	public SFDataExpressionTypeWrapper clone(){
		return new SFDataExpressionTypeWrapper(getType());
	}
	
	@Override
	public void readFromStream(SFInputStream stream) {
		setType(stream.readShort());
		int n=stream.readShort();
		for (int i = 0; i < n; i++) {
			String element=stream.readString();
			SFExpressionElement expression=(SFExpressionData.getExpressionElement(element));
			SFDataObject dataset=(SFDataObject)expression;
			dataset.readFromStream(stream);
			this.list.add(expression);
		}
	}
	
	
	@Override
	public void writeOnStream(SFOutputStream stream) {
		stream.writeShort(getType());
		LinkedList<SFExpressionElement> list=this.list;
		stream.writeShort((short)(list.size()));
		for (SFExpressionElement sfExpressionElement : list) {
			String element=SFExpressionData.getExpressionElement(sfExpressionElement);
			stream.writeString(element);
			((SFDataObject)sfExpressionElement).writeOnStream(stream);
		}
	}
	
}
