package in.shamit.util.cors;


/**
 * Unsupported HTTP method exception.
 *
 * @author Vladimir Dzhuvinov
 */
public class UnsupportedHTTPMethodException extends CORSException {

	
	/**
	 * The requested HTTP method.
	 */
	private final String method;
	
	
	/**
	 * Creates a new unsupported HTTP method exception with the specified 
	 * message.
	 *
	 * @param message The message.
	 */
	public UnsupportedHTTPMethodException(final String message) {
	
		this(message, null);
	}
	
	
	/**
	 * Creates a new unsupported HTTP method exception with the specified 
	 * message and requested method.
	 *
	 * @param message         The message.
	 * @param requestedMethod The requested HTTP method, {@code null} if 
	 *                        unknown.
	 */
	public UnsupportedHTTPMethodException(final String message, final String requestedMethod) {
	
		super(message);
		
		method = requestedMethod;
	}
	
	
	/**
	 * Gets the requested method.
	 *
	 * @return The requested method, {@code null} if unknown or not set.
	 */
	public String getRequestedMethod() {
	
		return method;
	}
}
