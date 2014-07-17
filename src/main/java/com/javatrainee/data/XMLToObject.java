package com.javatrainee.data;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.javatrainee.data.Catalog.Books.Book;

public class XMLToObject {

	public static void main(String[] args) throws JAXBException {
		
		File xml = new File(
				"C:/Users/Oleg/workspace/BooksXML/src/main/resources/books.xml");
		JAXBContext context = JAXBContext.newInstance(Catalog.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		Catalog catalog = (Catalog) unmarshaller.unmarshal(xml);
		
		for(Book s : catalog.getBooks().getBook()){
				System.out.println(s.getName());
		}

	}

}
