package it.diepet.spring.tx.eventdispatcher.test.app;

import org.junit.Assert;
import org.junit.Test;

import it.diepet.spring.tx.eventdispatcher.event.failure.BeginTransactionErrorEvent;
import it.diepet.spring.tx.eventdispatcher.event.failure.ResumeTransactionErrorEvent;
import it.diepet.spring.tx.eventdispatcher.event.failure.RollbackTransactionErrorEvent;
import it.diepet.spring.tx.eventdispatcher.event.failure.SetRollbackOnlyTransactionErrorEvent;
import it.diepet.spring.tx.eventdispatcher.event.failure.SuspendTransactionErrorEvent;
import it.diepet.spring.tx.eventdispatcher.model.BeginTransactionInfo;
import it.diepet.spring.tx.eventdispatcher.model.ResumeTransactionInfo;
import it.diepet.spring.tx.eventdispatcher.model.RollbackTransactionInfo;
import it.diepet.spring.tx.eventdispatcher.model.SetRollbackOnlyTransactionInfo;
import it.diepet.spring.tx.eventdispatcher.model.SuspendTransactionInfo;

public class TxEventDispatcherFailureTest {

	@Test
	public void testFailures() {
		RuntimeException error = new RuntimeException("some error");
		BeginTransactionInfo beginTxInfo = new BeginTransactionInfo(null, null);
		BeginTransactionErrorEvent beginError = new BeginTransactionErrorEvent(beginTxInfo, error);
		ResumeTransactionInfo resumeTxInfo = new ResumeTransactionInfo(null, null);
		ResumeTransactionErrorEvent resumeError = new ResumeTransactionErrorEvent(resumeTxInfo, error);
		RollbackTransactionInfo rollbackTxInfo = new RollbackTransactionInfo(null);
		RollbackTransactionErrorEvent rollbackError = new RollbackTransactionErrorEvent(rollbackTxInfo, error);
		SetRollbackOnlyTransactionInfo setRollbackOnlyTxInfo = new SetRollbackOnlyTransactionInfo(null);
		SetRollbackOnlyTransactionErrorEvent setRollbackOnlyError = new SetRollbackOnlyTransactionErrorEvent(
				setRollbackOnlyTxInfo, error);
		SuspendTransactionInfo suspendTxInfo = new SuspendTransactionInfo(null);
		SuspendTransactionErrorEvent suspendError = new SuspendTransactionErrorEvent(suspendTxInfo, error);

		Assert.assertEquals(beginTxInfo, beginError.getTransactionInfo());
		Assert.assertEquals(error, beginError.getError());
		Assert.assertEquals(resumeTxInfo, resumeError.getTransactionInfo());
		Assert.assertEquals(error, beginError.getError());
		Assert.assertEquals(rollbackTxInfo, rollbackError.getTransactionInfo());
		Assert.assertEquals(error, rollbackError.getError());
		Assert.assertEquals(setRollbackOnlyTxInfo, setRollbackOnlyError.getTransactionInfo());
		Assert.assertEquals(error, setRollbackOnlyError.getError());
		Assert.assertEquals(suspendTxInfo, suspendError.getTransactionInfo());
		Assert.assertEquals(error, suspendError.getError());

	}
}
