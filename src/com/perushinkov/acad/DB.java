package com.perushinkov.acad;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DB {
	private File usersFile;
	private File testsFile;
	private File resultsFile;
	
	private ArrayList<ExamUser> users;
	private ArrayList<ExamTest> tests;
	private ArrayList<ExamResult> results;
	
	public DB(String rootPath){
		users = new ArrayList<ExamUser>();
		tests = new ArrayList<ExamTest>();
		results = new ArrayList<ExamResult>();
		
		usersFile = new File(rootPath + "\\db\\users.xml");
		testsFile = new File(rootPath + "\\db\\tests.xml");
		resultsFile = new File(rootPath + "\\db\\results.xml");
	    try {
			usersFile.createNewFile();
			testsFile.createNewFile();
			resultsFile.createNewFile();
			
			loadUsers();
			loadTests();
			loadResults();
			
			saveFiles(true, true, true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void loadResults() throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(testsFile);
		
		doc.getDocumentElement().normalize();
		
		NodeList resultList = doc.getElementsByTagName("result");
		for (int i = 0; i < resultList.getLength(); i++) {
			Element result = (Element) resultList.item(i);
			String user = result.getElementsByTagName("user").item(0).getFirstChild().getTextContent();
			String test = result.getElementsByTagName("test").item(0).getFirstChild().getTextContent();
			long timestamp = Long.parseLong(result.getElementsByTagName("timestamp").item(0).getFirstChild().getTextContent());
			ExamResult temp = new ExamResult(user, test, timestamp);
			
			NodeList selectedList = result.getElementsByTagName("answer");
			for (int j = 0; j < selectedList.getLength(); j++) {
				temp.addAnswer(Integer.parseInt(selectedList.item(j).getFirstChild().getTextContent()));
			}
			results.add(temp);
		}
	}
	private void loadTests() throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(testsFile);
		
		doc.getDocumentElement().normalize();
		
		NodeList testList = doc.getElementsByTagName("test");
		
		ExamTest temp;
		for (int i = 0; i < testList.getLength(); i++) {
			Element test = (Element) testList.item(i);
			
			String testId = test.getElementsByTagName("id").item(0).getFirstChild().getTextContent();
			temp = new ExamTest(testId);
			NodeList questionList = test.getElementsByTagName("question");
			for (int j = 0; j < questionList.getLength(); j++) {
				Element question = (Element) questionList.item(j);
				
				String text = question.getElementsByTagName("text").item(0).getFirstChild().getTextContent();
				int correctOption = Integer.parseInt(question.getElementsByTagName("correctOption").item(0).getFirstChild().getTextContent());
				ArrayList<String> options = new ArrayList<String>();
				
				NodeList optionList = question.getElementsByTagName("option");
				for (int k = 0; k < optionList.getLength(); k++) {
					options.add(optionList.item(k).getFirstChild().getTextContent());
				}
				temp.addQuestion(text, correctOption, options.toArray(new String[options.size()]));
			}
			tests.add(temp);
		}
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
	/**
	 * adds a user to users.
	 * @param user
	 */
	public void addUser(ExamUser user) {
		users.add(user);
	}
	/**
	 * adds a test to tests.
	 * @param test
	 */
	public void addTest(ExamTest test) {
		tests.add(test);
	}
	/**
	 * adds a result to results.
	 * @param result
	 */
	public  void addResult(ExamResult result) {
		results.add(result);
	}
	
	public boolean saveFiles(boolean saveUsers, boolean saveTests, boolean saveResults){
		DocumentBuilderFactory fact = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        
        try{
        	builder = fact.newDocumentBuilder();
        	if(saveUsers) {
        		Document doc = builder.newDocument();
            	Element topElem = doc.createElement("users");
            	doc.appendChild(topElem);
            	for (ExamUser user: users) {
            		topElem.appendChild(getUserElement(doc, user));
            	}
            	docToFile(doc, usersFile);
        	}
        	if(saveTests) {
        		Document doc = builder.newDocument();
            	Element topElem = doc.createElement("tests");
            	doc.appendChild(topElem);
            	for (ExamTest test: tests) {
            		topElem.appendChild(getTestElement(doc, test));
            	}
            	docToFile(doc, testsFile);
        	}
        	if(saveResults) {
        		Document doc = builder.newDocument();
            	Element topElem = doc.createElement("results");
            	doc.appendChild(topElem);
            	for (ExamResult result: results) {
            		topElem.appendChild(getResultElement(doc, result));
            	}
            	docToFile(doc, resultsFile);
        	}
        } catch (Exception e) {
        	return false;
        }
        return true;
	}
	private Node getResultElement(Document doc, ExamResult result) {
		Element resultElem = doc.createElement("result");
		resultElem.appendChild(getElem(doc, "user", result.getUser()));
		resultElem.appendChild(getElem(doc, "test", result.getTest()));
		resultElem.appendChild(getElem(doc, "timestamp", Long.toString(result.getTimestamp())));
        //TODO: add answers here 
		return resultElem;
	}
	private Node getTestElement(Document doc, ExamTest test) {
		// TODO save to file. See above method 
		return null;
	}
	private Node getUserElement(Document doc, ExamUser user) {
		Element userElem = doc.createElement("user");
        userElem.appendChild(getElem(doc, "username", user.getUsername()));
        userElem.appendChild(getElem(doc, "password", user.getPassword()));
        userElem.appendChild(getElem(doc, "role", user.getRole()));
        return userElem;
	}
	private Node getElem(Document doc, String nodeName, String textContent) {
		Element node = doc.createElement(nodeName);
		node.appendChild(doc.createTextNode(textContent));
		return node;
	}
	private void docToFile(Document doc, File file) throws TransformerException {
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(file);
		transformer.transform(source, result);
		
	}
}
