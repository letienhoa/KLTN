/**
 * 
 */
package carbook.config;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@ComponentScan("carbook")
@EnableTransactionManagement
@EnableJpaRepositories({ "carbook.repository" })
public class DatasourceConfig {

	@Autowired
	private ApplicationProperties config;

	@Autowired
	private Environment environment;



	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(config.getDriverClassname());
		dataSource.setUrl(config.getDatasourceUrl());
		dataSource.setUsername(config.getDatasourceUsername());
		dataSource.setPassword(config.getDatasourcePassword());
		return dataSource;
	}

	@Bean
	@Primary
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Qualifier("dataSource") DataSource ds)
			throws PropertyVetoException {
		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactory.setDataSource(ds);
		entityManagerFactory.setPackagesToScan(new String[] { "carbook.entity"});
		
		JpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		entityManagerFactory.setJpaVendorAdapter(jpaVendorAdapter);
		return entityManagerFactory;
	}

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan(new String[] { "carbook.entity" });
		sessionFactory.setHibernateProperties(hibernateProperties());
		return sessionFactory;
	}

	private Properties hibernateProperties() {

		Properties properties = new Properties();
		properties.put(org.hibernate.cfg.Environment.DIALECT, environment.getRequiredProperty("hibernate.dialect"));
		properties.put(org.hibernate.cfg.Environment.SHOW_SQL, environment.getRequiredProperty("hibernate.show_sql"));
		properties.put(org.hibernate.cfg.Environment.FORMAT_SQL,
				environment.getRequiredProperty("hibernate.format_sql"));
		properties.put(org.hibernate.cfg.Environment.ENABLE_LAZY_LOAD_NO_TRANS, true);
		properties.put(org.hibernate.cfg.Environment.HBM2DDL_AUTO, "none");
		properties.put(org.hibernate.cfg.Environment.ISOLATION, String.valueOf(Connection.TRANSACTION_READ_COMMITTED));

		properties.put(org.hibernate.cfg.Environment.C3P0_MIN_SIZE, 5);
		properties.put(org.hibernate.cfg.Environment.C3P0_MAX_SIZE, 250);
		properties.put(org.hibernate.cfg.Environment.C3P0_TIMEOUT, 300);
		properties.put(org.hibernate.cfg.Environment.C3P0_MAX_STATEMENTS, 50);
		properties.put(org.hibernate.cfg.Environment.C3P0_IDLE_TEST_PERIOD, 100);
		properties.put(org.hibernate.cfg.Environment.STATEMENT_BATCH_SIZE, 100);

		return properties;
	}

	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory s) {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(s);
		return txManager;
	}
	


}