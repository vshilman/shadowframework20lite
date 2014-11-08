/*
 * SFResizeListener.h
 *
 *  Created on: 11/set/2013
 *      Author: Alessandro
 */

#ifndef SFRESIZELISTENER_H_
#define SFRESIZELISTENER_H_


class SFResizeListener{

public:
	virtual ~SFResizeListener()=0;

    virtual void resize(int w,int h)=0;
};

#endif /* SFRESIZELISTENER_H_ */
