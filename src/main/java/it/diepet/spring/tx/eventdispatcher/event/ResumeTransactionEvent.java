package it.diepet.spring.tx.eventdispatcher.event;

import it.diepet.spring.tx.eventdispatcher.model.ResumeTransactionInfo;

/**
 * The Class ResumeTransactionEvent.
 */
public class ResumeTransactionEvent extends TransactionEvent<ResumeTransactionInfo> {


	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8281147876374578686L;

	/**
	 * Instantiates a new resume transaction event.
	 *
	 * @param resumeTransactionInfo the resume transaction info
	 */
	public ResumeTransactionEvent(ResumeTransactionInfo resumeTransactionInfo) {
		super(resumeTransactionInfo);
	}

}
