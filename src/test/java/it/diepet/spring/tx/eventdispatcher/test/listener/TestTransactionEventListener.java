package it.diepet.spring.tx.eventdispatcher.test.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;

import it.diepet.spring.tx.eventdispatcher.event.TransactionEvent;
import it.diepet.spring.tx.eventdispatcher.test.util.StringCollector;

public class TestTransactionEventListener<T extends TransactionEvent<?>> implements ApplicationListener<T> {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Override
	public void onApplicationEvent(T event) {
		String msg = event.getClass().getName() + ": " + event.getTransactionInfo();
		LOGGER.info(msg);
		StringCollector.add(msg);
	}

}
