package it.diepet.spring.tx.eventdispatcher.test.service;

import java.util.List;

import it.diepet.spring.tx.eventdispatcher.test.error.ApplicationException;
import it.diepet.spring.tx.eventdispatcher.test.model.Product;

public interface ProductService {

	void add(Product product);

	List<Product> findAll();

	void launchCheckedException() throws ApplicationException;

	void launchCheckedExceptionForRollback() throws ApplicationException;

	void checkWarehouse();

}
