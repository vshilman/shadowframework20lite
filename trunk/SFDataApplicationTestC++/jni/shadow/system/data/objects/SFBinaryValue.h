
#ifndef SFBinaryValue_H
#define SFBinaryValue_H


namespace sf{
class SFBinaryValue {
    
public:
    int value;
    
    virtual ~SFBinaryValue(){};

	virtual SFBinaryValue* clone()=0;
    
	virtual int getBitSize()=0;
    
	SFBinaryValue();
    
	int getValue();
    
	void setValue(int value);
};
}

#endif /* defined(SFBinaryValue__) */
