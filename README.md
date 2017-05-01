# spring-tx-eventdispatcher 

A Spring Framework plugin in order to dispatch ad-hoc Spring events during a transaction life cycle.

Current last version: 0.9.0

# Requisites

* Java 1.6 or higher
* Spring 3.2 or higher

Currently, the module has been tested only using Spring 3.2.2 and org.springframework.orm.jpa.JpaTransactionManager as transaction manager.

# Configuration

In order to dispatch ad-hoc events for tracking a transaction lifecycle, replace the Spring transaction manager with a new implementation that overrides the original transaction manager.

For now, the transaction managers supported are: 

* `org.springframework.jdbc.datasource.DataSourceTransactionManager`
* `org.springframework.orm.hibernate3.HibernateTransactionManager`
* `org.springframework.orm.hibernate4.HibernateTransactionManager`
* `org.springframework.orm.jdo.JdoTransactionManager`
* `org.springframework.orm.jpa.JpaTransactionManager`
* `org.springframework.transaction.jta.JtaTransactionManager`

For example this configuration:

```xml
	<!-- TX Configuration -->		
	<bean id="transactionManager" class="org.springframework.orm.jpa.JpaTransactionManager">
		<property name="entityManagerFactory" ref="entityManagerFactory"></property>
	</bean>
```

must be replaced by


```xml
	<!-- TX Configuration -->		
	<bean id="transactionManager" class="it.diepet.spring.tx.eventdispatcher.EventDispatcherJpaTransactionManager">
		<property name="entityManagerFactory" ref="entityManagerFactory"></property>
	</bean>
```

Notes that all supported transaction manager classes are subclass of `org.springframework.transaction.support.AbstractPlatformTransactionManager`.

# Transaction Events


An event is triggered when one of these transaction manager method terminates without runtime errors:

* `[Original Transaction Manager Class].doBegin(Object transaction, TransactionDefinition definition)`

* `[Original Transaction Manager Class].doCommit(DefaultTransactionStatus status)`

* `[Original Transaction Manager Class].doRollback(DefaultTransactionStatus status)`

* `[Original Transaction Manager Class].doSuspend(Object transaction)`

* `[Original Transaction Manager Class].doResume(Object transaction, Object suspendedResources)`

* `[Original Transaction Manager Class].doSetRollbackOnly(DefaultTransactionStatus status)`

where `[Original Transaction Manager Class]` is one of the Spring transaction manager supported listed above.

All the events triggered for tracking a transaction life cycle are subclass of

`it.diepet.spring.tx.eventdispatcher.event.TransactionEvent<T>`

The list of events triggered is:

* `it.diepet.spring.tx.eventdispatcher.event.BeginTransactionEvent`: when a new transaction begins.

* `it.diepet.spring.tx.eventdispatcher.event.CommitTransactionEvent`: when a transaction commits successfully.

* `it.diepet.spring.tx.eventdispatcher.event.RollbackTransactionEvent`: when a transaction is roll backed.

* `it.diepet.spring.tx.eventdispatcher.event.SuspendTransactionEvent`: when a transaction is suspended (for example because of a call of a method having the transaction propagation attribute set to *REQUIRES_NEW*).

* `it.diepet.spring.tx.eventdispatcher.event.ResumeTransactionEvent`: when a previous suspended transaction resumes (for example when a method having the transaction propagation attribute set to *REQUIRES_NEW* completes).

* `it.diepet.spring.tx.eventdispatcher.event.SetRollbackOnlyTransactionEvent`: when a transaction is set to rollback.

# Transaction Error Events

An error event is triggered when one of the original transaction manager fails for a runtime exception. 

For example, when the transaction manager tries to commit a transaction set to rollback only the `[Original Transaction Manager Class].doCommit(DefaultTransactionStatus status)` method will throw a runtime exception.

All the error events related to a transaction lifecycle are subclass of

`it.diepet.spring.tx.eventdispatcher.event.failure.TransactionErrorEvent<T>`

The list of error events triggered is:

