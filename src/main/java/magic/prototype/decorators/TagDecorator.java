package magic.prototype.decorators;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;

import magic.prototype.tags.Tag;
import magic.prototype.utils.TagUtils;

public class TagDecorator implements Decorator {

	protected String prefixSeparator = ".";
	protected Method method;
	protected Tag tag;

	public URI decorate(URI uri) {
		
		URI result = null;
		
		if (method == Method.PREFIX) {
			
			result = decoratePrefix(uri);
			
		} else if (method == Method.SUFFIX) {
			
			result = decorateSuffix(uri);
			
		}
		
		return result;
	}

	protected URI decoratePrefix(URI uri) {
		
		String scheme = uri.getScheme();
		String schemeSpecificPart = uri.getSchemeSpecificPart();
		
		URI result = null;
		
		try {
			
			result = new URI(scheme, tag.getValue() + prefixSeparator + schemeSpecificPart, null);
			
		} catch (URISyntaxException urise) {
			
			throw new RuntimeException(urise.getMessage(), urise);
		}
		
		return result;
	}

	protected URI decorateSuffix(URI uri) {
		
		String scheme = uri.getScheme();
		String schemeSpecificPart = uri.getSchemeSpecificPart();
		
		String decoratedSchemeSpecificPart = null;
		
		if (schemeSpecificPart.contains("?")) {
			
			int index = schemeSpecificPart.indexOf("?");
			String sspWithoutQuery = schemeSpecificPart.substring(0, index);
			String query = schemeSpecificPart.substring(index + 1);
			
			// ADD TAG
			String normalizedQuery = TagUtils.addSuffixTag(query, tag);
			
			// AN ERROR OCCURRED
			if (normalizedQuery == null) {
				throw new RuntimeException();
			}
			
			// REAPPEND THE TWO PARTS
			decoratedSchemeSpecificPart = sspWithoutQuery + "?" + normalizedQuery;	
			
		} else {
			
			// GOING TO BE THE FIRST TAG, SO WE NEED TO ADD ON THE FULL TAG
			decoratedSchemeSpecificPart = schemeSpecificPart + "?" + tag.toFullString();
		}
		
		URI result = null;
		
		try {
			
			result = new URI(scheme, decoratedSchemeSpecificPart, null);
			
		} catch (URISyntaxException urise) {
			
			throw new RuntimeException(urise.getMessage(), urise);
		}
		
		return result;
	}

	public Collection<URI> decorate(Collection<URI> uris) {
		
		Collection<URI> decorateUris = new ArrayList<URI>();
		
		URI rval = null;
		
		for (URI uri : uris) {
			
			rval = decorate(uri);
			
			if (rval != null) {
				decorateUris.add(rval);
			}
		}
		
		return decorateUris;
	}

	public void setMethod(Method method) {
		this.method = method;
	}
	
	public Method getMethod() {
		return method;
	}

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	public String getPrefixSeparator() {
		return prefixSeparator;
	}

	public void setPrefixSeparator(String prefixSeparator) {
		this.prefixSeparator = prefixSeparator;
	}
}