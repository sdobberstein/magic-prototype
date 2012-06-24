package magic.prototype.location;

import java.net.URI;

import magic.prototype.property.MagicProperty;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class WebRepositoryLocationTest {

	private WebRepositoryLocation location;
	
	@Before
	public void setUp() {
		
		location = new WebRepositoryLocation();
	}
	
	@Test
	public void testGetRemoteLocationPropertyWithFileSet() {
		
		System.out.println("--testGetRemoteLocationPropertyWithFileSet--");
		location.setUriString("https://raw.github.com/bluemagicsource/magic-config/master/src/test/resources/test.properties");
		
		URI key = null;
		
		try {
			key = new URI("abc");
		} catch (Throwable t) {
			throw new RuntimeException(t.getMessage(), t);
		}
		
		MagicProperty property = location.locate(key);
		System.out.println(property.getValue());
		
		Assert.assertEquals("123", property.getValue());
	}
	
	@Test
	public void testGetRemoteLocationProperyNoFileOrPrefix() {
		
		System.out.println("--testGetRemoteLocationProperyNoFileOrPrefix--");
		
		URI key = null;
		
		try {
			key = new URI("https://raw.github.com/bluemagicsource/magic-config/master/src/test/resources/test.properties");
		} catch (Throwable t) {
			throw new RuntimeException(t.getMessage(), t);
		}
			
		MagicProperty property = location.locate(key);
		System.out.println(property.getValue());
		
		Assert.assertEquals("abc=123", property.getValue());
	}
	
	@Test
	public void testGetRemoteLocationProperyWithPrefix() {
		
		System.out.println("--testGetRemoteLocationProperyWithPrefix--");
		location.setPrefix("https://raw.github.com/bluemagicsource/magic-config/master/");
		
		URI key = null;
		
		try {
			key = new URI("src/test/resources/test.properties");
		} catch (Throwable t) {
			throw new RuntimeException(t.getMessage(), t);
		}
		
		MagicProperty property = location.locate(key);
		System.out.println(property.getValue());
		
		Assert.assertEquals("abc=123", property.getValue());
	}
	
	@Test
	public void testGetRemoteLocationProperyWithPrefixInUri() {
		
		System.out.println("--testGetRemoteLocationProperyWithPrefixInUri--");
		location.setPrefix("https://raw.github.com/bluemagicsource/magic-config/master/");
		
		URI key = null;
		
		try {
			key = new URI("https://raw.github.com/bluemagicsource/magic-config/master/src/test/resources/test.properties");
		} catch (Throwable t) {
			throw new RuntimeException(t.getMessage(), t);
		}
		
		MagicProperty property = location.locate(key);
		System.out.println(property.getValue());
		
		Assert.assertEquals("abc=123", property.getValue());
	}
}