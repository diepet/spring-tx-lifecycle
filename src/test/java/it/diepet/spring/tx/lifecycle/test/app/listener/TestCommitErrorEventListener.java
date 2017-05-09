package it.diepet.spring.tx.lifecycle.test.app.listener;

import it.diepet.spring.tx.lifecycle.event.failure.CommitErrorEvent;

public class TestCommitErrorEventListener
		extends TestTransactionLifecycleErrorEventListener<CommitErrorEvent> {

}
