package magic.prototype.location;

import java.net.URI;

import magic.prototype.encoding.Encoding;

public class FileRepositoryLocation extends RepositoryBackedLocation {
	
	public FileRepositoryLocation() {
		this.encoding = Encoding.FILE;
	}

	public boolean supports(URI key) {
		
		boolean supports = false;

		if ((key != null) && ("file".equals(key.getScheme()) || ("classpath".equals(key.getScheme())) || ("".equals(key.getScheme()) || (key.getScheme() == null)))) {
			supports = true;
		}
		return supports;
	}
}