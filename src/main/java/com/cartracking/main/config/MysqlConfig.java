package com.cartracking.main.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(basePackages={"com.cartracking.main.repositories"})
@EnableTransactionManagement
public class MysqlConfig extends Config {

    @Bean
    public BasicDataSource mysqlDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUsername(env.getProperty("mysql.username"));
        dataSource.setPassword(env.getProperty("mysql.password"));
        dataSource.setUrl(env.getProperty("mysql.url"));
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");

        return dataSource;
    }

    @Bean
    LocalContainerEntityManagerFactoryBean entityManagerFactory(BasicDataSource dataSource,
                                                                HibernatePersistenceProvider persistenceProvider) {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setPackagesToScan("com.cartracking.main.entities");
        entityManagerFactory.setDataSource(dataSource);
        Properties jpaProperites = new Properties();
        jpaProperites.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        jpaProperites.setProperty("hibernate.show_sql", "false");
        jpaProperites.setProperty("hibernate.current_session_context_class", "thread");
        entityManagerFactory.setJpaProperties(jpaProperites);
        entityManagerFactory.setPersistenceProvider(persistenceProvider);

        return entityManagerFactory;
    }

    @Bean
    HibernatePersistenceProvider getPersistenceProvider() {
        return new HibernatePersistenceProvider();
    }

    @Bean(name = "transactionManager")
    PlatformTransactionManager getTransactionManager(EntityManagerFactory entityManagerFactory, BasicDataSource dataSource) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setDataSource(dataSource);
        transactionManager.setEntityManagerFactory(entityManagerFactory);

        return transactionManager;
    }
}
