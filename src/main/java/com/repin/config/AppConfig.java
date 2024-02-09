package com.repin.config;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.cfg.Environment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class AppConfig {
    @Bean
    public LocalSessionFactoryBean sessionFactoryBean() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("com.repin.domain");
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put(Environment.JAKARTA_JDBC_DRIVER, "com.p6spy.engine.spy.P6SpyDriver");
        properties.put(Environment.JAKARTA_JDBC_URL, "jdbc:p6spy:mysql://localhost:3306/todo"); // localhost
//        properties.put(Environment.JAKARTA_JDBC_URL, "jdbc:p6spy:mysql://db:3306/todo");      // docker
        properties.put(Environment.HBM2DDL_AUTO, "validate");
        return properties;
    }

    @Bean
    public DataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName("com.p6spy.engine.spy.P6SpyDriver");
        dataSource.setJdbcUrl("jdbc:p6spy:mysql://localhost:3306/todo");
        dataSource.setUsername("root");
        dataSource.setPassword("H#1gqUFQInYp*E9kDe3b"); // root
        dataSource.setMaximumPoolSize(10);
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory factory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(factory);
        return transactionManager;
    }
}