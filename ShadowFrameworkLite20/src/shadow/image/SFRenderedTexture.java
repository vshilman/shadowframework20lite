
package shadow.image;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Alessandro
 */
public class SFRenderedTexture {

	private SFBufferData depthBuffer;
	private SFBufferData stencilBuffer;
	private List<SFBufferData> colorsData=new LinkedList<SFBufferData>();
	
	public SFRenderedTexture() {
		super();
	}

	public SFRenderedTexture(List<SFBufferData> colorsData) {
		super();
		this.colorsData.addAll(colorsData);
	}

	public SFRenderedTexture(SFBufferData depthBuffer,
			List<SFBufferData> colorsData) {
		super();
		this.depthBuffer = depthBuffer;
		this.colorsData.addAll(colorsData);
	}

	public SFRenderedTexture(SFBufferData depthBuffer,
			SFBufferData stencilBuffer, List<SFBufferData> colorsData) {
		super();
		this.depthBuffer = depthBuffer;
		this.stencilBuffer = stencilBuffer;
		this.colorsData.addAll(colorsData);
	}

	public void setDepthBuffer(SFBufferData depthBuffer) {
		this.depthBuffer = depthBuffer;
	}

	public void setStencilBuffer(SFBufferData stencilBuffer) {
		this.stencilBuffer = stencilBuffer;
	}

	public void addColorData(SFBufferData colorData) {
		this.colorsData.add(colorData);
	}

	public SFBufferData getDepthBuffer() {
		return depthBuffer;
	}

	public SFBufferData getStencilBuffer() {
		return stencilBuffer;
	}

	public List<SFBufferData> getColorsData() {
		return colorsData;
	}
	
}
