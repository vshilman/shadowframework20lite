/*
 * SFQuadsGrid.h
 *
 *  Created on: 24/set/2013
 *      Author: Alessandro
 */

#ifndef SFQUADSGRID_H_
#define SFQUADSGRID_H_

namespace sf {

class SFQuadsGrid {

	bool closeU;
	bool closeV;
	float* vSplits;
	float* uSplits;
	short uSplits_length;
	short vSplits_length;

public:
	virtual ~SFQuadsGrid();


	SFQuadsGrid();

	bool isCloseU();

	void setCloseU(bool closeU);

	bool isCloseV();

	void setCloseV(bool closeV);

	float* getvSplits();

	void setvSplits(float* vSplits,short vSplits_length);

	float* getuSplits();

	void setuSplits(float* uSplits,short uSplits_length);

	float* getU_splits();

	void setU_splits( float* uSplits,short uSplits_length);

	float* getV_splits();

	void setV_splits(float* vSplits,short vSplits_length);

	int getNu();

	void setNu( int nu);

	int getNv();

	void setNv(int nv);

	static int getPartitionedSplitsSize(int n1,int splitsCount);

	float* generatePartitionedSplits( int n1, float stepn1, float* vSplits,int vSplits_length);
};

} /* namespace sf */
#endif /* SFQUADSGRID_H_ */
