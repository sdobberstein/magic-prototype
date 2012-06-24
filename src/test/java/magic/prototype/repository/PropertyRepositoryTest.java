package magic.prototype.repository;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.net.URL;

import org.junit.Assert;
import org.junit.Test;

public class PropertyRepositoryTest {

	private PropertyRepository repository;
	
	@Test
	public void testRemotePropertiesFile() {
		
		String file = "https://raw.github.com/bluemagicsource/magic-config/master/src/test/resources/test.properties";
		Reader reader = getReaderFromFile(file);
		repository = new PropertyRepository(reader);
		
		URI uri = null;
		
		try {
			uri = new URI("abc");
		} catch (Throwable t) {
			throw new RuntimeException(t.getMessage(), t);
		}
		
		String result = repository.get(uri);
		
		System.out.println(result);
		Assert.assertEquals("123", result);
	}
	
	@Test
	public void testLocalPropertiesFile() {
		
		File file = getFileFromDisk("test.properties");
		repository = new PropertyRepository(file);
		
		URI uri = null;
		
		try {
			uri = new URI("abc");
		} catch (Throwable t) {
			throw new RuntimeException(t.getMessage(), t);
		}
		
		String result = repository.get(uri);
		
		System.out.println(result);
		Assert.assertEquals("123", result);
	}
	
	@Test
	public void testLocalXmlPropertiesFile() {
		
		File file = getFileFromDisk("testProperties.xml");
		repository = new PropertyRepository(file);
		
		URI uri = null;
		
		try {
			uri = new URI("foo");
		} catch (Throwable t) {
			throw new RuntimeException(t.getMessage(), t);
		}
		
		String result = repository.get(uri);
		
		System.out.println(result);
		Assert.assertEquals("bar", result);
	}
	
	private Reader getReaderFromFile(String file) {
		
		Reader reader = null;
		
		try {
			
			URL url = new URL(file);
			InputStream inputStream = url.openStream();
			reader = new InputStreamReader(inputStream);
			
			return reader;
		} catch (Throwable t) {
			throw new RuntimeException(t.getMessage(), t);
		}
	}
	
	private File getFileFromDisk(String file) {
		
		File propertiesFile = null;
		
		try {
			URI uri = this.getClass().getClassLoader().getResource(file).toURI();							
			propertiesFile = new File(uri);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return propertiesFile;
	}
}
