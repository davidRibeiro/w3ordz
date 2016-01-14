package wwwordz.shared;

import java.io.Serializable;

public class WWWordzException extends java.lang.Exception implements Serializable{

	private static final long serialVersionUID = 1L;

	public WWWordzException() {
		super();
	}

	public WWWordzException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		//super(message, cause, enableSuppression, writableStackTrace);
	}

	public WWWordzException(String message, Throwable cause) {
		super(message, cause);
	}

	public WWWordzException(String message) {
		super(message);
	}

	public WWWordzException(Throwable cause) {
		super(cause);
	}

}
