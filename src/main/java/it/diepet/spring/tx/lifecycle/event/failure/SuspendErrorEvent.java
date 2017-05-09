package it.diepet.spring.tx.lifecycle.event.failure;

import it.diepet.spring.tx.lifecycle.model.SuspendTransactionInfo;

/**
 * The Class SuspendErrorEvent.
 */
public class SuspendErrorEvent extends TransactionLifecycleErrorEvent<SuspendTransactionInfo> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2747252413803660635L;

	/**
	 * Instantiates a new suspend transaction error event.
	 *
	 * @param suspendTransactionInfo
	 *            the suspend transaction info
	 * @param error
	 *            the error
	 */
	public SuspendErrorEvent(SuspendTransactionInfo suspendTransactionInfo, RuntimeException error) {
		super(suspendTransactionInfo, error);
	}

}
