#ifndef SFINDEXRANGE_H
#define SFINDEXRANGE_H


namespace sf{

class SFIndexRange{

private:
    int position;
    int size;

public:
    SFIndexRange(){
    	position=0;
    	size=0;
    }

    SFIndexRange(int position, int size);

    int getPosition();

    void setPosition(int position);

    int getSize();

    void setSize(int size);

    void insertIndex(int index);
};

}

#endif // SFINDEXRANGE_H
