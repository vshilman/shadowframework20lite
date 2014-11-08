#ifndef SFDRAWABLE_H
#define SFDRAWABLE_H

namespace sf{

class SFDrawable{

public:

	virtual ~SFDrawable(){};

    virtual void draw()=0;
};

}

#endif // SFDRAWABLE_H
