package testPackage;

import java.util.HashMap;

import codeconverter.codepieces.ConcreteValue;
import codeconverter.templates.basicattributedeclaration.BasicAtrributeDeclarationCppHeaderTemplate;
import codeconverter.templates.basicattributedeclaration.BasicAttributeDeclarationJavaTemplate;
import codeconverter.templates.basicconstructor.BasicConstructorCppHeaderTemplate;
import codeconverter.templates.basicconstructor.BasicConstructorCppTemplate;
import codeconverter.templates.basicconstructor.BasicConstructorIntializationListCppTemplate;
import codeconverter.templates.basicconstructor.BasicConstructorJavaTemplate;
import codeconverter.templates.basicconstructor.BasicContructorJsTemplate;
import codeconverter.templates.basicget.BasicGetCppHeaderTemplate;
import codeconverter.templates.basicget.BasicGetCppTemplate;
import codeconverter.templates.basicget.BasicGetJavaTemplate;
import codeconverter.templates.basicget.BasicGetJsTemplate;
import codeconverter.templates.basicset.BasicSetCppHeaderTemplate;
import codeconverter.templates.basicset.BasicSetCppTemplate;
import codeconverter.templates.basicset.BasicSetJavaTemplate;
import codeconverter.templates.basicset.BasicSetJsTemplate;

public class Testtesttest {

	public static void main(String[] args) {

		BasicAttributeDeclarationJavaTemplate t = new BasicAttributeDeclarationJavaTemplate ();

		//Get .h
		//String s="string  getName();";
		//String s="bool isValid()  ;";

		//Get .cpp
		//String s="string SFParameter::getName() {\nreturn  this->name;\n}";
		//String s="bool SFParameter::isValid() {\nreturn  this->isValid;\n}";

		//Get .java
		//String s="public String getType ( ) {\n return this.type;\n}";
		//String s="public boolean isValid () {\n return isValid;\n}";

		//Get .js
		//String s="SFGridModel.prototype[\"getEdges\"] = function ( ) {\nreturn this.edges; \n};";
		//String s="SFGridModel.prototype[\"isValid\"] = function ( ) {\nreturn this.isValid; \n};";

		//							----------------------------------------------------------------------

		//Set .h
		//String s="void setType (short type); ";
		//String s="void setValid (bool isValid);";

		//Set .cpp
		//String s="void SFParameter::setType(short type) {\nthis->type=type;\n}";
		//String s="void SFParameter::setValid(bool isValid) {\nthis->isValid=isValid;\n}";

		//Set .java
		//String s="public void setFoo ( String foo ) {\nthis.foo = foo;\n}";
		//String s="public void setValid ( boolean isValid ) {\nthis.isValid = isValid;}\n";

		//Set .js
		//String s="SFShort.prototype[\"setShortValue\"] = function ( shortValue ) { \n this.shortValue = shortValue;\n};";
		//String s="SFShort.prototype[\"setShortValue\"] = function ( isShortValue ) { \n this.isShortValue = isShortValue;\n};";

		//                          -----------------------------------------------------------------------

		//Constructor .h
		//String s="SFParameter(string name, short type );";
		//String s="SFParameter();";

		//Constructor .cpp
		//String s="SFParameter::SFParameter  (string  name  , int c , short  type , int x  ) : SFPara( name , c )  { this->type = type;\n   this->x=x;\n}";
		//String s="SFParameter::SFParameter  () {}";

		//Constructor .cpp with init-list
		//String s="SFParameter::SFParameter(string  name, int c , short type, int x) : SFPara(name,c) ,type (type)  , x(x) { \n}";
		//String s="SFParameter::SFParameter  () {}";

		//Constructor .java
		//String s="public BasicGetJavaTemplate(){\n}";
		//String s="private BasicConstructorJavaTemplate(String modifier,String classn,List<String> map) {\nsuper();\nthis.classn = classn;\nthis.map = map;\nthis.modifier=modifier;}";

		//Constructor .js
		// s="function SFFloat(floatValue,ciao){\nthis.floatValue=floatValue;\nthis.ciao=ciao;}";
		//String s="function SFFloat(){\n}";

		//							----------------------------------------------------------------------

		//Attributes .java
		//String s="private int  code;";
		//String s="private int  code = 0 ;";

		//Attributes .cpp
		String s="private bool code=false;";

		boolean b=t.matchCode(s);
		if(b==true){
			BasicAttributeDeclarationJavaTemplate t2=( BasicAttributeDeclarationJavaTemplate) t.clone();
			String out=t2.constructCode();
			System.out.println(out);
		}


	}
}
