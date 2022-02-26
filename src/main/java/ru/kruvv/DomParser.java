package ru.kruvv;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import model.People;
import model.Root;

public class DomParser {

	public Root parse() {
		
		Root root = new Root();
		
		Document doc;
		try {
			doc = buildDocument();
		} catch (Exception e) {
			System.out.println("Open parsing error " + e.toString());
			return null;
		}
		
		Node rootNode = doc.getFirstChild();		
		NodeList rootChilds = rootNode.getChildNodes();
		
		String mainName = null;
		Node peopleNode = null;
		
		for (int i = 0; i < rootChilds.getLength(); i++) {
			if(rootChilds.item(i).getNodeType() != Node.ELEMENT_NODE) {
				continue;
			}
			
			switch(rootChilds.item(i).getNodeName()) {
				case TagList.TAG_NAME_MAIN : {
					mainName = rootChilds.item(i).getTextContent();
					break; 
				}
				case TagList.TAG_PEOPLE : {
					peopleNode = rootChilds.item(i);
					break;				
				}
			}			
		}
		
		if(peopleNode == null) {
			return null;
		}
		
		List<People> parsePeople = parsePeople(peopleNode);
		
		
		root.setName(mainName);
		root.setPeople(parsePeople);		
		
		return root;		
	}
	
	private Document buildDocument() throws Exception {
		File file = new File("test.xml");
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();		
		return dbf.newDocumentBuilder().parse(file);
		
	}
	
	private List<People> parsePeople(Node peopleNode) {

		List<People> peopleList = new ArrayList<>(); 
		
		NodeList peopleChilds = peopleNode.getChildNodes();
		
		for (int i = 0; i < peopleChilds.getLength(); i++) {
					
			if(peopleChilds.item(i).getNodeType() != Node.ELEMENT_NODE) {
				continue;
			}
			
			if(!peopleChilds.item(i).getNodeName().equals(TagList.TAG_ELEMENT)) {
				continue;
			}			
			
			peopleList.add(parseElement(peopleChilds.item(i)));					
		}
		return peopleList;
	}
	
	private  People parseElement(Node elementNode) {
		
		String name = "";
		int age = 0;
		
		NodeList elementChilds = elementNode.getChildNodes();
		for (int j = 0; j < elementChilds.getLength(); j++) {
			
			if(elementChilds.item(j).getNodeType() != Node.ELEMENT_NODE) {
				continue;
			}
			
			switch(elementChilds.item(j).getNodeName()) {
				case TagList.TAG_NAME : {
					name = elementChilds.item(j).getTextContent();
					break;
				}
				case TagList.TAG_AGE : {
					try {
						age = Integer.valueOf(elementChilds.item(j).getTextContent());
						break;
					} catch (Exception e) {
						System.out.println("Error parse value age" + e.toString());
					}
				}
			}
		}
		return new People(name, age);
	}
}


