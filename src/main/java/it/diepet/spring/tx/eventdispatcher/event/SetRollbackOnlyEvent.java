package it.diepet.spring.tx.eventdispatcher.event;

import it.diepet.spring.tx.eventdispatcher.model.SetRollbackOnlyTransactionInfo;

/**
 * The Class SetRollbackOnlyEvent.
 */
public class SetRollbackOnlyEvent extends TransactionLifecycleEvent<SetRollbackOnlyTransactionInfo> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6489854089642176585L;

	/**
	 * Instantiates a new sets the rollback only transaction event.
	 *
	 * @param setRollbackOnlyTransactionInfo
	 *            the set rollback only transaction info
	 */
	public SetRollbackOnlyEvent(SetRollbackOnlyTransactionInfo setRollbackOnlyTransactionInfo) {
		super(setRollbackOnlyTransactionInfo);
	}

}
