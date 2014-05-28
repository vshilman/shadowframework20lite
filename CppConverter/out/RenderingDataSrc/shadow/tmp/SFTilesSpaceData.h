#ifndef shadow_tmp_H_
#define shadow_tmp_H_

#include "java/util/StringTokenizer.h"

#include "shadow/geometry/data.SFFixedFloat16.h"
#include "shadow/renderer/SFAbstractReferenceNode.h"
#include "shadow/renderer/SFNode.h"
#include "shadow/renderer/data.SfAbstractReferenceNodeData.h"
#include "shadow/system/data.SFDataObjectsList.h"
#include "shadow/system/data.SFLibraryReference.h"
#include "shadow/system/data.SFNamedParametersObject.h"
#include "shadow/system/data.SFWritableDataObject.h"
#include "shadow/system/data.objects.SFBinaryObject.h"
#include "shadow/system/data.objects.SFCompositeDataArray.h"
#include "shadow/system/data.objects.SFIntShortField.h"
#include "shadow/tmp/SFTilesSpace.h"

namespace sf{
class SFTilesSpaceData extends SfAbstractReferenceNodeData{

	SFIntShortField space=new SFIntShortField(0);
	SFDataObjectsList<TiledElementData> tiledElements=new SFDataObjectsList<TiledElementData>(
			new TiledElementData());

	SFTilesSpaceData() {
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("transform", transform);
		parameters.addObject("nodes", nodes);
		parameters.addObject("space", space);
		parameters.addObject("tiledElements", tiledElements);
		setData(parameters);
	}

	
	SFNode buildResource() {
		final SFTilesSpace tiledNode=(SFTilesSpace)(super.buildResource());

		for (int i = 0; i < tiledElements.getSize(); i++) {
			final TiledElementData element=tiledElements.get(i);

			SFNode node=element.getReference().getResource();
			tiledNode.addNode(node, element.getX(), element.getY(), element.getZ());
		}

		return tiledNode;
	}

	
	SFAbstractReferenceNode generateReferenceNode() {
		return new SFTilesSpace(space.getShort(0),space.getShort(1));
	}

	void setSpace(int x,int y){
		space.setShort(0, x);
		space.setShort(1, y);
	}

	void addElement(int x,int y,float z,String reference){
		TiledElementData tile=new TiledElementData();
		tile.setup(x, y, z, reference);
		tiledElements.add(tile);
	}

	Array implements SFWritableDataObject{
		SFIntShortField location;
		SFBinaryObject<SFFixedFloat16> zed;
		SFLibraryReference<SFNode> reference;
		
		void generateData() {
			location=new SFIntShortField(0);
			zed=new SFBinaryObject<SFFixedFloat16>(new SFFixedFloat16());
			reference=new SFLibraryReference<SFNode>();
			addDataObject(location);
			addDataObject(zed);
			addDataObject(reference);
		}

		void setup(int x,int y,float z,String reference){
			location.setShort(0, x);
			location.setShort(1, y);
			zed.getBinaryValue().setFloat(z);
			this->reference.setReference(reference);
		}

		int getX(){
			return location.getShort(0);
		}

		int getY(){
			return location.getShort(1);
		}

		float getZ(){
			return zed.getBinaryValue().getFloat();
		}

		SFLibraryReference<SFNode> getReference() {
			return reference;
		}

		
		void setStringValue(String value) {
			StringTokenizer tokenizer=new StringTokenizer("","(,)");
			setup(new Short(tokenizer.nextToken()), new Short(tokenizer.nextToken()), 
					new Float(tokenizer.nextToken()), tokenizer.nextToken());
		}

		
		String toStringValue() {
			return "("+getX()+","+getY()+","+getZ()+","+reference.getReference()+")";
		}

		
		SFCompositeDataArray clone() {
			return new TiledElementData();
		}
	}
}
;
}
#endif
