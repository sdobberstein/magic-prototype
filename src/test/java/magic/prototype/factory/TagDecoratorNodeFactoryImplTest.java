package magic.prototype.factory;

import java.net.InetAddress;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import magic.prototype.decorators.Decorator.Method;
import magic.prototype.decorators.TagDecorator;
import magic.prototype.tags.DoubleTag;
import magic.prototype.tags.HashTag;
import magic.prototype.tags.HostnameTag;
import magic.prototype.tags.SingleTag;
import magic.prototype.tags.TripleTag;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class TagDecoratorNodeFactoryImplTest {

	private TagDecoratorFactory factory;
	
	@Before
	public void setUp() {
		
		factory = new TagDecoratorNodeFactoryImpl();
	}
	
	@Test
	public void testHostnameTagDecorator() {
		
		// CREATE THE NODE
		Document doc = createDomDocument();
		Element data = doc.createElement("hostnameTag");
		data.setAttribute("method", "suffix");
		
		String localhost = null;
		
		try {
			
			localhost = InetAddress.getLocalHost().getHostName();
		} catch (Throwable t) {
			throw new RuntimeException(t.getMessage(), t);
		}
		
		TagDecorator tagDecorator = factory.newInstance(data);
		
		Assert.assertEquals(Method.SUFFIX, tagDecorator.getMethod());
		Assert.assertNotNull(tagDecorator.getTag());
		Assert.assertTrue(tagDecorator.getTag() instanceof HostnameTag);
		Assert.assertEquals("tags", tagDecorator.getTag().getKey());
		Assert.assertEquals(localhost, tagDecorator.getTag().getValue());
	}
	
	@Test
	public void testHostnameTagDecoratorFixedHost() {
		
		// CREATE THE NODE
		Document doc = createDomDocument();
		Element data = doc.createElement("hostnameTag");
		data.setTextContent("some.host");
		data.setAttribute("method", "suffix");
		
		TagDecorator tagDecorator = factory.newInstance(data);
		
		Assert.assertEquals(Method.SUFFIX, tagDecorator.getMethod());
		Assert.assertNotNull(tagDecorator.getTag());
		Assert.assertTrue(tagDecorator.getTag() instanceof HostnameTag);
		Assert.assertEquals("tags", tagDecorator.getTag().getKey());
		Assert.assertEquals("some.host", tagDecorator.getTag().getValue());
	}
	
	@Test
	public void testHostnameTagDecoratorFixedHostAttribute() {
		
		// CREATE THE NODE
		Document doc = createDomDocument();
		Element data = doc.createElement("hostnameTag");
		data.setAttribute("value", "some.host");
		data.setAttribute("method", "suffix");
		
		TagDecorator tagDecorator = factory.newInstance(data);
		
		Assert.assertEquals(Method.SUFFIX, tagDecorator.getMethod());
		Assert.assertNotNull(tagDecorator.getTag());
		Assert.assertTrue(tagDecorator.getTag() instanceof HostnameTag);
		Assert.assertEquals("tags", tagDecorator.getTag().getKey());
		Assert.assertEquals("some.host", tagDecorator.getTag().getValue());
	}
	
	@Test
	public void testHashTagDecorator() {
		
		// CREATE THE NODE
		Document doc = createDomDocument();
		Element data = doc.createElement("hashTag");
		data.setTextContent("production");
		data.setAttribute("method", "suffix");
		
		TagDecorator tagDecorator = factory.newInstance(data);
		
		Assert.assertEquals(Method.SUFFIX, tagDecorator.getMethod());
		Assert.assertNotNull(tagDecorator.getTag());
		Assert.assertTrue(tagDecorator.getTag() instanceof HashTag);
		Assert.assertEquals("tags", tagDecorator.getTag().getKey());
		Assert.assertEquals("#production", tagDecorator.getTag().getValue());
	}
	
	@Test
	public void testHashTagDecoratorAttribute() {
		
		// CREATE THE NODE
		Document doc = createDomDocument();
		Element data = doc.createElement("hashTag");
		data.setAttribute("value", "production");
		data.setAttribute("method", "suffix");
		
		TagDecorator tagDecorator = factory.newInstance(data);
		
		Assert.assertEquals(Method.SUFFIX, tagDecorator.getMethod());
		Assert.assertNotNull(tagDecorator.getTag());
		Assert.assertTrue(tagDecorator.getTag() instanceof HashTag);
		Assert.assertEquals("tags", tagDecorator.getTag().getKey());
		Assert.assertEquals("#production", tagDecorator.getTag().getValue());
	}
	
	@Test
	public void testSingleTagDecorator() {
		
		// CREATE THE NODE
		Document doc = createDomDocument();
		Element data = doc.createElement("singleTag");
		data.setTextContent("production");
		data.setAttribute("method", "suffix");
		
		TagDecorator tagDecorator = factory.newInstance(data);
		
		Assert.assertEquals(Method.SUFFIX, tagDecorator.getMethod());
		Assert.assertNotNull(tagDecorator.getTag());
		Assert.assertTrue(tagDecorator.getTag() instanceof SingleTag);
		Assert.assertEquals("tags", tagDecorator.getTag().getKey());
		Assert.assertEquals("production", tagDecorator.getTag().getValue());
	}
	
	@Test
	public void testSingleTagDecoratorAttribute() {
		
		// CREATE THE NODE
		Document doc = createDomDocument();
		Element data = doc.createElement("singleTag");
		data.setAttribute("value", "production");
		data.setAttribute("method", "suffix");
		
		TagDecorator tagDecorator = factory.newInstance(data);
		
		Assert.assertEquals(Method.SUFFIX, tagDecorator.getMethod());
		Assert.assertNotNull(tagDecorator.getTag());
		Assert.assertTrue(tagDecorator.getTag() instanceof SingleTag);
		Assert.assertEquals("tags", tagDecorator.getTag().getKey());
		Assert.assertEquals("production", tagDecorator.getTag().getValue());
	}
	
	@Test
	public void testDoubleTagDecorator() {
		
		// CREATE THE NODE
		Document doc = createDomDocument();
		Element data = doc.createElement("doubleTag");
		data.setTextContent("production");
		data.setAttribute("key", "systemType");
		data.setAttribute("method", "suffix");
		
		TagDecorator tagDecorator = factory.newInstance(data);
		
		Assert.assertEquals(Method.SUFFIX, tagDecorator.getMethod());
		Assert.assertNotNull(tagDecorator.getTag());
		Assert.assertTrue(tagDecorator.getTag() instanceof DoubleTag);
		Assert.assertEquals("systemType", tagDecorator.getTag().getKey());
		Assert.assertEquals("production", tagDecorator.getTag().getValue());
	}
	
	@Test
	public void testDoubleTagDecoratorAttribute() {
		
		// CREATE THE NODE
		Document doc = createDomDocument();
		Element data = doc.createElement("doubleTag");
		data.setAttribute("value", "production");
		data.setAttribute("key", "systemType");
		data.setAttribute("method", "suffix");
		
		TagDecorator tagDecorator = factory.newInstance(data);
		
		Assert.assertEquals(Method.SUFFIX, tagDecorator.getMethod());
		Assert.assertNotNull(tagDecorator.getTag());
		Assert.assertTrue(tagDecorator.getTag() instanceof DoubleTag);
		Assert.assertEquals("systemType", tagDecorator.getTag().getKey());
		Assert.assertEquals("production", tagDecorator.getTag().getValue());
	}
	
	@Test
	public void testTripleTagDecorator() {
		
		// CREATE THE NODE
		Document doc = createDomDocument();
		Element data = doc.createElement("tripleTag");
		data.setTextContent("30.5");
		data.setAttribute("namespace", "geo");
		data.setAttribute("predicate", "lat");
		data.setAttribute("method", "suffix");
		
		TagDecorator tagDecorator = factory.newInstance(data);
		
		Assert.assertEquals(Method.SUFFIX, tagDecorator.getMethod());
		Assert.assertNotNull(tagDecorator.getTag());
		Assert.assertTrue(tagDecorator.getTag() instanceof TripleTag);
		Assert.assertEquals("geo:lat", tagDecorator.getTag().getKey());
		Assert.assertEquals("30.5", tagDecorator.getTag().getValue());
	}
	
	@Test
	public void testTripleTagDecoratorAttribute() {
		
		// CREATE THE NODE
		Document doc = createDomDocument();
		Element data = doc.createElement("tripleTag");
		data.setAttribute("value", "30.5");
		data.setAttribute("namespace", "geo");
		data.setAttribute("predicate", "lat");
		data.setAttribute("method", "suffix");
		
		TagDecorator tagDecorator = factory.newInstance(data);
		
		Assert.assertEquals(Method.SUFFIX, tagDecorator.getMethod());
		Assert.assertNotNull(tagDecorator.getTag());
		Assert.assertTrue(tagDecorator.getTag() instanceof TripleTag);
		Assert.assertEquals("geo:lat", tagDecorator.getTag().getKey());
		Assert.assertEquals("30.5", tagDecorator.getTag().getValue());
	}
	
	public static Document createDomDocument() {
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.newDocument();
            return doc;
        } catch (ParserConfigurationException e) {
        }
        return null;
    }
}
