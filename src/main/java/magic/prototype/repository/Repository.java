package magic.prototype.repository;

import java.net.URI;

public interface Repository {

	/**
     * @param  uri - Requested uri
     * @return boolean - true when the incoming uri is supported
     **/
	public boolean supports(URI uri);
	
    /**
     * @param  key - Unique uri
     * @return Object The current value corresponding to the key or MissingProperty
     *         if the value does not exist in the repository
     **/
	public String get(URI key);
	
    /**
     * Returns the total number of properties in the repository
     **/
	public int size();
}
