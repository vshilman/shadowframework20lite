


function SFBasisSpline2(closed){
	this.vertices=new Array();
	this.closed=closed;
}

inherit(SFBasisSpline2,SFUnOptimizedCurve);//TODO: verify constructors

SFBasisSpline2.prototype["getControlPointSize"]=function(){
			return this.vertices.length;
		};


SFBasisSpline2.prototype["getControlPoint"]=function(index){
			return this.vertices[i];
		};

SFBasisSpline2.prototype["getVertices"]=function(){
			return this.vertices;
		};

SFBasisSpline2.prototype["add"]=function(value){
			this.vertices.push(value);
		};

SFBasisSpline2.prototype["generateValue"]=function(){
			return new SFValuenf(this.vertices[0].get().length);
		};

SFBasisSpline2.prototype["getDev2Dt"]=function(T,write){
			var vertices=this.vertices;
			
			var v_index=Math.floor(T*vertices.length);
			if(v_index==vertices.length)
				v_index--;
			if(v_index<0)
				v_index=0;
			
			if(this.closed){
				
				var indexl1=v_index>0?v_index-1:vertices.length-1;
				var indexm1=v_index<vertices.length-1?v_index+1:0;
				
				var A=SFValuenf_middle(vertices[indexl1], vertices[v_index]);
				var B=vertices[v_index];
				var C=SFValuenf_middle(vertices[v_index], vertices[indexm1]);
				
				write.setValue(A);
				write.mult(2);
				write.addMult3f(-4, B);
				write.addMult3f(2, C);
			}else{
				if(v_index==0){
					write.mult(0);
				}else if(v_index==vertices.length-1){
					write.mult(0);
				}else{
					var A=SFValuenf_middle(vertices[v_index-1], vertices[v_index]);
					var B=vertices[v_index];
					var C=SFValuenf_middle(vertices[v_index], vertices[v_index+1]);
					write.setValue(A);
					write.mult(2);
					write.addMult3f(-4, B);
					write.addMult3f(2, C);
				}
			}
		};

SFBasisSpline2.prototype["getDevDt"]=function(T,write){
			var vertices=this.vertices;
			
			var v_index=Math.floor(T*vertices.length);
			if(v_index==vertices.length)
				v_index--;
			if(v_index<0)
				v_index=0;
			
			var t=(T*vertices.length)-v_index;
			
			if(this.closed){
				
				var indexl1=v_index>0?v_index-1:vertices.length-1;
				var indexm1=v_index<vertices.length-1?v_index+1:0;
				
				var A=SFValuenf_middle(vertices[indexl1], vertices[v_index]);
				var B=vertices[v_index];
				var C=SFValuenf_middle(vertices[v_index], vertices[indexm1]);
				
				write.setValue(A);
				write.mult(-2*(1-t));
				write.addMult3f(2-4*t, B);
				write.addMult3f(2*t, C);
			}else{
				if(v_index==0){
					var A=vertices[0];
					var B=SFValuenf_middle(vertices[0],vertices[1]);
					write.setValue(B);
					write.subtract3f(A);
					
				}else if(v_index==vertices.length-1){
					var A=SFValuenf_middle(vertices[v_index-1],vertices[v_index]);
					var B=vertices[v_index];
					write.setValue(B);
					write.subtract3f(A);
				}else{
					var A=SFValuenf_middle(vertices[v_index-1], vertices[v_index]);
					var B=vertices[v_index];
					var C=SFValuenf_middle(vertices[v_index], vertices[v_index+1]);
					write.setValue(A);
					write.mult(-2*(1-t));
					write.addMult3f(2-4*t, B);
					write.addMult3f(2*t, C);
				}
			}
			
		};

SFBasisSpline2.prototype["getVertex"]=function(T,write){
			var vertices=this.vertices;
			
			var v_index=Math.floor(T*vertices.length);
			if(v_index==vertices.length)
				v_index--;
			if(v_index<0)
				v_index=0;
			
			var t=(T*vertices.length)-v_index;
			
			if(this.closed){
				
				var indexl1=v_index>0?v_index-1:vertices.length-1;
				var indexm1=v_index<vertices.length-1?v_index+1:0;
				
				var A=SFValuenf_middle(vertices[indexl1], vertices[v_index]);
				var B=vertices[v_index];
				var C=SFValuenf_middle(vertices[v_index], vertices[indexm1]);
				
				write.setValue(A);
				write.mult((1-t)*(1-t));
				write.addMult3f(2*t*(1-t), B);
				write.addMult3f(t*t, C);
				
			}else{
				if(v_index==0){
					var A=vertices[0];
					var B=SFValuenf_middle(vertices[0],vertices[1]);
					write.setValue(A);
					write.mult(1-t);
					write.addMult3f(t, B);
				}else if(v_index==vertices.length-1){
					
					var A=SFValuenf_middle(vertices[v_index-1],vertices[v_index]);
					var B=vertices[v_index];
					write.setValue(A);
					write.mult(1-t);
					write.addMult3f(t, B);
				}else{
			
					var A=SFValuenf_middle(vertices[v_index-1], vertices[v_index]);
					var B=vertices[v_index];
					var C=SFValuenf_middle(vertices[v_index], vertices[v_index+1]);
					
					write.setValue(A);
					write.mult((1-t)*(1-t));
					write.addMult3f(2*t*(1-t), B);
					write.addMult3f(t*t, C);
				}
			}
			
		};

SFBasisSpline2.prototype["getTMax"]=function(){
			return 1;
		};

SFBasisSpline2.prototype["getTMin"]=function(){
			return 0;
		};

SFBasisSpline2.prototype["init"]=function(){
			
		};

SFBasisSpline2.prototype["destroy"]=function(){
			
		};
	