* `it.diepet.spring.tx.eventdispatcher.event.failure.BeginTransactionErrorEvent`: when a `[Original Transaction Manager Class].doBegin(Object transaction, TransactionDefinition definition)` method call fails.

* `it.diepet.spring.tx.eventdispatcher.event.failure.CommitTransactionErrorEvent`: when a `[Original Transaction Manager Class].doCommit(DefaultTransactionStatus status)` method call fails.

* `it.diepet.spring.tx.eventdispatcher.event.failure.RollbackTransactionErrorEvent`: when a `[Original Transaction Manager Class].doRollback(DefaultTransactionStatus status)` method call fails.

* `it.diepet.spring.tx.eventdispatcher.event.failure.SuspendTransactionErrorEvent`: when a `[Original Transaction Manager Class].doSuspend(Object transaction)` method call fails.

* `it.diepet.spring.tx.eventdispatcher.event.failure.ResumeTransactionErrorEvent`: when a `[Original Transaction Manager Class].doResume(Object transaction, Object suspendedResources)` method call fails.

* `it.diepet.spring.tx.eventdispatcher.event.failure.SetRollbackOnlyTransactionErrorEvent`: when a `[Original Transaction Manager Class].doSetRollbackOnly(DefaultTransactionStatus status)` method call fails.

Generally, when the one of these error event is thrown the transaction will not succeed.

# Transaction Life Cycle Examples

When a transactional method is called successfully these events will be triggered:

* `it.diepet.spring.tx.eventdispatcher.event.BeginTransactionEvent`
* `it.diepet.spring.tx.eventdispatcher.event.CommitTransactionEvent`

When transactional method throws a checked exception:

* `it.diepet.spring.tx.eventdispatcher.event.BeginTransactionEvent`
* `it.diepet.spring.tx.eventdispatcher.event.CommitTransactionEvent`

(notes that the transaction is committed event if the method has thrown an exception).

When a transactional method throws an unchecked exception:

* `it.diepet.spring.tx.eventdispatcher.event.BeginTransactionEvent`
* `it.diepet.spring.tx.eventdispatcher.event.RollbackTransactionEvent`

When a transactional method throws a checked exception instance of the exception class set into the *rollbackFor* attribute of the Spring `@Transactional` annotation:

* `it.diepet.spring.tx.eventdispatcher.event.BeginTransactionEvent`
* `it.diepet.spring.tx.eventdispatcher.event.RollbackTransactionEvent`

When a transactional method calls another transactional method having *REQUIRES_NEW* as propagation attribute:

* `it.diepet.spring.tx.eventdispatcher.event.BeginTransactionEvent` (for Tx \#1)
* `it.diepet.spring.tx.eventdispatcher.event.SuspendTransactionEvent` (for Tx \#1)
* `it.diepet.spring.tx.eventdispatcher.event.BeginTransactionEvent` (for Tx \#2)
* `it.diepet.spring.tx.eventdispatcher.event.CommitTransactionEvent` (for Tx \#2)
* `it.diepet.spring.tx.eventdispatcher.event.ResumeTransactionEvent` (for Tx \#1)
* `it.diepet.spring.tx.eventdispatcher.event.CommitTransactionEvent` (for Tx \#1)


When a transactional method calls another transactional method having *REQUIRED* as propagation attribute:

* `it.diepet.spring.tx.eventdispatcher.event.BeginTransactionEvent` 
* `it.diepet.spring.tx.eventdispatcher.event.CommitTransactionEvent`

When a transactional method calls another transactional method having *REQUIRED* as propagation attribute and the second transactional method fails:

* `it.diepet.spring.tx.eventdispatcher.event.BeginTransactionEvent` (triggered by the call of the first transactional method)
* `it.diepet.spring.tx.eventdispatcher.event.SetRollbackOnlyTransactionEvent` (triggered because the second transactional method fails for some reason)
* `it.diepet.spring.tx.eventdispatcher.event.failure.CommitTransactionErrorEvent` (triggered because the first method tries to commit a transaction marked as set to rollback only by the second transactional method called)

Note: all the previous scenario are represented in this JUnit test included in the source code:

`it.diepet.spring.tx.eventdispatcher.test.app.AppTest`
