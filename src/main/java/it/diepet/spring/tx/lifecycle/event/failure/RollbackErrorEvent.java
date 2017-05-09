package it.diepet.spring.tx.lifecycle.event.failure;

import it.diepet.spring.tx.lifecycle.model.RollbackTransactionInfo;

/**
 * The Class RollbackErrorEvent.
 */
public class RollbackErrorEvent extends TransactionLifecycleErrorEvent<RollbackTransactionInfo> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5794066984457838304L;

	/**
	 * Instantiates a new rollback transaction error event.
	 *
	 * @param rollbackTransactionInfo
	 *            the rollback transaction info
	 * @param error
	 *            the error
	 */
	public RollbackErrorEvent(RollbackTransactionInfo rollbackTransactionInfo, RuntimeException error) {
		super(rollbackTransactionInfo, error);
	}

}
