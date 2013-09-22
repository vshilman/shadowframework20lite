package jcodecomparator.test;

import jcodecomparator.core.DefaultScanner;

/**
 *
 * @author Nicola Pellicano'
 *
 */

public class JsScanner extends DefaultScanner{


	public JsScanner() {
		super();
		fgKeywords=new String[]{"break","else","new","var","case","finally",
								"return","void","catch","for","switch","while","continue","function","this",
								"with","default","if","throw","delete","in","try","do","instanceof","typeof","abstract",
								"enum","int","short","boolean","export","interface","static","byte","extends","long",
								"super","char","final","native","synchronized","class","float","package","throws","const",
								"goto","private","transient","debugger","implements","protected","volatile","double",
								"import","public","true","false","null"};
		initialize();
		}



	}





