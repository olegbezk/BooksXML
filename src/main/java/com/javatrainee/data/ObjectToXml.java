package com.javatrainee.data;

import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.GregorianCalendar;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class ObjectToXml {
	
	private static Marshaller marshaller;

	static {
		try {
			JAXBContext context = JAXBContext.newInstance(Catalog.class);
			marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws JAXBException,
			FileNotFoundException, DatatypeConfigurationException {
		
		new ObjectToXml().marshBooksToStream(System.out);
		
		String xml = new ObjectToXml().marshallBooksToString();

		System.out.println("\n toString output : \n");
		System.out.println(xml);

	}

	public String marshallBooksToString() throws DatatypeConfigurationException, JAXBException {
		StringWriter st = new StringWriter();
		marshaller.marshal(getCatalog(), st);
		return st.toString();
	}
	
	public void marshBooksToStream(OutputStream os) throws JAXBException, DatatypeConfigurationException {
		marshaller.marshal(getCatalog(), os);
	}
	
	public Catalog getCatalog() throws DatatypeConfigurationException {
		Catalog catalog = new Catalog();

		Books books = new Books();

		catalog.setBooks(books);

		Authors author = new Authors();

		author.getName().add("Vova Zemsky");

		GregorianCalendar c = new GregorianCalendar();
		c.set(2009, 7, 22);
		XMLGregorianCalendar date = DatatypeFactory.newInstance()
				.newXMLGregorianCalendar(c);

		Book b1 = new Book();

		b1.setAuthors(author);
		b1.setId((short) 4);
		b1.setISBN(123465);
		b1.setName("About Nothing");
		b1.setDescription("All about All");
		b1.setPrice("44 $");
		b1.setPublishDate(date);

		books.getBook().add(b1);
		
		return catalog;
	}
}
