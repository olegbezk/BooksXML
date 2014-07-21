package com.javatrainee.test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;

import org.custommonkey.xmlunit.DetailedDiff;
import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.XMLAssert;
import org.custommonkey.xmlunit.XMLUnit;
import org.custommonkey.xmlunit.examples.MultiLevelElementNameAndTextQualifier;
import org.junit.Test;
import org.xml.sax.SAXException;

import com.javatrainee.data.Catalog;
import com.javatrainee.data.ObjectToXml;
import com.javatrainee.data.XMLToObject;

public class MarshUnmarshTest {

	@Test
	public void marshToStr() throws DatatypeConfigurationException,
			JAXBException, SAXException, IOException {

		String extracted = new ObjectToXml().marshallBooksToString();

		String expected = "<?xml version=\"1.0\"" + " encoding=\"UTF-8\""
				+ " standalone=\"yes\"?>" + " <catalog> <books>"
				+ " <book id=\"4\">" + " <name>About Nothing</name>"
				+ " <ISBN>123465</ISBN>" + " <price>44 $</price>"
				+ " <authors>" + " <name>Vova Zemsky</name>" + " </authors>"
				+ " <description>All about All</description>"
				+ " <publishDate>2009-08-22+03:00</publishDate>" + " </book>"
				+ " </books>" + " </catalog>";

		assertEqualXmls(expected, extracted);

	}

	@Test
	public void marshToConsole() throws JAXBException,
			DatatypeConfigurationException, SAXException, IOException {
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		new ObjectToXml().marshBooksToStream(baos);

		String extracted = new String(baos.toByteArray());

		String expected = "<?xml version=\"1.0\"" + " encoding=\"UTF-8\""
				+ " standalone=\"yes\"?>" + " <catalog> <books>"
				+ " <book id=\"4\">" + " <name>About Nothing</name>"
				+ " <ISBN>123465</ISBN>" + " <price>44 $</price>"
				+ " <authors>" + " <name>Vova Zemsky</name>" + " </authors>"
				+ " <description>All about All</description>"
				+ " <publishDate>2009-08-22+03:00</publishDate>" + " </book>"
				+ " </books>" + " </catalog>";

		assertEqualXmls(expected, extracted);

	}
	
	@Test
	public void unmarshFromClassPath() throws SAXException, IOException{
		XMLToObject xto = new XMLToObject();
		String s = xto.unmarchFromClassPath();
		String expected = "<?xml version=\"1.0\"" + " encoding=\"UTF-8\""
				+ " standalone=\"yes\"?>" + " <catalog> <books>"
				+ " <book id=\"4\">" + " <name>About Nothing</name>"
				+ " <ISBN>123465</ISBN>" + " <price>44 $</price>"
				+ " <authors>" + " <name>Vova Zemsky</name>" + " </authors>"
				+ " <description>All about All</description>"
				+ " <publishDate>2009-08-22+03:00</publishDate>" + " </book>"
				+ " </books>" + " </catalog>";
		assertEqualXmls(expected, s);
	}
	
	@Test
	public void unmarshFromFile() throws JAXBException{
		XMLToObject xto = new XMLToObject();
		
		Catalog cat = xto.unmarshFromFile();
		
		Catalog catalog = new Catalog();
		
		equals(catalog.equals(cat));
		
	}

	private void assertEqualXmls(String expectedXml, String actualXml)
			throws SAXException, IOException {

		XMLUnit.setIgnoreWhitespace(true);
		XMLUnit.setIgnoreAttributeOrder(true);
		XMLUnit.setIgnoreComments(true);

		Diff xmlDiff = new DetailedDiff(new Diff(expectedXml, actualXml));
		xmlDiff.overrideElementQualifier(new MultiLevelElementNameAndTextQualifier(
				Integer.MAX_VALUE));
		XMLAssert.assertXMLEqual(
				"The actual XML differs from the expected one", xmlDiff, true);

	}

}