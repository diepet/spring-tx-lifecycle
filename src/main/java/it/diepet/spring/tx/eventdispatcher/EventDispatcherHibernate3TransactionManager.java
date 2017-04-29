package it.diepet.spring.tx.eventdispatcher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.DefaultTransactionStatus;

import it.diepet.spring.tx.eventdispatcher.event.BeginTransactionEvent;
import it.diepet.spring.tx.eventdispatcher.event.CommitTransactionEvent;
import it.diepet.spring.tx.eventdispatcher.event.ResumeTransactionEvent;
import it.diepet.spring.tx.eventdispatcher.event.RollbackTransactionEvent;
import it.diepet.spring.tx.eventdispatcher.event.SetRollbackOnlyTransactionEvent;
import it.diepet.spring.tx.eventdispatcher.event.SuspendTransactionEvent;
import it.diepet.spring.tx.eventdispatcher.event.failure.BeginTransactionErrorEvent;
import it.diepet.spring.tx.eventdispatcher.event.failure.CommitTransactionErrorEvent;
import it.diepet.spring.tx.eventdispatcher.event.failure.ResumeTransactionErrorEvent;
import it.diepet.spring.tx.eventdispatcher.event.failure.RollbackTransactionErrorEvent;
import it.diepet.spring.tx.eventdispatcher.event.failure.SetRollbackOnlyTransactionErrorEvent;
import it.diepet.spring.tx.eventdispatcher.event.failure.SuspendTransactionErrorEvent;
import it.diepet.spring.tx.eventdispatcher.model.BeginTransactionInfo;
import it.diepet.spring.tx.eventdispatcher.model.CommitTransactionInfo;
import it.diepet.spring.tx.eventdispatcher.model.ResumeTransactionInfo;
import it.diepet.spring.tx.eventdispatcher.model.RollbackTransactionInfo;
import it.diepet.spring.tx.eventdispatcher.model.SetRollbackOnlyTransactionInfo;
import it.diepet.spring.tx.eventdispatcher.model.SuspendTransactionInfo;

/**
 * The Class EventDispatcherHibernate3TransactionManager.
 */
