package it.diepet.spring.tx.eventdispatcher.test.service;

import it.diepet.spring.tx.eventdispatcher.test.error.ApplicationException;

public interface WarehouseService {

	public void suspendCurrentTransaction();

	public void launchCheckedExceptionForRollback() throws ApplicationException;

}
