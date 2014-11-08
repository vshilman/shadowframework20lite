#ifndef SFINITIABLE_H
#define SFINITIABLE_H


namespace sf{
	class SFInitiable{

	public:

		virtual ~SFInitiable(){};

		virtual void init()=0;

		virtual void destroy()=0;
	};
}

#endif // SFINITIABLE_H
