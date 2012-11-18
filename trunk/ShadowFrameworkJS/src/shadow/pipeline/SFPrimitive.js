//Java to JS on 21/03/2012


function SFPrimitive(name,gridModel){
	this.setName(name);
	this.gridModel=gridModel;
}

inherit(SFPrimitive,SFProgramModule);

SFPrimitive.prototype["containRegister"]=function(register){
			for(var key in this.blocks){
				if(register==this.blocks[key].getRegister()){
					return true;
				}
			}
			return false;
		};	

SFPrimitive.prototype["setPrimitiveElements"]=function(blocks,components){
			this.blocks=blocks;
			this.components=components;
			this.generateGridInstances();
		};

//Will not be used by viewers
/*SFPrimitive.prototype["addPrimitiveElement"]=function(block,component){
			
			var tmpBlocks=new Array();
			for (var i = 0; i < this.blocks.length; i++) {
				tmpBlocks[i]=this.blocks[i];
			}
			tmpBlocks[this.blocks.length]=block;
			this.blocks=tmpBlocks;
			
			var tmpComponents=new Array();
			for (var i = 0; i < this.components.length; i++) {
				tmpComponents[i]=this.components[i];
			}
			tmpComponents[this.components.length]=component;
			this.components=tmpComponents;
			this.generateGridInstances();
		};
*/

SFPrimitive.prototype["generateGridInstances"]=function(){
			
			var grids=new Array();
			var gridBlocksIndex=new Array();
			var types=new Array();
			
			for (var i = 0; i < this.blocks.length; i++) {
				var grid=this.components[i].getGrid();
				for(var j=0;j<grid.length;j++){
					var sfPipelineGridInstance=grid[j];
					grids.push(sfPipelineGridInstance);
					gridBlocksIndex.push(i);
					var type=sfPipelineGridInstance.getParameterType(0);
					if(type==SFParameteri_GLOBAL_GENERIC){
						type=this.blocks[i].getType();
					}
					types.push(type);
				}
			}
			//C++: delete grids;
			this.grids=grids;
			this.gridBlocksIndex=gridBlocksIndex;
			this.types=types;
			this.generateIndicesSizes();
		};
		

SFPrimitive.prototype["findBlockPosition"]=function(block){
			for (var i = 0; i < this.blocks.length; i++) {
				if(this.blocks[i]==block){
					return i;
				}
			}
			return -1;
		};		


SFPrimitive.prototype["getComponents"]=function(){
			return this.components;
		};			

SFPrimitive.prototype["getGrids"]=function(){
			return this.grids;
		};	

SFPrimitive.prototype["getGridModel"]=function(){
			return this.gridModel;
		};	

SFPrimitive.prototype["getGridsCount"]=function(){
			return this.grids.length;
		};	
		
SFPrimitive.prototype["getBlock"]=function(gridIndex){
			return this.blocks[this.gridBlocksIndex[gridIndex]];
		};	

SFPrimitive.prototype["getGrid"]=function(gridIndex){
			return this.grids[gridIndex];
		};	

SFPrimitive.prototype["getType"]=function(gridIndex){
			return this.types[gridIndex];
		};	

SFPrimitive.prototype["getBlocks"]=function(){
			return this.blocks;
		};		

SFPrimitive.prototype["getBlockR"]=function(register){
		var registerName=register.getName();
		var rName=registerName.split("");
		
		var block=SFPrimitiveBlock_POSITION;
		if(rName[0]=='N'){
			block=SFPrimitiveBlock_NORMAL;
		}else if(rName.length>1 && rName[0]=='T' && rName[1]=='x'){
			block=SFPrimitiveBlock_TXO;
		}else if(rName.length>1 && rName[0]=='d' && rName[1]=='u'){
			block=SFPrimitiveBlock_DU;
		}else if(rName.length>1 && rName[0]=='d' && rName[1]=='v'){
			block=SFPrimitiveBlock_DV;
		}
		return block;
	};		

SFPrimitive.prototype["isQuad"]=function(){
			return this.gridModel===SFGridModel_Quad;
		};	

SFPrimitive.prototype["isLine"]=function(){
			return this.isLine;
		};		


SFPrimitive.prototype["getIndicesPositions"]=function(){
			return this.indicesPositions;
		};	
				

SFPrimitive.prototype["getIndicesCount"]=function(){
			return this.indicesCount;
		};	
		
SFPrimitive.prototype["getIndicesSizes"]=function(){
			return this.indicesSizes;
		};	
		
SFPrimitive.prototype["generateIndicesSizes"]=function(){
			this.indicesPositions=new Array();
			this.indicesSizes=new Array();
			this.indicesCount=0;
			for (var i = 0; i < this.grids.length; i++) {
				this.indicesPositions[i]=this.indicesCount;
				this.indicesSizes[i]=this.grids[i].getGridSize();
				this.indicesCount+=this.indicesSizes[i];
			}
		};	

SFPrimitive.prototype["getConstructionPrimitive"]=function(){
			var primitive=new SFPrimitive("",this.gridModel);
			for (var i = 0; i < this.blocks.length; i++) {
				if(this.blocks[i]==SFPrimitiveBlock_POSITION){
					var components=new Array();
					components[0]=this.components[i];
					var blocks=new Array();
					blocks[0]=SFPrimitiveBlock_UV;
					primitive.setPrimitiveElements(blocks, components);
				}
			}
			
			return primitive;
		};	
		
		