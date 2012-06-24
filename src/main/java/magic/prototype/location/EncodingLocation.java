package magic.prototype.location;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import magic.prototype.encoding.Encoding;

public abstract class EncodingLocation implements Location {

	public char[] INVALID_WINDOWS_CHARACTERS = { ':', '*', '\\', '<', '>', '|', '?' };
	
	public char[] INVALID_LINUX_CHARACTERS = { '/' };
	
	protected Encoding encoding = Encoding.NONE;
	
	public String getEncodedString(String unencoded) {
		
		String encoded = "";
		
		if (encoding == Encoding.FILE) {
			
			String property = System.getProperty("os.name");
			if (property.toLowerCase().startsWith("linux") || property.toLowerCase().startsWith("mac")) {
				encoded = unencoded.replaceAll("/", "");
				
			} else {
				encoded = encoded.replaceAll(":", "");
				encoded = encoded.replaceAll("\\*", "");
				encoded = encoded.replaceAll("\\\"", "");
				encoded = encoded.replaceAll("<", "");
				encoded = encoded.replaceAll(">", "");
				encoded = encoded.replaceAll("|", "");
				encoded = encoded.replaceAll("\\\\", "");
				encoded = encoded.replaceAll("\\?", "");
			}
			
		} else if (encoding == Encoding.WEB) {
			
			try {
				encoded = URLEncoder.encode(unencoded, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			
		} else {
			
			encoded = unencoded;
			
		}
		
		return encoded;
	}
	
	public void setEncoding(Encoding encoding) {
		this.encoding = encoding;
	}
	
	public Encoding getEncoding() {
		return encoding;
	}

}