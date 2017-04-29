package it.diepet.spring.tx.eventdispatcher.event;

import it.diepet.spring.tx.eventdispatcher.model.BeginTransactionInfo;

/**
 * The Class TransactionBeginEvent.
 */
public class BeginTransactionEvent extends TransactionEvent<BeginTransactionInfo> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2265263773330032633L;

	/**
	 * Instantiates a new begin transaction event.
	 *
	 * @param beginTransactionInfo the begin transaction info
	 */
	public BeginTransactionEvent(BeginTransactionInfo beginTransactionInfo) {
		super(beginTransactionInfo);
	}
	
}
