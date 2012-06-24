package magic.prototype.exceptions;

import java.net.URI;

import magic.prototype.property.MagicProperty;

public class LocatedProperty extends MagicProperty {

	public LocatedProperty(URI original, URI key, String value,	Class<?> locationClass) {
		super(original, key, value, locationClass);
	}

}
