package it.diepet.spring.tx.eventdispatcher.model;

import org.springframework.transaction.TransactionDefinition;

/**
 * The Class TransactionInfo.
 */
public class BeginTransactionInfo implements TransactionInfo {

	/** The transaction. */
	private Object transaction;

	/** The definition. */
	private TransactionDefinition definition;

	/**
	 * Instantiates a new transaction info.
	 *
	 * @param transaction
	 *            the transaction
	 * @param definition
	 *            the definition
	 */
	public BeginTransactionInfo(Object transaction, TransactionDefinition definition) {
		super();
		this.transaction = transaction;
		this.definition = definition;
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
	 * Gets the definition.
	 *
	 * @return the definition
	 */
	public TransactionDefinition getDefinition() {
		return definition;
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
		buffer.append(", definition=");
		buffer.append(this.getDefinition());
		buffer.append("]");
		return buffer.toString();
	}

}
