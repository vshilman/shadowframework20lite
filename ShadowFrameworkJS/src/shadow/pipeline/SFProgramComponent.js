//Java to JS on 21/03/2012

function SFProgramComponent(){
	this.code=new Array();
	this.registers=new Array();
	this.temps=new Array();
	this.structures=new Array();
	this.grid=new Array();
}

inherit(SFProgramComponent,SFPipelineElement);

SFProgramComponent.prototype["addRegister"]=function(global){
			if(this.set!=undefined){
				this.set.push(global);
			}
			this.registers.push(global);
		};
		
SFProgramComponent.prototype["addCodeFunction"]=function(func){
			this.code.push(func);
		};

SFProgramComponent.prototype["addGridInstance"]=function(grid){
			this.grid.push(grid);
		};

SFProgramComponent.prototype["addStructureInstance"]=function(structure){
			this.structures.push(structure);
		};

SFProgramComponent.prototype["addParameter"]=function(global){
			if(this.set!=undefined){
				this.set.push(global);
			}
			this.temps.push(global);
		};

SFProgramComponent.prototype["getStructures"]=function(){
			return this.structures;
		};

SFProgramComponent.prototype["getGrid"]=function(){
			return this.grid;
		};

SFProgramComponent.prototype["loadShaderParameters"]=function(set){
			for(var i=0;i<this.registers.length;i++){
				set.push(this.registers[i]);
			}
		};

SFProgramComponent.prototype["getRegisters"]=function(){
			return this.registers;
		};

SFProgramComponent.prototype["getShaderCodeLines"]=function(){
			return this.code;
		};

SFProgramComponent.prototype["getName"]=function(){
			return this.name;
		};

SFProgramComponent.prototype["setName"]=function(name){
			this.name=name;
		};

SFProgramComponent.prototype["getParameterArray"]=function(name){
			return this.getParameterSet();
		};

		
SFProgramComponent.prototype["getParameterSet"]=function(){
			if(this.set===undefined){
			
				this.set=new Array();
				for(var i=0;i<this.registers.length;i++){//AddAll
					this.set.push(this.registers[i]);
				}
				
				for(var i=0;i<this.structures.length;i++){
					var parameters = this.structures[i].getParameters();
					for(var j=0;j<parameters.length;j++){//AddAll
						this.set.push(parameters[j]);
					}
				}
				
				if(this.grid!=undefined){
					for(var i=0;i<this.grid.length;i++){
						var parameters = this.grid[i].getParameters();
						for(var j=0;j<parameters.length;j++){//AddAll
							this.set.push(parameters[j]);
						}
					}
				}
			}
			
			if(this.temps.length!=0){
				for(var i=0;i<this.temps.length;i++){//AddAll
					this.set.push(this.temps[i]);
				}
				this.temps=[];
			}
			return this.set;
		};
	