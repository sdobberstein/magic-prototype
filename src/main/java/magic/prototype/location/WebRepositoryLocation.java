package magic.prototype.location;

import java.net.URI;

import magic.prototype.encoding.Encoding;

public class WebRepositoryLocation extends RepositoryBackedLocation {
	
	public WebRepositoryLocation() {
		this.encoding = Encoding.WEB;
	}

	public boolean supports(URI key) {
		
		String scheme = key.getScheme();
		return (scheme.isEmpty()) || (scheme.startsWith("http")); 
	}
}