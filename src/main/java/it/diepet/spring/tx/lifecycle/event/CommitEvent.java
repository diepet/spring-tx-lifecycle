package it.diepet.spring.tx.lifecycle.event;

import it.diepet.spring.tx.lifecycle.model.CommitTransactionInfo;

/**
 * The Class CommitEvent.
 */
public class CommitEvent extends TransactionLifecycleEvent<CommitTransactionInfo> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5387346432136249952L;

	/**
	 * Instantiates a new commit transaction event.
	 *
	 * @param commitTransactionInfo
	 *            the commit transaction info
	 */
	public CommitEvent(CommitTransactionInfo commitTransactionInfo) {
		super(commitTransactionInfo);
	}

}
