package model;
import controller.*;
import commons.*;
import interfaces.*;
import viewer.*;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

import shadow.math.SFMatrix3f;
import shadow.math.SFVertex3f;
import shadow.renderer.SFNode;


public class AvatarsHandler{
	private static final AvatarsHandler instance = new AvatarsHandler();
	ConcurrentHashMap<Integer, CharacterDef> mapChar = new ConcurrentHashMap<Integer, CharacterDef>();
	private static ArrayList<ModelAvatars> objList = new ArrayList<ModelAvatars>();

	
	private AvatarsHandler() {
	
	}

	public static AvatarsHandler getInstance() {
		
		return instance;
		
	}
public static AvatarsHandler getInstance(ModelAvatars me) {
		
		objList.add(me);
		return instance;

	}
	
	
	public void setPositionAt(int id, SFVertex3f position, SFMatrix3f direction){
		
			if (mapChar.containsKey(id)) {

			mapChar.get(id).setState(position, direction);
			notifyChgState(id);
			
		} else{

			CharacterDef character = new CharacterDef();

			character.setPosition(position);
			character.setDirection(direction);
			character.createNode();

			
			mapChar.put(id, character);
			notifyNewChar(id);
		}
		
		
	}
	
	public SFVertex3f getPositionAt(int id){
		
		SFVertex3f position = null;
		
		if (mapChar.containsKey(id)) {

		position = mapChar.get(id).getPosition();
		}	
	
		return position;
	}
	
	public SFNode getNodeAt(int id){
		SFNode node = null;
		if (mapChar.containsKey(id)) {

			node = mapChar.get(id).getNode();
		
		}	
		
		return node;	

		
		
	}
	
	public SFMatrix3f getDirectionAt(int id){
		
		SFMatrix3f direction = null;
		
		if (mapChar.containsKey(id)) {

		direction = mapChar.get(id).getDirection();
		}	
	
		return direction;
	}
	
	
	public void notifyRemoveChar(int id){
		
		for (ModelAvatars obs : objList) {
			
			obs.notifyRemoveChar(id);

		}
	}
	
	
	public void notifyNewChar(int id){
		for (ModelAvatars obs : objList) {
			
			obs.notifyNewChar(id);

		}
	}
		public void notifyChgState(int id){
			
			for (ModelAvatars obs : objList) {
				obs.notifyChgState(id);
			}

		
	}
	
	public  ConcurrentHashMap<Integer, CharacterDef> getAllChar(){
		
		return mapChar;
		
	}
	
	
	public void removeChar(int id){
	
		if (mapChar.containsKey(id)) {
			mapChar.remove(id);
			notifyRemoveChar(id);
			
		}
		
	}

	
}
