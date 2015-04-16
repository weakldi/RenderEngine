package renderengine.core;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;

public class Buffers {
	/**
	 * Erstellt aus einem Floatarray ein Floatbuffer.
	 * 
	 * @param data
	 * @return
	 */
	public static FloatBuffer genFlippedFloatBuffer(float[] data){
		FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}
	/**
	 * Erstellt aus einem Intarray ein Intbuffer.
	 * 
	 * @param data
	 * @return
	 */
	public static IntBuffer genFlippedIntBuffer(int[] data){
		IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}
}
