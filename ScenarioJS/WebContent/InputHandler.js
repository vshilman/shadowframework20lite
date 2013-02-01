function InputHandler(){
	
    if ( InputHandler.instance )
		    return InputHandler.instance;

    InputHandler.instance = this;
	this.obj = [];
   
	}


InputHandler.prototype.keyListen = function(){
	
	  var _scope = this;
	  
	    window.addEventListener('keypress', function(event) {
	   
	    
	    	_scope.obj.forEach(function(item){
	    		
	    		item.Update(event);
	    		
	    	});
	    });
	
	
};


InputHandler.prototype.addObserver = function(object){
	

	
		this.obj.push(object);
	
	
	
};

