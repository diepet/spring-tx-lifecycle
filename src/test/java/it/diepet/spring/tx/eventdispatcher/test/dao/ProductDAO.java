package it.diepet.spring.tx.eventdispatcher.test.dao;

import it.diepet.spring.tx.eventdispatcher.test.model.Product;

public class ProductDAO extends AbstractJpaDAO<Product> {
	public ProductDAO() {
		setClazz(Product.class);
	}
}
