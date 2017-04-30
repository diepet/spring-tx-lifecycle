# spring-tx-eventdispatcher

A Spring Framework plugin in order to dispatch ad-hoc Spring events during a transaction life cycle.

# Requisites

* Java 1.6 or higher
* Spring 3.2 or higher

Currently, the module has been tested only using Spring 3.2.2.

# Configuration

In order to dispatch the transaction events, the Spring transaction manager used have to be replaced by a new implementation that overrides the original transaction manager.

The transaction managers supported are:

* org.springframework.jdbc.datasource.DataSourceTransactionManager
* org.springframework.orm.hibernate3.HibernateTransactionManager
* org.springframework.orm.hibernate4.HibernateTransactionManager
* org.springframework.orm.jdo.JdoTransactionManager
* org.springframework.orm.jpa.JpaTransactionManager
* org.springframework.transaction.jta.JtaTransactionManager



