package magic.prototype.tags;

import java.net.InetAddress;

public class HostnameTag extends SingleTag {

	public HostnameTag(String value) {
		super(value);
	}
	
	@Override
	public String getValue() {
		
		if ((this.value == null) || (this.value.isEmpty())) {
			try {
				this.value = InetAddress.getLocalHost().getHostName();
			} catch (Throwable t) {
				throw new RuntimeException(t);
			}
		}
		
		return value;
	}
}
