package shadow.image.data;

import shadow.image.SFImageFormat;
import shadow.image.SFPipelineTexture;
import shadow.image.SFPipelineTexture.Filter;
import shadow.image.SFPipelineTexture.WrapMode;
import shadow.image.SFTextureDataToken;
import shadow.system.data.objects.SFCompositeDataArray;
import shadow.system.data.objects.SFInt;

public class SFTextureDataObject extends SFCompositeDataArray{

	private SFInt widthHeight;
	private SFInt params;
	
	public void generateData() {
		widthHeight=new SFInt(0);
		params=new SFInt(0);
		
		addDataObject(widthHeight);
		addDataObject(params);
	}
	
	@Override
	public SFCompositeDataArray clone() {
		return new SFTextureDataObject();
	}

	public int getWidth() {
		return widthHeight.getShort(0);
	}

	public void setWidth(int width) {
		widthHeight.setShort(0, width);
	}

	public int getHeight() {
		return widthHeight.getShort(1);
	}

	public void setHeight(int height) {
		widthHeight.setShort(1, height);
	}

	public SFImageFormat getFormat() {
		int format=params.getByte(0);
		return SFImageFormat.values()[format];
	}
	
	public Filter getFilter() {
		int filter=params.getByte(1);
		return Filter.values()[filter];
	}
	
	public WrapMode getWrapModeS() {
		int filter=params.getByte(2);
		return WrapMode.values()[filter];
	}
	
	public WrapMode getWrapModeT() {
		int filter=params.getByte(3);
		return WrapMode.values()[filter];
	}

	public void setFormat(SFImageFormat format) {
		for (int i = 0; i < SFImageFormat.values().length; i++) {
			if(format==SFImageFormat.values()[i]){
				params.setByte(0, i);
				return;
			}
		}
	}
	
	public void setFilter(Filter filter) {
		for (int i = 0; i < Filter.values().length; i++) {
			if(filter==Filter.values()[i]){
				params.setByte(1, i);
				return;
			}
		}
	}

	public void setWrapModeS(WrapMode wrapMode) {
		for (int i = 0; i < WrapMode.values().length; i++) {
			if(wrapMode==WrapMode.values()[i]){
				params.setByte(2, i);
				return;
			}
		}
	}

	public void setWrapModeT(WrapMode wrapMode) {
		for (int i = 0; i < WrapMode.values().length; i++) {
			if(wrapMode==WrapMode.values()[i]){
				params.setByte(3, i);
				return;
			}
		}
	}
	
	public SFPipelineTexture getTexture(){
		SFPipelineTexture token=new SFTextureDataToken(getWidth(),getHeight(),getFormat(),getFilter(),getWrapModeS(),getWrapModeT());
		return token;
	}
	
	public void setTexture(SFPipelineTexture texture){
		setWidth(texture.getWidth());
		setHeight(texture.getHeight());
		setFormat(texture.getFormat());
		setFilter(texture.getFilters());
		setWrapModeS(texture.getWrapS());
		setWrapModeT(texture.getWrapT());
	}
}
