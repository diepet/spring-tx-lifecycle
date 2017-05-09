package it.diepet.spring.tx.lifecycle.event.failure;

import org.springframework.context.ApplicationEvent;

import it.diepet.spring.tx.lifecycle.model.TransactionInfo;

/**
 * The Class TransactionLifecycleErrorEvent.
 *
 * @param <T>
 *            the generic type
 */
public class TransactionLifecycleErrorEvent<T extends TransactionInfo> extends ApplicationEvent {

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
	public TransactionLifecycleErrorEvent(T event, RuntimeException error) {
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
