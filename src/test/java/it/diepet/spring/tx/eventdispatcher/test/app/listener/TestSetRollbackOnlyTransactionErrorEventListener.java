package it.diepet.spring.tx.eventdispatcher.test.app.listener;

import it.diepet.spring.tx.eventdispatcher.event.failure.SetRollbackOnlyTransactionErrorEvent;

public class TestSetRollbackOnlyTransactionErrorEventListener
		extends TestTransactionErrorEventListener<SetRollbackOnlyTransactionErrorEvent> {

}
