package it.diepet.spring.tx.lifecycle.test.app.error;

@SuppressWarnings("serial")
public class ApplicationRuntimeException extends RuntimeException {

	public ApplicationRuntimeException() {
	}

	public ApplicationRuntimeException(String msg) {
		super(msg);
	}

	public ApplicationRuntimeException(Throwable t) {
		super(t);
	}

	public ApplicationRuntimeException(String msg, Throwable t) {
		super(msg, t);
	}

}
