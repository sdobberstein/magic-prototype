package magic.prototype.tags;

public class SingleTag extends DoubleTag {
	
	public static final String SINGLE_TAG_KEY = "tags";
	
	public SingleTag(String value) {
		super(SINGLE_TAG_KEY, value);
	}
	
	@Override
	public String toString() {
		return value;
	}
}