package magic.prototype.location;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import magic.prototype.encoding.Encoding;
import magic.prototype.exceptions.LocatedProperty;
import magic.prototype.property.MagicProperty;
import magic.prototype.property.MissingProperty;
import magic.prototype.repository.DirectoryRepository;
import magic.prototype.repository.PropertyRepository;
import magic.prototype.repository.Repository;
import magic.prototype.repository.TextFileRepository;

public abstract class RepositoryBackedLocation extends EncodingLocation {

	protected URI uri;
	protected String prefix;
	protected Repository repository;
	
	protected void init() {
		
		// IF A FILE HAS BEEN PROVIDED...
		if (this.uri != null) {

			this.repository = getPropertyRepository();
			
		} else {
			
			this.repository = new DirectoryRepository(this.prefix, new TextFileRepository());
		}
	}
	
	// By the time this is called, there should be a check to make sure the URI is not null.
	private PropertyRepository getPropertyRepository() {
		
		PropertyRepository propertyRepository = null;
		
		// DEALING WITH A WEB LOCATION IF:
		// 1)  The scheme is null, but there is a prefix.
		// 2)  The scheme starts with "http"
		if ((this.uri.getScheme() == null && this.prefix != null) || (this.uri.getScheme() != null && this.uri.getScheme().startsWith("http"))) {
			
			Reader reader = null;
			
			try {
				
				URL url = this.uri.toURL();
				InputStream inputStream = url.openStream();
				reader = new InputStreamReader(inputStream);
				
				propertyRepository = new PropertyRepository(reader);
			} catch (Throwable t) {
				System.err.println(t);
			}
		} else {
			
			// DEALING WITH A FILE LOCATION
			File propertiesFile = null;
			
			try {
				URI uri = this.getClass().getClassLoader().getResource(this.uri.toASCIIString()).toURI();
				propertiesFile = new File(uri);
				
				propertyRepository = new PropertyRepository(propertiesFile);
			} catch (Throwable t) {
				System.err.println(t);
			}
		}
		
		return propertyRepository;
	}
	
	public MagicProperty locate(URI key) {
		
		if (repository == null) {
			init();
		}
		
		MagicProperty property = null;
		URI encodedUri = encodeUri(key);
		String rval = getPropertyFromRepository(encodedUri);
		
		if (rval != null) {
			// TODO:
			property = new LocatedProperty(null, key, rval, this.getClass());
		} else {
			// TODO:
			property = new MissingProperty(null, key, this.getClass());
		}
		
		return property;
	}
	
	protected String getPropertyFromRepository(URI encodedUri) {
		
		return this.repository.get(encodedUri);
	}

	private URI encodeUri(URI key) {
		
		String uriString = key.toASCIIString();
		URI rval = null;
		
		if (uriString.contains("?") && this.encoding != Encoding.NONE) {
			
			int index = uriString.indexOf("?");
			String sspWithoutQuery = uriString.substring(0, index);
			String query = uriString.substring(index + 1);
			
			// ENCODE STRING
			String encodedString = getEncodedString(query);
			
			try {
				
				rval = new URI(sspWithoutQuery + "?" + encodedString);
			} catch (Throwable t) {
				throw new RuntimeException(t.getMessage(), t);
			}
			
		} else {
			rval = key;
		}
		
		return rval;
	}

	public void setUriString(String uriString) {
		
		try {
			
			this.uri = new URI(uriString);
			
		} catch (URISyntaxException urise) {
			
			throw new IllegalStateException();
			
		}
	}
	
	public void setUri(URI uri) {
		this.uri = uri;
	}
	
	public URI getUri() {
		return uri;
	}

	public Repository getRepository() {
		return repository;
	}

	public void setRepository(Repository repository) {
		this.repository = repository;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

}