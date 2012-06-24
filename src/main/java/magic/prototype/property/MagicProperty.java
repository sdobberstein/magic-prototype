package magic.prototype.property;

import java.net.URI;
import java.util.Map.Entry;

public class MagicProperty implements Entry<URI, String> {

	private URI key;
	private String value;
	private Class<?> locationClass;
	
	public MagicProperty(URI original, URI key, String value, Class<?> locationClass) {
		this.key = key;
		this.value = value;
		this.locationClass = locationClass;
	}
	
	public URI getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	public String setValue(String value) {
		this.value = value;
		return this.value;
	}

	public Class<?> getLocationClass() {
		return locationClass;
	}
}
