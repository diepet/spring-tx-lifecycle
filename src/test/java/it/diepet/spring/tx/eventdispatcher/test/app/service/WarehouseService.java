package it.diepet.spring.tx.eventdispatcher.test.app.service;

import it.diepet.spring.tx.eventdispatcher.test.app.error.ApplicationException;

public interface WarehouseService {

	public void suspendCurrentTransaction();

	public void launchCheckedExceptionForRollback() throws ApplicationException;

	public void executeRequiresNewTransaction();

}
