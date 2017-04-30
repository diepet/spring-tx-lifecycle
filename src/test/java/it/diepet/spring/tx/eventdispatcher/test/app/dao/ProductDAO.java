package it.diepet.spring.tx.eventdispatcher.test.app.dao;

import it.diepet.spring.tx.eventdispatcher.test.app.model.Product;

public class ProductDAO extends AbstractJpaDAO<Product> {
	public ProductDAO() {
		setClazz(Product.class);
	}
}
