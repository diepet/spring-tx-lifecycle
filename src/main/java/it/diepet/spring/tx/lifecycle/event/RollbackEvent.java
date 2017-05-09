package it.diepet.spring.tx.lifecycle.event;

import it.diepet.spring.tx.lifecycle.model.RollbackTransactionInfo;

/**
 * The Class RollbackEvent.
 */
public class RollbackEvent extends TransactionLifecycleEvent<RollbackTransactionInfo> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 528647198184522029L;

	/**
	 * Instantiates a new rollback transaction event.
	 *
	 * @param rollbackTransactionInfo
	 *            the rollback transaction info
	 */
	public RollbackEvent(RollbackTransactionInfo rollbackTransactionInfo) {
		super(rollbackTransactionInfo);
	}

}