public class EventDispatcherHibernate3TransactionManager extends HibernateTransactionManager
		implements ApplicationEventPublisherAware {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6227540738117736828L;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(EventDispatcherHibernate3TransactionManager.class);

	/** The application event publisher. */
	private ApplicationEventPublisher applicationEventPublisher;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.orm.jpa.JpaTransactionManager#doResume(java.lang.
	 * Object, java.lang.Object)
	 */
	@Override
	protected void doResume(final Object transaction, final Object suspendedResources) {
		LOGGER.debug("[START] doResume()");
		final ResumeTransactionInfo resumeTransactionInfo = new ResumeTransactionInfo(transaction, suspendedResources);
		try {
			super.doResume(transaction, suspendedResources);
			// publish begin transaction event
			this.applicationEventPublisher.publishEvent(new ResumeTransactionEvent(resumeTransactionInfo));
		} catch (RuntimeException error) {
			// publish begin transaction error event
			this.applicationEventPublisher.publishEvent(new ResumeTransactionErrorEvent(resumeTransactionInfo, error));
			throw error;
		} finally {
			LOGGER.debug("[END] doResume()");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.orm.jpa.JpaTransactionManager#doSetRollbackOnly(org
	 * .springframework.transaction.support.DefaultTransactionStatus)
	 */
	@Override
	protected void doSetRollbackOnly(final DefaultTransactionStatus status) {
		LOGGER.debug("[START] doSetRollbackOnly()");
		final SetRollbackOnlyTransactionInfo setRollbackOnlyTransactionInfo = new SetRollbackOnlyTransactionInfo(
				status);
		try {
			super.doSetRollbackOnly(status);
			// publish set rollback only transaction event
			this.applicationEventPublisher
					.publishEvent(new SetRollbackOnlyTransactionEvent(setRollbackOnlyTransactionInfo));
		} catch (RuntimeException error) {
			// publish set rollback only transaction error event
			this.applicationEventPublisher
					.publishEvent(new SetRollbackOnlyTransactionErrorEvent(setRollbackOnlyTransactionInfo, error));
			throw error;
		} finally {
			LOGGER.debug("[END] doSetRollbackOnly()");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.orm.jpa.JpaTransactionManager#doSuspend(java.lang
	 * .Object)
	 */
	@Override
	protected Object doSuspend(final Object transaction) {
		LOGGER.debug("[START] doSuspend()");
		final SuspendTransactionInfo suspendTransactionInfo = new SuspendTransactionInfo(transaction);
		try {
			Object result = super.doSuspend(transaction);
			// publish suspend transaction event
			this.applicationEventPublisher.publishEvent(new SuspendTransactionEvent(suspendTransactionInfo));
			return result;
		} catch (RuntimeException error) {
			// publish suspend transaction error event
			this.applicationEventPublisher
					.publishEvent(new SuspendTransactionErrorEvent(suspendTransactionInfo, error));
			throw error;
		} finally {
			LOGGER.debug("[END] doSuspend()");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.orm.jpa.JpaTransactionManager#doBegin(java.lang.
	 * Object , org.springframework.transaction.TransactionDefinition)
	 */
	@Override
	protected void doBegin(final Object transaction, final TransactionDefinition definition) {
		LOGGER.debug("[START] doBegin()");
		final BeginTransactionInfo beginTransactionInfo = new BeginTransactionInfo(transaction, definition);
		try {
			super.doBegin(transaction, definition);
			// publish begin transaction event
			this.applicationEventPublisher.publishEvent(new BeginTransactionEvent(beginTransactionInfo));
		} catch (RuntimeException error) {
			// publish begin transaction error event
			this.applicationEventPublisher.publishEvent(new BeginTransactionErrorEvent(beginTransactionInfo, error));
			throw error;
		} finally {
			LOGGER.debug("[END] doBegin()");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.orm.jpa.JpaTransactionManager#doCommit(org.
	 * springframework.transaction.support.DefaultTransactionStatus)
	 */
	@Override
	protected void doCommit(final DefaultTransactionStatus status) {
		LOGGER.debug("[START] doCommit()");
		final CommitTransactionInfo commitTransactionInfo = new CommitTransactionInfo(status);
		try {
			super.doCommit(status);
			// publish commit transaction event
			this.applicationEventPublisher.publishEvent(new CommitTransactionEvent(commitTransactionInfo));
		} catch (RuntimeException error) {
			// publish commit transaction error event
			this.applicationEventPublisher.publishEvent(new CommitTransactionErrorEvent(commitTransactionInfo, error));
			throw error;
		} finally {
			LOGGER.debug("[END] doCommit()");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.orm.jpa.JpaTransactionManager#doRollback(org.
	 * springframework.transaction.support.DefaultTransactionStatus)
	 */
	@Override
	protected void doRollback(final DefaultTransactionStatus status) {
		LOGGER.debug("[START] doRollback()");
		final RollbackTransactionInfo rollbackTransactionInfo = new RollbackTransactionInfo(status);
		try {
			super.doRollback(status);
			// publish rollback transaction event
			this.applicationEventPublisher.publishEvent(new RollbackTransactionEvent(rollbackTransactionInfo));
		} catch (RuntimeException error) {
			// publish rollback transaction event
			this.applicationEventPublisher
					.publishEvent(new RollbackTransactionErrorEvent(rollbackTransactionInfo, error));
			throw error;
		} finally {
			LOGGER.debug("[END] doRollback()");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.context.ApplicationEventPublisherAware#
	 * setApplicationEventPublisher
	 * (org.springframework.context.ApplicationEventPublisher)
	 */
	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		this.applicationEventPublisher = applicationEventPublisher;
	}

}
