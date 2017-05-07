package it.diepet.spring.tx.eventdispatcher.test.app.listener;

import it.diepet.spring.tx.eventdispatcher.event.SetRollbackOnlyEvent;

public class TestSetRollbackOnlyEventListener
		extends TestTransactionLifecycleEventListener<SetRollbackOnlyEvent> {

}
