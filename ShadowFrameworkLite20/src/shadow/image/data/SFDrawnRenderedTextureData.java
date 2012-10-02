package shadow.image.data;

import shadow.image.SFDrawnRenderedTexture;
import shadow.image.SFImageFormat;
import shadow.image.SFPipelineTexture;
import shadow.image.SFPipelineTexture.Filter;
import shadow.image.SFPipelineTexture.WrapMode;
import shadow.image.SFRenderedTexturesSet;
import shadow.image.SFTextureDataToken;
import shadow.renderer.SFNode;
import shadow.renderer.SFRenderer;
import shadow.renderer.data.SFDataAsset;
import shadow.renderer.data.SFDataAssetObject;
import shadow.renderer.data.SFDataObjectsList;
import shadow.renderer.data.SFLibraryReference;
import shadow.system.data.SFDataCenterListener;
import shadow.system.data.SFDataset;
import shadow.system.data.SFNamedParametersObject;
import shadow.system.data.objects.SFShort;

public class SFDrawnRenderedTextureData extends SFDataAsset<SFRenderedTexturesSet>{

	private static short USE_DEFAULT_DEPTH=1;
	private static short USE_DEFAULT_STENCIL=3;
	
	private SFDataObjectsList<SFTextureDataObject> textures=
		new SFDataObjectsList<SFTextureDataObject>(new SFTextureDataObject());
	private SFLibraryReference<SFNode> node=
		new SFLibraryReference<SFNode>();
	private SFDataAssetObject<SFRenderer> renderer=
		new SFDataAssetObject<SFRenderer>(null);
	private SFDrawnRenderedTexture drawn;
	private SFShort depthUse=new SFShort((short)0);
	
	public SFDrawnRenderedTextureData() {
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("textures", textures);
		parameters.addObject("node", node);
		parameters.addObject("renderer", renderer);
		parameters.addObject("depthUse", depthUse);
		setData(parameters);
		setReferences(node);
	}
	
	@Override
	protected SFRenderedTexturesSet buildResource() {
		drawn=new SFDrawnRenderedTexture();
		SFPipelineTexture[] textures=new SFPipelineTexture[this.textures.size()];
		for (int i = 0; i < textures.length; i++) {
			textures[i]=this.textures.get(i).getTexture();
		}
		drawn.setTextures(textures);
		
		node.retrieveDataset(new SFDataCenterListener<SFDataAsset<SFNode>>() {
			@Override
			public void onDatasetAvailable(String name, SFDataAsset<SFNode> dataset) {
				drawn.setNode(dataset.getResource());
			}
		});
		drawn.setRenderer(renderer.getDataset().getResource());
		drawn.setUseDefaultDepthBuffer((depthUse.getShortValue() & USE_DEFAULT_DEPTH)>0);
		drawn.setUseDefaultStencilBuffer((depthUse.getShortValue() & USE_DEFAULT_STENCIL)>0);
		return drawn;
	}
	
	public void addOutputTexture(int width, int height, SFImageFormat format,
			Filter filters, WrapMode wrapS, WrapMode wrapT){
		addOutputTexture(new SFTextureDataToken(width, height, format, filters, wrapS, wrapT));
	}
	
	public void addOutputTexture(SFPipelineTexture texture){
		SFTextureDataObject dataObject=new SFTextureDataObject();
		dataObject.setTexture(texture);
		this.textures.add(dataObject);
	}
	
	public void setRenderer(SFDataAsset<SFRenderer> renderer){
		this.renderer.setDataset(renderer);
	}

	public void setNode(SFDataAsset<SFNode> node) {
		this.node.setDataset(node);
	}
	
	public void setNode(String node) {
		this.node.setReference(node);
	}
	
	public void useDefaultDepth(boolean use){
		depthUse.setShortValue((short)(depthUse.getShortValue()+USE_DEFAULT_DEPTH));
	}
	
	public void useDefaultStencil(boolean use){
		depthUse.setShortValue((short)(depthUse.getShortValue()+USE_DEFAULT_STENCIL));
	}
}
