package it.diepet.spring.tx.lifecycle.model;

import org.springframework.transaction.support.DefaultTransactionStatus;

/**
 * The Class SetRollbackOnlyTransactionInfo.
 */
public class SetRollbackOnlyTransactionInfo implements TransactionInfo {

	/** The status. */
	private DefaultTransactionStatus status;

	/**
	 * Instantiates a new sets the rollback only transaction info.
	 *
	 * @param status
	 *            the status
	 */
	public SetRollbackOnlyTransactionInfo(DefaultTransactionStatus status) {
		super();
		this.status = status;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public DefaultTransactionStatus getStatus() {
		return status;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		final StringBuilder buffer = new StringBuilder();
		buffer.append("[status=");
		buffer.append(this.getStatus());
		buffer.append("]");
		return buffer.toString();
	}
}
