package com.javatrainee.data;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class XMLToObject {

	public static void main(String[] args) throws JAXBException {
		
		File xml = new File(
				"C:/Users/Oleg/git/BooksXML/src/main/resources/books.xml");
		JAXBContext context = JAXBContext.newInstance(Catalog.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		Catalog catalog = (Catalog) unmarshaller.unmarshal(xml);
		
		System.out.println(catalog);
		

	}

}
