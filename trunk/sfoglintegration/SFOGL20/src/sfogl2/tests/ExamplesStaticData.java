package sfogl2.tests;


/**
 * TODO : Mac GPU hates the precision identifier.
 * That's boring :(
 * 
 * @author Alessandro Martinelli
 */
public class ExamplesStaticData {
	
	public static String vertexShader01="" +
			"attribute vec3 position;" +
			"void main(){\n" +
			"	gl_Position=vec4(position,1);" +
			"}";
			
	public static String fragmentShader01="" +
			"uniform vec4 color;" +
			"void main(){\n" +
			"	gl_FragColor=color;" +
			"}";
	

	public static String vertexShader02="" +
			"uniform mat4 transform;" +
			"attribute vec3 position;" +
			"attribute vec3 normal;" +
			"varying float light;" +
			"void main(){" +
			"	gl_Position=transform*vec4(position,1);" +
			"	light=dot(normal,vec3(0,0.6,0))+0.4;" +
			"}";
			
	public static String fragmentShader02="" +
			"uniform vec4 color;" +
			"varying float light;" +
			"void main(){" +
			"	gl_FragColor=color*vec4(vec3(light),1);" +
			"}";	

	public static String textureVertexShader="" +
			"attribute vec3 position;" +
			"attribute vec2 txCoord;" +
			"varying vec2 texCoord;" +
			"void main(){" +
			"	gl_Position=vec4(position,1);" +
			"	texCoord=txCoord;" +
			"}";
			
	public static String textureFragmentShader="" +
			"uniform sampler2D texture;" +
			"varying vec2 texCoord;" +
			"void main(){" +
			"	gl_FragColor=texture2D(texture,texCoord);" +
			"}";	
	
	
	
	public static String blurFragmentShader=""+
	"uniform sampler2D texture;\n"+
	"uniform float texelSize;\n"+
	"varying vec2 texCoord;\n"+
	"vec4 readTexture(vec2 delta){"+
	"   vec4 color=texture2D(texture,texCoord+delta*vec2(texelSize));"+
	"   return color;"+
	"}"+
	"void main(){\n"+
	"	vec4 grayLL=readTexture(vec2(-1.0,-1.0));"+
	"	vec4 grayL0=readTexture(vec2(-1.0,0.0));"+
	"	vec4 grayLP=readTexture(vec2(-1.0,1.0));"+
	"	vec4 gray0L=readTexture(vec2(0.0,-1.0));"+
	"	vec4 gray00=readTexture(vec2(0.0,0.0));"+
	"	vec4 gray0P=readTexture(vec2(0.0,1.0));"+
	"	vec4 grayPL=readTexture(vec2(1.0,-1.0));"+
	"	vec4 grayP0=readTexture(vec2(1.0,0.0));"+
	"	vec4 grayPP=readTexture(vec2(1.0,1.0));"+
	"	vec4 gray= vec4(0.0625)*(grayLL+grayLP+grayPL+grayPP)+vec4(0.125)*(grayL0+grayP0+gray0P+gray0L)+vec4(0.25)*(gray00) ;"+
	"	vec4 color=gray;"+
	//"	gl_FragColor=vec4(1.0,0.0,0.0,1.0);\n"+
	"	gl_FragColor=vec4(color.xyz,1.0);"+
	"}";

	public static String blurVertexShader=""+
			"attribute vec3 position;\n"+
			"attribute vec2 txCoord;\n"+
			"varying vec2 texCoord;\n"+
			"void main(){\n"+
			"	gl_Position=vec4(position,1);\n"+
			"	texCoord=(position.xy+vec2(1.0))*vec2(0.5);\n"+
			"}";
	
	
	public final static float[] triangleVertices={
		0,0,0,
		1,0,0,
		0,1,0
	};
	
	public final static float[] quadVertices={
		0,0,0,
		0.8f,0,0,
		0,0.9f,0,
		0.6f,0.7f,0
	};
	
	public final static short[] quadIndices={
		0,1,2,1,2,3	
	};

	public final static float[] textureQuadVertices={
		0,0,0,
		1.0f,0,0,
		0,1.0f,0,
		1.0f,1.0f,0
	};
	
	public final static float[] screenQuadVertices={
		-1,-1,0,
		1.0f,-1,0,
		-1,1.0f,0,
		1.0f,1.0f,0
	};
	
	public final static float[] cubeVertices={
			-1,-1,-1,
			-1,1,-1,
			1,-1,-1,
			1,1,-1,
			
			-1,-1,1,
			-1,1,1,
			1,-1,1,
			1,1,1,
			
			-1,-1,-1,
			-1,-1,1,
			-1,1,-1,
			-1,1,1,
			
			1,-1,-1,
			1,-1,1,
			1,1,-1,
			1,1,1,
			
			-1,-1,-1,
			1,-1,-1,
			-1,-1,1,
			1,-1,1,
			
			-1,1,-1,
			1,1,-1,
			-1,1,1,
			1,1,1,
			
			
		};
		
	public final static float[]  cubeNormals={
			0,0,-1,
			0,0,-1,
			0,0,-1,
			0,0,-1,
			0,0,1,
			0,0,1,
			0,0,1,
			0,0,1,
			
			-1,0,0,
			-1,0,0,
			-1,0,0,
			-1,0,0,
			1,0,0,
			1,0,0,
			1,0,0,
			1,0,0,
			
			0,-1,0,
			0,-1,0,
			0,-1,0,
			0,-1,0,
			
			0,1,0,
			0,1,0,
			0,1,0,
			0,1,0,
			
		};
		
	public final static short[] cubeIndices={
			0,1,2,1,2,3,
			4,5,6,5,6,7,
			8,9,10,9,10,11,
			12,13,14,13,14,15,
			16,17,18,17,18,19,
			20,21,22,21,22,23
		};
	
	public final static float[] textureVertices={
		-1,-1, 1,-1, -1,1, 1,1
	};
	
	public final static float[] textureTexCoord={
		0,0, 1,0, 0,1, 1,1
	};
	
	public static int[][] framBuffersViewports={
		{50,50,256,256},
		{350,50,256,256},
		{650,50,256,256},
		{50,350,256,256},
		{350,350,256,256},
		{650,350,256,256},
	};
	
	public static int[][] framBuffersSizes={
		{256,256},
		{128,128},
		{64,64},
		{32,32},
		{16,16},
		{8,8},
	};
}
