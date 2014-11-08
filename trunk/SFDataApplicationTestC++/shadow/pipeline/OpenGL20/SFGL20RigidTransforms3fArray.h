//
//  SFGL20RigidTransforms3fArray.h
//  
//
//  Created by Alessandro Martinelli on 22/10/12.
//  Copyright (c) 2012 Alessandro Martinelli. All rights reserved.
//

#ifndef SFGL20RigidTransforms3fArray__
#define SFGL20RigidTransforms3fArray__


#include "../SFRigidTransform3fArray.h"
#include "../../math/SFTransform3f.h"
#include "../SFPipeline.h"
#include <vector>

using namespace std;


namespace sf{
class SFGL20RigidTransforms3fArray : public SFRigidTransform3fArray {
    
	class SFGL20RigidTransform {
    
        public:
		
        SFTransform3f transform;
		SFTransform3f effectiveTransform;
		SFGL20RigidTransform* father;
		vector<SFGL20RigidTransform*> sons;
        
		void update() {
			if (father == 0) {
				effectiveTransform.set(&transform);
				sonsUpdate();
			} else {
				sonUpdate();
			}
		}
        
		void sonUpdate() {
			effectiveTransform.set(&(father->effectiveTransform));
			effectiveTransform.mult(this->transform);
			//System.out.println("Transform "+transform);
			//System.out.println("Effective Transform "+effectiveTransform);
			sonsUpdate();
		}
        
		void sonsUpdate() {
            for(unsigned int i=0;i<sons.size();i++){
                sons.at(i)->sonUpdate();
            }
		}
        
		void attach(SFGL20RigidTransform* father) {
			
			if (this->father != 0) {
                //will not be frequent
                int index=0;
                for(unsigned int i=0;i<this->father->sons.size();i++){
                    if(this->father->sons.at(i)==this){
                        index=i;
                        i=father->sons.size();
                    }
                }
				this->father->sons.erase(this->father->sons.begin()+index);
			}
			this->father = father;
			this->father->sons.push_back(this);
			update();
		}
	};
    
	vector<SFGL20RigidTransform*> transforms;
	
public:
	
    ~SFGL20RigidTransforms3fArray(){
        for(unsigned int i=0;i<transforms.size();i++){
            delete transforms[i];
        }
    }
    
	void apply(int index) {
        SFPipeline::getSfPipelineGraphics()->setupTransform(transforms.at(index)->effectiveTransform.getV());
	}
	
	void attach(SFRigidTransform3fArray* sonArray, int sonIndex,
                       int fatherIndex) {
		
        //	System.err.println("sonIndex "+sonIndex+" fatherIndex "+fatherIndex);
		SFGL20RigidTransforms3fArray* trueSon = (SFGL20RigidTransforms3fArray*) sonArray;
		trueSon->transforms.at(sonIndex)->attach(transforms.at(fatherIndex));
        //		System.out.println("Updating... "+sonIndex);
		trueSon->transforms.at(sonIndex)->update();
	}
    
	
	void eraseElements(int index, int elementsCount) {
		for (int i = 0; i < elementsCount; i++) {
			transforms.erase(transforms.begin()+index);
		}
	}
	
	
	SFTransform3f* generateSample() {
		return new SFTransform3f();
	}
    
	
	int generateElement() {
		
		transforms.push_back(new SFGL20RigidTransform());
		return transforms.size() - 1;
	}
    
	
	int generateElements(int count) {
		int size = transforms.size();
		for (int i = 0; i < count; i++) {
			transforms.push_back(new SFGL20RigidTransform());
		}
		return size;
	}
    
	
	void getElement(int index, SFTransform3f* element) {
		element->set(&(transforms.at(index)->transform));
	}
    
	
	int getElementsCount() {
		return transforms.size();
	}
    
	
	void setElement(int index, SFTransform3f* element){
		transforms.at(index)->transform.set(element);
		transforms.at(index)->update();
	}
    
	
	void getElementOrientation(int index, SFMatrix3f* matrix) {
		transforms.at(index)->transform.getMatrix(matrix);
	}
	
	
	void getElementPosition(int index, SFVertex3f* vertex) {
		transforms.at(index)->transform.getPosition(vertex);
	}
	
	
	void setElementOrientation(int index, SFMatrix3f* matrix) {
		transforms.at(index)->transform.setMatrix(*matrix);
		transforms.at(index)->update();
        //	System.out.println("Setting Element Orientation "+index);
        
        //		System.out.println("Transform "+this->transforms.at(index).transform);
        //		System.out.println("Effective Transform "+this->transforms.at(index).effectiveTransform);
	}
	
	
	void setElementPosition(int index, SFVertex3f* vertex) {
		transforms.at(index)->transform.setPosition(*vertex);
		transforms.at(index)->update();
        //		System.out.println("Setting Element Position "+index);
        
        //		System.out.println("Transform "+this->transforms.at(index).transform);
        //		System.out.println("Effective Transform "+this->transforms.at(index).effectiveTransform);
	}
};

}


#endif /* defined(SFGL20RigidTransforms3fArray__) */
