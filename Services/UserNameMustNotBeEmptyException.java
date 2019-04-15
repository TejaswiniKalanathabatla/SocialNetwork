package Services;

public class UserNameMustNotBeEmptyException extends Exception {
	private static final long serialVersionUID = 8948303014588489880L;

	public UserNameMustNotBeEmptyException() {
		super();

	}

	public UserNameMustNotBeEmptyException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

	public UserNameMustNotBeEmptyException(String message, Throwable cause) {
		super(message, cause);

	}

	public UserNameMustNotBeEmptyException(String message) {
		super(message);

	}

	public UserNameMustNotBeEmptyException(Throwable cause) {
		super(cause);

	}

}


