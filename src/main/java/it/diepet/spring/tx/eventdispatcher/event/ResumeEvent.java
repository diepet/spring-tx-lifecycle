package it.diepet.spring.tx.eventdispatcher.event;

import it.diepet.spring.tx.eventdispatcher.model.ResumeTransactionInfo;

/**
 * The Class ResumeEvent.
 */
public class ResumeEvent extends TransactionLifecycleEvent<ResumeTransactionInfo> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8281147876374578686L;

	/**
	 * Instantiates a new resume transaction event.
	 *
	 * @param resumeTransactionInfo
	 *            the resume transaction info
	 */
	public ResumeEvent(ResumeTransactionInfo resumeTransactionInfo) {
		super(resumeTransactionInfo);
	}

}
