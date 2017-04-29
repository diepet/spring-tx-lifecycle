package it.diepet.spring.tx.eventdispatcher.event.failure;

import org.springframework.context.ApplicationEvent;

import it.diepet.spring.tx.eventdispatcher.model.TransactionInfo;

/**
 * The Class TransactionErrorEvent.
 *
 * @param <T>
 *            the generic type
 */
public class TransactionErrorEvent<T extends TransactionInfo> extends ApplicationEvent {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2518421182341826961L;

	/** The error. */
	private RuntimeException error;

	/**
	 * Instantiates a new transaction error event.
	 *
	 * @param event
	 *            the event
	 * @param error
	 *            the error
	 */
	public TransactionErrorEvent(T event, RuntimeException error) {
		super(event);
		this.error = error;
	}

	/**
	 * Gets the transaction info.
	 *
	 * @return the transaction info
	 */
	@SuppressWarnings("unchecked")
	public T getTransactionInfo() {
		return (T) this.getSource();
	}

	/**
	 * Gets the error.
	 *
	 * @return the error
	 */
	public RuntimeException getError() {
		return error;
	}
}
