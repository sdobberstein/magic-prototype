package magic.prototype.location;

import java.net.URI;

import magic.prototype.encoding.Encoding;
import magic.prototype.property.MagicProperty;

public interface Location {

	public boolean supports(URI key);
	
	public URI getUri();
	
	public MagicProperty locate(URI key);
	
	public Encoding getEncoding();
}
