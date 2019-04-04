package school.client;


import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = {"school.dao.impl", "school.service.impl"})
@PropertySource("classpath:postgresqldatabase.properties")
public class AppConfig {
    @Autowired
    Environment environment;

    @Bean
    BasicDataSource postgresqlDataSource() {
        BasicDataSource postgresqlDatasource = new BasicDataSource();
        postgresqlDatasource.setDriverClassName(environment.getProperty("db.driver"));
        postgresqlDatasource.setUrl(environment.getProperty("db.url"));
        postgresqlDatasource.setUsername(environment.getProperty("db.userName"));
        postgresqlDatasource.setPassword(environment.getProperty("db.password"));

        return postgresqlDatasource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(postgresqlDataSource());
        sessionFactory.setPackagesToScan(
                new String[] { "school.model" });
        sessionFactory.setHibernateProperties(hibernateProperties());

        return sessionFactory;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager txManager
                = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);

        return txManager;
    }

    Properties hibernateProperties() {
        return new Properties() {
            {
                setProperty("hibernate.show_sql",
                        environment.getProperty("hibernate.show_sql"));
                setProperty("hibernate.dialect",
                        environment.getProperty("hibernate.dialect"));
            }
        };
    }
}
