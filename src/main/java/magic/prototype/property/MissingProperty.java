package magic.prototype.property;

import java.net.URI;

import magic.prototype.exceptions.MissingPropertyException;

public class MissingProperty extends MagicProperty {

	public MissingProperty(URI original, URI key, Class<?> locationClass) {
		super(original, key, null, locationClass);
	}

	@Override
	public String getValue() {
		throw new MissingPropertyException();
	}
}