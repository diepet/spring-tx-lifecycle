package it.diepet.spring.tx.eventdispatcher.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import it.diepet.spring.tx.eventdispatcher.test.error.ApplicationException;
import it.diepet.spring.tx.eventdispatcher.test.error.ApplicationRuntimeException;
import it.diepet.spring.tx.eventdispatcher.test.model.Product;
import it.diepet.spring.tx.eventdispatcher.test.service.ProductService;
import it.diepet.spring.tx.eventdispatcher.test.util.StringCollector;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:META-INF/test-application-context.xml")
public class AppTest {

	@Autowired
	private ProductService productService;

	@Before
	public void init() {
		StringCollector.reset();
	}

	@Test
	public void testAddProduct() {
		Product product = new Product();
		product.setId(9L);
		product.setCode("99999");
		product.setDescription("Apple");
		productService.add(product);

		List<String> stringList = StringCollector.getList();
		Assert.assertNotNull(stringList);
		Assert.assertEquals(3, stringList.size());
		Assert.assertTrue(
				stringList.get(0).startsWith("it.diepet.spring.tx.eventdispatcher.event.BeginTransactionEvent"));
		Assert.assertEquals("productService.add()", stringList.get(1));
		Assert.assertTrue(
				stringList.get(2).startsWith("it.diepet.spring.tx.eventdispatcher.event.CommitTransactionEvent"));
	}

	@Test
	public void testSuccessfulOperation() {
		productService.successfullOperation();
		List<String> stringList = StringCollector.getList();
		Assert.assertNotNull(stringList);
		Assert.assertEquals(3, stringList.size());
		Assert.assertTrue(
				stringList.get(0).startsWith("it.diepet.spring.tx.eventdispatcher.event.BeginTransactionEvent"));
		Assert.assertEquals("productService.successfullOperation()", stringList.get(1));
		Assert.assertTrue(
				stringList.get(2).startsWith("it.diepet.spring.tx.eventdispatcher.event.CommitTransactionEvent"));
	}

	@Test
	public void testLaunchCheckedException() {
		try {
			productService.launchCheckedException();
		} catch (ApplicationException e) {
			List<String> stringList = StringCollector.getList();
			Assert.assertNotNull(stringList);
			Assert.assertEquals(3, stringList.size());
			Assert.assertTrue(
					stringList.get(0).startsWith("it.diepet.spring.tx.eventdispatcher.event.BeginTransactionEvent"));
			Assert.assertEquals("productService.launchCheckedException()", stringList.get(1));
			Assert.assertTrue(
					stringList.get(2).startsWith("it.diepet.spring.tx.eventdispatcher.event.CommitTransactionEvent"));
			return;
		}
		Assert.fail();
	}

	@Test
	public void testLaunchUncheckedException() {
		try {
			productService.launchUncheckedException();
		} catch (ApplicationRuntimeException e) {
			List<String> stringList = StringCollector.getList();
			Assert.assertNotNull(stringList);
			Assert.assertEquals(3, stringList.size());
			Assert.assertTrue(
					stringList.get(0).startsWith("it.diepet.spring.tx.eventdispatcher.event.BeginTransactionEvent"));
			Assert.assertEquals("productService.launchUncheckedException()", stringList.get(1));
			Assert.assertTrue(
					stringList.get(2).startsWith("it.diepet.spring.tx.eventdispatcher.event.RollbackTransactionEvent"));
			return;
		}
		Assert.fail();
	}

	@Test
	public void testLaunchCheckedExceptionForRollback() {
		try {
			productService.launchCheckedExceptionForRollback();
		} catch (ApplicationException e) {
			List<String> stringList = StringCollector.getList();
			Assert.assertNotNull(stringList);
			Assert.assertEquals(3, stringList.size());
			Assert.assertTrue(
					stringList.get(0).startsWith("it.diepet.spring.tx.eventdispatcher.event.BeginTransactionEvent"));
			Assert.assertEquals("productService.launchCheckedExceptionForRollback()", stringList.get(1));
			Assert.assertTrue(
					stringList.get(2).startsWith("it.diepet.spring.tx.eventdispatcher.event.RollbackTransactionEvent"));
			return;
		}
		Assert.fail();
	}

	@Test
	public void testCheckWarehouse() {
		productService.checkWarehouse();
		List<String> stringList = StringCollector.getList();
		Assert.assertNotNull(stringList);
		Assert.assertEquals(6, stringList.size());
		Assert.assertTrue(
				stringList.get(0).startsWith("it.diepet.spring.tx.eventdispatcher.event.BeginTransactionEvent"));
		Assert.assertEquals("productService.checkWarehouse()", stringList.get(1));
		Assert.assertTrue(
				stringList.get(2).startsWith("it.diepet.spring.tx.eventdispatcher.event.SuspendTransactionEvent"));
		Assert.assertEquals("warehouseService.suspendCurrentTransaction()", stringList.get(3));
		Assert.assertTrue(
				stringList.get(4).startsWith("it.diepet.spring.tx.eventdispatcher.event.ResumeTransactionEvent"));
		Assert.assertTrue(
				stringList.get(5).startsWith("it.diepet.spring.tx.eventdispatcher.event.CommitTransactionEvent"));
	}

	@Test
	public void testFailureInheritedTransaction() {
		try {
			productService.callFailingWarehouseMethod();
		} catch (RuntimeException e) {
			List<String> stringList = StringCollector.getList();
			Assert.assertNotNull(stringList);
			Assert.assertEquals(5, stringList.size());
			Assert.assertTrue(
					stringList.get(0).startsWith("it.diepet.spring.tx.eventdispatcher.event.BeginTransactionEvent"));
			Assert.assertEquals("productService.callFailingWarehouseMethod()", stringList.get(1));
			Assert.assertEquals("warehouseService.launchCheckedExceptionForRollback()", stringList.get(2));
			Assert.assertTrue(stringList.get(3)
					.startsWith("it.diepet.spring.tx.eventdispatcher.event.SetRollbackOnlyTransactionEvent"));
			Assert.assertTrue(stringList.get(4)
					.startsWith("it.diepet.spring.tx.eventdispatcher.event.failure.CommitTransactionErrorEvent"));
		}

	}
}
