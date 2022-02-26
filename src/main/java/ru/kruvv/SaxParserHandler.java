package ru.kruvv;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import model.People;
import model.Root;

public class SaxParserHandler extends DefaultHandler {
	
	private Root root = new Root();
	private List<People> peopleList = new ArrayList<People>();
	private String currentTagName;
	private boolean isPeople = false;
	private boolean isElement = false;	
	private String name;
	private int age;	
	
	public Root getRoot() {
		return root;
	}

	@Override
	public void startDocument() throws SAXException {
//		System.out.println("Start document");
	}
	
	@Override
	public void endDocument() throws SAXException {
//		System.out.println("End document");
		root.setPeople(peopleList);
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		// System.out.println("Start element " + qName);
		currentTagName = qName;
		if(currentTagName.equals(TagList.TAG_PEOPLE)){
			isPeople = true;
		} else	if(currentTagName.equals(TagList.TAG_ELEMENT)){
			isElement = true;
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		//	System.out.println("End element " + qName);
		if(qName.equals(TagList.TAG_PEOPLE)){
			isPeople = false;
		} else if (qName.equals(TagList.TAG_ELEMENT)) {
			isElement = false;
			People people = new People(name, age);
			peopleList.add(people);				
		}
		currentTagName = null;
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
//		System.out.println("characters " + new String(ch, start, length));
		
		if (currentTagName == null) {
			return;
		}
		
		if (!isPeople && currentTagName.equals(TagList.TAG_NAME_MAIN)) {
			root.setName(new String(ch, start, length));
		}
		
		if(isPeople && isElement) {
			if(currentTagName.equals(TagList.TAG_AGE)) {
				try {
					age = Integer.valueOf(new String(ch, start, length));
				} catch (Exception e) {
					System.out.println("Error parse value age" + e.toString());
				}
				} else if (currentTagName.equals(TagList.TAG_NAME)) {
					name = new String(ch, start, length);
				}
		}
	}

}
