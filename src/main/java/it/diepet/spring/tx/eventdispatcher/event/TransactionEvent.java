package it.diepet.spring.tx.eventdispatcher.event;

import org.springframework.context.ApplicationEvent;

import it.diepet.spring.tx.eventdispatcher.model.TransactionInfo;

/**
 * The Class TransactionBeginEvent.
 */
public class TransactionEvent<T extends TransactionInfo> extends
		ApplicationEvent {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4125435776589282017L;

	/**
	 * developer Instantiates a new transaction begin event.
	 *
	 * @param transaction
	 *            the transaction
	 */
	public TransactionEvent(T event) {
		super(event);
	}

	@SuppressWarnings("unchecked")
	public T getTransactionInfo() {
		return (T) this.getSource();
	}

}
