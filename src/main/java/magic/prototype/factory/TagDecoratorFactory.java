package magic.prototype.factory;

import magic.prototype.decorators.TagDecorator;

public interface TagDecoratorFactory {

	public TagDecorator newInstance(Object data);
}
