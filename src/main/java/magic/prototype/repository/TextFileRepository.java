package magic.prototype.repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;

public class TextFileRepository implements Repository {
	
	public boolean supports(URI uri) {
		return (uri != null);
	}

	public String get(URI key) {
		
		String value = null;
		StringBuilder b = new StringBuilder();
		
		try {
			
			InputStream inputStream = null;
			
			if (key.getScheme() != null) {
				
				URL url = key.toURL();
				inputStream = url.openStream();
			} else {
				
				URI uri = this.getClass().getClassLoader().getResource(key.toASCIIString()).toURI();
				File file = new File(uri);
				inputStream = new FileInputStream(file);
			}
			
			// Get the object of DataInputStream
			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
			String strLine;

			// Read File Line By Line
			while ((strLine = br.readLine()) != null) {
				// Print the content on the console
				b.append(strLine);
			}
			
			value = b.toString().trim();
			
			// Close the input stream
			inputStream.close();
		} catch (Throwable t) {
				
			System.err.println(t);
		}
		
		return value;
	}

	public int size() {
		return 1;
	}

}
