package magic.prototype.factory;

import magic.prototype.decorators.Decorator.Method;
import magic.prototype.decorators.TagDecorator;
import magic.prototype.tags.DoubleTag;
import magic.prototype.tags.HashTag;
import magic.prototype.tags.HostnameTag;
import magic.prototype.tags.SingleTag;
import magic.prototype.tags.Tag;
import magic.prototype.tags.TripleTag;
import magic.prototype.utils.NodeUtils;

import org.w3c.dom.Node;

/**
 * Takes in a org.w3c.dom.Node and spits out a magic.prototype.decorators.TagDecorator object.
 * 
 * @author sean
 *
 */
public class TagDecoratorNodeFactoryImpl implements TagDecoratorFactory {

	public TagDecorator newInstance(Object data) {
		
		if (data instanceof Node) {
			
			return parseNode((Node) data);
			
		} else {
			
			throw new IllegalArgumentException("Was expecting " 
			                                 + Node.class.getName()
			                                 + " but found "
			                                 + data.getClass().getName());
		}
	}

	private TagDecorator parseNode(Node node) {
		
		TagDecorator tagDecorator = null;
		
		// LOOK FOR A HOSTNAME TAG
		if ("hostnameTag".equals(node.getNodeName())) {
			
			tagDecorator = parseHostnameTag(node);
			
		} else if ("hashTag".equals(node.getNodeName())) {
			
			tagDecorator = parseHashTag(node);
			
		} else if ("singleTag".equals(node.getNodeName())) {
			
			tagDecorator = parseSingleTag(node);
			
		} else if ("tripleTag".equals(node.getNodeName())) {
			
			tagDecorator = parseTripleTag(node);
			
		} else if ("doubleTag".equals(node.getNodeName())) {
			
			tagDecorator = parseDoubleTag(node);
			
		} else {
			
			tagDecorator = parseCustomTag(node);
			
		}
		
		return tagDecorator;
	}

	private TagDecorator parseHostnameTag(Node node) {
		
		String method = NodeUtils.getAttribute(node, "method");
		String value = getValue(node);
		
		if (method == null) {
			throw new IllegalArgumentException("One of the following is null: method=" + method);
		}
		
		Tag tag = new HostnameTag(value);
		TagDecorator tagDecorator = createTagDecorator(method, tag);
		return tagDecorator;
	}

	private TagDecorator parseHashTag(Node node) {
		
		String method = NodeUtils.getAttribute(node, "method");
		String value = getValue(node);
		
		if (method == null || value == null) {
			throw new IllegalArgumentException("One of the following is null: method=" + method
					                         + ", value=" + value);
		}
		
		Tag tag = new HashTag(value);
		TagDecorator tagDecorator = createTagDecorator(method, tag);
		return tagDecorator;
	}

	private TagDecorator parseSingleTag(Node node) {
		
		String method = NodeUtils.getAttribute(node, "method");
		String value = getValue(node);
		
		if (method == null || value == null) {
			throw new IllegalArgumentException("One of the following is null: method=" + method
					                         + ", value=" + value);
		}
		
		Tag tag = new SingleTag(value);
		TagDecorator tagDecorator = createTagDecorator(method, tag);
		return tagDecorator;
	}

	private TagDecorator parseTripleTag(Node node) {
		
		String method = NodeUtils.getAttribute(node, "method");
		String namespace = NodeUtils.getAttribute(node, "namespace");
		String predicate = NodeUtils.getAttribute(node, "predicate");
		String value = getValue(node);
		
		if (method == null
				|| namespace == null
				|| predicate == null
				|| value == null) {
			throw new IllegalArgumentException("One of the following is null: method=" + method
					                         + ", namespace=" + namespace
					                         + ", predicate=" + predicate
					                         + ", value=" + value);
		}
		
		Tag tag = new TripleTag(namespace, predicate, value);
		TagDecorator tagDecorator = createTagDecorator(method, tag);
		return tagDecorator;

	}

	private TagDecorator parseDoubleTag(Node node) {
		
		String method = NodeUtils.getAttribute(node, "method");
		String key = NodeUtils.getAttribute(node, "key");
		String value = getValue(node);
		
		if (method == null || key == null || value == null) {
			throw new IllegalArgumentException("One of the following is null: method=" + method
					                         + ", key=" + key
					                         + ", value=" + value);
		}
		
		Tag tag = new DoubleTag(key, value);
		TagDecorator tagDecorator = createTagDecorator(method, tag);
		return tagDecorator;
	}

	private TagDecorator createTagDecorator(String method, Tag tag) {
		
		TagDecorator tagDecorator = new TagDecorator();
		tagDecorator.setMethod(Method.valueOf(method.toUpperCase()));
		tagDecorator.setTag(tag);
		
		return tagDecorator;
	}

	private TagDecorator parseCustomTag(Node node) {
		throw new UnsupportedOperationException();
	}
	
	private String getValue(Node node) {
		
		// TRY TO GET THE VALUE FROM THE NODE.GETVALUE() METHOD FIRST
		String value = node.getTextContent();
		
		// IF THERE WAS NO VALUE, TRY TO GET IT FROM THE ATTRIBUTES
		if (value == null || value.trim().isEmpty()) {
			
			value = NodeUtils.getAttribute(node, "value");
		}
		
		return value;
	}

}
