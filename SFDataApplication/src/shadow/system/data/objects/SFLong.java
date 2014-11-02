/**
 * 
 */
package shadow.system.data.objects;

import shadow.system.data.SFInputStream;
import shadow.system.data.SFOutputStream;
import shadow.system.data.SFWritableDataObject;

/**
 * @author Luigi Pasotti
 *
 */
public class SFLong implements SFWritableDataObject {

	private long longValue;
	
	public SFLong(long longValue) {
		super();
		this.longValue = longValue;
	}
	
	public long getLongValue() {
		return longValue;
	}
	
	public void setLongValue(long longValue) {
		this.longValue = longValue;
	}

	/* (non-Javadoc)
	 * @see shadow.system.data.SFDataObject#readFromStream(shadow.system.data.SFInputStream)
	 */
	@Override
	public void readFromStream(SFInputStream stream) {
		longValue = stream.readInt();
		longValue = (longValue<<32)+ stream.readInt();
	}

	/* (non-Javadoc)
	 * @see shadow.system.data.SFDataObject#writeOnStream(shadow.system.data.SFOutputStream)
	 */
	@Override
	public void writeOnStream(SFOutputStream stream) {
//		TODO: test read/write
		stream.writeInt((int) (longValue>>32));
		stream.writeInt((int) (longValue & 0x0ffffffff));
	}
	
	@Override
	public SFLong copyDataObject() {
		return new SFLong(longValue);
	}
	
	/* (non-Javadoc)
	 * @see shadow.system.data.SFCharsetObject#setStringValue(java.lang.String)
	 */
	@Override
	public void setStringValue(String value) {
		longValue = Long.parseLong(value);
	}

	/* (non-Javadoc)
	 * @see shadow.system.data.SFCharsetObject#toStringValue()
	 */
	@Override
	public String toStringValue() {
		return longValue + "";
	}

}
