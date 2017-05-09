package it.diepet.spring.tx.lifecycle.model;

/**
 * The Class TransactionInfo.
 */
public class SuspendTransactionInfo implements TransactionInfo {

	/** The transaction. */
	private Object transaction;

	/**
	 * Instantiates a new transaction info.
	 *
	 * @param transaction
	 *            the transaction
	 * @param definition
	 *            the definition
	 */
	public SuspendTransactionInfo(Object transaction) {
		super();
		this.transaction = transaction;
	}

	/**
	 * Gets the transaction.
	 *
	 * @return the transaction
	 */
	public Object getTransaction() {
		return transaction;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		final StringBuilder buffer = new StringBuilder();
		buffer.append("[transaction=");
		buffer.append(this.getTransaction());
		buffer.append("]");
		return buffer.toString();
	}

}
