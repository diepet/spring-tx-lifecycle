package it.diepet.spring.tx.lifecycle.test.app.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import it.diepet.spring.tx.lifecycle.test.app.dao.ProductDAO;
import it.diepet.spring.tx.lifecycle.test.app.error.ApplicationException;
import it.diepet.spring.tx.lifecycle.test.app.error.ApplicationRuntimeException;
import it.diepet.spring.tx.lifecycle.test.app.model.Product;
import it.diepet.spring.tx.lifecycle.test.util.StringCollector;

public class ProductServiceImpl implements ProductService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Autowired
	private ProductDAO productDAO;

	@Autowired
	private WarehouseService warehouseService;

	@Override
	@Transactional
	public void add(Product product) {
		LOGGER.debug("[START] add()");
		StringCollector.add("productService.add()");
		productDAO.create(product);
		LOGGER.debug("[END] add()");
	}

	@Override
	@Transactional
	public List<Product> findAll() {
		LOGGER.debug("[START] findAll()");
		StringCollector.add("productService.findAll()");
		List<Product> result = productDAO.findAll();
		LOGGER.debug("[END] findAll()");
		return result;
	}

	@Override
	@Transactional
	public void successfullOperation() {
		LOGGER.debug("[START] successfullOperation()");
		StringCollector.add("productService.successfullOperation()");
		LOGGER.debug("[END] successfullOperation()");
	}

	@Override
	@Transactional
	public void launchCheckedException() throws ApplicationException {
		LOGGER.debug("[START] launchCheckedException()");
		StringCollector.add("productService.launchCheckedException()");
		throw new ApplicationException();
	}

	@Override
	@Transactional
	public void launchUncheckedException() throws ApplicationRuntimeException {
		LOGGER.debug("[START] launchUncheckedException()");
		StringCollector.add("productService.launchUncheckedException()");
		throw new ApplicationRuntimeException();
	}

	@Override
	@Transactional(rollbackFor = ApplicationException.class)
	public void launchCheckedExceptionForRollback() throws ApplicationException {
		LOGGER.debug("[START] launchCheckedExceptionForRollback()");
		StringCollector.add("productService.launchCheckedExceptionForRollback()");
		throw new ApplicationException();
	}

	@Override
	@Transactional
	public void callSuspendingTransactionWarehouseMethod() {
		LOGGER.debug("[START] callSuspendingTransactionWarehouseMethod()");
		StringCollector.add("productService.callSuspendingTransactionWarehouseMethod()");
		warehouseService.suspendCurrentTransaction();
		LOGGER.debug("[END] callSuspendingTransactionWarehouseMethod()");
	}

	@Override
	@Transactional
	public void callFailingWarehouseMethod() {
		LOGGER.debug("[START] callFailingWarehouseMethod()");
		StringCollector.add("productService.callFailingWarehouseMethod()");
		try {
			warehouseService.launchCheckedExceptionForRollback();
		} catch (ApplicationException e) {

		}
		LOGGER.debug("[END] callFailingWarehouseMethod()");
	}

	@Override
	@Transactional
	public void callRequiresNewWarehouseMethod() {
		LOGGER.debug("[START] callRequiresNewWarehouseMethod()");
		StringCollector.add("productService.callRequiresNewWarehouseMethod()");
		warehouseService.executeRequiresNewTransaction();
		LOGGER.debug("[END] callRequiresNewWarehouseMethod()");
	}

}
