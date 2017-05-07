# spring-tx-eventdispatcher [![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

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


An event is triggered when one of these transaction manager method terminates **without** runtime errors:

* `[Original Transaction Manager Class].doBegin(Object transaction, TransactionDefinition definition)`

* `[Original Transaction Manager Class].doCommit(DefaultTransactionStatus status)`

* `[Original Transaction Manager Class].doRollback(DefaultTransactionStatus status)`

* `[Original Transaction Manager Class].doSuspend(Object transaction)`

* `[Original Transaction Manager Class].doResume(Object transaction, Object suspendedResources)`

* `[Original Transaction Manager Class].doSetRollbackOnly(DefaultTransactionStatus status)`

where `[Original Transaction Manager Class]` is one of the Spring transaction manager supported listed above.

All the events triggered for tracking a transaction life cycle are subclass of

`it.diepet.spring.tx.eventdispatcher.event.TransactionLifecycleEvent<T>`

The list of events triggered is:

* `it.diepet.spring.tx.eventdispatcher.event.BeginEvent`: when a new transaction begins.

* `it.diepet.spring.tx.eventdispatcher.event.CommitEvent`: when a transaction commits successfully.

* `it.diepet.spring.tx.eventdispatcher.event.RollbackEvent`: when a transaction is roll backed.

* `it.diepet.spring.tx.eventdispatcher.event.SuspendEvent`: when a transaction is suspended .

* `it.diepet.spring.tx.eventdispatcher.event.ResumeEvent`: when a previous suspended transaction resumes.

* `it.diepet.spring.tx.eventdispatcher.event.SetRollbackOnlyEvent`: when a transaction is set to rollback.

# Transaction Error Events

An error event is triggered when one of the these transaction manager method call **fails** for a **runtime exception**:

* `[Original Transaction Manager Class].doBegin(Object transaction, TransactionDefinition definition)`

* `[Original Transaction Manager Class].doCommit(DefaultTransactionStatus status)`

* `[Original Transaction Manager Class].doRollback(DefaultTransactionStatus status)`

* `[Original Transaction Manager Class].doSuspend(Object transaction)`

* `[Original Transaction Manager Class].doResume(Object transaction, Object suspendedResources)`

* `[Original Transaction Manager Class].doSetRollbackOnly(DefaultTransactionStatus status)`

where `[Original Transaction Manager Class]` is one of the Spring transaction manager supported listed above.

For example, when the transaction manager tries to commit a transaction set to rollback the `doCommit(...)` method will throw a runtime exception.

All the error events related to a transaction life cycle are subclass of

`it.diepet.spring.tx.eventdispatcher.event.failure.TransactionLifecycleErrorEvent<T>`

The list of possible error events triggered is:

* `it.diepet.spring.tx.eventdispatcher.event.failure.BeginErrorEvent`

* `it.diepet.spring.tx.eventdispatcher.event.failure.CommitErrorEvent`

* `it.diepet.spring.tx.eventdispatcher.event.failure.RollbackErrorEvent`

* `it.diepet.spring.tx.eventdispatcher.event.failure.SuspendErrorEvent`

* `it.diepet.spring.tx.eventdispatcher.event.failure.ResumeErrorEvent`

* `it.diepet.spring.tx.eventdispatcher.event.failure.SetRollbackOnlyErrorEvent`

Generally, when one of these error events is triggered than the transaction will not succeed.

# Transaction Life Cycle Examples

Following a list of events triggered by calling a transactional method `f()`.

```Java
// Example #1

@Transactional
void f() { 

}

```

Example \#1 events:

* `it.diepet.spring.tx.eventdispatcher.event.BeginEvent`
* `it.diepet.spring.tx.eventdispatcher.event.CommitEvent`

```Java
// Example #2

@Transactional
void f() { 
	// throws a checked exception
	throw new CheckedException();
}

```

Example #2 events:

* `it.diepet.spring.tx.eventdispatcher.event.BeginEvent`
* `it.diepet.spring.tx.eventdispatcher.event.CommitEvent`

(notes that the transaction is committed even if the method has thrown an exception)


```Java
// Example #3

@Transactional
void f() { 
	// throws a unchecked exception
	throw new UncheckedException();
}

```

Example #3 events:

* `it.diepet.spring.tx.eventdispatcher.event.BeginEvent`
* `it.diepet.spring.tx.eventdispatcher.event.RollbackEvent`

```Java
// Example #4

@Transactional(rollbackFor=CheckedException.class)
void f() { 
	// throws a checked exception
	throw new CheckedException();
}

```

Example \#4 events:

* `it.diepet.spring.tx.eventdispatcher.event.BeginEvent`
* `it.diepet.spring.tx.eventdispatcher.event.RollbackEvent`

```Java
// Example #5

// Tx #1
@Transactional
void f() { 
	
	g();
}

// Tx #2
@Transactional(propagation = Propagation.REQUIRES_NEW)
void g() {

}

```

Example \#5 events:


* `it.diepet.spring.tx.eventdispatcher.event.BeginEvent` (for Tx \#1)
* `it.diepet.spring.tx.eventdispatcher.event.SuspendEvent` (for Tx \#1)
* `it.diepet.spring.tx.eventdispatcher.event.BeginEvent` (for Tx \#2)
* `it.diepet.spring.tx.eventdispatcher.event.CommitEvent` (for Tx \#2)
* `it.diepet.spring.tx.eventdispatcher.event.ResumeEvent` (for Tx \#1)
* `it.diepet.spring.tx.eventdispatcher.event.CommitEvent` (for Tx \#1)


```Java
// Example #6

@Transactional
void f() { 
	g();
}

@Transactional(propagation = Propagation.REQUIRED)
void g() {

}

```

Example \#6 events:


* `it.diepet.spring.tx.eventdispatcher.event.BeginEvent` 
* `it.diepet.spring.tx.eventdispatcher.event.CommitEvent`

```Java
// Example #7

@Transactional
void f() { 
	try {
		g();	
	} catch (CheckedException e) {
		// do nothing
	}
}

@Transactional(propagation = Propagation.REQUIRED, rollbackFor=CheckedException.class)
void g() throws CheckedException {
	throw new CheckedException();
}
```

Example \#7 events:

* `it.diepet.spring.tx.eventdispatcher.event.BeginEvent`: triggered by the calling `f()`.
* `it.diepet.spring.tx.eventdispatcher.event.SetRollbackOnlyEvent`: triggered because `g()` throws a checked exception instance of the exception class set into the *rollbackFor* attribute.
* `it.diepet.spring.tx.eventdispatcher.event.failure.CommitErrorEvent`: triggered because `f()` tries to commit, but the transaction was set to be rollbacked by calling `g()`.

# License

This project is licensed under the terms of the MIT license.
