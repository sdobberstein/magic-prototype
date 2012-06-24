package magic.prototype.tags;

public class TripleTag extends DoubleTag {

	public static final String TRIPLE_TAG_SEPARATOR = ":";
	
	public TripleTag(String namespace, String predicate, String value) {
		super (namespace + TRIPLE_TAG_SEPARATOR + predicate, value);
	}
}