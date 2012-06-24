package magic.prototype.repository;

import java.io.File;
import java.net.URI;

public class DirectoryRepository implements Repository {

	private Repository repository;
	private String path;
	
	public DirectoryRepository(String path, Repository repository) {
		this.setPath(path);
		this.setRepository(repository);
	}
	
	public boolean supports(URI uri) {
		if (this.repository == null) {
			return false;
		} else {
			return this.repository.supports(uri);
		}
	}

	public String get(URI key) {
		
		// CHECK IF THE KEY HAS THE PREFIX APPENDED TO IT, IF NOT THEN APPEND IT.
		String originalKey = key.toString();
		String normalizedKey = null;
		
		if (path != null) {
			
			// CHECK TO SEE IF THE PREFIX IS IN THE KEY ALREADY
			// IF IT IS, THEN NO NEED TO APPEND THE PREFIX
			if (!originalKey.startsWith(path)) {
				normalizedKey = path + originalKey;
			} else {
				normalizedKey = originalKey;
			}
		} else {
			normalizedKey = originalKey;
		}
		
		URI uri = null;
		
		try {
			uri = new URI(normalizedKey);
		} catch (Throwable t) {
			System.err.println(t);
		}
		
		return this.repository.get(uri);
	}

	public int size() {
		if (this.repository == null) {
			return 0;
		} else {
			return this.repository.size();
		}
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		
		if (path != null) {
			
			if (!path.endsWith(File.separator)) {
				path = path + File.separator;
			}
		} else {
			path = "";
		}
		
		this.path = path;
	}

	public Repository getRepository() {
		return repository;
	}

	public void setRepository(Repository repository) {
		this.repository = repository;
	}

}
