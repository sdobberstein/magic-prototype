package magic.prototype.repository;

import java.net.URI;

public interface ModifiableRepository {
	
    /**
     * The put method inserts a new data record or updates
     * an existing data record's value
     *
     * @param  key - Unique uri (note that our URIs should already
     *         be normalized prior to reaching this interface.
     * @param  value -
     * @return return uri of the located value
     **/
	public String put(URI key, String value);

    /**
     * Removes the item from the repository.
     *
     * @param  key - Unique uri
     * @return Object value removed
     **/
	public String remove(URI key);
	
    /**
     * Clears the entire repository.
     **/
	public void clear();
}
