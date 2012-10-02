package shadow.renderer.data;


//public class SFPrimitiveData extends SFDataObjectsList<SFPrimitiveData.SFPrimitiveBlockData>{
//
//	protected static class SFPrimitiveBlockData extends SFCompositeDataArray implements SFWritableDataObject{
//	
//		public SFString name;
//		public SFShort block;
//		
//		@Override
//		public SFCompositeDataArray clone() {
//			return new SFPrimitiveBlockData();
//		}
//		
//		@Override
//		public void generateData() {
//			name=new SFString();
//			block=new SFShort((short)0);
//			addDataObject(name);
//			addDataObject(block);
//		}
//		
//		@Override
//		public String toStringValue() {
//			return "("+SFPrimitiveBlock.getBlock(block.getShortValue())+":"+name.getString()+")";
//		}
//		
//		@Override
//		public void setStringValue(String value) {
//			StringTokenizer tokenizer=new StringTokenizer(value,"(:)",false);
//			String token=tokenizer.nextToken();
//			System.err.println("token "+token);
//			block.setShortValue((short)(SFPrimitiveBlock.valueOf(token).getIndex()));
//			name.setStringValue(tokenizer.nextToken());
//		}
//	}
//	
//	
//	private static final long serialVersionUID=0;
//	private SFPrimitive primitive;
//
//	public SFPrimitiveData() {
//		super(new SFPrimitiveBlockData());
//	}
//
//	public SFPrimitive getPrimitive() {
//		if(primitive==null)
//			setupPrimitive();
//		return primitive;
//	}
//
//	public void setPrimitive(SFPrimitive primitive) {
//		this.primitive = primitive;
//		retrievePrimitiveData();
//	}
//
//	private void setupPrimitive(){
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
//			}
//			primitive.setPrimitiveElements(blocks, components);
//		} catch (SFException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	private void retrievePrimitiveData(){
//		clear();
//		
//		SFProgramComponent[] components=primitive.getComponents();
//		
//		for (int gridIndex = 0; gridIndex < primitive.getGridSize(); gridIndex++) {
//			SFPrimitiveBlockData blockData=new SFPrimitiveBlockData();
//			blockData.name.setString(components[primitive.getGridBlocksIndex(gridIndex)].getName());
//			blockData.block.setShortValue((short)primitive.getBlock(gridIndex).getIndex());
//			add(blockData);
//		}
//	}
//}
