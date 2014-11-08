/*
 * SFTilesSet.h
 *
 *  Created on: 24/set/2013
 *      Author: Alessandro
 */

#ifndef SFTILESSET_H_
#define SFTILESSET_H_

namespace sf {

class SFTilesSet {
	int sizeX;
	int sizeY;
	float stepX;
	float stepY;
public:
	virtual ~SFTilesSet();

	SFTilesSet();

	void setSizeX(int sizeX);

	int getSizeX(void* sfTilesSpace);

	void setSizeY( int sizeY);

	int getSizeY(void* sfTilesSpace);

	void setSizes(int sizeX, int sizeY);

	int getSizeX();

	int getSizeY();

	float getStepX();

	float getStepY();
};

} /* namespace sf */
#endif /* SFTILESSET_H_ */
