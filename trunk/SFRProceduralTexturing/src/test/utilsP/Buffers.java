package test.utilsP;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

public class Buffers {
	
	public static final int SIZE_OF_FLOAT=4;
	public static final int SIZE_OF_SHORT=2;

	public static ByteBuffer loadFloatBuffer(float[] data){
		
		ByteBuffer buffer=ByteBuffer.allocateDirect(data.length*SIZE_OF_FLOAT);
		buffer.order(ByteOrder.nativeOrder());
		FloatBuffer floatBuffer=buffer.asFloatBuffer();
		floatBuffer.put(data, 0, data.length);
		floatBuffer.rewind();
		return buffer;
	}
	
	
	public static ByteBuffer loadShortBuffer(short[] data){
		
		ByteBuffer buffer=ByteBuffer.allocateDirect(data.length*SIZE_OF_SHORT);
		//sistema la questione little-end big-end
		buffer.order(ByteOrder.nativeOrder());
		ShortBuffer shortBuffer=buffer.asShortBuffer();
		shortBuffer.put(data, 0, data.length);
		//il put inserisce elementi nel buffer. l'indice del buffer segna all'ultimo elemento inserito
		//la rewind riporta l'indice all'inizio
		shortBuffer.rewind();
		return buffer;
	}
}
