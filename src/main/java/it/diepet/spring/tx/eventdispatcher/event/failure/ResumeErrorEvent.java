package it.diepet.spring.tx.eventdispatcher.event.failure;

import it.diepet.spring.tx.eventdispatcher.model.ResumeTransactionInfo;

/**
 * The Class ResumeErrorEvent.
 */
public class ResumeErrorEvent extends TransactionLifecycleErrorEvent<ResumeTransactionInfo> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6548406599071911274L;

	/**
	 * Instantiates a new resume transaction error event.
	 *
	 * @param resumeTransactionInfo
	 *            the resume transaction info
	 * @param error
	 *            the error
	 */
	public ResumeErrorEvent(ResumeTransactionInfo resumeTransactionInfo, RuntimeException error) {
		super(resumeTransactionInfo, error);
	}

}
