package deferredShading;

import shadow.image.SFPipelineTexture;
import shadow.math.SFVertex3f;
import shadow.pipeline.openGL20.SFGL20Pipeline;
import shadow.utils.SFTutorial;
import deferredShading.DSAlgorithm;

public class DSAlgorithmExample extends SFTutorial{
	
	/*
	 * setup:
	 * carica oggetto da visualizzare, le componenti del colore, le caratteristiche della luce
	 * crea le texture necessarie all'algoritmo 
	 */
	
	String obj = "models/vagone.obj";
	
	SFVertex3f diffColor = new SFVertex3f(1,0,0);
	SFVertex3f ambColor = new SFVertex3f(1,0,0);
	SFVertex3f specColor= new SFVertex3f(1,1,0);
	
	SFVertex3f intensity = new SFVertex3f(1, 1, 1);
	SFVertex3f lPosition = new SFVertex3f(1, 1, -1);
	
	private  SFPipelineTexture texture0; 
	private  SFPipelineTexture texture1;
	private  SFPipelineTexture texture2;
	private  SFPipelineTexture texture3;
	
	public static void main(String[] args) {

		SFGL20Pipeline.setup();
		DSAlgorithmExample example=new DSAlgorithmExample();
		example.prepareFrame("Deferred Shading", 600, 600);
		
	}
	
	@Override
	public void init() {
		
		texture0 = DSAlgorithm.textureSetUp();
		texture1 = DSAlgorithm.textureSetUp();
		texture2 = DSAlgorithm.textureSetUp();
		texture3 = DSAlgorithm.textureSetUp();
		
		/*
		 * primo step dell'algoritmo:salvataggio delle informazioni presenti nel mondo 3D in 4 texture differenti:
		 * 1. colore ambientale e diffuso
		 * 2. colore speculare
		 * 3. vettore posizione
		 * 4. vettore normale
		 * 
		 * oltre alle texture devo caricare il modello 3D da visualizzare e le componenti del colore 
		 */
		
		DSAlgorithm.firstPass(obj,diffColor,ambColor,specColor,texture0,texture1,texture2,texture3);
		
	}
	
	@Override
	public void render() {
		
		/*
		 * secondo step dell'algoritmo: utilizzo le informazione presenti nelle texture precedenti
		 * e le metto in relazione con la posizione e l'intensità della luce
		 * in modo da visualizzare l'immagine finale
		 */
		
		DSAlgorithm.secondPass(intensity,lPosition,texture0,texture1,texture2,texture3);
		
	}
}
