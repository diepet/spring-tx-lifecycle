package it.diepet.spring.tx.eventdispatcher.event;

import it.diepet.spring.tx.eventdispatcher.model.SuspendTransactionInfo;

/**
 * The Class SuspendEvent.
 */
public class SuspendEvent extends TransactionLifecycleEvent<SuspendTransactionInfo> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4177514771533256504L;

	/**
	 * Instantiates a new suspend transaction event.
	 *
	 * @param suspendTransactionInfo
	 *            the suspend transaction info
	 */
	public SuspendEvent(SuspendTransactionInfo suspendTransactionInfo) {
		super(suspendTransactionInfo);
	}

}
