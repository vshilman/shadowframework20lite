package shadow.pipeline.expression;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.StringTokenizer;

import shadow.pipeline.parameters.SFParameter;
import shadow.pipeline.parameters.SFParameteri;

public class SFExpressionParser {

	private static SFExpressionParser parser=new SFExpressionParser();
	
	private String parsingStringPlus="+";
	private String parsingStringProduct="*";
	private String operatorsSum="#°:/*-+";
	private String brackets="()";
	private String parsingString=operatorsSum+brackets;
	
	private SFExpressionGeneratori generator;
	
	private SFExpressionParser(){
		
	}
	
	/*public SFExpressionElement getExpression(String expression,SFParametersSet set){

		StringTokenizer tok=new StringTokenizer(expression,parsingStringPlus);
		
		ArrayList<String> addings=new ArrayList<String>();
		
		while (tok.hasMoreTokens()) {
			addings.add(tok.nextToken());
		}
		
		SFExpressionOperator plus=generator.getOperator("+");
		
		for(int i=0;i<addings.size();i++){			
			tok=new StringTokenizer(addings.get(i),parsingStringProduct);
		
			int count=tok.countTokens();
			if(count==1){
				String token=tok.nextToken();
				plus.addElement(getDirectExpressionElement(token,set));
			}else{
				SFExpressionOperator mult=generator.getOperator( "*");
				while (tok.hasMoreTokens()) {
					String token=tok.nextToken();
					mult.addElement(getDirectExpressionElement(token,set));
				}		
				plus.addElement(mult);
			}
		}
		return plus;
	}*/
	

	public SFExpressionGeneratori getGenerator() {
		return generator;
	}

	public void setGenerator(SFExpressionGeneratori generator) {
		this.generator = generator;
	}



	/*private SFExpressionElement getDirectExpressionElement(String element,SFParametersSet set){
		if(element.startsWith("_")){
			return new SFExpressionVariable(element,set);
		}else{
			return new SFExpressionNumber(element);
		}
		//this is all you can do? 
	}*/
	
	public SFExpressionElement parseString(String operation,Collection<SFParameteri> set) {
		
		StringTokenizer tok=new StringTokenizer(operation,parsingString,true);

		SFExpressionElement lastValue=null;
		SFExpressionElement lSymbol=null;
		int indexOfLastOperation=-1;
		
		ArrayList<SFExpressionOperator> expressions=new ArrayList<SFExpressionOperator>();
		ArrayList<ArrayList<SFExpressionOperator>> storedExpressiond=new ArrayList<ArrayList<SFExpressionOperator>>();
		ArrayList<SFExpressionElement> storedlSymbol=new ArrayList<SFExpressionElement>();
		ArrayList<Integer> storedlastIndexOf=new ArrayList<Integer>();
		
		boolean dispatchValue=false;
		while (tok.hasMoreTokens()) {
			String token=tok.nextToken();
				int index=parsingString.indexOf(token);
				int indexB=brackets.indexOf(token);
				
				if(indexB==-1){
					if(index==-1){
						lastValue=generator.getExpressionElement(token,set);
						dispatchValue=true;
					}else{
						if(expressions.size()==0){
							SFExpressionOperator symbol=generator.getOperator(token);
							if(lastValue!=null)
								symbol.addElement(lastValue);
							if(symbol!=null)
								expressions.add(symbol);
							lSymbol=symbol;
						}else{
							if(index<indexOfLastOperation){
								SFExpressionOperator symbol=generator.getOperator(token);
								SFExpressionOperator lastSymbol=expressions.get(expressions.size()-1);
								//greater priority
								lastSymbol.addElement(symbol);
								expressions.add(symbol);
								symbol.addElement(lastValue);
								lSymbol=symbol;
							}else if(index>indexOfLastOperation){
								
								//System.out.println("C "+token);
								SFExpressionOperator symbol=generator.getOperator( token);
								SFExpressionOperator lastSymbol=expressions.get(expressions.size()-1);

								//System.out.println("symbol "+symbol.getElement());
								//System.out.println("last "+lastSymbol.getElement());
								int indexBack=expressions.size()-1;
								int j=-1;
								for(int k=indexBack;k>=0;k--){
									//System.out.println(expressions.get(k).getElement()+" "+" "+symbol.getElement()+" "+k);
									if(expressions.get(k).getElement().equalsIgnoreCase(symbol.getElement()))
										j=k;
								}
								if(j>=0){
									lastSymbol.addElement(lastValue);
									//System.out.println("back "+j+" "+expressions.get(j).getElement());
									for(int k=expressions.size()-1;k>j;k--){
										expressions.remove(k);
									}
									symbol=expressions.get(j);
									lSymbol=symbol;
								}else{
								
									int k=indexBack;
									while(k>=0 && parsingString.indexOf(expressions.get(k).getElement())<index){
										k--;
									}	
									if(k>=0){
										//System.out.println("this is the case");
										
										expressions.get(k).removeElement(lastSymbol);
										expressions.get(k).addElement(symbol);
										symbol.addElement(expressions.get(k+1));
										for(int d=expressions.size()-1;d>k;d--){
											expressions.remove(d);	
										}
										lastSymbol.addElement(lastValue);
										expressions.add(symbol);
										
									}else{
										symbol.addElement(expressions.get(k+1));
										expressions.clear();
										//expressions.get(k).addElement(symbol);
										//for(int d=expressions.size()-1;d>k;d--){
										//	expressions.remove(d);	
										//}
										lastSymbol.addElement(lastValue);
										expressions.add(symbol);
									}
									
									//symbol.addElement(lastSymbol);
									//expressions.remove(expressions.size()-1);
									//expressions.add(symbol);
									//lastSymbol.addElement(lastValue);
									lSymbol=symbol;
								}
								
								//Problema: ne stiamo facendo uno nuovo. Non stiamo risalendo 
							}else{
								SFExpressionOperator lastSymbol=expressions.get(expressions.size()-1);
								lastSymbol.addElement(lastValue);
							}
						}
						indexOfLastOperation=index;
						
						dispatchValue=false;
					}
				}else{
					if(indexB==0){
						//What is open for?
						storedExpressiond.add(expressions);
						storedlSymbol.add(lSymbol);
						storedlastIndexOf.add(indexOfLastOperation);
						
						lastValue=null;
						lSymbol=null;
						indexOfLastOperation=-1;
						expressions=new ArrayList<SFExpressionOperator>();
						
					}else{
						if(dispatchValue){
							lSymbol.addElement(lastValue);
						}
						lastValue=expressions.get(0);
						
						int position=storedExpressiond.size()-1;
						expressions=storedExpressiond.get(position);
						lSymbol=storedlSymbol.get(position);
						indexOfLastOperation=storedlastIndexOf.get(position);
						storedExpressiond.remove(position);
						storedlSymbol.remove(position);
						storedlastIndexOf.remove(position);
						
						dispatchValue=true;
					}
					
					//this is a shit problem.
				}
		}
		if(dispatchValue){
			if(lSymbol!=null)
				lSymbol.addElement(lastValue);
			else{
				return lastValue;
			}
		}
		return expressions.get(0);
	}
	
	

