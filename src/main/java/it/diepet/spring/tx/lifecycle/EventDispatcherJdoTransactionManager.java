package it.diepet.spring.tx.lifecycle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.orm.jdo.JdoTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.DefaultTransactionStatus;

import it.diepet.spring.tx.lifecycle.event.BeginEvent;
import it.diepet.spring.tx.lifecycle.event.CommitEvent;
import it.diepet.spring.tx.lifecycle.event.ResumeEvent;
import it.diepet.spring.tx.lifecycle.event.RollbackEvent;
import it.diepet.spring.tx.lifecycle.event.SetRollbackOnlyEvent;
import it.diepet.spring.tx.lifecycle.event.SuspendEvent;
import it.diepet.spring.tx.lifecycle.event.failure.BeginErrorEvent;
import it.diepet.spring.tx.lifecycle.event.failure.CommitErrorEvent;
import it.diepet.spring.tx.lifecycle.event.failure.ResumeErrorEvent;
import it.diepet.spring.tx.lifecycle.event.failure.RollbackErrorEvent;
import it.diepet.spring.tx.lifecycle.event.failure.SetRollbackOnlyErrorEvent;
import it.diepet.spring.tx.lifecycle.event.failure.SuspendErrorEvent;
import it.diepet.spring.tx.lifecycle.model.BeginTransactionInfo;
import it.diepet.spring.tx.lifecycle.model.CommitTransactionInfo;
import it.diepet.spring.tx.lifecycle.model.ResumeTransactionInfo;
import it.diepet.spring.tx.lifecycle.model.RollbackTransactionInfo;
import it.diepet.spring.tx.lifecycle.model.SetRollbackOnlyTransactionInfo;
import it.diepet.spring.tx.lifecycle.model.SuspendTransactionInfo;

/**
 * The Class EventDispatcherJdoTransactionManager.
 */
public class EventDispatcherJdoTransactionManager extends JdoTransactionManager
		implements ApplicationEventPublisherAware {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6544460022801736358L;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(EventDispatcherJdoTransactionManager.class);

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
			this.applicationEventPublisher.publishEvent(new ResumeEvent(resumeTransactionInfo));
		} catch (RuntimeException error) {
			// publish begin transaction error event
			this.applicationEventPublisher.publishEvent(new ResumeErrorEvent(resumeTransactionInfo, error));
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
					.publishEvent(new SetRollbackOnlyEvent(setRollbackOnlyTransactionInfo));
		} catch (RuntimeException error) {
			// publish set rollback only transaction error event
			this.applicationEventPublisher
					.publishEvent(new SetRollbackOnlyErrorEvent(setRollbackOnlyTransactionInfo, error));
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
			this.applicationEventPublisher.publishEvent(new SuspendEvent(suspendTransactionInfo));
			return result;
		} catch (RuntimeException error) {
			// publish suspend transaction error event
			this.applicationEventPublisher
					.publishEvent(new SuspendErrorEvent(suspendTransactionInfo, error));
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
			this.applicationEventPublisher.publishEvent(new BeginEvent(beginTransactionInfo));
		} catch (RuntimeException error) {
			// publish begin transaction error event
			this.applicationEventPublisher.publishEvent(new BeginErrorEvent(beginTransactionInfo, error));
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
			this.applicationEventPublisher.publishEvent(new CommitEvent(commitTransactionInfo));
		} catch (RuntimeException error) {
			// publish commit transaction error event
			this.applicationEventPublisher.publishEvent(new CommitErrorEvent(commitTransactionInfo, error));
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
			this.applicationEventPublisher.publishEvent(new RollbackEvent(rollbackTransactionInfo));
		} catch (RuntimeException error) {
			// publish rollback transaction event
			this.applicationEventPublisher
					.publishEvent(new RollbackErrorEvent(rollbackTransactionInfo, error));
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
