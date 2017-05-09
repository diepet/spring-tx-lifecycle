package it.diepet.spring.tx.lifecycle.test.app.service;

import java.util.List;

import it.diepet.spring.tx.lifecycle.test.app.error.ApplicationException;
import it.diepet.spring.tx.lifecycle.test.app.error.ApplicationRuntimeException;
import it.diepet.spring.tx.lifecycle.test.app.model.Product;

public interface ProductService {

	void add(Product product);

	List<Product> findAll();

	void successfullOperation();

	void launchCheckedException() throws ApplicationException;

	void launchCheckedExceptionForRollback() throws ApplicationException;

	void launchUncheckedException() throws ApplicationRuntimeException;

	void callSuspendingTransactionWarehouseMethod();

	void callFailingWarehouseMethod();

	void callRequiresNewWarehouseMethod();

}
