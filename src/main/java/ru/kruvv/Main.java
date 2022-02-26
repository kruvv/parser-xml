package ru.kruvv;

import model.Root;

public class Main {
	
	public static void main(String[] args) {
		
		SaxMyParser saxparser = new SaxMyParser();
		Root rootSaxParser = saxparser.parse();		
		System.out.println("SaxMyParser: " + rootSaxParser.toString());
		
		DomParser domparser = new DomParser();
		Root rootDomParser = domparser.parse();
		System.out.println("DomParser: " + rootDomParser.toString());		
		
	}
}
	
	