package magic.prototype.repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.Reader;
import java.net.URI;
import java.util.Properties;

public class PropertyRepository implements Repository {

	private Properties properties = new Properties();
	
	public PropertyRepository(File file) {

		try {
			// XML FILE
			if (file.getName().endsWith(".xml")) {
				properties.loadFromXML(new FileInputStream(file));
			} else {
				// STANDARD PROPERTIES FILE
				properties.load(new FileInputStream(file));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public PropertyRepository(Reader reader) {
		try {
			properties.load(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean supports(URI uri) {
		return (uri != null);
	}

	public String get(URI key) {
		return properties.getProperty(key.toASCIIString());
	}

	public int size() {
		return properties.size();
	}

}