	public static SFExpressionParser getParser() {
		return parser;
	}

	public static void main(String[] args) {
		
		//String operation="u*u*u*A+v*v*v*A";//Perfetto
		
		String operation="projection*modelview*A";
		
		//String operation="a+c:0:1";
		//String operation="(_a+_b)/_c*(_d*_a/(_b+_e))";
		//String operation="NA°NB*F+C";//Still no troubles would say
		
		//Adesso devo assolutamente considerare la funzionalità di re-arrange of types.
		//Così importante....
		
		//PERFETTO!!
		
		parser.setGenerator(new SFBasicExpressionGenerator());
		
		SFParameter A=new SFParameter("A",SFParameter.GLOBAL_FLOAT3);
		SFParameter B=new SFParameter("B",SFParameter.GLOBAL_FLOAT3);
		SFParameter C=new SFParameter("C",SFParameter.GLOBAL_FLOAT3);
		SFParameter modelview=new SFParameter("modelview",SFParameter.GLOBAL_MATRIX4);
		SFParameter projection=new SFParameter("projection",SFParameter.GLOBAL_MATRIX4);
		Collection<SFParameteri> set=new LinkedList<SFParameteri>();
		set.add(A);
		set.add(B);
		set.add(C);
		set.add(projection);
		set.add(modelview);
		
		SFExpressionElement element = parser.parseString(operation,set);

		SFExpressionElementInterpreter interpreter=new SFExpressionElementInterpreter(){
			@Override
			public void closeElement(SFExpressionElement element) {
				System.out.print(")");
			}
			@Override
			public void refreshElement(SFExpressionElement element) {
				//System.out.print(element.getElement());
				System.out.print(",");
			}
			@Override
			public void startElement(SFExpressionElement element) {
				System.out.print("("+element.getElement()+"T"+element.getType()+",");
				
				//if(!(element instanceof SFExpressionOperator))
					//System.out.print(element.getElement());
			}
		};
		element.traverse(interpreter);
		System.out.println();
		try {
			element.evaluateType();
		} catch (SFExpressionException e) {
			e.printStackTrace();
		}
		element.traverse(interpreter);
	}
}
