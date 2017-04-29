package it.diepet.spring.tx.eventdispatcher.event;

import it.diepet.spring.tx.eventdispatcher.model.SetRollbackOnlyTransactionInfo;

/**
 * The Class SetRollbackOnlyTransactionEvent.
 */
public class SetRollbackOnlyTransactionEvent extends
		TransactionEvent<SetRollbackOnlyTransactionInfo> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6489854089642176585L;

	/**
	 * Instantiates a new sets the rollback only transaction event.
	 *
	 * @param setRollbackOnlyTransactionInfo
	 *            the set rollback only transaction info
	 */
	public SetRollbackOnlyTransactionEvent(
			SetRollbackOnlyTransactionInfo setRollbackOnlyTransactionInfo) {
		super(setRollbackOnlyTransactionInfo);
	}

}
