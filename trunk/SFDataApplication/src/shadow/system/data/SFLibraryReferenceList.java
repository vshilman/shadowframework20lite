package shadow.system.data;

import java.util.ArrayList;

public class SFLibraryReferenceList<T> extends ArrayList<SFLibraryReference<T>> implements SFDataObject{

	private static final long serialVersionUID=0;
	
	private SFLibraryReference<T> type;
	
	public SFLibraryReferenceList(SFLibraryReference<T> type) {
		super();
		this.type = type;
	}
	
	public void add(String reference){
		SFLibraryReference<T> libraryReference = type.copyDataObject();
		libraryReference.setReference(reference);
		add(libraryReference);
	}
	
	public void add(SFDataAsset<T> reference){
		SFLibraryReference<T> libraryReference = type.copyDataObject();
		libraryReference.setDataset(reference);
		libraryReference.setReference(SFLibraryReference.NULL_REFERENCE);
		add(libraryReference);
	}

	@Override
	public void readFromStream(SFInputStream stream) {
		clear();
		int n=stream.readShort();
		for (int i = 0; i < n; i++) {
			SFLibraryReference<T> reference=type.copyDataObject();
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
	public SFLibraryReferenceList<T> copyDataObject(){
		return new SFLibraryReferenceList<T>(type);
	}
	
}
