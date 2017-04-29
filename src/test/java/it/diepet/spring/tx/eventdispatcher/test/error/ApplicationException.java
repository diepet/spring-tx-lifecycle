package it.diepet.spring.tx.eventdispatcher.test.error;

@SuppressWarnings("serial")
public class ApplicationException extends Exception {

	public ApplicationException() {
	}

	public ApplicationException(String msg) {
		super(msg);
	}

	public ApplicationException(Throwable t) {
		super(t);
	}

	public ApplicationException(String msg, Throwable t) {
		super(msg, t);
	}

	public ApplicationException(String msg, Throwable t, boolean enableSuppression, boolean writableStackTrace) {
		super(msg, t, enableSuppression, writableStackTrace);
	}

}
