package shadow.renderer.tests.utils;

import shadow.geometry.editing.SFConcreteTriangleExtractor;
import shadow.geometry.functions.SFCurvedTubeFunction;
import shadow.geometry.geometries.SFQuadsSurfaceGeometry;
import shadow.renderer.SFNode;
import shadow.renderer.data.SFStructureArrayData;
import shadow.system.data.SFGenericDatasetFactory;
import shadow.system.data.SFObjectsLibrary;
import shadow.tmp.SFRigidReferencesSystem;

public class SFViewerDatasetFactory extends SFGenericDatasetFactory{
	
	public SFViewerDatasetFactory(){
		addSFDataset(new SFRigidReferencesSystem());
		addSFDataset(new SFQuadsSurfaceGeometry());
		addSFDataset(new TexCoordFunction());
		addSFDataset(new SFConcreteTriangleExtractor());
		addSFDataset(new SFCurvedTubeFunction());
		addSFDataset(new SFObjectsLibrary());
		addSFDataset(new SFNode());
		addSFDataset(new SFStructureArrayData());
	}
}
