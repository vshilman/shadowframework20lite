package shadow.renderer.data;

import java.util.LinkedList;

import shadow.system.SFInitiable;
import shadow.system.data.SFDataObject;
import shadow.system.data.SFInputStream;
import shadow.system.data.SFOutputStream;

public class SFLibraryReferenceList<T extends SFInitiable> extends LinkedList<SFLibraryReference<T>> implements SFDataObject{

	private static final long serialVersionUID=0;
	
	private SFLibraryReference<T> type;
	
	public SFLibraryReferenceList(SFLibraryReference<T> type) {
		super();
		this.type = type;
	}
	
	public void add(String reference){
		SFLibraryReference<T> libraryReference = type.clone();
		libraryReference.setReference(reference);
		add(libraryReference);
	}

	@Override
	public void readFromStream(SFInputStream stream) {
		int n=stream.readShort();
		for (int i = 0; i < n; i++) {
			SFLibraryReference<T> reference=type.clone();
			add(reference);
			reference.readFromStream(stream);
		}
	}
	
	@Override
	public void writeOnStream(SFOutputStream stream) {
		stream.writeShort((short)(size()));
		for (int i = 0; i < size(); i++) {
			get(i).writeOnStream(stream);
		}
	}
	
	@Override
	public SFLibraryReferenceList<T> clone(){
		return new SFLibraryReferenceList<T>(type);
	}
	
}
