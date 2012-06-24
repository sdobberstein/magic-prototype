package magic.prototype.location;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.net.URI;

import org.junit.Before;
import org.junit.Test;

public class FileRepositoryLocationTest {

	private FileRepositoryLocation location;
	
	@Before
	public void setUp() {
		
		location = new FileRepositoryLocation();
	}
	
	@Test
	public void readFromUriXmlFile() {
		
		location.setUriString("testProperties.xml");
		assertEquals("bar", location.locate(getUri("foo")).getValue().toString());
		
		location.setUriString("testProperties.xml");
		assertEquals("bar", location.locate(getUri("foo")).getValue().toString());
		
		location.setUriString("nested/testProperties.xml");
		assertEquals("bar", location.locate(getUri("foo")).getValue().toString());
		
		location.setUriString("nested/testProperties.xml");
		assertEquals("bar", location.locate(getUri("foo")).getValue().toString());
	}	
	
	@Test
	public void readFromClasspathUriXmlFile() {
		
		location.setUriString("testProperties.xml");
		assertEquals("bar", location.locate(getUri("foo")).getValue().toString());
		
		location.setUriString("classpath://testProperties.xml");
		assertEquals("bar", location.locate(getUri("foo")).getValue().toString());
		
		location.setUriString("classpath://nested/testProperties.xml");
		assertEquals("bar", location.locate(getUri("foo")).getValue().toString());
		
		location.setUriString("classpath://nested/testProperties.xml");
		assertEquals("bar", location.locate(getUri("foo")).getValue().toString());
	}		
	
	@Test
	public void readFromClasspathUriPropertiesFile() {
		
		location.setUriString("test.properties");
		assertEquals("123", location.locate(getUri("abc")).getValue().toString());
		
		location.setUriString("classpath://test.properties");
		assertEquals("123", location.locate(getUri("abc")).getValue().toString());
		
		location.setUriString("classpath://nested/test.properties");
		assertEquals("123", location.locate(getUri("abc")).getValue().toString());
		
		location.setUriString("classpath://nested/test.properties");
		assertEquals("123", location.locate(getUri("abc")).getValue().toString());
	}
	
	@Test
	public void isSupportedClasspath() {
		
		
		
		assertTrue(location.supports(getUri("classpath:abc.xml")));
		assertTrue(location.supports(getUri("classpath://abc.xml")));
		assertTrue(location.supports(getUri("file:abc.xml")));
		assertTrue(location.supports(getUri("file://abc.xml")));
		
		assertFalse(location.supports(getUri("http://abc.xml")));
		assertFalse(location.supports(getUri("https://abc.xml")));
	}
	
	private URI getUri(String input) {
		
		URI uri = null;
		
		try {
			uri = new URI(input);
		} catch (Throwable t) {
			System.err.println(t);
		}
		
		return uri;
	}
}
