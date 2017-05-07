package it.diepet.spring.tx.eventdispatcher.test.app;

import org.junit.Assert;
import org.junit.Test;

import it.diepet.spring.tx.eventdispatcher.event.failure.BeginErrorEvent;
import it.diepet.spring.tx.eventdispatcher.event.failure.ResumeErrorEvent;
import it.diepet.spring.tx.eventdispatcher.event.failure.RollbackErrorEvent;
import it.diepet.spring.tx.eventdispatcher.event.failure.SetRollbackOnlyErrorEvent;
import it.diepet.spring.tx.eventdispatcher.event.failure.SuspendErrorEvent;
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
		BeginErrorEvent beginError = new BeginErrorEvent(beginTxInfo, error);
		ResumeTransactionInfo resumeTxInfo = new ResumeTransactionInfo(null, null);
		ResumeErrorEvent resumeError = new ResumeErrorEvent(resumeTxInfo, error);
		RollbackTransactionInfo rollbackTxInfo = new RollbackTransactionInfo(null);
		RollbackErrorEvent rollbackError = new RollbackErrorEvent(rollbackTxInfo, error);
		SetRollbackOnlyTransactionInfo setRollbackOnlyTxInfo = new SetRollbackOnlyTransactionInfo(null);
		SetRollbackOnlyErrorEvent setRollbackOnlyError = new SetRollbackOnlyErrorEvent(
				setRollbackOnlyTxInfo, error);
		SuspendTransactionInfo suspendTxInfo = new SuspendTransactionInfo(null);
		SuspendErrorEvent suspendError = new SuspendErrorEvent(suspendTxInfo, error);

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
