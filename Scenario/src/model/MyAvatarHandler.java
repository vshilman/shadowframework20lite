package model;
import client.SFDataUtils;
import client.SFViewerObjectsLibrary;
import controller.*;
import commons.*;
import interfaces.*;
import viewer.*;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import shadow.math.SFMatrix3f;
import shadow.math.SFVertex3f;
// states
// 1: connected
// 2: helo received
// 3: position received
import shadow.renderer.SFNode;
import shadow.system.data.SFDataCenter;

public class MyAvatarHandler implements InputObserver{

	private static final MyAvatarHandler instance = new MyAvatarHandler();
	private static int myId;
	private static SFNode myNode;
	private static String myName;
	private static float inc = 0;
	private static SFVertex3f myPosition;
	private static SFMatrix3f myDirection;
	private static ArrayList<ModelMyAvatar> objList = new ArrayList<ModelMyAvatar>();
	boolean connected = false;
	
	private MyAvatarHandler() {
	
	
	}

	public static MyAvatarHandler getInstance() {
		return instance;
	}

	
	
	public static MyAvatarHandler getInstance(ModelMyAvatar me) {
		
		objList.add(me);
		return instance;

	}

	public int getMyId() {

		return myId;

	}

	public void setMyId(int id) {

		myId = id;
		

	}

	
	
public void setState(SFVertex3f pos, SFMatrix3f dir){
		
		myPosition = pos;
		myDirection = dir;
		myNode.getTransform().setPosition(pos);
		myNode.getTransform().setOrientation(dir);
	}
	
	public SFVertex3f getMyPosition() {

		return myPosition;

	}
	public SFMatrix3f getMyDirection() {

		return myDirection;
	}

	public void setNode(SFNode node){
		
		this.myNode = node;
		
	}
	
	public SFNode getNode(){
		
		
		return this.myNode;
		
	}
	
	public void createNode(){
		
		
		   SFViewerObjectsLibrary library = new SFViewerObjectsLibrary("/", "test0610.sf");
			SFDataCenter.setDataCenterImplementation(library);
		myNode = (SFDataUtils.getResource("Dummy01"));
	   myNode.getTransform().setPosition(myPosition);
	myNode.getTransform().setOrientation(myDirection);
		
	}
	
	public void setMyPosition(SFVertex3f pos){
		myPosition = pos;
		inc = myPosition.getZ();

	//	myNode.getTransform().setPosition(new SFVertex3f(0,0,inc));
		
	}
public void setMyDirection(SFMatrix3f dir){
		
		myDirection = dir;
		myNode.getTransform().setOrientation(dir);
	}
	
	public void setMyInitialPosAndDir(SFVertex3f position, SFMatrix3f direction) {
		
		myPosition = position;
		myDirection = direction;
		setConnected(true);
	   notifyConnection();
	}

	public boolean isConnected(){
		
		return connected;
		
	}
	public void setConnected(boolean flag){
		
		connected = flag;
		createNode();
	
	}

	@Override
	public void keyUpdate(KeyEvent e) {
	
float code = e.getKeyCode();
		
		if(code == KeyEvent.VK_UP){

		    SFVertex3f vertex1 = new SFVertex3f(0,0,1);
		    getMyPosition().addMult(0.1f, getMyDirection().Mult(vertex1));
		    getNode().getTransform().setPosition(getMyPosition());
		   SFVertex3f vertex = new SFVertex3f();
		   getNode().getTransform().getPosition(vertex);
		   
		    
			
		}else if( code == KeyEvent.VK_DOWN){
			
				

		    SFVertex3f vertex1 = new SFVertex3f(0,0,1);
		    getMyPosition().addMult(-0.1f, getMyDirection().Mult(vertex1));
		    getNode().getTransform().setPosition(getMyPosition());
		    //SFVertex3f vertex = new SFVertex3f(x,y,z);
		    //setMyPosition(vertex);
			   System.out.println(getMyPosition());

		  	 	
		}else if( code == KeyEvent.VK_RIGHT){
			
			 SFMatrix3f direction = new SFMatrix3f();
		     SFMatrix3f matrix = SFMatrix3f.getRotationY((float)( Math.PI*0.01f));
			 direction = getMyDirection();
			 direction = matrix.Mult(direction);
		     setMyDirection(direction);
				 
		    
		}else if( code == KeyEvent.VK_LEFT){
		
		   	SFMatrix3f direction = new SFMatrix3f();
			 SFMatrix3f matrix = SFMatrix3f.getRotationY((float)(-Math.PI*0.01f));
			 direction = getMyDirection();
			 direction = matrix.Mult(direction);
			 setMyDirection(direction);
			 
		}else if(code == KeyEvent.VK_A){
			
			getNode().getTransform().setPosition(new SFVertex3f(0,0,-20));
			
		}
	
		
	notifyChgState(code);	
		
		
	}
	
public void notifyChgState(float code){
		
		for (ModelMyAvatar obs : objList) {
			
			obs.notifyChgState(code);

		}
	}

public void notifyConnection(){
	
	for (ModelMyAvatar obs : objList) {
		
		obs.notifyConnection();

	}
}

	@Override
	public void mouseUpdate(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}
