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

import java.util.LinkedList;

/**
 * This is a singleton class which can store data which need to be initialized.
 * 
 * @author Alessandro Martinelli
 */
public class SFInitiator {

	private static SFInitiator initiator=new SFInitiator();

	private LinkedList<SFInitiable> initiables=new LinkedList<SFInitiable>();
	private LinkedList<SFInitiable> destroyables=new LinkedList<SFInitiable>();

	private SFInitiator() {
	}

	public synchronized static void addInitiable(SFInitiable initiable) {
		if (!initiator.initiables.contains(initiable)){
			//System.out.println("initializing "+initiable);
			initiator.initiables.add(initiable);
		}
	}
	
	public synchronized static void addDestroyable(SFInitiable initiable) {
		if (!initiator.destroyables.contains(initiable)){
			//System.out.println("initializing "+initiable);
			initiator.destroyables.add(initiable);
		}
	}

	public synchronized static void solveInitiables() {
		for (SFInitiable destroyable : initiator.destroyables) {
			destroyable.destroy();
		}
		initiator.destroyables.clear();
		for (SFInitiable initiable : initiator.initiables) {
			initiable.init();
		}
		initiator.initiables.clear();
	}
}
