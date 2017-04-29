package it.diepet.spring.tx.eventdispatcher.event.failure;

import it.diepet.spring.tx.eventdispatcher.model.BeginTransactionInfo;

/**
 * The Class BeginTransactionErrorEvent.
 */
public class BeginTransactionErrorEvent extends TransactionErrorEvent<BeginTransactionInfo> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2987892365067503025L;

	/**
	 * Instantiates a new begin transaction error event.
	 *
	 * @param beginTransactionInfo
	 *            the begin transaction info
	 * @param error
	 *            the error
	 */
	public BeginTransactionErrorEvent(BeginTransactionInfo beginTransactionInfo, RuntimeException error) {
		super(beginTransactionInfo, error);
	}

}
