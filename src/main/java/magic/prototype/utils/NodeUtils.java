package magic.prototype.utils;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class NodeUtils {

	public static String getAttribute(Node node, String attributeName) {
		
		String rval = null;
		
		if (node != null) {
			
			NamedNodeMap attributes = node.getAttributes();
			
			if (attributes != null) {
				
				Node valueNode = attributes.getNamedItem(attributeName);
				
				if (valueNode != null
						&& valueNode.getNodeValue() != null
						&& !valueNode.getNodeValue().trim().isEmpty()) {
					
					rval = valueNode.getNodeValue().trim();
				}
			}
		}
		
		return rval;
	}
}
