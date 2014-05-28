#ifndef shadow_renderer_data_java_H_
#define shadow_renderer_data_java_H_

interface SFDataInterpreter {

	abstract void pushElement(String type,String name);

	abstract void popElement(String type);

	abstract void insertElement(String name,String info);
}
;
}
#endif
