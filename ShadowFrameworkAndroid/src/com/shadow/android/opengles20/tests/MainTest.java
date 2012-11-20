package com.shadow.android.opengles20.tests;

import java.io.IOException;
import java.io.InputStream;

import shadow.image.SFRenderedTexturesSet;
import shadow.pipeline.builder.SFPipelineBuilder;
import shadow.pipeline.data.SFDataPipelineBuilder;
import shadow.renderer.SFNode;
import shadow.renderer.data.SFDataAsset;
import shadow.renderer.data.utils.SFViewerDatasetFactory;
import shadow.renderer.data.utils.SFViewerObjectsLibrary;
import shadow.system.SFException;
import shadow.system.data.SFDataCenter;
import shadow.system.data.SFInputStream;
import shadow.system.data.java.SFInputStreamJava;
import shadow.system.data.tests.DefaultExceptionKeeper;
import android.app.Activity;
import android.content.res.AssetManager;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.util.Log;

import com.shadow.android.opengles20.SFGL20Pipeline;

public class MainTest extends Activity {

    private GLSurfaceView view;
  
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);    

		loadPipeline();
		
        SFDataCenter.setDatasetFactory(new SFViewerDatasetFactory());
        
        Log.e("MainTest","LoadedData");
        try {
			AssetManager assets = getAssets();
			InputStream stream = assets.open( "test0017b.sf" );
			SFDataCenter.setDataCenterImplementation(new SFViewerObjectsLibrary(stream));
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        //Node
        String assetName="StoneMushroom";
        SFNode node=getAlreadyAvailableNode(assetName);
        view = new SFTestGLES20SurfaceView(this,new SFTestRenderer(node));
        
        //String assetName="MushroomsScene01b";
        //SFRenderedTexturesSet textureSet=getAlreadyAvailableTexture(assetName);
        //SFTexture texture=new SFTexture(textureSet, 0);
        //view = new SFTestGLES20SurfaceView(this,new SFTestTextureRenderer(texture));
        
        setContentView(view);
    }

	private void loadPipeline() {
		SFGL20Pipeline.setup();
		
		try {
			SFDataPipelineBuilder builder2=new SFDataPipelineBuilder();
			AssetManager assets = getAssets();
			InputStream input = assets.open( "test0020.sf" );
			SFInputStream inputStream=new SFInputStreamJava(input, new DefaultExceptionKeeper());
			builder2.readFromStream(inputStream);
			builder2.apply(new SFPipelineBuilder());
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
			
			throw new RuntimeException("Pipeline has not been initialized!!");
		}
	}

	@SuppressWarnings("unchecked")
	private SFRenderedTexturesSet getAlreadyAvailableTexture(String assetName) {
		 SFRenderedTexturesSet texture;
		try {
			/*the warning on-this line is suppressed; IF there is any casting-check problem, it will be 
			 * caught as an SF exception and rightly messaged to this method caller*/
			texture=(SFRenderedTexturesSet)((SFDataAsset<SFRenderedTexturesSet>)SFDataCenter.getDataCenter().getAlreadyAvailableDataset(assetName)).getResource();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SFException("The asset " + assetName + " is not an 'already available' asset");
		}
		return texture;
	}
	
    @SuppressWarnings("unchecked")
	private SFNode getAlreadyAvailableNode(String assetName) {
		SFNode node;
		try {
			/*the warning on-this line is suppressed; IF there is any casting-check problem, it will be 
			 * caught as an SF exception and rightly messaged to this method caller*/
			node=(SFNode)((SFDataAsset<SFNode>)SFDataCenter.getDataCenter().getAlreadyAvailableDataset(assetName)).getResource();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SFException("The asset " + assetName + " is not an 'already available' asset");
		}
		return node;
	}
    
    @Override
    protected void onPause() {
        super.onPause();
        view.onPause();
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        view.onResume();
    }
}