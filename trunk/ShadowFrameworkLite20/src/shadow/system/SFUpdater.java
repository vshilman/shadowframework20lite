/*
	The Shadow Framework 1.0 Lead Version - a complete framework for Real Time Graphics based on OpenGL 2.0
    Copyright (C) 2010  Alessandro Martinelli  <alessandro.martinelli@unipv.it>

    This file is part of The Shadow Framework.

    The Shadow Framework is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    The Shadow Framework is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with Shadow Framework.  If not, see <http://www.gnu.org/licenses/>.
*/
package shadow.system;

import java.util.ArrayList;
import java.util.Iterator;


/**
 * This is a class which can store data which need to be updated.
 * 
 * @author Alessandro Martinelli
 */
public class SFUpdater{
	
	private static SFUpdater updater=new SFUpdater();

	private ArrayList<SFUpdatable> updatables=new ArrayList<SFUpdatable>();
	
	private SFUpdater(){
		
	}
	
	public static void addUpdatable(SFUpdatable updatable){
		if(!updater.updatables.contains(updatable))
			updater.updatables.add(updatable);
	}
	
	public static void removeUpdatable(SFUpdatable updatable){
		if(updater.updatables.contains(updatable))
			updater.updatables.remove(updatable);
	}
	
	public static void refresh(){
		
		for (Iterator<SFUpdatable> iterator=updater.updatables.iterator(); iterator
				.hasNext();) {
			SFUpdatable updatable=(SFUpdatable) iterator.next();
			updatable.update();
		}
		
		updater.updatables.clear();
	}
}
