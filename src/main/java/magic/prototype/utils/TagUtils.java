package magic.prototype.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import magic.prototype.tags.DoubleTag;
import magic.prototype.tags.SingleTag;
import magic.prototype.tags.Tag;

public class TagUtils {

	public static String addSuffixTag(String query, Tag tag) {
		
		String decoratedTag = null;
		
		// FIRST CHECK TO SEE IF IT'S A DOUBLE TAG...SINGLE TAG ARE THE EXCEPTION
		if (tag instanceof SingleTag) {
		
			decoratedTag = addSingleTag(query, tag.getValue());
			
		// SINGLE TAGS GET APPENDED DIFFERENTLY
		} else if (tag instanceof DoubleTag) {
			
			decoratedTag = addTag(query, tag);
		}
		
		return decoratedTag;
	}
	
	private static String addTag(String query, Tag tag) {
		
		// PARSE OUT THE TAGS INTO THEIR SEPARATE PARTS
		Map<String, String> tagsMapping = parseQueryIntoTags(query);
		
		// Check to see if there are single tags in there.
		if (tagsMapping.containsKey(tag.getKey())) {
			
			String tagValue = tagsMapping.get(tag.getKey());
			
			// THIS BETTER BE THE SAME VALUE AS THE TAG COMING IN!
			if (tag.getValue() != null) {
				
				// IN TROUBLE, TRYING TO ADD THE SAME TAG WITH A DIFFERENT VALUE.  WHICH ONE IS RIGHT?!
				if (!tag.getValue().equals(tagValue)) {
					
					throw new RuntimeException("Unable to add same tag with different value!");
				}
				
			} else {
				
				// IN TROUBLE, THE TAG IN THERE ALREADY IS NULL AND THIS ONE ISN'T
				if (tagValue != null) {
					
					throw new RuntimeException("Unable to add same tag with different value!");
				}
			}
			
		} else {
			
			tagsMapping.put(tag.getKey(), tag.getValue());
		}
		
		String reorderedTags = reorderTags(tagsMapping);
		
		return reorderedTags;
	}

	/**
	 * Only used when appended to the suffix.
	 */
	public static String addSingleTag(String query, String value) {
		
		// PARSE OUT THE TAGS INTO THEIR SEPARATE PARTS
		Map<String, String> tagsMapping = parseQueryIntoTags(query);
		
		// Check to see if there are single tags in there.
		if (tagsMapping.containsKey(SingleTag.SINGLE_TAG_KEY)) {
			
			// GET OUT ALL THE SINGLE TAG VALUES
			String singleTags = tagsMapping.get(SingleTag.SINGLE_TAG_KEY);
			
			// ADD THE NEW TAG VALUE
			String updatedSingleTags = appendSingleTag(singleTags, value);
			
			tagsMapping.put(SingleTag.SINGLE_TAG_KEY, updatedSingleTags);
			
		} else {
			
			tagsMapping.put(SingleTag.SINGLE_TAG_KEY, value);
		}
		
		String reorderedTags = reorderTags(tagsMapping);
		
		return reorderedTags;
	}

	private static String appendSingleTag(String singleTags, String value) {
		
		// SPLIT ALONG COMMAS
		String[] tags = singleTags.split(",");
		
		// ADD TO THE SET, WE DON'T WANT DUPLICATES
		TreeSet<String> tagsSet = new TreeSet<String>(Arrays.asList(tags));
		tagsSet.add(value);
		
		// RE-WRITE THEM OUT IN ALPHABETICAL ORDER
		StringBuilder reorderedTags = new StringBuilder();
		Iterator<String> iter = tagsSet.iterator();
		
		while (iter.hasNext()) {
			
			reorderedTags.append(iter.next());
			
			if (iter.hasNext()) {
				reorderedTags.append(',');
			}
		}
		
		return reorderedTags.toString();
	}
	
	private static String reorderTags(Map<String, String> tagsMapping) {
		
		// RE-ASSEMBLE TAGS
		List<String> tagPairs = new ArrayList<String>();
		for (Map.Entry<String, String> tagPair : tagsMapping.entrySet()) {
			
			tagPairs.add(tagPair.getKey() + "=" + tagPair.getValue());
		}
		
		// CREATE TREESET TO GET RIDE OF DUPLICATES
		TreeSet<String> tags = new TreeSet<String>(tagPairs);
		StringBuilder reorderedTags = new StringBuilder();
		Iterator<String> iter = tags.iterator();
		
		while (iter.hasNext()) {
			
			reorderedTags.append(iter.next());
			
			if (iter.hasNext()) {
				reorderedTags.append('&');
			}
		}
		
		return reorderedTags.toString();
	}

	private static Map<String, String> parseQueryIntoTags(String query) {
		
		Map<String, String> tagsMapping = new HashMap<String, String>();
		
		// SPLIT ALONG AMPERSANDS, FOR THE VARIOUS TAGS
		String[] tagKeyValuePairs = query.split("&");
		
		// ADD EACH ONE TO THE MAPPING
		for (String tagKeyValuePair : tagKeyValuePairs) {
			
			// SPLIT ALONG EQUAL SIGNS
			String[] keyValue = tagKeyValuePair.split("=");
			
			tagsMapping.put(keyValue[0], keyValue[1]);
		}
		
		return tagsMapping;
	}
}