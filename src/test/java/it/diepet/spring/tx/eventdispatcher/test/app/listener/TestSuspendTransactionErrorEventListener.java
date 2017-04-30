package it.diepet.spring.tx.eventdispatcher.test.app.listener;

import it.diepet.spring.tx.eventdispatcher.event.failure.SuspendTransactionErrorEvent;

public class TestSuspendTransactionErrorEventListener
		extends TestTransactionErrorEventListener<SuspendTransactionErrorEvent> {

}
