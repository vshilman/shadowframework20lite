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
package shadow.pipeline.parameters;

import java.util.ArrayList;
import java.util.Collection;


///**
// * This class represent a set of parameters.
// * 
// * @author Alessandro Martinelli
// */
//public class SFParametersSet {
//
//	private ArrayList<SFParameteri> set=new ArrayList<SFParameteri>();
//	
//	public void addParameters(Collection<SFParameteri> p){
//		set.addAll(p);
//	}
//	
//	public void addRegisters(Collection<SFPipelineRegister> p){
//		set.addAll(p);
//	}
//	
//	public void add(SFParameteri p){
//		if(!set.contains(p))
//			set.add(p);
//	}
//	
//	public void clear(){
//		set.clear();
//	}
//
//	public Collection<SFParameteri> getSet() {
//		return set;
//	}
//	
//	public SFParameteri getParameter(String name){
//		for (int i = 0; i < set.size(); i++) {
//			if(set.get(i).getName().equalsIgnoreCase(name)){
//				return set.get(i);
//			}
//		}
//		return null;
//	}	
//}