package it.diepet.spring.tx.lifecycle.event.failure;

import it.diepet.spring.tx.lifecycle.model.SetRollbackOnlyTransactionInfo;

/**
 * The Class SetRollbackOnlyErrorEvent.
 */
public class SetRollbackOnlyErrorEvent extends TransactionLifecycleErrorEvent<SetRollbackOnlyTransactionInfo> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -9214810245384000054L;

	/**
	 * Instantiates a new sets the rollback only transaction error event.
	 *
	 * @param setRollbackOnlyTransactionInfo
	 *            the set rollback only transaction info
	 * @param error
	 *            the error
	 */
	public SetRollbackOnlyErrorEvent(SetRollbackOnlyTransactionInfo setRollbackOnlyTransactionInfo,
			RuntimeException error) {
		super(setRollbackOnlyTransactionInfo, error);
	}

}
