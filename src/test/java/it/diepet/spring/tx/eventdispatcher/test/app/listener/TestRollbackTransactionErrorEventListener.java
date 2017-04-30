package it.diepet.spring.tx.eventdispatcher.test.app.listener;

import it.diepet.spring.tx.eventdispatcher.event.failure.RollbackTransactionErrorEvent;

public class TestRollbackTransactionErrorEventListener
		extends TestTransactionErrorEventListener<RollbackTransactionErrorEvent> {

}
