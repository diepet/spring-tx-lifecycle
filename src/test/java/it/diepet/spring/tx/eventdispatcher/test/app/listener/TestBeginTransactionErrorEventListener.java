package it.diepet.spring.tx.eventdispatcher.test.app.listener;

import it.diepet.spring.tx.eventdispatcher.event.failure.BeginTransactionErrorEvent;

public class TestBeginTransactionErrorEventListener
		extends TestTransactionErrorEventListener<BeginTransactionErrorEvent> {

}
