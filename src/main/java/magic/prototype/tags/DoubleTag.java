package magic.prototype.tags;

public class DoubleTag implements Tag {

	protected String key;
	protected String value;
	
	public DoubleTag(String key, String value) {
		this.key = key;
		this.value = value;
	}
	
	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}
	
	public String toFullString() {
		return key + "=" + value;
	}
	
	@Override
	public String toString() {
		return key + "=" + value;
	}
}