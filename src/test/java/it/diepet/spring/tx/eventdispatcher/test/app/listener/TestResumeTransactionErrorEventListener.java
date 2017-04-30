package it.diepet.spring.tx.eventdispatcher.test.app.listener;

import it.diepet.spring.tx.eventdispatcher.event.failure.ResumeTransactionErrorEvent;

public class TestResumeTransactionErrorEventListener
		extends TestTransactionErrorEventListener<ResumeTransactionErrorEvent> {

}
