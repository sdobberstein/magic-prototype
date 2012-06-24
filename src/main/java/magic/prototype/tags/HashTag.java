package magic.prototype.tags;

public class HashTag extends SingleTag {

	public static final String HASHTAG_PREFIX = "#";
	
	public HashTag(String value) {
		// Needed because no empty constructor is set.
		super(value);
		
		// Now make sure the it started with a # symbol.
		if (!value.startsWith("#")) {
			this.value = "#" + value;
		}
	}

}