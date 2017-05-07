package it.diepet.spring.tx.eventdispatcher.test.app.listener;

import it.diepet.spring.tx.eventdispatcher.event.failure.CommitErrorEvent;

public class TestCommitErrorEventListener
		extends TestTransactionLifecycleErrorEventListener<CommitErrorEvent> {

}
