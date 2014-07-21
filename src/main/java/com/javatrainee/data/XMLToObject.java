package com.javatrainee.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class XMLToObject {

	public static void main(String[] args) throws JAXBException {

		XMLToObject xto = new XMLToObject();
		System.out.println(xto.unmarchFromClassPath());
		System.out.println(xto.unmarshFromFile());
	}

	public String unmarchFromClassPath() {

		InputStream is = Catalog.class.getClassLoader().getResourceAsStream(
				"books.xml");

		String result = new XMLToObject().getStringFromInputStream(is);
		return result;
	}

	public Catalog unmarshFromFile() throws JAXBException {

		JAXBContext context = JAXBContext.newInstance(Catalog.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		
		File xml = new File(
				"C:/Users/Oleg/workspace/BooksXML/src/main/resources/books.xml");

		return (Catalog) unmarshaller.unmarshal(xml);
	}
	
	

	public String getStringFromInFile() {
		
		

		return null;
	}

	public String getStringFromInputStream(InputStream is) {

		StringBuilder sb = new StringBuilder();

		try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return sb.toString();
	}
}
