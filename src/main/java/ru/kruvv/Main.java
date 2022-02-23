package ru.kruvv;

import model.Root;

public class Main {

	public static void main(String[] args) {
		
		SaxMyParser parser = new SaxMyParser();
		Root root = parser.parse();
		
		System.out.println("Root " + root.toString());

	}

}
