
function SFOutputStreamJava(stream, keeper){
		this.stream=stream;
		this.keeper=keeper;
}

SFOutputStreamJava.prototype = {

	writeString:function(s){
	writeInt(s.length());//Warning: Not well Identified 
	writeBytes(s.getBytes());//Warning: Not well Identified 
	}

};