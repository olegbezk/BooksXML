package com.javatrainee.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.javatrainee.data.Book;


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
		
		InputStream is = Catalog.class.getClassLoader().getResourceAsStream("books.xml");
		
		String result = getStringFromInputStream(is);
		 
		System.out.println(result);
	}
 
	private static String getStringFromInputStream(InputStream is) {
 
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
