package ru.kruvv;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import model.Root;

public class SaxMyParser {

	
	
	public Root parse() {
		
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SaxParserHandler handler = new SaxParserHandler();
		SAXParser parser = null;
		
		try {
			parser = factory.newSAXParser();
		} catch (Exception e) {
			System.out.println("Open sax parser error " + e.toString() );
			return null;
		} 
		
		File file = new File("test.xml");
		
		try {
			parser.parse(file, handler);
		} catch (SAXException e) {
			System.out.println("Sax parsing error " + e.toString() );
			return null;
		} catch (IOException e) {
			System.out.println("IO parsing error " + e.toString() );
			return null;
		}
		
		return handler.getRoot();
	}

}
