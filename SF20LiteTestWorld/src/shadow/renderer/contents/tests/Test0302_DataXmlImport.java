package shadow.renderer.contents.tests;

import shadow.renderer.data.java.SFDataInterpreter;
import shadow.renderer.data.java.SFXMLDataInterpreter;

public class Test0302_DataXmlImport {

	public static void main(String argv[]) {

		SFXMLDataInterpreter interpreter=new SFXMLDataInterpreter(new TestDataInterpreter());
		interpreter.generateInterpretation("testsData/test0001.xml");
	}

	
	private static class TestDataInterpreter implements SFDataInterpreter{
		
		private int tabs=0;
		
		private String tabs(){
			char[] ts=new char[tabs];
			for (int i = 0; i < tabs; i++) {
				ts[i]='\t';
			}
			return new String(ts);
		}

		public void end() {
			tabs--;
			System.out.println(tabs()+"}");
		}
		
		
		@Override
		public void popElement(String type) {
			end();
		}
		
		public void start(String message) {
			System.out.println(tabs()+message+"{");
			tabs++;
		}
		
		public void print(String message) {
			System.out.println(tabs()+message);
		}
		
		@Override
		public void pushElement(String type, String name) {
			start(type+"("+name+")");
		}
		
		@Override
		public void insertElement(String name, String info) {
			print(name+":"+info);
		}
		
	}
}
