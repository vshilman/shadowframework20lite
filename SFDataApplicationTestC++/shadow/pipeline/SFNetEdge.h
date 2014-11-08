/*
 * SFNetEdge.h
 *
 *  Created on: 05/giu/2013
 *      Author: Alessandro
 */

#ifndef SFNETEDGE_H_
#define SFNETEDGE_H_


namespace sf{

class SFNetEdge {

	/* The number of elements this edge contains*/
	int size;
	/* indices vector for each grid. Note: must be indices[i].length=gridSize[i]*size+1 */
	short** indices;

public:
	SFNetEdge();
	SFNetEdge(int size);
	virtual ~SFNetEdge();

	int getSize();

	short** getIndices();

	void setIndices(short** indices);

	void setSize(int size);
};

}

#endif /* SFNETEDGE_H_ */
