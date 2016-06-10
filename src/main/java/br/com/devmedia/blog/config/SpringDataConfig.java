package br.com.devmedia.blog.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories("br.com.devmedia.blog.repository")
@EnableTransactionManagement
@PropertySource("classpath:/application.properties")
public class SpringDataConfig {
    
    @Autowired
    private Environment env;
    
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager manager = new JpaTransactionManager();
        manager.setEntityManagerFactory(emf);
        manager.setJpaDialect(new HibernateJpaDialect());
        
        return manager;
    }
    
    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setShowSql(this.env.getProperty("hibernate.show.sql", Boolean.class));
        adapter.setGenerateDdl(this.env.getProperty("hibernate.ddl", Boolean.class));
        
        return adapter;
    }
    
    @Bean
    public EntityManagerFactory entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(this.jpaVendorAdapter());
        factory.setPackagesToScan(this.env.getProperty("hibernate.package.scan"), this.env.getProperty("java.time.jpa.converter"));
        factory.setDataSource(this.dataSource());
        factory.afterPropertiesSet();
        
        return factory.getObject();
    }
    
    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUsername(this.env.getProperty("jdbc.user"));
        dataSource.setPassword(this.env.getProperty("jdbc.pass"));
        dataSource.setDriverClassName(this.env.getProperty("jdbc.driverClass"));
        dataSource.setUrl(this.env.getProperty("jdbc.url"));
        
        return dataSource;
    }
}