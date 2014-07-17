package com.javatrainee.data;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.StringWriter;
import java.util.GregorianCalendar;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class ObjectToXml {

	public static void main(String[] args) throws JAXBException,
			FileNotFoundException, DatatypeConfigurationException {

		JAXBContext context = JAXBContext.newInstance(Catalog.class);

		Marshaller m = context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

		Catalog catalog = new Catalog();

		Catalog.Books books = new Catalog.Books();

		catalog.setBooks(books);

		Catalog.Books.Book.Authors author = new Catalog.Books.Book.Authors();

		author.getName().add("Vova Zemsky");

		GregorianCalendar c = new GregorianCalendar();
		c.set(2009, 7, 22);
		XMLGregorianCalendar date = DatatypeFactory.newInstance()
				.newXMLGregorianCalendar(c);

		Catalog.Books.Book b1 = new Catalog.Books.Book();

		b1.setAuthors(author);
		b1.setId((short) 4);
		b1.setISBN(123465);
		b1.setName("About Nothing");
		b1.setDescription("All about All");
		b1.setPrice("44 $");
		b1.setPublishDate(date);

		books.getBook().add(b1);

		m.marshal(catalog, System.out);

		m.marshal(
				catalog,
				new FileOutputStream(
						"C:/Users/Oleg/git/BooksXML/src/main/resources/books.xml"));

		StringWriter st = new StringWriter();
		m.marshal(catalog, st);
		String xml = st.toString();

		System.out.println(" toString output :");
		System.out.println(xml);

	}

}
