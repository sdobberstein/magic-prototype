package magic.prototype.tags;

import java.net.InetAddress;

import org.junit.Assert;
import org.junit.Test;

public class TagsTest {

	@Test
	public void testCreateSingleTag() {
		
		Tag tag = new SingleTag("abc");
		
		Assert.assertEquals(SingleTag.SINGLE_TAG_KEY, tag.getKey());
		Assert.assertEquals("abc", tag.getValue());
		Assert.assertEquals("tags=abc", tag.toFullString());
		Assert.assertEquals("abc", tag.toString());
	}
	
	@Test
	public void testCreateDoubleTag() {
		
		Tag tag = new DoubleTag("system", "production");
		
		Assert.assertEquals("system", tag.getKey());
		Assert.assertEquals("production", tag.getValue());
		Assert.assertEquals("system=production", tag.toFullString());
		Assert.assertEquals("system=production", tag.toString());
	}
	
	@Test
	public void testCreateTripleTag() {
		
		Tag tag = new TripleTag("service", "odometer", "1");
		
		Assert.assertEquals("service:odometer", tag.getKey());
		Assert.assertEquals("1", tag.getValue());
		Assert.assertEquals("service:odometer=1", tag.toFullString());
		Assert.assertEquals("service:odometer=1", tag.toString());
	}
	
	@Test
	public void testCreateHashTag() {
		
		Tag tag = new HashTag("abc");
		
		Assert.assertEquals(SingleTag.SINGLE_TAG_KEY, tag.getKey());
		Assert.assertEquals("#abc", tag.getValue());
		Assert.assertEquals("tags=#abc", tag.toFullString());
		Assert.assertEquals("#abc", tag.toString());
	}
	
	@Test
	public void testCreateHostnameTagDefaultKeyFixedHost() {
		
		Tag tag = new HostnameTag("some.host");
		
		Assert.assertEquals(SingleTag.SINGLE_TAG_KEY, tag.getKey());
		Assert.assertEquals("some.host", tag.getValue());
		Assert.assertEquals("tags=some.host", tag.toFullString());
		Assert.assertEquals("some.host", tag.toString());
	}
	
	@Test
	public void testCreateHostnameTagDefaultKeyEmptyHost() {
		
		Tag tag = new HostnameTag("");
		
		String localhost = null;
		
		try {
			
			localhost = InetAddress.getLocalHost().getHostName();
		} catch (Throwable t) {
			throw new RuntimeException(t.getMessage(), t);
		}
		
		Assert.assertEquals(SingleTag.SINGLE_TAG_KEY, tag.getKey());
		Assert.assertEquals(localhost, tag.getValue());
		Assert.assertEquals("tags=" + localhost, tag.toFullString());
		Assert.assertEquals(localhost, tag.toString());
	}
	
	@Test
	public void testCreateHostnameTagDefaultKeyNullHost() {
		
		Tag tag = new HostnameTag(null);
		
		String localhost = null;
		
		try {
			
			localhost = InetAddress.getLocalHost().getHostName();
		} catch (Throwable t) {
			throw new RuntimeException(t.getMessage(), t);
		}
		
		Assert.assertEquals(SingleTag.SINGLE_TAG_KEY, tag.getKey());
		Assert.assertEquals(localhost, tag.getValue());
		Assert.assertEquals("tags=" + localhost, tag.toFullString());
		Assert.assertEquals(localhost, tag.toString());
	}
}
