package it.diepet.spring.tx.eventdispatcher.test.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import it.diepet.spring.tx.eventdispatcher.test.dao.ProductDAO;
import it.diepet.spring.tx.eventdispatcher.test.error.ApplicationException;
import it.diepet.spring.tx.eventdispatcher.test.model.Product;
import it.diepet.spring.tx.eventdispatcher.test.util.StringCollector;

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
		List<Product> result = productDAO.findAll();
		LOGGER.debug("[END] findAll()");
		return result;
	}

	@Override
	@Transactional
	public void launchCheckedException() throws ApplicationException {
		LOGGER.debug("[START] launchCheckedException()");
		StringCollector.add("productService.launchCheckedException()");
		throw new ApplicationException();
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
	public void checkWarehouse() {
		LOGGER.debug("[START] checkWarehouse()");
		StringCollector.add("productService.checkWarehouse()");
		warehouseService.suspendCurrentTransaction();
		LOGGER.debug("[END] checkWarehouse()");
	}

}
