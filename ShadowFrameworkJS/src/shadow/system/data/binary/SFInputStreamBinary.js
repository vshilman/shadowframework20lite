
function SFInputStreamBinary(buffer) {
	this.buffer = buffer;
	this.pos = 0;
};

	
SFInputStreamBinary.prototype["_getInt8"]=function(){
			var b = this._getByte(this.pos);
			return b > Math.pow(2, 7) - 1 ? b - Math.pow(2, 8) : b;
		};

SFInputStreamBinary.prototype["_getByte"]=function(){
			var byte=this.buffer.charCodeAt(this.pos) & 0xff;
			//alert(this.buffer.charCodeAt(this.pos)+" '"+this.buffer.charAt(this.pos)+"'");
			this.pos++;
			return byte;
		};
		
SFInputStreamBinary.prototype["readString"]=function(){
			var length = this._getInt8();
			var value  = '';
			for (var i = 0; i < length; ++i) {
				var char = this._getByte(this.pos);
				value += String.fromCharCode(char > 127 ? 65533 : char);
			}
			alert("readString "+value);
	
			return value;
		};
		
SFInputStreamBinary.prototype["readName"]=function(){
			return this.readString();
		};

SFInputStreamBinary.prototype["readByte"]=function(){
			var b_byte=this._getByte();
			alert("readByte "+b_byte);
			return b_byte;
		};
				
SFInputStreamBinary.prototype["readShort"]=function(){
			var b1 = this._getByte(this.pos+0),
				b0 = this._getByte(this.pos+1);
			var b= (b1 << 8) + b0;
			var _short= b > Math.pow(2, 15) - 1 ? b - Math.pow(2, 16) : b;
			alert("readShort "+_short);
			return _short;
		};


SFInputStreamBinary.prototype["readInt"]=function(){
			var b3 = this._getByte(this.pos+0),
				b2 = this._getByte(this.pos+1),
				b1 = this._getByte(this.pos+2),
				b0 = this._getByte(this.pos+3);
			var b = (b3 * Math.pow(2, 24)) + (b2 << 16) + (b1 << 8) + b0;
			var _int= b > Math.pow(2, 31) - 1 ? b - Math.pow(2, 32) : b;
			alert("readInt "+_int);
			return _int;
		};


SFInputStreamBinary.prototype["readFloat"]=function(){
			var b0 = this._getByte(this.pos+0),
				b1 = this._getByte(this.pos+1),
				b2 = this._getByte(this.pos+2),
				b3 = this._getByte(this.pos+3),
	
				sign = 1 - (2 * (b0 >> 7)),
				exponent = (((b0 << 1) & 0xff) | (b1 >> 7)) - 127,
				mantissa = ((b1 & 0x7f) << 16) | (b2 << 8) | b3;
	
			if (exponent === 128) {
				if (mantissa !== 0) {
					return NaN;
				} else {
					return sign * Infinity;
				}
			}
	
			if (exponent === -127) { // Denormalized
				return sign * mantissa * Math.pow(2, -126 - 23);
			}
	
			return sign * (1 + mantissa * Math.pow(2, -23)) * Math.pow(2, exponent);
		};


SFInputStreamBinary.prototype["readShorts"]=function(n){
			var array=new Array();
			for(var i=0;i<n;i++){
				array[i]=this.readShort();
			}
			return array;
		};

SFInputStreamBinary.prototype["readInts"]=function(n){
			var array=new Array();
			for(var i=0;i<n;i++){
				array[i]=this.readInt();
			}
			return array;
		};
		
SFInputStreamBinary.prototype["readFloats"]=function(n){
			var array=new Array();
			for(var i=0;i<n;i++){
				array[i]=this.readFloat();
			}
			return array;
		};
		
SFInputStreamBinary.prototype["readBytes"]=function(n){
			var array=new Array();
			for(var i=0;i<n;i++){
				array[i]=this._getByte();
			}
			return array;
		};
		
SFInputStreamBinary.prototype["readBinaryData"]=function(n,bitSize){
			var byteSize = ((bitSize - 1) >> 3) + 1;
			var bytes = this.readBytes(n * byteSize);
			var data = new Array();
	
			for (var i = 0; i < n; i++) {
				var value = 0;
				for (var j = 0; j < byteSize; j++) {
					//read Bytes will extract positive values...
					value += bytes[i * byteSize + j];
				}
				data[i] = value;
			}
			alert("readBinaryData (bytes )"+bytes);
			alert("readBinaryData"+data);
	
			return data;
		};


