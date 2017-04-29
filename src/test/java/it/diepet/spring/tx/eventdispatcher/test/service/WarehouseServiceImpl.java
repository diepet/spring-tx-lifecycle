package it.diepet.spring.tx.eventdispatcher.test.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import it.diepet.spring.tx.eventdispatcher.test.util.StringCollector;

public class WarehouseServiceImpl implements WarehouseService {

	private static final Logger LOGGER = LoggerFactory.getLogger(WarehouseServiceImpl.class);

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public void suspendCurrentTransaction() {
		LOGGER.debug("[START] suspendCurrentTransaction()");
		StringCollector.add("warehouseService.suspendCurrentTransaction()");
		LOGGER.debug("[END] suspendCurrentTransaction()");
	}

}
