package it.diepet.spring.tx.eventdispatcher.event;

import it.diepet.spring.tx.eventdispatcher.model.CommitTransactionInfo;

/**
 * The Class TransactionBeginEvent.
 */
public class CommitTransactionEvent extends TransactionEvent<CommitTransactionInfo> {


	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5387346432136249952L;

	/**
	 * Instantiates a new commit transaction event.
	 *
	 * @param commitTransactionInfo the commit transaction info
	 */
	public CommitTransactionEvent(CommitTransactionInfo commitTransactionInfo) {
		super(commitTransactionInfo);
	}

}
