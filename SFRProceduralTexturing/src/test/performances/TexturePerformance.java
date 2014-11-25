package test.performances;

import java.io.File;
import java.nio.ByteBuffer;

import javax.media.opengl.GL;
import javax.media.opengl.GL2ES2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.JFrame;

import test.utilsP.Buffers;
import test.utilsP.Shader;

import com.jogamp.opengl.util.Animator;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;



public class TexturePerformance {
		
	public static void main(String[] args) {
		
		JFrame frame=new JFrame();
		
		frame.setSize(1920,1060);
		frame.setTitle("Hello Universe");

		//Costruisco un GLEventListener
		GraphicsListener listener=new GraphicsListener();
		//Costruisco un GLCanvas
		GLCanvas canvas = new GLCanvas();
		//Associo il listener al canvas
		canvas.addGLEventListener(listener);
		//Il canvas è un componente grafico!! Posso aggiungerlo al Frame
		frame.getContentPane().add(canvas);
		Animator an = new Animator(canvas);
		an.start();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public static class GraphicsListener implements GLEventListener{
	
		private int texture;
		
		private static String vertexShader="" +
				"attribute vec3 position;" + 
				"attribute vec2 txCoord;" + 
				"varying vec2 vtxCoord;" +
				"void main(){\n" +
				"	gl_Position=vec4(position,1);" +
				"	vtxCoord=txCoord;" +
				"}";
				
	
		private static String fragmentShader="" +
				"uniform sampler2D texture01;" +
				"varying vec2 vtxCoord;" +
				"void main(){\n" +
				//"	gl_FragColor=vec4(vtxCoord,0,1);" +
				"	gl_FragColor=texture2D(texture01, vtxCoord );" +
				"}";		
		private Shader shader;
		
		private int vbo; 
		private int tbo;
		private int attribObject;
		private int attribObject2;
		private int uniformTexture;

	
		public void display(GLAutoDrawable arg0) {
			
			//Take the gl elemnt
			//GL2ES2 va bene sia per sistemi desktop che embedded per android uso GLES2
			GL2ES2 gl=(GL2ES2)arg0.getGL();
			
			//Clear the image
			gl.glClearColor(1, 1, 1, 1);
			
			gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
			
			gl.glUseProgram(shader.getShadingProgram());
			
			//aggiunta
			
			//multitexture
			gl.glUniform1i(uniformTexture, 0);
			gl.glActiveTexture(GL.GL_TEXTURE0);
			gl.glBindTexture(GL.GL_TEXTURE_2D, texture);
			
			gl.glEnableVertexAttribArray(attribObject); //position deve essere attivato
			gl.glEnableVertexAttribArray(attribObject2);
			gl.glBindBuffer(GL.GL_ARRAY_BUFFER, vbo);
			//aggancio "attribObject" (che qui è la position) all'ultimo buffer su cui ho fatto il bind precisando come vanno interpretati i dati
			gl.glVertexAttribPointer(attribObject, 3, GL2ES2.GL_FLOAT, false, 0, 0); 
			gl.glBindBuffer(GL.GL_ARRAY_BUFFER, tbo);
			gl.glVertexAttribPointer(attribObject2, 2, GL2ES2.GL_FLOAT, false, 0, 0); 
			
			//disegna una certa primitiva usando tutti i vertici disponibili dando l'indice di partenza 
			//dei vertici e il numero di vertici da utilizzare. Draw arrays non usa la mesh indicizzata
			gl.glDrawArrays(GL2ES2.GL_TRIANGLE_STRIP, 0, 4);
			
		}
		@Override
		public void dispose(GLAutoDrawable arg0) {
			// TODO Auto-generated method stub
			
		}

		
		public void init(GLAutoDrawable arg0) {
			//INIT SHADER
			//Take the gl elemnt
			GL2ES2 gl=(GL2ES2)arg0.getGL();	
			shader=new Shader(fragmentShader, vertexShader);
			shader.compileShader(gl);
			
			
			try {
				long start = System.nanoTime();
				Texture t = TextureIO.newTexture(new File("images/Brick1920x1060.jpg"),true);
				long end = System.nanoTime();
				System.err.println("nanoTime: "+ (end - start));
				System.err.println("Time: "+ (end - start)/1000000000);
				texture=t.getTextureObject(gl);
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			
			//per accedere a queste proprietà dello shader devo recuperare l'ID
			//dato l'id del programma e il nome dell'attributo recupero l'id col quale lo shader si ricorda di 
			//un certo attributo
			attribObject=gl.glGetAttribLocation(shader.getShadingProgram(), "position");
			
			uniformTexture=gl.glGetUniformLocation(shader.getShadingProgram(), "texture01");
			attribObject2=gl.glGetAttribLocation(shader.getShadingProgram(), "txCoord");
			
			
			//INIT BUFFER OBJ
			int[] vbos = new int[1];
			int[] tbos = new int[1];
			
			//crea strutture dati in memoria
			//1 = numero buffer da generare; vbos= vettore di allocazione; 
			//0=primo buffer va nel primo elemento del vettore
			gl.glGenBuffers(1, vbos, 0);
			gl.glGenBuffers(1, tbos, 0);
			
			this.vbo=vbos[0];
			this.tbo=tbos[0];
			
			//i 3 vertici
			float[] data={
				-1,-1,0,
				1,-1,0,
				-1,1,0,
				1,1,0,

			};
			float[] texCoord={
					0,1,
					1,1,
					0,0,
					1,0,
				};
			
			
			
			//l'oggetto buffer ha una libreria che fornisce un'interfaccia verso buffer di basso livello (più efficiente)
			//la classe buffer trasforma i vettori in buffer
			ByteBuffer byteData=Buffers.loadFloatBuffer(data);
			ByteBuffer byteTexCoord=Buffers.loadFloatBuffer(texCoord);
			
			gl.glBindBuffer(GL.GL_ARRAY_BUFFER, this.vbo);
			gl.glBufferData(GL.GL_ARRAY_BUFFER, data.length*Buffers.SIZE_OF_FLOAT, byteData, GL.GL_STATIC_DRAW);
			
			gl.glBindBuffer(GL.GL_ARRAY_BUFFER, this.tbo);
			gl.glBufferData(GL.GL_ARRAY_BUFFER, texCoord.length*Buffers.SIZE_OF_FLOAT, byteTexCoord, GL.GL_STATIC_DRAW);
			
			
			
		}

		public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {

		}
	}
	static final long serialVersionUID=100;



	
	
}
