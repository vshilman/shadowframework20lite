#ifndef shadow_geometry_vertices_SFVerticesParameters_H_
#define shadow_geometry_vertices_SFVerticesParameters_H_

#include <map>

using namespace std;

namespace sf{
class SFVerticesParameters {

	static SFVerticesParameters verticesParameters;

	SFVerticesParameters();

public:
	static SFVerticesParameters* getParameters();

	map<string,float> parameters;

	void clear();

	void setParameter(string parameter,float value);

	float getValue(string parameter);

};

}
#endif
