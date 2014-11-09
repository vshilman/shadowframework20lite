#ifndef shadow_renderer_SFViewTransform_H_
#define shadow_renderer_SFViewTransform_H_

namespace sf{

class SFViewTransform {

public:
	virtual ~SFViewTransform();

	virtual float* extractTransform()=0;

};
}
#endif
