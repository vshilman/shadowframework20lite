#ifndef shadow_renderer_data_H_
#define shadow_renderer_data_H_


class SFPrimitiveData extends SFDataObjectsList<SFPrimitiveData.SFPrimitiveBlockData>{
//
//	static class SFPrimitiveBlockData extends SFCompositeDataArray implements SFWritableDataObject{
//	
//		SFString name;
//		SFShort block;
//		

//		SFCompositeDataArray clone() {
//			return new SFPrimitiveBlockData();
}
//		

//		void generateData() {
//			name=new SFString();
//			block=new SFShort((short)0);
//			addDataObject(name);
//			addDataObject(block);
}
//		

//		String toStringValue() {
//			return "("+SFPrimitiveBlock.getBlock(block.getShortValue())+":"+name.getString()+")";
}
//		

//		void setStringValue(String value) {
//			StringTokenizer tokenizer=new StringTokenizer(value,"(:)",false);
//			String token=tokenizer.nextToken();
//			System.err.println("token "+token);
//			block.setShortValue((short)(SFPrimitiveBlock.valueOf(token).getIndex()));
//			name.setStringValue(tokenizer.nextToken());
}
}
//	
//	
//	static final long serialVersionUID=0;
//	SFPrimitive primitive;
//
//	SFPrimitiveData() {
//		super(new SFPrimitiveBlockData());
}
//
//	SFPrimitive getPrimitive() {
//		if(primitive==null)
//			setupPrimitive();
//		return primitive;
}
//
//	void setPrimitive(SFPrimitive primitive) {
this->primitive = primitive;
//		retrievePrimitiveData();
}
//
//	void setupPrimitive(){
//		
//		if(1==1)
//			throw new SFException("Drop ME!! I'm useless!!");
//		
//		int index=0;
//		//TODO : Bad! STILL BAD; but this is going to be dropped
//		primitive=new SFPrimitive("",SFGridModel.Triangle);
//		
//		try {
//			int size=(size());
//			SFProgramComponent[] components=new SFProgramComponent[size];
//			SFPrimitiveBlock[] blocks=new SFPrimitiveBlock[size];
//			for (int i = 0; i < size; i++) {
//				blocks[i]=SFPrimitiveBlock.getBlock(get(index).block.getShortValue());//SFPipelineRegister.getFromName(primitiveData.get(index).getLabel());
//				components[i]=(SFProgramComponent)SFPipeline.getModule(get(index).name.getString());
//				index++;
}
//			primitive.setPrimitiveElements(blocks, components);
}
//			e.printStackTrace();
}
}
//	
//	void retrievePrimitiveData(){
//		clear();
//		
//		SFProgramComponent[] components=primitive.getComponents();
//		
//		for (int gridIndex = 0; gridIndex < primitive.getGridSize(); gridIndex++) {
//			SFPrimitiveBlockData blockData=new SFPrimitiveBlockData();
//			blockData.name.setString(components[primitive.getGridBlocksIndex(gridIndex)].getName());
//			blockData.block.setShortValue((short)primitive.getBlock(gridIndex).getIndex());
//			add(blockData);
}
}
}
;
}
#endif
