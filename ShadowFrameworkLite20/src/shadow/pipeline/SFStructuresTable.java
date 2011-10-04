package shadow.pipeline;

import shadow.renderer.SFAsset;
import shadow.system.data.SFDataset;
import shadow.system.data.SFInputStream;
import shadow.system.data.SFOutputStream;

public class SFStructuresTable extends SFAsset {

	private SFStructureArray array;
	private String structure;

	@Override
	public void allocateBuffers() {
		SFPipelineStructure structure=SFPipeline.getStructure(this.structure);
		array=SFPipeline.getSfPipelineMemory().generateStructureData(structure);
	}

	public SFStructureArray getDataArray() {
		return array;
	}

	@Override
	public void deallocateBuffers() {
		// TODO Deallocation is still not supported
	}

	@Override
	public void readFromStream(SFInputStream stream) {
		structure=stream.readString();
	}

	@Override
	public void writeOnStream(SFOutputStream stream) {
		stream.writeString(structure);
	}

	@Override
	public SFDataset generateNewDatasetInstance() {
		return new SFStructuresTable();
	}

	@Override
	public String getCode() {
		return getClass().getSimpleName();
	}

	public String getStructure() {
		return structure;
	}

}
