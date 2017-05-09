package it.diepet.spring.tx.lifecycle.event.failure;

import it.diepet.spring.tx.lifecycle.model.BeginTransactionInfo;

/**
 * The Class BeginErrorEvent.
 */
public class BeginErrorEvent extends TransactionLifecycleErrorEvent<BeginTransactionInfo> {

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
	public BeginErrorEvent(BeginTransactionInfo beginTransactionInfo, RuntimeException error) {
		super(beginTransactionInfo, error);
	}

}
