package com.perushinkov.acad;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DB {
	private File usersFile;
	private File testsFile;
	
	private ArrayList<ExamUser> users;
	
	public DB(String rootPath){
		users = new ArrayList<ExamUser>();

		usersFile = new File(rootPath + "\\db\\users.xml");
		testsFile = new File(rootPath + "\\db\\tests.xml");
	    try {
			if (usersFile.createNewFile()){
				System.out.println("File is created!");
			}else{
				System.out.println("File already exists."+usersFile.getAbsolutePath());
			}
			if (testsFile.createNewFile()){
				System.out.println("File is created!");
			}else{
			    System.out.println("File already exists."+testsFile.getAbsolutePath());
			}
			
			
			loadUsers();
			loadTests();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void loadTests() {
		// TODO Auto-generated method stub
		
	}
	private void loadUsers() throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(usersFile);
		
		doc.getDocumentElement().normalize();
		
		NodeList nList = doc.getElementsByTagName("user");
		
		for (int i = 0; i < nList.getLength(); i++) {
			Element user = (Element) nList.item(i);
			String username = user.getElementsByTagName("username").item(0).getFirstChild().getTextContent();
			String password = user.getElementsByTagName("password").item(0).getFirstChild().getTextContent();
			String userrole = user.getElementsByTagName("role").item(0).getFirstChild().getTextContent();
			users.add(new ExamUser(username, password, userrole));
		}
	}
}
