package it.diepet.spring.tx.eventdispatcher.event;

import it.diepet.spring.tx.eventdispatcher.model.SuspendTransactionInfo;

/**
 * The Class SuspendTransactionEvent.
 */
public class SuspendTransactionEvent extends TransactionEvent<SuspendTransactionInfo> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4177514771533256504L;

	/**
	 * Instantiates a new suspend transaction event.
	 *
	 * @param suspendTransactionInfo the suspend transaction info
	 */
	public SuspendTransactionEvent(SuspendTransactionInfo suspendTransactionInfo) {
		super(suspendTransactionInfo);
	}

}
