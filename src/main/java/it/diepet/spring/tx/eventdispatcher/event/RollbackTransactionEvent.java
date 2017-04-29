package it.diepet.spring.tx.eventdispatcher.event;

import it.diepet.spring.tx.eventdispatcher.model.RollbackTransactionInfo;

/**
 * The Class RollbackTransactionEvent.
 */
public class RollbackTransactionEvent extends TransactionEvent<RollbackTransactionInfo> {


	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 528647198184522029L;

	/**
	 * Instantiates a new rollback transaction event.
	 *
	 * @param rollbackTransactionInfo the rollback transaction info
	 */
	public RollbackTransactionEvent(RollbackTransactionInfo rollbackTransactionInfo) {
		super(rollbackTransactionInfo);
	}

}
