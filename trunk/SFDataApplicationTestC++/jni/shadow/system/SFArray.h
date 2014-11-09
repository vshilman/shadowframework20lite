#ifndef SFARRAY_H
#define SFARRAY_H

namespace sf{

template <class E>
class SFArray{

public:

	virtual ~SFArray(){};

    /** @return the number of elements in the array */
    virtual int getElementsCount()=0;

    /** writes a <E> with the content of the element at a given position
     * @param index the index at which element is
     * read
     * @param element the element where to store data
     * */
    virtual void getElement(int index, E* element)=0;

    /**
     * Generate a valid instance of E
     * @return
     */
    virtual E* generateSample()=0;

    /** Writes the content of a given element at the given position
     * @param index the index at which element is written
     * @param element the element where data are read
     */
    virtual void setElement(int index,E* element)=0;

    /** Generate a new Element in the Array
     * @return the index of the position in which the element has been added, or -1 if this array does not allow elements generation*/
    virtual int generateElement()=0;
    /** Generate a new Element in the Array
     * @param count number of elements to be destroyed
     * @return the index of the position in which the element has been added, or -1 if this array does not allow elements generation*/
    virtual int generateElements(int count)=0;

    /**Destroy a set of consecutive elements
     * @param index starting position at which elements will be destroyed
     * @param elementsCount the number of elements which will be destroyed*/
    virtual void eraseElements(int index,int elementsCount)=0;


};

}

#endif // SFARRAY_H
