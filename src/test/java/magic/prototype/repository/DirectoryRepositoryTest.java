package magic.prototype.repository;

import java.net.URI;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class DirectoryRepositoryTest {

	private DirectoryRepository repository;
	
	@Test
	public void testRemoteDirectory() {
		
		repository = new DirectoryRepository("https://raw.github.com/bluemagicsource/magic-config/master/", new TextFileRepository());
		
		URI key = null;
		
		try {
			key = new URI("src/test/resources/test.properties");
		} catch (Throwable t) {
			throw new RuntimeException(t.getMessage(), t);
		}
		
		String result = repository.get(key);
		
		Assert.assertEquals("abc=123", result);
	}
	
	@Ignore("Not tested yet")
	@Test
	public void testLocalDirectory() {
		
	}
}
