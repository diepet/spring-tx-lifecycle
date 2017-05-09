package it.diepet.spring.tx.lifecycle.event;

import org.springframework.context.ApplicationEvent;

import it.diepet.spring.tx.lifecycle.model.TransactionInfo;

/**
 * The Class TransactionLifecycleEvent.
 *
 * @param <T>
 *            the generic type
 */
public class TransactionLifecycleEvent<T extends TransactionInfo> extends ApplicationEvent {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4125435776589282017L;

	/**
	 * developer Instantiates a new transaction begin event.
	 *
	 * @param event
	 *            the event
	 */
	public TransactionLifecycleEvent(T event) {
		super(event);
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

}
