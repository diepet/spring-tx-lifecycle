package it.diepet.spring.tx.eventdispatcher.test.app.listener;

import it.diepet.spring.tx.eventdispatcher.event.failure.CommitTransactionErrorEvent;

public class TestCommitTransactionErrorEventListener
		extends TestTransactionErrorEventListener<CommitTransactionErrorEvent> {

}
