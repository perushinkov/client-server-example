package com.perushinkov.acad;

import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

//for more info see gitlab.com/sitpractice

/**
 * Sax example
 * @author root
 *
 */

public class SaxHandler extends DefaultHandler {
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attr) throws SAXException {
		 System.out.println("start element    : " + qName);
	}
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		System.out.println("end element    : " + qName);
	}
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String content = new String(ch, start, length);
		System.out.println("characters    : " + content);
	}
	
	public static void main(String args[]) {
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();
			SaxHandler handler = new SaxHandler();
			
			parser.parse(new File("C:\\Documents and Settings\\root\\Desktop\\workspace\\client-server-example\\WebContent\\WEB-INF\\db\\tests.xml"), handler);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
