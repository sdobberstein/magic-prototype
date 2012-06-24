package magic.prototype.repository;

import java.net.URI;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TextFileRepositoryTest {

	private TextFileRepository repository;
	
	@Before
	public void setUp() {
		
		repository = new TextFileRepository();
	}
	
	@Test
	public void testRemoteFile() {
		
		URI uri = null;
				
		try {
			
			uri = new URI("https://raw.github.com/bluemagicsource/magic-config/master/src/test/resources/test.properties");
		} catch (Throwable t) {
			throw new RuntimeException(t.getMessage(), t);
		}
		
		String result = repository.get(uri);
		
		System.out.println(result);
		
		Assert.assertEquals("abc=123", result);
	}
	
	@Test
	public void testLocalFile() {
		
		URI uri = null;
		
		try {
			
			uri = new URI("test.properties");
		} catch (Throwable t) {
			throw new RuntimeException(t.getMessage(), t);
		}
		
		String result = repository.get(uri);
		
		System.out.println(result);
		
		Assert.assertEquals("abc=123", result);
	}
}
