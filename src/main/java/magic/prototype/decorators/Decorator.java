package magic.prototype.decorators;

import java.net.URI;
import java.util.Collection;

public interface Decorator {

	public enum Method { PREFIX, SUFFIX }
	
	public Method getMethod();
	
	public URI decorate(URI uri);
	
	public Collection<URI> decorate(Collection<URI> uris);
}