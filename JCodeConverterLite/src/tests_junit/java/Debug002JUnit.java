package tests_junit.java;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

import org.junit.Test;

import codeconverter.CodePattern;
import codeconverter.java.codelines.JavaFor;

public class Debug002JUnit {

	@Test
	public void test() {
		JavaFor attributePattern=new JavaFor();		
		String codeLine="for (i = 0;i < data.length;i++)";		
		CodePattern pattern=attributePattern.match(codeLine);
		String s="";
		if(pattern==null)
			s+="Failed";
		else
			s+="Success";
		
		
		//TODO non esiste il pattern per "for (String c: ciao)"
		
		
	}

}
