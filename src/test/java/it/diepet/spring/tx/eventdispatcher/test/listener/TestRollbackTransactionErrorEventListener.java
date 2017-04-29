package it.diepet.spring.tx.eventdispatcher.test.listener;

import it.diepet.spring.tx.eventdispatcher.event.failure.RollbackTransactionErrorEvent;

public class TestRollbackTransactionErrorEventListener
		extends TestTransactionErrorEventListener<RollbackTransactionErrorEvent> {

}
