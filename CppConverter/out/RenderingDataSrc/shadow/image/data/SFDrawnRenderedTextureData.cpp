#ifndef shadow_image_data_H_
#define shadow_image_data_H_

#include "shadow/image/SFDrawnRenderedTexture.h"
#include "shadow/image/SFImageFormat.h"
#include "shadow/image/SFPipelineTexture.h"
#include "shadow/image/SFPipelineTexture.Filter.h"
#include "shadow/image/SFPipelineTexture.WrapMode.h"
#include "shadow/image/SFRenderedTexturesSet.h"
#include "shadow/image/SFTextureDataToken.h"
#include "shadow/renderer/SFNode.h"
#include "shadow/renderer/SFRenderer.h"
#include "shadow/renderer/data.SFGraphicsAsset.h"
#include "shadow/system/data.SFDataAsset.h"
#include "shadow/system/data.SFDataAssetObject.h"
#include "shadow/system/data.SFDataObjectsList.h"
#include "shadow/system/data.SFLibraryReference.h"
#include "shadow/system/data.SFNamedParametersObject.h"
#include "shadow/system/data.objects.SFShort.h"

namespace sf{
class SFDrawnRenderedTextureData extends SFGraphicsAsset<SFRenderedTexturesSet>{

	AULT_DEPTH=1;
	AULT_STENCIL=3;

	SFDataObjectsList<SFTextureDataObject> textures=
		new SFDataObjectsList<SFTextureDataObject>(new SFTextureDataObject());
	SFLibraryReference<SFNode> node=
		new SFLibraryReference<SFNode>();
	AssetObject<SFRenderer> renderer=
		new SFDataAssetObject<SFRenderer>(null);

	SFShort depthUse=new SFShort((short)0);

	SFDrawnRenderedTextureData() {
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("textures", textures);
		parameters.addObject("node", node);
		parameters.addObject("renderer", renderer);
		parameters.addObject("depthUse", depthUse);
		setData(parameters);
	}

	
	SFRenderedTexturesSet buildResource() {
		SFDrawnRenderedTexture drawn=new SFDrawnRenderedTexture();
		updateResource(drawn);
		return drawn;
	}

	
	void updateResource(SFRenderedTexturesSet resource) {
		SFDrawnRenderedTexture drawn=(SFDrawnRenderedTexture)resource;
		SFPipelineTexture[] textures=new SFPipelineTexture[this->textures.size()];
		for (int i = 0; i < textures.length; i++) {
			textures[i]=this->textures.get(i).getTexture();
		}
		drawn.setTextures(textures);

		drawn.setNode(node.getResource());
		drawn.setRenderer(renderer.getDataset().getResource());
			if((depthUse.getShortValue() & USE_DEFAULT_DEPTH) >0){
				drawn.setDepthBuffer(textures.length-1);
			}
		//drawn.setUseDefaultDepthBuffer((depthUse.getShortValue() & USE_DEFAULT_DEPTH)>0);
		drawn.setUseDefaultStencilBuffer((depthUse.getShortValue() & USE_DEFAULT_STENCIL)>0);

	}

	void addOutputTexture(int width, int height, SFImageFormat format,
			Filter filters, WrapMode wrapS, WrapMode wrapT){
		addOutputTexture(new SFTextureDataToken(width, height, format, filters, wrapS, wrapT));
	}

	void addOutputTexture(SFPipelineTexture texture){
		SFTextureDataObject dataObject=new SFTextureDataObject();
		dataObject.setTexture(texture);
		this->textures.add(dataObject);
	}

	void setRenderer(SFDataAsset<?> renderer){
		this->renderer.setDataset((SFDataAsset<SFRenderer>)renderer);
	}

	void setNode(SFGraphicsAsset<SFNode> node) {
		this->node.setDataset(node);
	}

	void setNode(String node) {
		this->node.setReference(node);
	}

	void useDefaultDepth(booluse){
		depthUse.setShortValue((short)(depthUse.getShortValue()+USE_DEFAULT_DEPTH));
	}

	void useDefaultStencil(booluse){
		depthUse.setShortValue((short)(depthUse.getShortValue()+USE_DEFAULT_STENCIL));
	}
}
;
}
#endif
