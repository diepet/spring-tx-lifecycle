package it.diepet.spring.tx.eventdispatcher.test.app.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;

import it.diepet.spring.tx.eventdispatcher.event.failure.TransactionErrorEvent;
import it.diepet.spring.tx.eventdispatcher.test.util.StringCollector;

public abstract class TestTransactionErrorEventListener<T extends TransactionErrorEvent<?>>
		implements ApplicationListener<T> {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Override
	public void onApplicationEvent(T event) {
		String msg = event.getClass().getName() + ": " + event.getTransactionInfo() + " - " + event.getError();
		LOGGER.info(msg);
		StringCollector.add(msg);
	}

}
