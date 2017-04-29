package it.diepet.spring.tx.eventdispatcher.model;

/**
 * The Class ResumeTransactionInfo.
 */
public class ResumeTransactionInfo implements TransactionInfo {

	/** The transaction. */
	private Object transaction;

	/** The suspended resources. */
	private Object suspendedResources;

	/**
	 * Instantiates a new resume transaction info.
	 *
	 * @param transaction
	 *            the transaction
	 * @param suspendedResources
	 *            the suspended resources
	 */
	public ResumeTransactionInfo(Object transaction, Object suspendedResources) {
		super();
		this.transaction = transaction;
		this.suspendedResources = suspendedResources;
	}

	/**
	 * Gets the transaction.
	 *
	 * @return the transaction
	 */
	public Object getTransaction() {
		return transaction;
	}

	/**
	 * Gets the suspended resources.
	 *
	 * @return the suspended resources
	 */
	public Object getSuspendedResources() {
		return suspendedResources;
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
		buffer.append(this.transaction);
		buffer.append(", suspendedResources=");
		buffer.append(this.suspendedResources);
		buffer.append("]");
		return buffer.toString();
	}

}
