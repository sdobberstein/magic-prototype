package magic.prototype.decorators;

import java.net.URI;
import java.net.URISyntaxException;

import magic.prototype.decorators.Decorator.Method;
import magic.prototype.tags.DoubleTag;

import org.junit.Assert;
import org.junit.Test;

public class DoubleTagDecoratorTest {

	private TagDecorator tagDecorator;
	
	@Test
	public void testPrefix() {
		
		// Set up the decorator
		tagDecorator = new TagDecorator();
		tagDecorator.setMethod(Method.PREFIX);
		
		DoubleTag tag = new DoubleTag("zen", "abc");
		tagDecorator.setTag(tag);
		
		// Create the URI
		URI uri = createURI("foo");
		
		// Perform decoration
		URI decoratedUri = tagDecorator.decorate(uri);
		
		System.out.println("\n---testPrefix--");
		System.out.println("Original: " + uri.toASCIIString());
		System.out.println("Decorated: " + decoratedUri.toASCIIString());
		
		Assert.assertEquals("abc.foo", decoratedUri.toASCIIString());
	}
	
	@Test
	public void testPrefixTwice() {
		
		// Set up the decorator
		tagDecorator = new TagDecorator();
		tagDecorator.setMethod(Method.PREFIX);
		
		DoubleTag tag = new DoubleTag("zen", "abc");
		tagDecorator.setTag(tag);
		
		// Create the URI
		URI uri = createURI("foo");
		
		// Perform decoration
		URI decoratedUri = tagDecorator.decorate(uri);
		URI twiceDecoratedUri = tagDecorator.decorate(decoratedUri);
		
		System.out.println("\n---testPrefixTwice--");
		System.out.println("Original: " + uri.toASCIIString());
		System.out.println("Decorated: " + decoratedUri.toASCIIString());
		System.out.println("Twice Decorated: " + twiceDecoratedUri.toASCIIString());
		
		Assert.assertEquals("abc.foo", decoratedUri.toASCIIString());
		Assert.assertEquals("abc.abc.foo", twiceDecoratedUri.toASCIIString());
	}
	
	@Test
	public void testSuffix() {
		
		// Set up the decorator
		tagDecorator = new TagDecorator();
		tagDecorator.setMethod(Method.SUFFIX);
		
		DoubleTag tag = new DoubleTag("zen", "abc");
		tagDecorator.setTag(tag);
		
		// Create the URI
		URI uri = createURI("foo");
		
		// Perform decoration
		URI decoratedUri = tagDecorator.decorate(uri);
		
		System.out.println("\n---testSuffix--");
		System.out.println("Original: " + uri.toASCIIString());
		System.out.println("Decorated: " + decoratedUri.toASCIIString());
		
		Assert.assertEquals("foo?zen=abc", decoratedUri.toASCIIString());
	}
	
	@Test
	public void testSuffixTwiceSameDecorator() {
		
		// Set up the decorator
		tagDecorator = new TagDecorator();
		tagDecorator.setMethod(Method.SUFFIX);
		
		DoubleTag tag = new DoubleTag("zen", "abc");
		tagDecorator.setTag(tag);
		
		// Create the URI
		URI uri = createURI("foo");
		
		// Perform decoration
		URI decoratedUri = tagDecorator.decorate(uri);
		URI twiceDecoratedUri = tagDecorator.decorate(decoratedUri);
		
		System.out.println("\n---testSuffixTwice--");
		System.out.println("Original: " + uri.toASCIIString());
		System.out.println("Decorated: " + decoratedUri.toASCIIString());
		System.out.println("Twice Decorated: " + twiceDecoratedUri.toASCIIString());
		
		Assert.assertEquals("foo?zen=abc", decoratedUri.toASCIIString());
		Assert.assertEquals("foo?zen=abc", twiceDecoratedUri.toASCIIString());
	}
	
	@Test
	public void testSuffixTwiceDifferentDecorator() {
		
		// Set up first decorator
		TagDecorator first = new TagDecorator();
		first.setMethod(Method.SUFFIX);
		
		// Set up first tag
		DoubleTag firstTag = new DoubleTag("bar", "def");
		first.setTag(firstTag);
		
		// Set up the decorator
		tagDecorator = new TagDecorator();
		tagDecorator.setMethod(Method.SUFFIX);
		
		DoubleTag tag = new DoubleTag("zen", "abc");
		tagDecorator.setTag(tag);
		
		// Create the URI
		URI uri = createURI("foo");
		
		// Perform decoration
		URI decoratedUri = first.decorate(uri);
		URI twiceDecoratedUri = tagDecorator.decorate(decoratedUri);
		
		System.out.println("\n---testSuffixTwice--");
		System.out.println("Original: " + uri.toASCIIString());
		System.out.println("Decorated: " + decoratedUri.toASCIIString());
		System.out.println("Twice Decorated: " + twiceDecoratedUri.toASCIIString());
		
		Assert.assertEquals("foo?bar=def", decoratedUri.toASCIIString());
		Assert.assertEquals("foo?bar=def&zen=abc", twiceDecoratedUri.toASCIIString());
	}
	
	private static URI createURI(String uriString) {
		
		// Create the URI
		URI uri = null;
		
		try {
			
			uri = new URI(uriString);
			
		} catch (URISyntaxException urise) {
			
			throw new RuntimeException(urise.getMessage(), urise);
		}
		
		return uri;
	}
}